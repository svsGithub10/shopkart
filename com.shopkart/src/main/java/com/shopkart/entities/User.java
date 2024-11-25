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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String password;
	private String dob;
	private String gender;
	private String pinCode;
	private String phone;
	private String state;
	private String city;
	private String address1;
	private String address2;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition="LONGBLOB")
	private byte[] photo;
	
	public String getPhotoBase64() {
        if (photo == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(photo);
    }
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name, String email, String password, String dob, String gender, String pinCode, String phone,
			String state, String city, String address1, String address2, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.pinCode = pinCode;
		this.phone = phone;
		this.state = state;
		this.city = city;
		this.address1 = address1;
		this.address2 = address2;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", dob=" + dob
				+ ", gender=" + gender + ", pinCode=" + pinCode + ", phone=" + phone + ", state=" + state + ", city="
				+ city + ", address1=" + address1 + ", address2=" + address2 + ", photo=" + Arrays.toString(photo)
				+ "]";
	}


	
}
