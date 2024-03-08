package anudip.project.irctc.controller;

import java.util.List;

import anudip.project.irctc.entity.UserVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        int status = userService.checkUserStatus(user);
        if (status == 1)
            return "User already registered, Try to login";
        if (status == 2)
            return "User already registered, Verification pending";

        userService.saveUser(user);

        return "redirect:/verification?email=" + user.getEmail();
    }

    @GetMapping("/verify/{email}")
    @ResponseBody
    public String verifyUser(@PathVariable("email") String email,
                             @ModelAttribute("verification") UserVerification verification) {
        System.out.println(email + " " + verification.getOtp());

        verification.setEmail(email);

        if (userService.verifyUser(verification)) {
            return "User Registered Successfully";
        }
        return "Wrong Otp try again";
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

}