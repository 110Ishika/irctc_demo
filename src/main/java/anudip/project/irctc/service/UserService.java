package anudip.project.irctc.service;

import java.util.List;

import anudip.project.irctc.entity.User;

public interface UserService {
	
	User createUser(User user);
	List<User> getAllUser();
	User updateUser(User user);
	void deleteUser(int userId);

}
