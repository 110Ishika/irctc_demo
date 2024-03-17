package anudip.project.irctc.service;

import anudip.project.irctc.entity.Train;

public interface TrainService {
	
	Train getTrainByTrainNo(int trainNo);
	
	String getTrainScheduleList(Train train);
}
