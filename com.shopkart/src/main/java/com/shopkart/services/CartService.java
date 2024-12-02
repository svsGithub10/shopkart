package com.shopkart.services;

import java.util.List;
import java.util.Optional;

import com.shopkart.entities.Cart;
import com.shopkart.entities.Products;
import com.shopkart.entities.User;

public interface CartService {

	public Cart saveCart(Cart cart);

	public Optional<Cart> findByUserAndProduct(User user, Products product);

	List<Cart> fetchUserCart( User user);

	public void remove(long id);
}
