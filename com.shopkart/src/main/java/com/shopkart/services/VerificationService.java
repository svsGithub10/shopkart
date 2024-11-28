package com.shopkart.services;

public interface VerificationService {

	public void sendEmail(String toEmail, String subject, String body);
	
	public void prepareSecreteKey(String myKey);
	
	public String encrypt(String strToEncrypt, String secret);
	
	public String decrypt(String strToDecrypt, String secret);

	String getAlphaNumericString(int n);
	
}
