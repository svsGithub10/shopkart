package com.shopkart.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	
	private String orderId;
	
	@ManyToOne
	private Products product;

	@ManyToOne
	private User user;
	
	private String oStatus;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(long oid, String orderId, Products product, User user, String oStatus) {
		super();
		this.oid = oid;
		this.orderId = orderId;
		this.product = product;
		this.user = user;
		this.oStatus = oStatus;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderId=" + orderId + ", product=" + product + ", user=" + user + ", oStatus="
				+ oStatus + "]";
	}
	
	
}
