package com.belatrix.interns.StockAPIBackend.entities;

/**
 * @author fbalsas
 */

public class Employee{
	private int id;
	private String name;
	private String area;
	private String email;
	private String password;
	
	public String getNombre() {
		return name;
	}
	
	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
