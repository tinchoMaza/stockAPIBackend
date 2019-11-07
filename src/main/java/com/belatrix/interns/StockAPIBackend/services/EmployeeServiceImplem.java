package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.repository.EmployeeRepository;

@Service("employeeService")
@Transactional
public class EmployeeServiceImplem implements EmployeeService {
	
	private EmployeeRepository empRepo;
	
	@Autowired
	public EmployeeServiceImplem(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	public List<Employee> getAllEmployees() {
		Optional<List<Employee>> emps = empRepo.getAllEmployees();
		return emps.get();
	}

	public Employee findById(String id) throws EmployeeException {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp.isPresent())
			return emp.get();
		else
			throw new EmployeeException("Employee with id: " + id + " not found. Please try another id.");
	}

	public Employee findByName(String name) throws EmployeeException {
		Optional<Employee> emp = empRepo.findByName(name);
		if (emp.isPresent())
			return emp.get();
		else
			throw new EmployeeException("Employee with name: " + name + " not found. Please try another name.");
	}

	public List<Employee> findByArea(String area) throws EmployeeException {
		Optional<List<Employee>> emp = empRepo.findByArea(area);
		if (emp.isPresent())
			return emp.get();
		else
			throw new EmployeeException("Employees with area: " + area + " not found. Please try another area.");
	}

	public Employee saveEmployee(Employee e) {
		return empRepo.saveEmployee(e);
	}

	public void deleteEmployee(String id) {
		empRepo.deleteEmployee(id);
	}

	public void updateEmployee(Employee e) {
		empRepo.updateEmployee(e);
	}

	@Override
	public Employee findByMail(String mail) throws EmployeeException {
		Optional<Employee> e = empRepo.findByMail(mail);
		if(e.isPresent()) return e.get();
		throw new EmployeeException("Employee with mail: " + mail + " not found. Please try again");
	}

}
