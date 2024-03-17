package anudip.project.irctc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import anudip.project.irctc.entity.Station;

public interface StationRepository  extends JpaRepository<Station, Integer> {
    Station findByStationName(String stationName);
}





