package anudip.project.irctc.controller;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.entity.UserVerification;
import anudip.project.irctc.model.Login;
import anudip.project.irctc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        User existedUser = userService.getUserByEmail(user.getEmail());

        if(existedUser != null)
            user.setUserId(existedUser.getUserId());

        if(existedUser == null || existedUser.getStatus() == 0) {
            userService.saveUserAndSentOtp(user);
            if (user.getRole().equalsIgnoreCase("user")) {
                return "redirect:/verification?email=" + user.getEmail();
            }
            return "redirect:/adminRegistration";
        }
        return "redirect:/verifiedUser";
    }

    @GetMapping("/verify/{email}")
    public String verifyUser(@PathVariable("email") String email,
                             @ModelAttribute("verification") UserVerification verification) {
        boolean isVerified = false;

        System.out.println(verification.getEmail());
        verification.setEmail(email);

        if (userService.verifyUser(verification)) {
            isVerified = true;
            return "index";
        }
        return "verification?email=" + email;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> list = userService.getAllUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/details/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("user is deleted Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login){
        if(userService.userAuthentication(login)){
            return "home";
        }
        return "login";
    }
}