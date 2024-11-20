package com.shopkart.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.shopkart.entities.Products;
import com.shopkart.services.ProductsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductsController {

	@Autowired
	ProductsService service;
	
	
	@PostMapping("/addProduct")
	public String addProduct( @RequestParam String pname,
							  @RequestParam String brand,
							  @RequestParam String price,
							  @RequestParam String pdescription,
							  @RequestParam("pimage") MultipartFile pimage,
							  Model model, 
							  HttpSession session) {
		
		Products product=new Products();
		product.setBrand(brand);
		product.setPdescription(pdescription);
		product.setPname(pname);
		product.setPrice(price);
		try {						
			product.setPimage(pimage.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		service.addProduct(product);
		return "redirect:/addProduct";
	}
	
}
