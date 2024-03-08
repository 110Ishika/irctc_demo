package anudip.project.irctc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_validate_user")
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int validateId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int otp;

    public UserVerification(String email, int otp) {
        this.email = email;
        this.otp = otp;
    }


}
