package com.belatrix.interns.StockAPIBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.dto.OrderDTO;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Status;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.repository.OrderRepository;

@Service("orderService")
@Transactional
public class OrderServiceImplem implements OrderService{

	private OrderRepository ordRepo;
	private DepositService depositService;

	@Autowired
	public OrderServiceImplem(OrderRepository ordRepo, DepositService depositService) {
		this.ordRepo = ordRepo;
		this.depositService = depositService;
	}

	public List<Order> getAllOrders() {
		Optional<List<Order>> orders = ordRepo.getAllOrders();
		if(orders.isPresent())
			return orders.get();
		else return new ArrayList<Order>(); //como es un getAll, si no hay orders devuelvo un array vacio
	}

	public Order findById(String id) throws OrderException{
		Optional<Order> order = ordRepo.findById(id);
		if (order.isPresent())
			return order.get();
		else
			throw new OrderException("Order with id: " + id + " not found. Please try another id.");
	}

	public Order saveOrder(OrderDTO ord) throws ProductException {
		Order o = new Order();
		for (String p : ord.getOrderedProducts()) {
			o.addProductToOrder(new ObjectId(p));
		}
		return this.ordRepo.saveOrder(o);
	}

	public boolean existsAnother(Order o) {
		List<Order> orders = this.getAllOrders();
		for (Order ord : orders) {
			if(o.equals(ord) && (!ord.getStatus().equals(Status.Completed)))
				return true;
		}
		//recorri todas las ordenes y no hay ninguna igual en progreso
		return false;
	}

}