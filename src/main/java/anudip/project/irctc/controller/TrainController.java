package anudip.project.irctc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import anudip.project.irctc.entity.Booking;
import anudip.project.irctc.entity.Train;
import anudip.project.irctc.entity.User;
import anudip.project.irctc.model.Route;
import anudip.project.irctc.model.SearchInput;
import anudip.project.irctc.service.TrainService;
import anudip.project.irctc.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("train")
public class TrainController {

	@Autowired
	private TrainService trainService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/details", params = "id")
	public String getTrainDetails(@RequestParam("id") String trainNo, Model model, HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		Train train = trainService.getTrainByTrainNo(Integer.parseInt(trainNo));
		List<Route> route = new ArrayList<>();
		String trainShedule = "";
		boolean incorrect = true;

		if (train != null) {
			trainShedule = trainService.getTrainScheduleList(train);
			route = trainService.getTrainRoute(train);
			incorrect = false;
		}
		model.addAttribute("train", train);
		model.addAttribute("schedule", trainShedule);
		model.addAttribute("route", route);
		model.addAttribute("incorrect", incorrect);
		return "traindetails";
	}

	@PostMapping("/searchBydate")
	public String searchTrain(@ModelAttribute("search") SearchInput search, Model trainModel, Model dayModel,
			HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		List<Train> trainList = trainService.getAllTrains(search.getSource(), search.getDestination(),
				search.getDate());
		List<String> scheduleList = trainService.getTrainScheduleList(trainList);

		trainModel.addAttribute("ListOfTrain", trainList);
		dayModel.addAttribute("scheduleList", scheduleList);

		return "SearchBydate";
	}

	@GetMapping("/details")
	public String trainDetails(HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		return "traindetails";
	}

	@GetMapping("/searchBydate")
	public String searchByDate(HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		return "SearchBydate";
	}

	@GetMapping("/tickets")
	public String ticketManagement(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		User user = userService.getUserByEmail((String) httpSession.getAttribute("email"));
		List<Booking> bookingListByUser = trainService.getAllBookingByUser(user);
		Booking booking = new Booking();
		model.addAttribute("bookingList", bookingListByUser);
		model.addAttribute("selectedTicket", booking);
		List<Integer> pric = trainService.getPriceBySourceDestinationAndTrain("Muri", "Hatia", trainService.getTrainByTrainNo(18615));
		for(int n :pric)
			System.out.println(n);
		return "tickets";
	}

	@GetMapping(value = "/tickets", params = { "pnr", "date" })
	public String cancleTicket(@RequestParam("pnr") String pnr, @RequestParam("date") LocalDate date,
			HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		System.out.println(pnr);
		System.out.println(date.getDayOfWeek());
		return "redirect:/train/tickets";
	}

}
