package anudip.project.irctc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import anudip.project.irctc.entity.Train;
import anudip.project.irctc.entity.TrainAvailableDays;

public interface TrainAvailableRepository extends JpaRepository<TrainAvailableDays, Integer> {
   public List<TrainAvailableDays> findAllByTrain(Train train);
}



