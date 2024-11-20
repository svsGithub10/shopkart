package com.shopkart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopkart.entities.Products;
import com.shopkart.repositories.ProductsRepository;
import com.shopkart.services.ProductsService;

@Controller
public class NavigationController {
	
	@Autowired
	ProductsService service;
	
	@Autowired
	ProductsRepository repo;

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
	
	@GetMapping("/editProduct")
	public String editProduct(@RequestParam long pid,
							  Model model
							  ) {
		List<Products> allProducts=service.fetchAllProducts();
		model.addAttribute("allProducts", allProducts);
		
		try {
			Products product=repo.findById(pid).get();
			model.addAttribute("product", product);
			
			Products prod=new Products();
			prod.setBrand(prod.getBrand());
			prod.setPname(prod.getPname());
			prod.setPdescription(prod.getPdescription());
			prod.setPimage(prod.getPimage());
			prod.setImage1(prod.getImage1());
			prod.setImage2(prod.getImage2());
			model.addAttribute("prod", prod);
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/addProduct";
		}
		
		return "editProduct";
	}
	
}