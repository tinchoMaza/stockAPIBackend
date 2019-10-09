package com.belatrix.interns.StockAPIBackend.entities;

public class Supplier {
	private int idSupplier;
	private String name;
	private String mail;
	private String webUrl;
	private String phoneNumber;
	
	public int getIdSupplier() {
		return idSupplier;
	}
	
	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getWebUrl() {
		return webUrl;
	}
	
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
