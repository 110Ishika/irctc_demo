package anudip.project.irctc.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tbl_trains1")
@Entity
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "train_id")
	private int trainId;
	
	@Column(name="train_name" , nullable=false , unique=true,length=50)
	private String name;

	@Column(name = "source" ,nullable=false, length=50)
	private String source;
	
	 @Column(name = "destination" ,nullable=false, length=50)
	private String destination;

	 @Column(name = " arrival_time" ,nullable=false, length=5)
	private String arrivalTime;
	 
	 @Column(name = "depature_time" ,nullable=false, length=5)
		private String depatureTime;
	 
	 @Column(name ="seat1A_count" ,nullable=false, length=5)
	 private int seat1ACount;

	 @Column(name ="seat1A_price" ,nullable=false)
	 private double seat1APrice;
	 
	 @Column(name ="seat2A_count" ,nullable=false, length=5)
	 private int seat2ACount;

	 @Column(name ="seat2A_price" ,nullable=false)
	 private double seat2APrice;
	 
	 @Column(name ="seatSl_count" ,nullable=false, length=5)
	 private int seatS1Count;

	 @Column(name ="seatSl_price" ,nullable=false)
	 private double seatS1Price;
	 
	 @Column(name ="seatGen_count" ,nullable=false, length=5)
	 private int seatGenCount;

	 @Column(name ="seatGen_price" ,nullable=false)
	 private double seatGenPrice;
	 
	 
	 @ManyToMany(targetEntity = Destination.class)
	 @JoinColumn(name="destination_id")
	  private List<Destination> destinationId;
	 
//	 @ManyToMany(targetEntity = Source.class)
//	 private  List<Source> sourcesId;
//	 
////	 @ManyToMany(targetEntity = Destination.class)
////		private List<Destination> destinationId;
////		
////
////	 @ManyToMany(targetEntity = TrainDays.class)
////	 @Column(name="train_day")
////		private List<TrainDays> trainDay;
		
	 
}
