package com.shopkart.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.shopkart.entities.User;
import com.shopkart.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("/signup")
	public String addUser(@ModelAttribute User user) {
		//user exists?
		String name = user.getName();
		String email = user.getEmail();
		boolean status = service.userExists(name, email);
		if(status == false) {
			service.addUser(user);
			return "/validations/creationSuccess";
		}
		else {
			return "/validations/userExists";
		}
		
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email,
			@RequestParam String password,
			Model model, HttpSession session)	{
		boolean status = service.validateUser(email, password);
		if(status == true) {
			return "redirect:/home";
		}
		else {
			
			return "/validations/userNotFound";
		}
	}
}
