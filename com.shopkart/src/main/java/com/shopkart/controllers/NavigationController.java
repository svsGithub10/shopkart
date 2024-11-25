package com.shopkart.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopkart.entities.Products;
import com.shopkart.entities.User;
import com.shopkart.services.ProductsService;
import com.shopkart.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	
	@Autowired
	ProductsService service;
	
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {

		String email=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		List<Products> allProducts=service.fetchAllProducts();
		
	    for (Products product : allProducts) {
	        product.setPdescription(service.truncateToThreeLines(product.getPdescription()));
	    }
		
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
	public String admin() {
		return "/admin/admin";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model, HttpSession http) {
		List<Products> allProducts=service.fetchAllProducts();
		
	    for (Products product : allProducts) {
	        product.setPdescription(service.toNewLines(product.getPdescription()));
	    }
		
		model.addAttribute("allProducts", allProducts);
		return "/admin/addProduct";
	}
	
	@GetMapping("/orderProduct")
	public String orderProduct(@RequestParam long pid,
							  Model model, HttpSession session
							  ) {
		try {
			
			String email=(String) session.getAttribute("email");
			User user=userService.getUserByEmail(email);
			model.addAttribute("user", user);
			
			Products product=service.findByPid(pid);
		    product.setPdescription(service.toNewLines(product.getPdescription()));
			model.addAttribute("product", product);
			
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/home";
		}
		
		return "/orderProduct";
	}
	
	@GetMapping("/userProfile")
	public String userProfile(Model model, HttpSession session) {
		String email=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		return "userProfile";
	}
	
	@GetMapping("/editProfile")
	public String editProfile(Model model, HttpSession session) {
		String email=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		return "editProfile";
	}
	
	@GetMapping("/allUsers")
	public String allUsers(Model model, HttpSession session) {
		List<User> allUsers=userService.fetchAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "/admin/allUsers";
	}
	
	@GetMapping("/editUser")
	public String editUser(@RequestParam long id,Model model, HttpSession session) {
		User user=userService.findById(id);
		model.addAttribute("user", user);
		return "/admin/editUser";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam long id,Model model, HttpSession session) {
		userService.deleteUser(id);
		return "redirect:/allUsers";
	}
}