package com.belatrix.interns.StockAPIBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belatrix.interns.StockAPIBackend.services.SupplierService;

@RestController
@RequestMapping("/stockAPI/suppliers")
public class SupplierController {
	
	private final SupplierService supServ;
	
	@Autowired
	public SupplierController(SupplierService supServ) {
		this.supServ = supServ;
	}

}
