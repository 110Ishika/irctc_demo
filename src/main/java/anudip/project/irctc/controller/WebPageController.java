package anudip.project.irctc.controller;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.entity.UserVerification;
import anudip.project.irctc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebPageController {
	@Autowired
	private UserService userService;
    @GetMapping("/registration")
    public String registrationPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @GetMapping(value = "/verification", params = "email")
    public String verificationPage(@RequestParam("email") String email, Model model){
        UserVerification verification = new UserVerification();
        model.addAttribute("verification", verification);
        model.addAttribute("userEmail", email);

        return "verification";
    }
    
 @GetMapping("/login")
    public String logIn(){
        return "loginIn";
    }
 



}
