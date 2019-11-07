package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Admin;
import com.belatrix.interns.StockAPIBackend.entities.Order;

public interface AdminRepository {
	
	Optional<Admin> findById(String id);
	
	Optional<Admin> findByName(String name);
	
	Optional<Admin> findByMail(String mail);
	
	public Admin save(Admin a);
	
	public void delete(String id);
	
	public void update(String id, Admin updatedAdm);
	
	public void storeOrdersToBuy(Admin a, List<Order> ordersToBuy);
	
	public List<Order> showOrdersToBuy(Admin a);
	
}