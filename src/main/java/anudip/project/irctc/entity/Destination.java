package anudip.project.irctc.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="tbl_destination1")
@Entity
public class Destination {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="destination_id")
	 private int id;
	
	@Column(name="require_min" ,nullable=false)
	private int requiredTime;

	
	@Column(name="price" ,nullable=false)
	private double price;
	
	@ManyToMany(targetEntity = Train.class)
	@JoinColumn(name="train_id")
	private List<Train> trainId;
	
	@ManyToOne(targetEntity = Stations.class)
	@JoinColumn(name="station_id")
	private List<Stations> stationId;
	
	
}
