package anudip.project.irctc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import anudip.project.irctc.entity.Booking;
import anudip.project.irctc.entity.Station;

import anudip.project.irctc.entity.Train;
import anudip.project.irctc.entity.User;
import anudip.project.irctc.model.Route;
import anudip.project.irctc.model.SearchInput;
import anudip.project.irctc.repository.TrainRepository;
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


	@PostMapping(value = "/searchBydate/{email}")
	public String searchTrain(@PathVariable String email, @ModelAttribute("search") SearchInput search, Model model,
			HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		List<Train> trainList = trainService.getAllTrains(search.getSource(), search.getDestination(),
				search.getDate());
		List<String> scheduleList = trainService.getTrainScheduleList(trainList);

		model.addAttribute("ListOfTrain", trainList);
		model.addAttribute("scheduleList", scheduleList);
		model.addAttribute("searchDate", search.getDate());
		model.addAttribute("email", email);
		return "SearchBydate";

		// return "redirect:/train/searchBydate?date="+search.getDate();
	}
	////
	@GetMapping(value = "/Booking", params = { "src", "dst", "train", "date" })
	public String bookTicket(@RequestParam("src") String source, @RequestParam("dst") String destination,
			@RequestParam("train") String trainNo, @RequestParam("date") LocalDate date, Model model,HttpSession httpSession) {
		System.out.println(source);
		
		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";


		User user = userService.getUserByEmail((String)httpSession.getAttribute("email"));
		
		Booking bookingInfo = new Booking();
		bookingInfo.setSource(source);
		bookingInfo.setDestination(destination);
		bookingInfo.setTravelDate(date);
		bookingInfo.setUser(user);
        Train train= trainService.getTrainByTrainNo(Integer.parseInt(trainNo));
        bookingInfo.setTrain(train);
       
		model.addAttribute("userTicket", bookingInfo);
		model.addAttribute("train",train);
		
        System.out.println("train is here now ");
		System.out.println(date instanceof LocalDate);
		return "booking";

	}
	////


	@GetMapping("/details")
	public String trainDetails(HttpSession httpSession) {

		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";
  return "traindetails";
}

	@GetMapping(value = "/Booking", params = { "src", "dst", "train", "date" })
	public String bookTicket(@RequestParam("src") String source, @RequestParam("dst") String destination,
			@RequestParam("train") String trainNo, @RequestParam("date") LocalDate date, Model model,HttpSession httpSession) {
		System.out.println(source);
		
		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";


		User user = userService.getUserByEmail((String)httpSession.getAttribute("email"));
		
		Booking bookingInfo = new Booking();
		bookingInfo.setSource(source);
		bookingInfo.setDestination(destination);
		bookingInfo.setTravelDate(date);
		bookingInfo.setUser(user);
        Train train= trainService.getTrainByTrainNo(Integer.parseInt(trainNo));
        bookingInfo.setTrain(train);
       
		model.addAttribute("userTicket", bookingInfo);
		model.addAttribute("train",train);
		
        System.out.println("train is here now ");
		System.out.println(date instanceof LocalDate);
		return "booking";

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
    
	@PostMapping(value = "/userBookingInfo/")
	public String bookingInfo(@ModelAttribute("userTicket") Booking booking, HttpSession httpSession) {
		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		System.out.println("welcome");
		boolean status = trainService.bookTicket(booking);
		System.out.println("booking confirmed");
		return "getTicket";
	}
	
	@PostMapping(value = "/userBookingInfo/")
	public String bookingInfo(@ModelAttribute("userTicket") Booking booking, HttpSession httpSession) {
		if (httpSession.getAttribute("email") == null)
			return "redirect:/user/login";

		System.out.println("welcome");
		boolean status = trainService.bookTicket(booking);
		System.out.println("booking confirmed");
		return "getTicket";
	}

}
