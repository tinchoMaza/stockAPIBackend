package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.dbo.OrderDBO;
import com.belatrix.interns.StockAPIBackend.entities.Order;

public interface OrderRepository {

	public Optional<List<OrderDBO>> getAllOrders();
	public Optional<OrderDBO> findById(String id);
	public Optional<OrderDBO> saveOrder(Order o);
	public Optional<OrderDBO> updateOrder(Order o);
	public void deleteOrder(String _id);
	public Optional<OrderDBO> findByNumber(int number);
}
