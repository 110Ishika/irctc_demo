package anudip.project.irctc.service.ServiceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anudip.project.irctc.entity.Train;
import anudip.project.irctc.entity.TrainAvailableDays;
import anudip.project.irctc.repository.TrainAvailableRepository;
import anudip.project.irctc.repository.TrainRepository;
import anudip.project.irctc.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private TrainAvailableRepository trainAvailableDaysRepository;
	
	@Override
	public Train getTrainByTrainNo(int trainNo) {
		return trainRepository.findByTrainNo(trainNo);
	}

	@Override
	public String getTrainScheduleList(Train train) {
		Map<Integer, String> dayMapping = new HashMap<>();
		dayMapping.put(1, "Mon ");
		dayMapping.put(2, "Tue ");
		dayMapping.put(3, "Wed ");
		dayMapping.put(4, "Thus ");
		dayMapping.put(5, "Fri ");
		dayMapping.put(6, "Sat ");
		dayMapping.put(7, "Sun ");
		dayMapping.put(8, "Daily ");
		
		StringBuilder schedule = new StringBuilder();
		List<TrainAvailableDays> trainScheduleList = trainAvailableDaysRepository
				.findAllTrainAvailableDaysByTrain(train);
		
		for(TrainAvailableDays trainSchedule : trainScheduleList) {
			schedule.append(dayMapping.get(trainSchedule.getDay()));
		}
		return schedule.toString();
	}

}
