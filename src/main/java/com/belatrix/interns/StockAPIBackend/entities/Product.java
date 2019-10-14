package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;
import java.util.Date;

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
	private int minReserveStock;
	private Date arrival_date;
	private Date departure_date;
	
	public Product(ObjectId _id, String name, String description, int stock, int minReserveStock) {
		super();
		this._id = _id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.minReserveStock = minReserveStock;
		this.arrival_date = new Date(); //se asigna la fecha de hoy, se asume que llega y se registra el mismo dia
		this.departure_date = null; //lo asigno como null porque apenas recibo el producto no tengo fecha de envio definida
	}

	public Date getArrivalDate() {
		return arrival_date;
	}

	public void setArrivalDate(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public Date getDepartureDate() {
		return departure_date;
	}

	public void setDepartureDate(Date departure_date) {
		this.departure_date = departure_date;
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

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	
	@Override
	public String toString() {
		return "Product id: " + _id + ", name: " + name + ", description: " + description + ", stock: " + stock + ", contingency stock: " + minReserveStock + ", arrival date: " + arrival_date + ", depature date: " + departure_date;
	}
}