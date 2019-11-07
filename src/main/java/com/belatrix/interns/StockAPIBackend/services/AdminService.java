package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;

import com.belatrix.interns.StockAPIBackend.entities.Admin;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;

public interface AdminService {
	
	Admin findById(String id) throws InvalidDataException;
	
	Admin findByName(String name) throws InvalidDataException;
	
	Admin findByMail(String mail) throws InvalidDataException;
	
	public Admin save(Admin a);
	
	public void delete(String id) throws InvalidDataException;
	
	public void update(String id, Admin updatedAdm) throws InvalidDataException;
	
	public void storeOrdersToBuy(String id, List<Order> ordersToBuy) throws InvalidDataException;
	
	public List<Order> showOrdersToBuy(String id) throws InvalidDataException;

}