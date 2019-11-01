package com.belatrix.interns.StockAPIBackend.entities;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "orders")
@JsonPropertyOrder({"_id", "number", "status", "id_employee", "orderedProducts"})

public class Order implements Serializable{

	private static final long serialVersionUID = 5645243815748439237L;
	
	@Id
	private ObjectId _id;
	private int number;
	private Status status;
	private ObjectId id_employee;
	private List<Product> orderedProducts;
	
	public Order(ObjectId _id, int number, Status status, ObjectId id_employee, List<Product> orderedProducts) {
		super();
		this._id = _id;
		this.number = number;
		this.status = status;
		this.id_employee = id_employee;
		this.orderedProducts = orderedProducts;
	}
	
	public Order() {}

	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public String getId_Employee() {
		return id_employee.toHexString();
	}
	public void setId_Employee(ObjectId id_employee) {
		this.id_employee = id_employee;
	}
	
	@Override
	public String toString() {
		String productsId = "";
		for(Product p: orderedProducts) {
			productsId.concat(p.getId() + ", ");
		}
		return "Order id: " + _id + ", status: " + status + ", id employee who ordered: " + id_employee + ", id products: " + productsId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id_employee != other.id_employee)
			return false;
		if (orderedProducts == null) {
			if (other.orderedProducts != null)
				return false;
		} else if (!orderedProducts.equals(other.orderedProducts))
			return false;
		return true;
	}
	
}