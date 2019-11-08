/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.belatrix.interns.StockAPIBackend.entities.Supplier;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.SupplierException;
import com.belatrix.interns.StockAPIBackend.services.SuppliersService;

/**
 * @author aluna
 *
 */
@RestController
@RequestMapping("/stockAPI/suppliers")
public class SuppliersController {

    private final SuppliersService supService;
	
	@Autowired
	public SuppliersController(SuppliersService suppService) {
		this.supService = suppService;
	}

	@PostMapping("")
	public ResponseEntity<List<String>> save(@RequestBody Supplier newSupplier){
		try {
			this.supService.saveSupplier(newSupplier);
			return ResponseEntity.ok().build();
		}catch(InvalidDataException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessages());
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Supplier>> findAll(){
		List<Supplier> suppliers = this.supService.findAll();
		return ResponseEntity.ok(suppliers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Supplier> findById(@PathVariable String id){
		try {
			Supplier supplier = this.supService.findById(id);
			return ResponseEntity.ok().body(supplier);
		}catch(SupplierException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Supplier> findByName(@PathVariable String name){
		try {
			Supplier supplier = this.supService.findByName(name);
			return ResponseEntity.ok().body(supplier);
		}catch(SupplierException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<String>> update(@PathVariable String id, @RequestBody Supplier newData){
		try {
			this.supService.update(id, newData);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			List<String> msgs = ex.getMessages();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgs);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<String>> delete(@PathVariable String id){
		try {
			this.supService.delete(id);
			return ResponseEntity.ok().body(new ArrayList<String>());	
		}catch(SupplierException ex) {
			List<String> msgs = new ArrayList<String>();
			msgs.add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgs);
		}
	}

}
