package anudip.project.irctc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Aniket Mishra
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Route {
	
	private int sNo;
	private String stationCode;
	private String stationName;
	private String arrivalTime;
	private String departureTime;
}
