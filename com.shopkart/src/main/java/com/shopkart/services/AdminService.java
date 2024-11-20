package com.shopkart.services;

import com.shopkart.entities.Admin;

public interface AdminService {

	void addAdmin(Admin admin);
	
	boolean adminExists(String adminId, String secretCode);

	boolean validateAdmin(String adminId, String password);
}
