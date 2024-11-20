package com.shopkart.controllers;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
							  Model model,RedirectAttributes redirectAttributes
							) {
		try {
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
		redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to add product: " + e.getMessage());
			return "redirect:/addProduct";
		}
		return "redirect:/addProduct";
	}
	

	@GetMapping("/editProduct")
	public String editProduct(@RequestParam long pid,
							  Model model
							  ) {
		try {
			Products product=service.findByPid(pid);
			model.addAttribute("product", product);
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/addProduct";
		}
		
		return "/admin/editProduct";
	}
	
	@PostMapping("/editProduct")
	public String updateProduct(@RequestParam(required = false) long pid, 
			@RequestParam(required = false) String pname,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) String price,
			@RequestParam(required = false) String pdescription,
			@RequestParam(required = false) MultipartFile pimage, 
			@RequestParam(required = false) MultipartFile image1, 
			@RequestParam(required = false) MultipartFile image2,
			Model model, RedirectAttributes redirectAttributes) {
		try {
			Products product=service.findByPid(pid);
			if (pname != null && !pname.isEmpty()) product.setPname(pname);
			if (brand != null && !brand.isEmpty()) product.setBrand(brand);
			if (price != null && !price.isEmpty()) product.setPrice(price);
			if (pdescription != null && !pdescription.isEmpty()) product.setPdescription(pdescription);
			
	        // Update image fields only if new images are uploaded
	        try {
	            if (pimage != null && !pimage.isEmpty()) {
	                product.setPimage(pimage.getBytes());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        try {
	            if (image1 != null && !image1.isEmpty()) {
	                product.setImage1(image1.getBytes());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        try {
	            if (image2 != null && !image2.isEmpty()) {
	                product.setImage2(image2.getBytes());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			service.updateProduct(product);
			model.addAttribute("product", product);
			redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update product: " + e.getMessage());
			return "redirect:/editProduct";
		}
		return "redirect:/addProduct";
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam long pid, RedirectAttributes redirectAttributes) {
		try {
			Products product=service.findByPid(pid);
			service.deleteProduct(product);
			redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete product: " + e.getMessage());
			return "redirect:/addProduct";
		}
		return "redirect:/addProduct";
	}
	
	@GetMapping("/orderProduct")
	public String orderProduct(@RequestParam long pid,
							  Model model, HttpSession session
							  ) {
		try {
			Products product=service.findByPid(pid);
			model.addAttribute("product", product);
			
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/home";
		}
		
		return "/orderProduct";
	}

	
}
