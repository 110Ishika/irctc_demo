package anudip.project.irctc.service;

import java.util.List;

import anudip.project.irctc.entity.Destination;
import anudip.project.irctc.entity.Source;
import anudip.project.irctc.entity.Train;


public interface TrainService {
	
	public List<Source> getTrainBySource(String source);
	
	public List<Destination> getTrainByDestination(String destination);

	public List<Train> findAllTrain(String source,String destination);

}
