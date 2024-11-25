package com.shopkart.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopkart.entities.Admin;
import com.shopkart.entities.Products;
import com.shopkart.entities.User;
import com.shopkart.services.AdminService;
import com.shopkart.services.ProductsService;
import com.shopkart.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	
	@Autowired
	ProductsService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdminService adminService;

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
		
		List<Products> allProducts=productService.fetchAllProducts();
		
	    for (Products product : allProducts) {
	        product.setPdescription(productService.truncateToThreeLines(product.getPdescription()));
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
	public String addProduct(Model model, HttpSession session) {
		
		String adminId=(String) session.getAttribute("adminId");
		Admin admin = adminService.getAdminId(adminId);
		model.addAttribute("admin", admin);
		
		List<Products> allProducts=productService.fetchAllProducts();
		
	    for (Products product : allProducts) {
	        product.setPdescription(productService.toNewLines(product.getPdescription()));
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
			
			Products product=productService.findByPid(pid);
		    product.setPdescription(productService.toNewLines(product.getPdescription()));
			model.addAttribute("product", product);
			
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/home";
		}
		
		return "/orderProduct";
	}
	
	@GetMapping("/editProduct")
	public String editProduct(@RequestParam long pid,
							  Model model, HttpSession session
							  ) {
		try {
			String adminId=(String) session.getAttribute("adminId");
			Admin admin = adminService.getAdminId(adminId);
			model.addAttribute("admin", admin);
			
			Products product=productService.findByPid(pid);
			model.addAttribute("product", product);
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/addProduct";
		}
		
		return "/admin/editProduct";
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam long pid,HttpSession session, RedirectAttributes redirectAttributes) {
		try {		
			Products product=productService.findByPid(pid);
			productService.deleteProduct(product);
			redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete product: " + e.getMessage());
			return "redirect:/addProduct";
		}
		return "redirect:/addProduct";
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
		
		String adminId=(String) session.getAttribute("adminId");
		Admin admin = adminService.getAdminId(adminId);
		model.addAttribute("admin", admin);
		
		List<User> allUsers=userService.fetchAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "/admin/allUsers";
	}
	
	@GetMapping("/editUser")
	public String editUser(@RequestParam long id,Model model, HttpSession session) {
		
		String adminId=(String) session.getAttribute("adminId");
		Admin admin = adminService.getAdminId(adminId);
		model.addAttribute("admin", admin);
		
		User user=userService.findById(id);
		model.addAttribute("user", user);
		return "/admin/editUser";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam long id,Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		try {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
	}
	catch(Exception e) {
		redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user: " + e.getMessage());
		return "redirect:/allUsers";
	}
		return "redirect:/allUsers";
	}
}