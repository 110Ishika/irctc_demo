package anudip.project.irctc.controller;

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
import anudip.project.irctc.entity.Train;
import anudip.project.irctc.model.Route;
import anudip.project.irctc.model.SearchInput;
import anudip.project.irctc.service.TrainService;

@Controller
@RequestMapping("train")
public class TrainController {

	@Autowired
	private TrainService trainService;

	@GetMapping(value = "/details", params = "id")
	public String getTrainDetails(@RequestParam("id") String trainNo, Model model) {

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
	public String searchTrain(@ModelAttribute("search") SearchInput search,
			Model model) {

		List<Train> trainList = trainService.getAllTrains(search.getSource(), search.getDestination(),
				search.getDate());
		List<String> scheduleList = trainService.getTrainScheduleList(trainList);

		model.addAttribute("ListOfTrain", trainList);
		model.addAttribute("scheduleList", scheduleList);
		
		return "SearchBydate";
	}

	
	
	@GetMapping("/Booking/{trainNo}")
	public String bookTicket(@PathVariable("trainNo")  Integer trainNo)
	{ System.out.println("hii");
	return "redirect:/train/Booking?trainNo=" + trainNo;
	}
	
	@GetMapping(value = "Booking", params = "trainNo")
	public String bookingPage(@RequestParam("trainNo")  Integer trainNo, Model model,Model bookingInfo)
	{ 
	bookingInfo.addAttribute("userTicket",new Booking());
	model.addAttribute("trainNo",trainNo);
	return "Booking";
	}
	

	@GetMapping("/details")
	public String trainDetails() {
		return "traindetails";
	}

	@GetMapping("/searchBydate")
	public String searchByDate() {

		return "SearchBydate";
	}
	
	@PostMapping("/userBookingInfo")
	public String bookingInfo(@ModelAttribute("userTicket") Booking booking)
	{
	  System.out.println("booked confirmed");
		return "redirect:/";
	}

}
