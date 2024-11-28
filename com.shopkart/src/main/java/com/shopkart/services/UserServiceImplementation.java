package com.shopkart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.User;
import com.shopkart.repositories.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	VerificationService verify;
	
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
			String dbPass = verify.decrypt(repo.findByEmail(email).getPassword(),email);
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

	@Override
	public User getUserByEmail(String email) {
		return repo.findByEmail(email);
		
	}

	@Override
	public void updateUser(User user) {
		repo.save(user);
		
	}

	@Override
	public List<User> fetchAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public User deleteUser(long id) {
		// TODO Auto-generated method stub
		return repo.deleteById(id);
	}

	
}
