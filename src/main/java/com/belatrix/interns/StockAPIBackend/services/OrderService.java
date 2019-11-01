package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;


import com.belatrix.interns.StockAPIBackend.entities.Order;

public interface OrderService {
	
	List<Order> getAllOrders();
	public Order findById(String id);
	public Order saveOrder(Order o);
	boolean existsAnother( Order o);
}
