package com.belatrix.interns.StockAPIBackend.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateAccepted;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateCancelled;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateCompleted;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateInProcess;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOnHold;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateRejected;

public class OrderDTO {

	private String _id;
	private int number;
	private String status;
	private Employee employee;
	private List<Product> orderedProducts;
	private Date arrival_date;
	private Date departure_date;
	
	public OrderDTO(String _id, int number, String status, Employee employee, List<Product> orderedProducts,
			Date arrival_date, Date departure_date) {
		super();
		this._id = _id;
		this.number = number;
		this.status = status;
		this.employee = employee;
		this.orderedProducts = orderedProducts;
		this.arrival_date = arrival_date;
		this.departure_date = departure_date;
	}
	
	public OrderDTO() {
		super();
		this.orderedProducts = new ArrayList<Product>();
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<Product> orderedProducts) {
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
	
	private StateOrder getStateOrderStatus() {
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

	public Order toDomain() {
		return new Order(new ObjectId(_id), number, this.getStateOrderStatus(), employee, orderedProducts, arrival_date, departure_date);
	}
	
}