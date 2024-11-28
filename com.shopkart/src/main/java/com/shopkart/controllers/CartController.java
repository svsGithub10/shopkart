package com.shopkart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopkart.entities.Products;
import com.shopkart.entities.User;
import com.shopkart.entities.Cart;
import com.shopkart.services.CartService;
import com.shopkart.services.ProductsService;
import com.shopkart.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
    private CartService service;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductsService productService;

    @GetMapping("/addCart")
    public String addCart(@RequestParam long pid, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        try {
            String email = (String) session.getAttribute("email");
            if (email == null) {
                // If user is not logged in, redirect to login
                return "redirect:/logout";
            }

            User user = userService.getUserByEmail(email);
            Products product = productService.findByPid(pid);

            if (user == null || product == null) {
                // Handle cases where user or product is not found
                return "redirect:/home";
            }

            // Check if the product is already in the cart
            if (service.findByUserAndProduct(user, product).isPresent()) {
                // If the product is already in the cart, redirect with a message
            	redirectAttributes.addFlashAttribute("successMessage", "Product is already in the cart.");
                return "redirect:/cart"; // Adjust to the appropriate page
            }

            // Create Cart object
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);

            // Save to database
            service.saveCart(cart);
            redirectAttributes.addFlashAttribute("successMessage", "Product added to cart.");
            return "redirect:/home"; // Redirect to cart view
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("successMessage", "Failed to add "+e.getMessage());
            return "redirect:/home";
        }
    }

}