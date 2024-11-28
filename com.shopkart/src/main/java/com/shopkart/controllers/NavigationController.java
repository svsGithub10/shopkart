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
import com.shopkart.entities.Cart;
import com.shopkart.entities.Products;
import com.shopkart.entities.User;
import com.shopkart.services.AdminService;
import com.shopkart.services.CartService;
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
	
    @Autowired
    private CartService cartService;

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
        if (email == null) {
            // If user is not logged in, redirect to login
            return "redirect:/logout";
        }
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
        if (adminId == null) {
            // If user is not logged in, redirect to login
            return "redirect:/admin";
        }
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
	        if (email == null) {
	            // If user is not logged in, redirect to login
	            return "redirect:/logout";
	        }
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
	           if (adminId == null) {
	                // If user is not logged in, redirect to login
	                return "redirect:/admin";
	            }
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
			if((String)session.getAttribute("adminId")!=null) {
			Products product=productService.findByPid(pid);
			productService.deleteProduct(product);
			}
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
        if (email == null) {
            // If user is not logged in, redirect to login
            return "redirect:/logout";
        }
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		return "userProfile";
	}
	
	@GetMapping("/editProfile")
	public String editProfile(Model model, HttpSession session) {
		String email=(String) session.getAttribute("email");
        if (email == null) {
            // If user is not logged in, redirect to login
            return "redirect:/logout";
        }
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		return "editProfile";
	}
	
	@GetMapping("/allUsers")
	public String allUsers(Model model, HttpSession session) {
		
		String adminId=(String) session.getAttribute("adminId");
        if (adminId == null) {
            // If user is not logged in, redirect to login
            return "redirect:/admin";
        }
		Admin admin = adminService.getAdminId(adminId);
		model.addAttribute("admin", admin);
		
		List<User> allUsers=userService.fetchAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "/admin/allUsers";
	}
	
	@GetMapping("/editUser")
	public String editUser(@RequestParam long id,Model model, HttpSession session) {
		
		String adminId=(String) session.getAttribute("adminId");
        if (adminId == null) {
            // If user is not logged in, redirect to login
            return "redirect:/admin";
        }
		Admin admin = adminService.getAdminId(adminId);
		model.addAttribute("admin", admin);
		
		User user=userService.findById(id);
		model.addAttribute("user", user);
		return "/admin/editUser";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam long id,Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		try {
		if((String)session.getAttribute("adminId")!=null) userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
	}
	catch(Exception e) {
		redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user: " + e.getMessage());
		return "redirect:/allUsers";
	}
		return "redirect:/allUsers";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "/forgotPassword/main";
	}
	
	@GetMapping("/verify")
	public String verify() {
		return "/forgotPassword/verify";
	}
	
	@GetMapping("/verifyOtp")
	public String verifyOtp() {
		return "/verifyOtp";
	}

	@GetMapping("/changePassword")
	public String changePassword(Model model, HttpSession session) {
		String email=(String) session.getAttribute("email");
        if (email == null) {
            // If user is not logged in, redirect to login
            return "redirect:/logout";
        }
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		return "changePassword";
	}
	
	@GetMapping("/cart")
	public String AddToCart(Model model, HttpSession session) {
		String email=(String) session.getAttribute("email");
        if (email == null) {
            // If user is not logged in, redirect to login
            return "redirect:/logout";
        }
		User user=userService.getUserByEmail(email);
		model.addAttribute("user", user);
		if (user == null) {
            // Handle case where user is not found
            return "redirect:/home";
        }
		
		List<Cart> cartItems = cartService.fetchUserCart(user);
		
		model.addAttribute("cartItems", cartItems);
		
		return "/cart";
	}
}
	

