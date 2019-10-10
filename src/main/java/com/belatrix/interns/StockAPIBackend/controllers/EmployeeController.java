package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.services.EmployeeService;

@RestController
@RequestMapping("/stockAPI")
public class EmployeeController {
	
	private final EmployeeService empServ;
	private Employee e;
	
	@Autowired
	public EmployeeController(EmployeeService empService) {
		this.empServ = empService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(empServ.getAllEmployees());
	}
	
	@GetMapping("/{_id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("_id") String _id) throws EmployeeException{
		try {
			e = empServ.findById(_id);
		}
		catch(EmployeeException ex) {
			e = null;
		}
		return ResponseEntity.ok(e);
	}
	
	@GetMapping("/area/{area}")
	public ResponseEntity<List<Employee>> getEmployeeByArea(@PathVariable("area") String area) throws EmployeeException{
		List<Employee> emps = new ArrayList<Employee>();
		try {
			emps = empServ.findByArea(area);
		}
		catch(EmployeeException ex) {
			emps = null;
		}
		return ResponseEntity.ok(emps);
	}
	
	@PostMapping("/")
	public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee e){
		// no hace falta pasar el id, mongo lo asigna solo y lo devuelve solo
		return ResponseEntity.ok(empServ.saveEmployee(e));
	}
	
	@PutMapping("/")
	public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee e){
		//hay que mandarle el id, si no te crea uno nuevo con otro id
		return ResponseEntity.ok(empServ.saveEmployee(e));
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String _id){
		empServ.deleteEmployee(_id);
		return ResponseEntity.noContent().build();
	}

}