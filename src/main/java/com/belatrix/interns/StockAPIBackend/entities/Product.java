package com.belatrix.interns.StockAPIBackend.entities;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class Product{

	private ObjectId _id;
	private String name;
	private String description;
	private int stock;
	private int min_reserve_stock;
	private List<Supplier> suppliers;
	
	public Product(ObjectId _id, String name, String description, int stock, int min_reserve_stock) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.min_reserve_stock = min_reserve_stock;
	}
	
	public Product() {
		super();
		this.suppliers = new ArrayList<Supplier>();
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
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	public int getMin_reserve_stock() {
		return min_reserve_stock;
	}
	public void setMin_reserve_stock(int min_reserve_stock) {
		this.min_reserve_stock = min_reserve_stock;
	}
	public List<Supplier> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	public void addSupplierToProduct(Supplier s) {
		this.suppliers.add(s);
	}
	public void removeSupplierFromProduct(Supplier s) {
		this.suppliers.remove(s);
	}

}