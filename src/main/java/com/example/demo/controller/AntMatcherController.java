package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class AntMatcherController {

	@GetMapping("/home")
	public String home() {
		return "this is home page";   
	}
	
	@GetMapping("/log-in")
	public String logIn() {
		return "this is log in page";   
	}
	
	@GetMapping("/register")
	public String Register() {
		return "this is resgister page";   
	}
	
}
