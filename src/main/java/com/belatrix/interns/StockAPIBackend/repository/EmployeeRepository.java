package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.entities.Order;

public interface EmployeeRepository {
	
	public Optional <List<Employee>> getAllEmployees();
	
	public Optional<Employee> findById(String id);
	
	public Optional<Employee> findByName(String name);
	
	public Optional<List<Employee>> findByArea (String area);
	
	public Optional<Employee> findByMail(String mail);
	
	public Employee saveEmployee(Employee e);
	
	public void deleteEmployee(String id);
	
	public void updateEmployee(String id, Employee e);
	
	public Optional<List<Order>> inspectOrders(Employee e);

}