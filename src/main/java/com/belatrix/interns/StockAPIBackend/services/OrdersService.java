package com.belatrix.interns.StockAPIBackend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Status;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.entities.Email;

public interface OrdersService {
	
	public boolean sendEmail(Email email);

	public void save(ObjectId empId, List<Product> orderedProds, Status status, Date arrival, Optional<Date> departure) throws InvalidDataException;
	
	public List<Order> findAll();
	
	public Order findById(ObjectId id) throws OrderException;
	
	public void update(ObjectId toUpdateOrd, Order latestOrder) throws InvalidDataException;
	
	public void delete(ObjectId id) throws OrderException;
}
