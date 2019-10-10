package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	Employee findById(String id) throws EmployeeException;
	Employee findByName(String name) throws EmployeeException;
	List<Employee> findByArea (String area) throws EmployeeException;
	public Employee saveEmployee(Employee e);
	public void deleteEmployee(String id);
	public void updateEmployee(Employee e);
}