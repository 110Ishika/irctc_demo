package anudip.project.irctc.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="tbl_stations1")
@Entity
public class Stations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "station_id")
	private int id;
	
	@Column(name="station_name" ,nullable=false ,unique=true ,length=100)
	private String name;
	
	@Column(name="station_code" ,nullable=false ,unique=true,length=5)
	private String code;
	
	
	
	
	

}
