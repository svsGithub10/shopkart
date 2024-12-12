package com.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopkart.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

	boolean existsByOrderId(String orderId);
	
}
