package anudip.project.irctc.controller;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.entity.UserVerification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebPageController {

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

}
