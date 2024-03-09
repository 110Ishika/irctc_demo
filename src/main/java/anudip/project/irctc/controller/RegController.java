package anudip.project.irctc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegController {
	
	@GetMapping("/registration")
	public String regPage()
	{
		return "registration";
	}
	
}
