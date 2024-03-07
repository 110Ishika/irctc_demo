package anudip.project.irctc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegController {
	
	@GetMapping()
	public String regPage()
	{
		return "registration.html";
	}
	
}
