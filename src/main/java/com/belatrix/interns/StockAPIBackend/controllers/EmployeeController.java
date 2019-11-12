package com.belatrix.interns.StockAPIBackend.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
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
import com.belatrix.interns.StockAPIBackend.utils.Validations;

@RestController
@RequestMapping("/stockAPI/employees")
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
	
	@PostMapping(path = "/", consumes = "application/json", produces = {"application/json"})
	public ResponseEntity<Serializable> saveEmployee(@RequestBody @Valid Employee e) {
		// no hace falta pasar el id, mongo lo asigna solo y lo devuelve solo
		//si lo hacemos por capas debidamente, el objeto que recibe es un employee dto y no el objeto de negocio
		if(!Validations.validate(e.getMail()) || e.getMail().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is not in a valid format or is missing.");
		}
		if(e.getArea().isEmpty() || e.getNombre().isEmpty() || e.getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Area, Name or password is empty.");
		}
		Employee em;
		try {
			em = empServ.saveEmployee(e);
			return ResponseEntity.status(HttpStatus.CREATED).body(em);
		} catch (DuplicateKeyException ex) {
			//unica posible duplicate key es el mail definido como unique en mongo
			return ResponseEntity.status(HttpStatus.CONFLICT).body("The indicated email is already registered with another employee");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("We did something wrong. We'll be notified and we'll look into it. This is the error: " + ex.getMessage());
		}
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