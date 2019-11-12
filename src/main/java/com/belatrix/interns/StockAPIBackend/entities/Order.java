package com.belatrix.interns.StockAPIBackend.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.belatrix.interns.StockAPIBackend.dbo.OrderDBO;
import com.belatrix.interns.StockAPIBackend.dto.OrderDTO;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;

public class Order {
	
	private ObjectId _id;
	private int number;
	private StateOrder status;
	private Employee employee;
	private List<Product> orderedProducts;
	private Date arrival_date;
	private Date departure_date;
	
	public Order() {
		super();
		this.orderedProducts = new ArrayList<Product>();
	}

	public Order(ObjectId _id, int number, StateOrder status, Employee employee, List<Product> orderedProducts,
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
	public StateOrder getStatus() {
		return status;
	}
	public void setStatus(StateOrder status) {
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
	public void addProductToOrder(Product p) {
		this.orderedProducts.add(p);
	}
	public void removeProductFromOrder(Product p) {
		this.orderedProducts.remove(p);
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

	
	public boolean equalsDuplicate(Order obj) {
		if(employee.getMail().equalsIgnoreCase(obj.getEmployee().getMail()))
			if(sameProducts(orderedProducts,obj.orderedProducts))
				return true;
		return false;
	}
	
	private boolean sameProducts(List<Product> o1, List<Product> o2) {
		boolean flag = false;
		if(o1.size() == o2.size())
			for(Product p1 : o1) {
				for(Product p2 : o2) {
					if(p1.getName().equalsIgnoreCase(p2.getName())) //o1 esta en o2
						flag = true;
				}
				if(flag)
					flag = false; //producto de o1 esta en o2 entonces reseteo el boolean para el siguiente producto
				else
					return flag; //seria false
				if(o1.indexOf(p1) == o1.size()-1)
					return true;
			}
		return flag;
	}

	public OrderDBO toDBO() {
		List<ObjectId> pids = new ArrayList<ObjectId>();
		for(Product p : orderedProducts) {
			pids.add(new ObjectId(p.getId()));
		}
		return new OrderDBO(_id, number, status.getStateOrderString(), new ObjectId(employee.getId()), pids, arrival_date, departure_date);
	}
	
	public OrderDTO toDTO() {
		return new OrderDTO(_id.toHexString(), number, status.getStateOrderString(), employee, orderedProducts, arrival_date, departure_date);
	}
}