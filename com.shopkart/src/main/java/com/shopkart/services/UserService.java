package com.shopkart.services;

import com.shopkart.entities.User;


public interface UserService {
	
	void addUser(User user);

	boolean userExists(String name, String email);

	boolean validateUser(String email, String password);

	User getUser(String name);
	
}
