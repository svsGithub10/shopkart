package com.shopkart.entities;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String pname;
	private String brand;
	private String price;
	
	@Column(columnDefinition="text")
	private String pdescription;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition="LONGBLOB")
	private byte[] pimage;
	
	public String getPhotoBase64() {
        if (pimage == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(pimage);
    }

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(Long pid, String pname, String brand, String price, String pdescription,  byte[] pimage ) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.brand = brand;
		this.price = price;
		this.pdescription = pdescription;
		this.pimage = pimage;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public byte[] getPimage() {
		return pimage;
	}

	public void setPimage(byte[] pimage) {
		this.pimage = pimage;

	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", brand=" + brand + ", price=" + price + ", pdescription="
				+ pdescription  + ", pimage=" + Arrays.toString(pimage)  + "]";
	}
	
}
