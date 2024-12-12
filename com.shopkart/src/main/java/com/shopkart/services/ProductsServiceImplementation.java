package com.shopkart.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.Products;
import com.shopkart.entities.User;
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

	@Override
	public Products findByPid(long pid) {
		// TODO Auto-generated method stub
		return repo.findById(pid);
	}

	@Override
	public void updateProduct(Products product) {
		repo.save(product);
		
	}

	@Override
	public void deleteProduct(Products product) {
		repo.delete(product);		
	}

	@Override
	public String truncateToThreeLines(String description) {
		if (description == null || description.isEmpty()) {
            return description;
        }
        String[] lines = description.split("\n");
        return String.join("<br>", Arrays.copyOfRange(lines, 0, Math.min(lines.length, 3)));
	}
	
	@Override
	public String toNewLines(String description) {
		if (description == null || description.isEmpty()) {
			return description;
		}
		String[] lines = description.split("\n");
		return String.join("<br>", Arrays.copyOfRange(lines, 0,lines.length));
	}

	@Override
	public String truncateToSingleLine(String description) {
		if (description == null || description.isEmpty()) {
            return description;
        }
        String[] lines = description.split("\n");
        return lines[0];
	}

	
}
