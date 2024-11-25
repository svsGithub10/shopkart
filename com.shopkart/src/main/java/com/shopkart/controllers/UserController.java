package com.shopkart.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopkart.entities.Products;
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
			session.setAttribute("email", email);
			model.addAttribute("session", session);
			return "redirect:/home";
		}
		else {
			
			return "/validations/userNotFound";
		}
	}

	@PostMapping("/editProfile")
	public String updtaeProfile(@RequestParam String name, 
			@RequestParam String email, @RequestParam(required = false) String phone,
			@RequestParam(required = false) String dob, @RequestParam(required = false) String gender,
			@RequestParam(required = false) String state, @RequestParam(required = false) String city,
			@RequestParam(required = false) String pinCode, @RequestParam(required = false) String address1,
			@RequestParam(required = false) String address2, @RequestParam(required = false) MultipartFile photo,
			Model model, HttpSession session,RedirectAttributes redirectAttributes)	{
		try {
		User user=service.getUserByEmail(email);

		try {	
			if (photo != null && !photo.isEmpty())
			user.setPhoto(photo.getBytes());			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (name != null && !name.isEmpty()) user.setName(name);
		if (phone != null && !phone.isEmpty()) user.setPhone(phone);
		if (dob != null && !dob.isEmpty()) user.setDob(dob);
		if (gender != null && !gender.isEmpty()) user.setGender(gender);
		if (state != null && !state.isEmpty()) user.setState(state);
		if (city != null && !city.isEmpty()) user.setCity(city);
		if (pinCode != null && !pinCode.isEmpty()) user.setPinCode(pinCode);
		if (address1 != null && !address1.isEmpty()) user.setAddress1(address1);
		if (address2 != null && !address2.isEmpty()) user.setAddress2(address2);
		service.updateUser(user);
		model.addAttribute("user",user);
		redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update user: " + e.getMessage());
			return "redirect:/editProfile";
		}
		return "redirect:/userProfile";
	}
	
	@PostMapping("/editUser")
	public String updtaeUser(@RequestParam String name, 
			@RequestParam String email, @RequestParam(required = false) String phone,
			@RequestParam(required = false) String dob, @RequestParam(required = false) String gender,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String state, @RequestParam(required = false) String city,
			@RequestParam(required = false) String pinCode, @RequestParam(required = false) String address1,
			@RequestParam(required = false) String address2, @RequestParam(required = false) MultipartFile photo,
			Model model,HttpSession session, RedirectAttributes redirectAttributes)	{
		try {
			User user=service.getUserByEmail(email);
			
			try {	
				if (photo != null && !photo.isEmpty())
					user.setPhoto(photo.getBytes());			
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (name != null && !name.isEmpty()) user.setName(name);
			if (email != null && !email.isEmpty()) user.setEmail(email);
			if (password != null && !password.isEmpty()) user.setPassword(password);
			if (phone != null && !phone.isEmpty()) user.setPhone(phone);
			if (dob != null && !dob.isEmpty()) user.setDob(dob);
			if (gender != null && !gender.isEmpty()) user.setGender(gender);
			if (state != null && !state.isEmpty()) user.setState(state);
			if (city != null && !city.isEmpty()) user.setCity(city);
			if (pinCode != null && !pinCode.isEmpty()) user.setPinCode(pinCode);
			if (address1 != null && !address1.isEmpty()) user.setAddress1(address1);
			if (address2 != null && !address2.isEmpty()) user.setAddress2(address2);
			service.updateUser(user);
			model.addAttribute("user",user);
			redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update user: " + e.getMessage());
			return "redirect:/editUser";
		}
		return "redirect:/allUsers";
	}
	
	
}
