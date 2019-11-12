package com.belatrix.interns.StockAPIBackend.dbo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateAccepted;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateCancelled;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateCompleted;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateInProcess;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOnHold;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateRejected;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "orders")
@JsonPropertyOrder({"_id", "number", "status", "id_employee", "orderedProducts", "arrival_date", "departure_date"})

public class OrderDBO implements Serializable{

	private static final long serialVersionUID = 7514981507162362478L;

	@Id
	private ObjectId _id;
	private int number;
	private String status;
	private ObjectId id_employee;
	private List<ObjectId> orderedProducts;
	private Date arrival_date;
	private Date departure_date;
	
	public OrderDBO(ObjectId _id, int number, String status, ObjectId id_employee, List<ObjectId> orderedProducts,
			Date arrival_date, Date departure_date) {
		super();
		this._id = _id;
		this.number = number;
		this.status = status;
		this.id_employee = id_employee;
		this.orderedProducts = orderedProducts;
		this.arrival_date = arrival_date;
		this.departure_date = departure_date;
	}

	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ObjectId getId_employee() {
		return id_employee;
	}
	public void setId_employee(ObjectId id_employee) {
		this.id_employee = id_employee;
	}
	public List<ObjectId> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<ObjectId> orderedProducts) {
		this.orderedProducts = orderedProducts;
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
	
	public StateOrder getStateOrderStatus() {
		switch (status) {
		case "ACCEPTED":
			return new StateAccepted();
		case "CANCELLED":
			return new StateCancelled();
		case "COMPLETED":
			return new StateCompleted();
		case "REJECTED":
			return new StateRejected();
		case "ON_HOLD":
			return new StateOnHold();
		default:
			return new StateInProcess();
		}
	}
	
}