package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.repository.EmployeeRepository;
import com.belatrix.interns.StockAPIBackend.paramValidations.*;

@Service("employeeService")
@Transactional
public class EmployeeServiceImplem implements EmployeeService {
	
	private EmployeeRepository empRepo;
	
	@Autowired
	public EmployeeServiceImplem(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	@Override
	public List<Employee> getAllEmployees() {
		Optional<List<Employee>> emps = empRepo.getAllEmployees();
		return emps.get();
	}

	@Override
	public Employee findById(String id) throws EmployeeException {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp.isPresent()) return emp.get();
		throw new EmployeeException("Employee with id: " + id + " not found. Please try another id.");
	}

	@Override
	public Employee findByName(String name) throws EmployeeException {
		Optional<Employee> emp = empRepo.findByName(name);
		if (emp.isPresent())
			return emp.get();
		else
			throw new EmployeeException("Employee with name: " + name + " not found. Please try another name.");
	}

	@Override
	public List<Employee> findByArea(String area) throws EmployeeException {
		Optional<List<Employee>> emp = empRepo.findByArea(area);
		if (!emp.get().isEmpty()) return emp.get();
		throw new EmployeeException("Employees with area: " + area + " not found. Please try another area.");
	}

	@Override
	public Employee saveEmployee(Employee e) throws InvalidDataException {
		List<String> msgs = paramValidations.empParamsValidator(e); 
		if(!msgs.isEmpty()) throw new InvalidDataException(msgs);
		return empRepo.saveEmployee(e);
	}

	@Override
	public void deleteEmployee(String id) throws EmployeeException{
		Optional<Employee> e = this.empRepo.findById(id);
		if(!e.isPresent()) throw new EmployeeException("Employee with this ID is not found in DataBase");
		empRepo.deleteEmployee(id);
	}

	@Override
	public void updateEmployee(String id, Employee e) throws InvalidDataException {
		List<String> msgs = paramValidations.empParamsValidator(e);
		if(!msgs.isEmpty()) throw new InvalidDataException(msgs);
		empRepo.updateEmployee(id, e);
	}

	@Override
	public Employee findByMail(String mail) throws EmployeeException {
		Optional<Employee> e = empRepo.findByMail(mail);
		if(e.isPresent()) return e.get();
		throw new EmployeeException("Employee with mail: " + mail + " not found. Please try again");
	}

}
