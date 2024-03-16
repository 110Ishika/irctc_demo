//package anudip.project.irctc.entity;
//
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(name="tbl_train_available_days1")
//@Entity
//public class TrainDays {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	 @Column(name = "day_id")
//	private int Id;
//	
//	@Column(name="day" ,nullable=true, length=10)
//	private String day;
//	
//	@ManyToMany(targetEntity = Train.class)
//	 @Column(name="train_id")
//		private List<Train> trainId ;
//
//}
