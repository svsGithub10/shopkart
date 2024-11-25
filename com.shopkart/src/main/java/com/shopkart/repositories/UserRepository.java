package com.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopkart.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	User findByEmail(String email);
	
	User findById(long id);
	
	User deleteById(long id);
}
