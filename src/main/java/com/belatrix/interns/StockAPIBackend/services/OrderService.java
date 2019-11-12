package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;

import com.belatrix.interns.StockAPIBackend.dto.OrderDTO;
import com.belatrix.interns.StockAPIBackend.dto.OrderView;
import com.belatrix.interns.StockAPIBackend.entities.Email;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;

public interface OrderService {
	
	List<OrderDTO> getAllOrders() throws ProductException, EmployeeException;
	public OrderDTO findById(String id) throws OrderException, ProductException, EmployeeException;
	public OrderDTO saveOrder(OrderView o) throws ProductException, Exception;
	boolean existsAnother(OrderView o) throws ProductException, EmployeeException;
	public void deleteOrder(String _id);
	public boolean sendEmail(Email email);
	public OrderDTO acceptOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException;
	public OrderDTO rejectOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException;
	public OrderDTO cancelOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException;
	public OrderDTO updateOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException;
	OrderDTO findByNumber(int number) throws OrderException, ProductException, EmployeeException;
}
