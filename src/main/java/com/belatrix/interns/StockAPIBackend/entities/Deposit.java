package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "deposit")
@JsonPropertyOrder({"_id", "stockedProducts"})

public class Deposit implements Serializable{

	private static final long serialVersionUID = -4630888530062914037L;
	
	@Id
	private ObjectId _id;
	private List<Product> stockedProducts;

	public Deposit(ObjectId _id) {
		super();
		this._id = _id;
		this.stockedProducts = new ArrayList<Product>();
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	
	public List<Product> getStockedProducts() {
		return stockedProducts;
	}
	public void setStockedProducts(List<Product> stockedProducts) {
		this.stockedProducts = stockedProducts;
	}
	
	public void addProductToStock(Product p) {
		this.stockedProducts.add(p);
	}
	public void removeProductFromStock(Product p) {
		this.stockedProducts.remove(p);
	}
}
