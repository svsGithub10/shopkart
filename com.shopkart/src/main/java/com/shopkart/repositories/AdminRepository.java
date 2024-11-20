package com.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopkart.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminId(String adminId);
		
}
