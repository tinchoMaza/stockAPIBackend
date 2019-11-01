package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "admins")
@JsonPropertyOrder({"_id", "name", "mail", "password", "ordersToBuy"})

public class Admin implements Serializable{
	
	private static final long serialVersionUID = 3358265610462809215L;
	@Id
	private ObjectId _id;
	private String name;
	private String mail;
	private String password;
	private List<Order> ordersToBuy;
		
	public Admin(ObjectId _id, String name, String mail, String password) {
		super();
		this._id = _id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.ordersToBuy = new ArrayList<Order>();
	}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Order> getOrdersToBuy() {
		return ordersToBuy;
	}
	public void setOrdersToBuy(List<Order> ordersToBuy) {
		this.ordersToBuy = ordersToBuy;
	}
	
	
}