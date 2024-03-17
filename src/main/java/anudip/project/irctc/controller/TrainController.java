package anudip.project.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import anudip.project.irctc.entity.Train;
import anudip.project.irctc.service.TrainService;

@Controller
@RequestMapping("train")
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@GetMapping(value = "/details", params = "id")
	public String getTrainDetails(@RequestParam("id") String trainNo, Model model) {
		
		Train train = trainService.getTrainByTrainNo(Integer.parseInt(trainNo));
		String trainShedule = "";
		if(train != null) {
			trainShedule = trainService.getTrainScheduleList(train);
		}
		model.addAttribute("train", train);
		model.addAttribute("schedule", trainShedule);
		return "traindetails";
	}
	
	@GetMapping("/details")
	public String trainDetails() {
		return "traindetails";
	}
}
