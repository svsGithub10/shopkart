package com.shopkart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.Admin;
import com.shopkart.repositories.AdminRepository;

@Service
public class AdminServiceImplementation implements AdminService{
	
	@Autowired
	AdminRepository repo;

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		repo.save(admin);
	}

	@Override
	public boolean adminExists(String adminId,  String secretCode) {
		Admin admin = repo.findByAdminId(adminId);
		if (admin == null  && secretCode.equals(Admin.secretCode) ){
			return true;
		}
		return false;
	}

	@Override
	public boolean validateAdmin(String adminId, String password) {
		if(repo.findByAdminId(adminId)==null) {
			return false;
		}
		else {
			String dbPass = repo.findByAdminId(adminId).getPassword();
			if(password.equals(dbPass)) {
			return true;
			}
			else return false;
		}
		
	}

}
