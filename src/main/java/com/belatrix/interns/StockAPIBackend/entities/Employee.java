package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "employees")
@JsonPropertyOrder({"_id", "name", "area", "mail", "password"})

public class Employee implements Serializable{
	
	private static final long serialVersionUID = -2088778252016118587L;
	@Id
	private ObjectId _id;
	private String name;
	private String area;
	private String mail;
	private String password;
	
	public Employee(ObjectId _id, String name, String area, String mail, String password) {
		super();
		this._id = _id;
		this.name = name;
		this.area = area;
		this.mail = mail;
		this.password = password;
	}

	public String getNombre() {
		return name;
	}
	
	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getId() {
		return _id.toHexString();
	}

	public void setId(ObjectId id) {
		this._id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Employee id: " + _id + ", name: " + name + ", area: " + area + ", mail: " + mail;
	}
}