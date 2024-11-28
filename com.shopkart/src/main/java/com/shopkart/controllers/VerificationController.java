package com.shopkart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopkart.entities.User;
import com.shopkart.services.VerificationService;

import jakarta.servlet.http.HttpSession;

import com.shopkart.services.UserService;

@Controller
public class VerificationController {
    
    @Autowired
    UserService service;
    
    @Autowired
    VerificationService verificationService;
    
    // Variable to temporarily hold user and OTP (for simplicity)
    private User tempUser;
    private String tempOtp;
    private String tempEmail;
    
    @PostMapping("/otp")
    public String getOtp(@RequestParam String email, Model model) {
        String otp = verificationService.getAlphaNumericString(6);

        // Retrieve user by email
        User user = service.getUserByEmail(email);
        if (user == null) {
            return "/validations/userNotFound";
        } else {
            // Save the user and OTP temporarily
            tempUser = user;
            tempOtp = otp;
            tempEmail = email;

            // Send OTP email
            verificationService.sendEmail(email, 
                "ShopKart: Account Recovery", 
                "Dear user, The One Time Password (OTP) to reset your password is \"" + otp + "\".\n\n"
                + "Do not share it with anyone. Please contact admin if it's not you.\n\n"
                + "This mail is auto-generated, please don't reply.\n\n"
                + "Warm regards,\nShopKart");
            

            return "redirect:/verify";
        }
    }
    
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String otp, @RequestParam String password, Model model) {
        // Check if OTP matches
        if (tempOtp != null && tempOtp.equals(otp)) {
            // Update user's password
        	password=verificationService.encrypt( password, tempEmail);
            tempUser.setPassword(password); // Assuming `User` has a `setPassword` method
            service.updateUser(tempUser); // Assuming `UserService` has an `updateUser` method
            
            // Clear temporary data
            tempUser = null;
            tempOtp = null;
            tempEmail=null;
            return "/forgotPassword/success";
        } else {
            return "/forgotPassword/failed";
        }
    }
    
    
    
}
