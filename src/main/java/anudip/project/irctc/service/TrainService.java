package anudip.project.irctc.service;

import java.time.LocalDate;
import java.util.List;

import anudip.project.irctc.entity.Booking;
import anudip.project.irctc.entity.Destination;
import anudip.project.irctc.entity.Source;
import anudip.project.irctc.entity.Train;
import anudip.project.irctc.entity.User;
import anudip.project.irctc.model.Route;

public interface TrainService {
	
	List<Source> getTrainBySource(String source);
	
	List<Destination> getTrainByDestination(String destination);

	List<Train> getAllTrains(String source, String destination, LocalDate date);
	
	Train getTrainByTrainNo(int trainNo);
	
	String getTrainScheduleList(Train train);
	
	List<String> getTrainScheduleList(List<Train> train);
	
	List<Route> getTrainRoute(Train train);
	
	List<Booking> getAllBookingByUser(User user);
	
	List<Integer> getPriceBySourceDestinationAndTrain(String source, String destination, Train train);

}
