package anudip.project.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import anudip.project.irctc.entity.User;

/**
 * Author: Ishika Dey
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
