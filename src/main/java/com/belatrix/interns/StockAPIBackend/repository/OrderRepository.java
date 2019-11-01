package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Order;

public interface OrderRepository {

	public Optional<List<Order>> getAllOrders();
	public Optional<Order> findById(String id);
	public Order saveOrder(Order o);
}
