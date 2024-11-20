package com.shopkart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.User;
import com.shopkart.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repo;
	
	public void addUser(User user) {
		repo.save(user);
	}
	
	@Override
	public boolean userExists(String name, String email) {
		User user1 = repo.findByName(name);
		User user2 = repo.findByEmail(email);
		if(user1 != null || user2 !=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean validateUser(String email, String password) {
		
		if(repo.findByEmail(email)==null) {
			return false;
		}
		else {
			String dbPass = repo.findByEmail(email).getPassword();
			if(password.equals(dbPass)) {
			return true;
			}
			else return false;
		}
	}

	@Override
	public User getUser(String name) {
		return repo.findByName(name);
	}

	
}
