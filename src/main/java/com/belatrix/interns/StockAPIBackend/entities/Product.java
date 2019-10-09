package com.belatrix.interns.StockAPIBackend.entities;

import java.util.Date;

public class Product {
	private int id;
	private String name;
	private String description;
	private int stock;
	private int minReserveStock;
	private Date ingressDate;
	private Date egressDate;
	
	public Date getIngressDate() {
		return ingressDate;
	}

	public void setIngressDate(Date ingressDate) {
		this.ingressDate = ingressDate;
	}

	public Date getEgressDate() {
		return egressDate;
	}

	public void setEgressDate(Date egressDate) {
		this.egressDate = egressDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getMinReserveStock() {
		return minReserveStock;
	}
	
	public void setMinReserveStock(int minReserveStock) {
		this.minReserveStock = minReserveStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
