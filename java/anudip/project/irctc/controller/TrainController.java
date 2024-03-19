package anudip.project.irctc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import anudip.project.irctc.entity.NewTrain;
import anudip.project.irctc.entity.Train;
import anudip.project.irctc.model.SearchInput;
import anudip.project.irctc.service.TrainService;

@Controller
public class TrainController {
	@Autowired
	TrainService trainService;

	@PostMapping("/searchBydate")
	public String searchTrain(@ModelAttribute("search") SearchInput search, Model trainModel, Model dayModel,Model trainName)
	{
		System.out.println(search.getSource()+ search.getDestination()+search.getDate());
		List<Train> trainList =trainService.findAllTrain(search.getSource(),search.getDestination());
		//List<Train> trainList
		
	    List<NewTrain> trainList1 = Arrays.asList(
                new NewTrain(1, "Train A", "Source A"),
                new NewTrain(2, "Train B", "Source B"),
                new NewTrain(3, "Train C", "Source C"),
                new NewTrain(4, "Train D", "Source D")  
        );
		List<String> scheduleList=new ArrayList<>();
		scheduleList.add("Daily");
		scheduleList.add("Mon");
		scheduleList.add("Tue");
		scheduleList.add("Mon,wed"); 
	
		
		//List<String> scheduleList =="Daily", "Mon Tue", "Sun"
		for(Train train:trainList )
			System.out.println(train.getTrainId());
		
	    trainModel.addAttribute("ListOfTrain",trainList1);
		dayModel.addAttribute("scheduleList", scheduleList);
		String train=new String();
	    trainName.addAttribute("train",train);
		return "SearchBydate";
	}
	
	
	@GetMapping("/Booking/{trainName}")
	public String bookTicket(@PathVariable("trainName") String trainName)
	{ 
	return "Booking" + trainName;
	}

}
