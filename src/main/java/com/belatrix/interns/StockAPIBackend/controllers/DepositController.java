package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.services.DepositService;

@RestController
@RequestMapping("/stockAPI")
public class DepositController {
	
	private final DepositService depServ;
	private Product p;
	
	@Autowired
	public DepositController(DepositService depServ) {
		this.depServ = depServ;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok(depServ.getAllProducts());
	}
	
	@GetMapping("/stock/{_id}")
	public ResponseEntity<Boolean> checkReserveStock(@PathVariable ("_id") String _id) throws ProductException{
		return ResponseEntity.ok(depServ.checkReserveStock(_id));
	}
	
}
