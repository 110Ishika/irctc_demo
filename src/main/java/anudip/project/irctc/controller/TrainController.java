package anudip.project.irctc.controller;

import java.time.DayOfWeek;
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

import anudip.project.irctc.entity.Source;
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
		
		if(train != null) {
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
	
	@GetMapping("/details")
	public String trainDetails() {
		return "traindetails";
	}

	@PostMapping("/searchTrain")
	public String searchTrain(@ModelAttribute("search") SearchInput search) {
		
		System.out.println(search.getSource()+ search.getDestination()+search.getDate());
		System.out.println(search.getDate().getDayOfWeek().getValue());
		
		List<Train> trainList = trainService.getAllTrains(search.getSource(),search.getDestination(),search.getDate());
		
		for(Train train:trainList )
			System.out.println(train);
		return "home";
	}
	
}
