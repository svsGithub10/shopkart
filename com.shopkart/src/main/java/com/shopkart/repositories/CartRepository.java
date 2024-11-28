package com.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopkart.entities.Cart;
import com.shopkart.entities.Products;
import com.shopkart.entities.User;

import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUserAndProduct(User user, Products product);
	
	List<Cart> findByUser(User user);
}
