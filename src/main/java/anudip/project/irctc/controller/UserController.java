package anudip.project.irctc.controller;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public void addUser(@RequestBody User user){

    }

    @GetMapping("/test")
    public void test(){
        System.out.println("testt");
    }
}
