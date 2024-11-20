package com.shopkart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopkart.entities.Products;
import com.shopkart.services.ProductsService;

@Controller
public class NavigationController {
	
	@Autowired
	ProductsService service;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Products> allProducts=service.fetchAllProducts();
		model.addAttribute("allProducts", allProducts);
		return "home";
	}
	
	@GetMapping("/success")
	public String success() {
		return "redirect:/";
	}
	
	@GetMapping("/serverside")
	public String serverside() {
		return "redirect:/admin";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		return "/admin/admin";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		List<Products> allProducts=service.fetchAllProducts();
		model.addAttribute("allProducts", allProducts);
		return "/admin/addProduct";
	}
	
}