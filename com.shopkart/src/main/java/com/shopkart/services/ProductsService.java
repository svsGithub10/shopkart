package com.shopkart.services;

import java.util.List;

import com.shopkart.entities.Products;

public interface ProductsService {
	
	public void addProduct(Products product);
	
	List<Products> fetchAllProducts();
	
	Products findByPid(long pid);

	public void updateProduct(Products product);

	public void deleteProduct(Products product);

}
