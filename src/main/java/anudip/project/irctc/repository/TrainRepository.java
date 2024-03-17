package anudip.project.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anudip.project.irctc.entity.Train;


public interface TrainRepository extends JpaRepository<Train, Integer>{
	Train findByTrainNo(int trainNo);
}
