package anudip.project.irctc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

@RequestMapping("irctc/registration")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User newUser=userService.createUser(user);
		 return new ResponseEntity<>(newUser,HttpStatus.CREATED);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAlluser()
	{
		List<User> list=userService.getAllUser();
		return new ResponseEntity<>(list,HttpStatus.OK); 
		
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("user is deleted Successfully", HttpStatus.OK);
	}

	
}
