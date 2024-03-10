package anudip.project.irctc.repository;

import anudip.project.irctc.entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Integer> {
    UserVerification findByEmail(String email);
}
