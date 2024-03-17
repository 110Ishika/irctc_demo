package anudip.project.irctc.service.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anudip.project.irctc.entity.Destination;
import anudip.project.irctc.entity.Source;
import anudip.project.irctc.entity.Station;
import anudip.project.irctc.entity.Train;
import anudip.project.irctc.entity.TrainAvailableDays;
import anudip.project.irctc.repository.DestinationRepository;
import anudip.project.irctc.repository.SourceRepository;
import anudip.project.irctc.repository.StationRepository;
import anudip.project.irctc.repository.TrainAvailableRepository;
import anudip.project.irctc.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	SourceRepository sourceRepository;
	@Autowired
	DestinationRepository destinationRepository;
	@Autowired
	TrainAvailableRepository trainAvailableDays;
	@Autowired
	StationRepository stationRepository;

	/*
	 * void getTrainDay(String source,String destination) {
	 * 
	 * List<Integer> trainId= getTrainId(source,destination); for(Integer i:trainId)
	 * trainAvailableDays.getAvailbleDays(i); }
	 */

	public List<Train> findAllTrain(String source, String destination) {
		ArrayList<Train> trains = new ArrayList<Train>();

		List<Source> sourceList = getTrainBySource(source);
		List<Destination> destinationList = getTrainByDestination(destination);

		for (Source s : sourceList) {
			for (Destination d : destinationList) { 
				if (d.getTrain().getTrainId() == s.getTrain().getTrainId()) {
					System.out.println(s.getTrain().getTrainId());

					if (s.getRequiredMinutes() < d.getRequiredMinutes()) {
						trains.add(s.getTrain());
						System.out.println(s.getTrain().getTrainId());
					}
				}
			} 
		}
		return trains;
	}

	@Override
	public List<Source> getTrainBySource(String source) {
		Station station = stationRepository.findByStationName(source);
		List<Source> sourceList = sourceRepository.findAllSourceByStation(station);
		for (Source s : sourceList) {
			System.out.println(s.getTrain().getTrainId());

		}
		return sourceList;
	}

	@Override
	public List<Destination> getTrainByDestination(String destination) {
		Station station = stationRepository.findByStationName(destination);
		List<Destination> destinationList = destinationRepository.findAllDestinationByStation(station);
		for (Destination d : destinationList) {
			System.out.println(d.getTrain().getTrainId());

		}
		return destinationList;
	} 

}
