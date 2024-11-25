package com.shopkart.services;

import java.util.List;

import com.shopkart.entities.User;


public interface UserService {
	
	void addUser(User user);

	boolean userExists(String name, String email);

	boolean validateUser(String email, String password);

	User getUser(String name);

	User getUserByEmail(String email);

	void updateUser(User user);
	
	List<User> fetchAllUsers();

	User findById(long id);
	
	User deleteUser(long id);
	
}
