package anudip.project.irctc.service.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anudip.project.irctc.Repository.UserRepository;
import anudip.project.irctc.entity.User;
import anudip.project.irctc.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User createUser(User user) {
             return userRepo.save(user) ;
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User updateUser(User user) {
		
		return null;
	}

	@Override
	public void deleteUser(int userId) {
			userRepo.deleteById(userId);	
	}

}
