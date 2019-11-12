package com.belatrix.interns.StockAPIBackend.dto;

import java.util.List;

public class OrderView {

	private String id_employee;
	private List<String> orderedProducts;
	
	public OrderView(String id_employee, List<String> orderedProducts) {
		super();
		this.id_employee = id_employee;
		this.orderedProducts = orderedProducts;
	}

	public String getId_Employee() {
		return id_employee;
	}

	public void setId_Employee(String id_employee) {
		this.id_employee = id_employee;
	}

	public List<String> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<String> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}