package com.belatrix.interns.StockAPIBackend.entities;

import java.util.List;

/**
 * @author fbalsas
 */

public class Order {
	private int idOrder;
	private List<Product> orderedProducts;
	private Status status;
	private int idEmployee;
	
	
	public int getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmpleado) {
		this.idEmployee = idEmpleado;
	}
	
	
}
