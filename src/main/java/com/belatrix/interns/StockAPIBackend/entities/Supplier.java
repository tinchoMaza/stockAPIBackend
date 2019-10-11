package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "suppliers")
@JsonPropertyOrder({"_id", "name", "mail", "web_url", "phone_number"})

public class Supplier implements Serializable {

	private static final long serialVersionUID = 1828888047815644952L;
	
	@Id
	private ObjectId _id;
	private String name;
	private String mail;
	private String webUrl;
	private String phoneNumber;
	
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
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
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "Supplier id: " + _id + ", name: " + name + ", mail: " + mail + ", webPage: " + webUrl + ", phone number: " + phoneNumber;
	}
}