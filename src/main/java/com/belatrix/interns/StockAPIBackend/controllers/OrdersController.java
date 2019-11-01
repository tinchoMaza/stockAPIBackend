package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.NoOrdersException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.services.OrdersService;

@RestController
@RequestMapping("/stockAPI/orders")
public class OrdersController {
	
	private final OrdersService ordService;
	
	@Autowired
	public OrdersController(OrdersService ordService) {
		this.ordService = ordService;
	}

	@PostMapping("")
	public ResponseEntity<InvalidDataException> save(@RequestBody @PathVariable ObjectId empId, List<Product> orderedProds, StateOrder status, Date arrival, Optional<Date> departure){
		try {
			this.ordService.save(empId, orderedProds, status, arrival, departure);
			return ResponseEntity.ok().build();
		}catch(InvalidDataException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Order>> findAll() throws NoOrdersException{
		List<Order> orders = this.ordService.findAll();
		if(orders.isEmpty()) throw new NoOrdersException("There are no orders to show");
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> findById(@PathVariable ObjectId id){
		try {
			Order ord = this.ordService.findById(id);
			return ResponseEntity.ok().body(ord);
		}catch(OrderException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<List<String>> update(@PathVariable ObjectId id, Order ltsOrder){
		try {
			this.ordService.update(id, ltsOrder);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			List<String> msgs = new ArrayList<String>();
			msgs = ex.getMessages();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgs);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<List<String>> delete(@PathVariable ObjectId id){
		try {
			this.ordService.delete(id);
			return ResponseEntity.ok().body(new ArrayList<String>());	
		}catch(OrderException ex) {
			List<String> msgs = new ArrayList<String>();
			msgs.add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgs);
		}
	}
	
}
