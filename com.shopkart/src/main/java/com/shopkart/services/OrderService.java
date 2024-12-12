package com.shopkart.services;

import com.shopkart.entities.Orders;

public interface OrderService {
	
	public String generateUniqueOrderId();

	void saveOrder(Orders order);
	
}
