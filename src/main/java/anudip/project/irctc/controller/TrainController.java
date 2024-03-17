package anudip.project.irctc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import anudip.project.irctc.entity.Train;
import anudip.project.irctc.model.SearchInput;
import anudip.project.irctc.service.TrainService;

@Controller
public class TrainController {
	@Autowired
	TrainService trainService;

	@PostMapping("/searchTrain")
	public void searchTrain(@ModelAttribute("search") SearchInput search)
	{
		System.out.println(search.getSource()+ search.getDestination()+search.getDate());
		List<Train> trainList =trainService.findAllTrain(search.getSource(),search.getDestination());
		for(Train train:trainList )
			System.out.println(train.getTrainId());

		
	}
	
	
}
