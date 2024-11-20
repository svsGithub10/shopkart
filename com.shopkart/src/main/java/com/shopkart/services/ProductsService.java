package com.shopkart.services;

import java.util.List;

import com.shopkart.entities.Products;

public interface ProductsService {
	
	public void addProduct(Products product);
	
	List<Products> fetchAllProducts();

}
