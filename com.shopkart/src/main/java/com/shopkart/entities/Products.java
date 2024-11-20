package com.shopkart.entities;

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
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition="LONGBLOB")
	private byte[] image1;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition="LONGBLOB")
	private byte[] image2;
	
	public String getPhotoBase64() {
        if (pimage == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(pimage);
    }
	
	public String getPhoto1Base64() {
        if (image1 == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(image1);
    }
	
	public String getPhoto2Base64() {
        if (image2 == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(image2);
    }

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(Long pid, String pname, String brand, String price, String pdescription, byte[] pimage,
			byte[] image1, byte[] image2) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.brand = brand;
		this.price = price;
		this.pdescription = pdescription;
		this.pimage = pimage;
		this.image1 = image1;
		this.image2 = image2;
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

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", brand=" + brand + ", price=" + price + ", pdescription="
				+ pdescription + ", pimage=" + Arrays.toString(pimage) + ", image1=" + Arrays.toString(image1)
				+ ", image2=" + Arrays.toString(image2) + "]";
	}

	
	
}
