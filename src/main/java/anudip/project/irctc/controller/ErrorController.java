package anudip.project.irctc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ErrorController {

    @GetMapping("/error")
    public String alreadyRegisteredError(){
        return "User already registered error";
    }
}
