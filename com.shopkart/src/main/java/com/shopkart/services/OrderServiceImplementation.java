package com.shopkart.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkart.entities.Orders;
import com.shopkart.repositories.OrderRepository;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	OrderRepository repo;

	@Override
    public String generateUniqueOrderId() {
        String orderId;
        do {
            orderId = generateOrderId();
        } while (orderIdExists(orderId)); // Check if the orderId already exists
        return orderId;
    }

    private boolean orderIdExists(String orderId) {
		
    	return repo.existsByOrderId(orderId);
	}

	private String generateOrderId() {
        Random random = new Random();
        StringBuilder orderId = new StringBuilder();

        // Generate 3 random uppercase alphabets
        for (int i = 0; i < 3; i++) {
            char randomAlphabet = (char) ('A' + random.nextInt(26)); // 'A' to 'Z'
            orderId.append(randomAlphabet);
        }

        // Generate 3 random digits
        for (int i = 0; i < 3; i++) {
            int randomDigit = random.nextInt(10); // 0 to 9
            orderId.append(randomDigit);
        }

        return orderId.toString();
    }

	@Override
	public void saveOrder(Orders order) {
		repo.save(order);
	}
	
}
