package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "products")
@JsonPropertyOrder({"_id", "name", "description", "stock", "min_reserve_stock", "arrival_date", "departure_date"})

public class Product implements Serializable{
	
	private static final long serialVersionUID = 23589110536490666L;
	
	@Id
	private ObjectId _id;
	private String name;
	private String description;
	private int stock;
	private int min_reserve_stock;
	
	public Product(String name, String description, int stock, int min_reserve_stock) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.min_reserve_stock = min_reserve_stock;
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
		return min_reserve_stock;
	}
	
	public void setMinReserveStock(int minReserveStock) {
		this.min_reserve_stock = minReserveStock;
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	
	@Override
	public String toString() {
		return "Product id: " + _id + ", name: " + name + ", description: " + description + ", stock: " + stock + ", contingency stock: " + min_reserve_stock;
	}
}