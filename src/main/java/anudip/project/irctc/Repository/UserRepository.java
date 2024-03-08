package anudip.project.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import anudip.project.irctc.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    User findByEmail(String email);
}
