package com.belatrix.interns.StockAPIBackend.entities;

import java.util.List;

/**
 * @author fbalsas
 */

public class Admin {
	private int idAdmin;
	private String name;
	private String email;
	private String password;
	private List<Product> ordersToBuy;
	
	
	public int getIdAdmin() {
		return idAdmin;
	}
	
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
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

	public List<Product> getOrdersToBuy() {
		return ordersToBuy;
	}

	public void setOrdersToBuy(List<Product> ordersToBuy) {
		this.ordersToBuy = ordersToBuy;
	}
	
}
