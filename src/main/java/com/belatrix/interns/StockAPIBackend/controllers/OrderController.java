package com.belatrix.interns.StockAPIBackend.controllers;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belatrix.interns.StockAPIBackend.dto.OrderDTO;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.services.OrderService;

@RestController
@RequestMapping("/stockAPI/orders")
public class OrderController {
	
	private final OrderService ordService;
	private Order o;
	
	@Autowired
	public OrderController(OrderService ordService) {
		this.ordService = ordService;
	}

	@GetMapping(path = "/", consumes = "application/json", produces = {"application/json"})
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = this.ordService.getAllOrders();
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
	
	@GetMapping(path = "/{_id}", produces = {"application/json"})
	public ResponseEntity<Serializable> getOrderById(@PathVariable("_id") String _id){
		try {
			o = ordService.findById(_id);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		}
		catch(OrderException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
	
	@PostMapping(path = "/", consumes = "application/json", produces = {"application/json"})
	public ResponseEntity<Serializable> saveOrder (@RequestBody @Valid OrderDTO o) {
		if(o.getId_Employee().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee id is missing.");
		}
		if(o.getOrderedProducts().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must add at least 1 product to the order.");
		}
		if(ordService.existsAnother(o.toDomain())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("There is another identical order in progress for the same employee with the same products");
		}
		try {
			Order ord = ordService.saveOrder(o);
			return ResponseEntity.status(HttpStatus.CREATED).body(ord);
		}  catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
	
}
