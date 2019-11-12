package com.belatrix.interns.StockAPIBackend.dbo;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "products")
@JsonPropertyOrder({"_id", "name", "description", "stock", "min_reserve_stock", "suppliers"})

public class ProductDBO implements Serializable{

	private static final long serialVersionUID = 1983421145139800179L;

	@Id
	private ObjectId _id;
	private String name;
	private String description;
	private int stock;
	private int min_reserve_stock;
	private List<ObjectId> suppliers;
	
	public ProductDBO(ObjectId _id, String name, String description, int stock, int min_reserve_stock,
			List<ObjectId> suppliers) {
		super();
		this._id = _id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.min_reserve_stock = min_reserve_stock;
		this.suppliers = suppliers;
	}

	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
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
	public int getMin_reserve_stock() {
		return min_reserve_stock;
	}
	public void setMin_reserve_stock(int min_reserve_stock) {
		this.min_reserve_stock = min_reserve_stock;
	}
	public List<ObjectId> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<ObjectId> suppliers) {
		this.suppliers = suppliers;
	}
	
}