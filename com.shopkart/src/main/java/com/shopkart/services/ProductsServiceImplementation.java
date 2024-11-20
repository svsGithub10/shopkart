package com.shopkart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.Products;
import com.shopkart.repositories.ProductsRepository;

@Service
public class ProductsServiceImplementation implements ProductsService{

	@Autowired
	ProductsRepository repo;
	
	@Override
	public void addProduct(Products product) {
		repo.save(product);
	}

	@Override
	public List<Products> fetchAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}	
	
}
