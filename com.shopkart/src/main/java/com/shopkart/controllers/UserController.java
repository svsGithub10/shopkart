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
import com.shopkart.services.VerificationService;
import com.shopkart.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService service;

	@Autowired
	VerificationService verify;

	private User tempUser;
	private String tempOtp;
	
	@PostMapping("/signup")
	public String addUser(@ModelAttribute User user) {
		//user exists?
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		String otp = verify.getAlphaNumericString(6);
		tempUser=user;
		tempOtp=otp;
		boolean status = service.userExists(name, email);
		if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && status == false) {
            verify.sendEmail(email, 
                    "ShopKart: Verify Email", 
                    "Dear user, The One Time Password (OTP) to verify your email is \"" + otp + "\".\n\n"
                    + "Do not share it with anyone. Please contact admin if it's not you.\n\n"
                    + "This mail is auto-generated, please don't reply.\n\n"
                    + "Warm regards,\nShopKart");

			return "/verifyOtp";
		}
		else {
			return "/validations/userExists";
		}
		
	}
	
	@PostMapping("/verifyOtp")
	public String verifyOtp(@RequestParam String OTP) {
		if (tempOtp != null && tempOtp.equals(OTP)) {
			tempUser.setPassword(verify.encrypt(tempUser.getPassword(), tempUser.getEmail()));
			service.addUser(tempUser);
			return "/validations/creationSuccess";
		}
		else {
			return "/validations/wrongOtp";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email,
			@RequestParam String password,
			Model model, HttpSession session)	{
		boolean status = service.validateUser(email, password);
		
		if(!password.isEmpty() && !email.isEmpty() && status == true) {
			session.setAttribute("email", email);
			model.addAttribute("session", session);
			return "redirect:/home";
		}
		else {
			//user exists?
			if(service.getUserByEmail(email) == null) {
				return "/validations/userNotFound";
			}
			else {
				return "/validations/wrongPassword";
			}
			
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
	public String updtaeUser(@RequestParam String name, @RequestParam(required = false) String phone,
			@RequestParam(required = false) String dob, @RequestParam(required = false) String gender,
			@RequestParam(required = false) String state, @RequestParam(required = false) String city,
			@RequestParam(required = false) String pinCode, @RequestParam(required = false) String address1,
			@RequestParam(required = false) String address2, @RequestParam(required = false) MultipartFile photo,
			Model model,HttpSession session, RedirectAttributes redirectAttributes)	{
		try {
			User user=service.getUser(name);
			
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
			return "redirect:/editUser";
		}
		return "redirect:/allUsers";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam String password, @RequestParam String newPassword,
			Model model,HttpSession session,RedirectAttributes redirectAttributes) {
			String email=(String) session.getAttribute("email");
			try {
			User user=service.getUserByEmail(email);
			String oldPassword=verify.decrypt(user.getPassword(), email);
			if(!password.isEmpty() && oldPassword.equals(password)) {
				user.setPassword(verify.encrypt(newPassword, email));
				service.updateUser(user);
				model.addAttribute("user",user);
				redirectAttributes.addFlashAttribute("successMessage", "Password Changed successfully!");
				return "redirect:/editProfile";
			}
			else {
				redirectAttributes.addFlashAttribute("successMessage", "Current password is incorrect!");
				return "redirect:/changePassword";
			}
			}
			catch(Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to change password: " + e.getMessage());
				return "redirect:/changePassword";
			}
			
	}
	
	
	
}
