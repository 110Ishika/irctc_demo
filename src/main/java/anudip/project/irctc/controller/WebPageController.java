package anudip.project.irctc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }
}
