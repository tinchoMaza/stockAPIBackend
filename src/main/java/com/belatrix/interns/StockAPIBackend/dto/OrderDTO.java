package com.belatrix.interns.StockAPIBackend.dto;

import java.util.List;

import org.bson.types.ObjectId;

import com.belatrix.interns.StockAPIBackend.entities.Order;

public class OrderDTO {

	private String id_employee;
	private List<String> orderedProducts;
	
	public OrderDTO(String id_employee, List<String> orderedProducts) {
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
	
	public Order toDomain() {
		Order o = new Order();
		o.setId_Employee(new ObjectId(id_employee));
		return o;
	}

}