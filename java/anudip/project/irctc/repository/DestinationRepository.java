package anudip.project.irctc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import anudip.project.irctc.entity.Destination;
import anudip.project.irctc.entity.Source;
import anudip.project.irctc.entity.Station;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
  public List<Destination> findAllDestinationByStation(Station station);
}
