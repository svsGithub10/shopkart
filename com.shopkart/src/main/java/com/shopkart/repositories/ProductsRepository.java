package com.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopkart.entities.Products;
import com.shopkart.entities.User;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

	Products findById(long pid);

	
	
}
