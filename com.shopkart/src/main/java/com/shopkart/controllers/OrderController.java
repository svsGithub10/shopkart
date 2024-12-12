package com.shopkart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopkart.entities.Orders;
import com.shopkart.entities.Products;
import com.shopkart.entities.User;
import com.shopkart.services.OrderService;
import com.shopkart.services.ProductsService;
import com.shopkart.services.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.Random;

@Controller
public class OrderController {

    @Autowired
    UserService userService;

    @Autowired
    ProductsService productsService;

    @Autowired
    OrderService service;

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam long pid, @RequestParam String address1,
                             @RequestParam String address2, @RequestParam String city,
                             @RequestParam String state, @RequestParam String pinCode,
                             @RequestParam(required = false) String addressUpdate, HttpSession session, Model model) {

        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/logout";
        }
        User user = userService.getUserByEmail(email);

        Products product;
        try {
            product = productsService.findByPid(pid);
        } catch (Exception e) {
            return "redirect:/home";
        }

        Orders order = new Orders();
        
        // Generate a unique order ID
        String orderId = service.generateUniqueOrderId();
        order.setOrderId(orderId);

        // Populate the order details
        order.setUser(user);
        order.setProduct(product);
        order.setAddress(address1+" "+address2+" "+city+" "+state+" - "+pinCode);
        
        if(addressUpdate != null && addressUpdate.equals("true")) {
        	user.setAddress1(address1);
        	user.setAddress2(address2);
        	user.setCity(city);
        	user.setState(state);
        	user.setPinCode(pinCode);
        	userService.updateUser(user);
        }

        // Save the order to the database
        service.saveOrder(order);
        model.addAttribute("order", order);

        return "redirect:/payment";
    }


}
