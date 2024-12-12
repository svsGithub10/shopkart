package com.shopkart.services;

import java.util.List;

import com.shopkart.entities.Products;
import com.shopkart.entities.User;

public interface ProductsService {
	
	public void addProduct(Products product);
	
	List<Products> fetchAllProducts();
	
	Products findByPid(long pid);

	public void updateProduct(Products product);

	public void deleteProduct(Products product);
	
	String truncateToThreeLines(String description);

	String truncateToSingleLine(String description);

	String toNewLines(String description);

}
