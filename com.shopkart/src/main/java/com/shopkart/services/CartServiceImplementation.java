package com.shopkart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.Cart;
import com.shopkart.entities.Products;
import com.shopkart.entities.User;
import com.shopkart.repositories.CartRepository;

@Service
public class CartServiceImplementation implements CartService {
    
	@Autowired
    private CartRepository repo;

    public Cart saveCart(Cart cart) {
        return repo.save(cart);
    }

	@Override
	public Optional<Cart> findByUserAndProduct(User user, Products product) {
        return repo.findByUserAndProduct(user, product);
    }

	@Override
	public List<Cart> fetchUserCart( User user) {
		return repo.findByUser(user);
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}



}
