package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Employee;

public interface EmployeeRepository {
	
	Optional <List<Employee>> getAllEmployees();
	
	Optional<Employee> findById(String id);
	
	Optional<Employee> findByName(String name);
	
	Optional<List<Employee>> findByArea (String area);
	
	public Employee saveEmployee(Employee e);
	
	public void deleteEmployee(String id);
	
	public void updateEmployee(Employee e);

}