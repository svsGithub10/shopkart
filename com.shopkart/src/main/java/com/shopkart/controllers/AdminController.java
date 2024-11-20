package com.shopkart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopkart.entities.Admin;
import com.shopkart.entities.Products;
import com.shopkart.services.AdminService;
import com.shopkart.services.ProductsService;

@Controller
public class AdminController {

	@Autowired
	AdminService service;
	
	
	@PostMapping("/addAdmin")
	public String addAdmin(@ModelAttribute Admin admin,@RequestParam String secretCode) {
		String adminId = admin.getAdminId();
		
		boolean status = service.adminExists(adminId, secretCode);
		if(status == true) {
			service.addAdmin(admin);
			return "/validations/adminSuccess";
		}
		else {
			return "/validations/adminExists";
		}
	}
	
	@PostMapping("/adminLogin")
	public String login(@RequestParam String adminId,
			@RequestParam String password,
			
			Model model)	{
		boolean status = service.validateAdmin(adminId, password);
		if(status == true) {
			return "redirect:/addProduct";
		}
		else {
			
			return "/validations/notAdmin";
		}
	}
}
