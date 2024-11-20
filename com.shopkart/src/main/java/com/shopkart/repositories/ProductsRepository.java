package com.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopkart.entities.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{
	
	

}
