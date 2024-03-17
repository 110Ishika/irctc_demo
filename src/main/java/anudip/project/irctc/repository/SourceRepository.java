package anudip.project.irctc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import anudip.project.irctc.entity.Source;
import anudip.project.irctc.entity.Station;


public interface SourceRepository extends JpaRepository<Source, Integer> {
	List<Source> findAllSourceByStation(Station station);
	
}