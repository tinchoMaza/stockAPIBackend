package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.belatrix.interns.StockAPIBackend.entities.Order;

public interface OrdersRepository {
	
	public void save(Order order);
	
	public void delete(ObjectId id);
	
	public List<Order> findAll();
	
	public Optional<Order> findById(ObjectId id);
	
	public void update(ObjectId orderId, Order lastOrder);
	
}
