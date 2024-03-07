package anudip.project.irctc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import anudip.project.irctc.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
