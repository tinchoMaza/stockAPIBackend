package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "orders")
@JsonPropertyOrder({"_id", "status", "id_employee", "orderedProducts"})

public class Order implements Serializable{

	private static final long serialVersionUID = 5645243815748439237L;
	
	@Id
	private ObjectId _id;
	private Status status;
	private int idEmployee;
	private List<Product> orderedProducts;
	private Date arrival_date;
	private Date departure_date;

	public Order(Status status, int idEmployee, List<Product> orderedProducts, Date arrival_date, Date departure_date) {
		super();
		this.status = status;
		this.idEmployee = idEmployee;
		this.orderedProducts = orderedProducts;
		this.arrival_date = arrival_date;
		this.departure_date = departure_date;
	}
	
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	
	public void addProductToOrder(Product p) {
		this.orderedProducts.add(p);
	}
	public void removeProductFromOrder(Product p) {
		this.orderedProducts.remove(p);
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
	
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	@Override
	public String toString() {
		String productsId = "";
		for(Product p: orderedProducts) {
			productsId.concat(p.getId() + ", ");
		}
		return "Order id: " + _id + ", status: " + status + ", id employee who ordered: " + idEmployee + ", id products: " + productsId + ", order date: " + arrival_date + ", departure date: " + departure_date;
	}
	
}