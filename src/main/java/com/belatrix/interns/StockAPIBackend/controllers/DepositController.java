package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.EmptyDepositException;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.services.DepositService;

@RestController
@RequestMapping("/stockAPI/deposit")
public class DepositController {
	
	private final DepositService depServ;
	
	@Autowired
	public DepositController(DepositService depServ) {
		this.depServ = depServ;
	}
	
	@GetMapping("/products/stock")
	public ResponseEntity<List<Product>> showProductsWithLowStock() {
		List<Product> lowStockProducts = this.depServ.showProductsWithLowStock();
		return ResponseEntity.ok(lowStockProducts);
	}
	
	@GetMapping("/products/{_id}/stock")
	public int showStockOfAProduct(@PathVariable String _id) {
		return this.depServ.showStockOfAProduct(_id);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProducts() throws EmptyDepositException{
		List<Product> prods = depServ.getAllProducts();
		boolean empty = prods.isEmpty();
		if(empty) throw new EmptyDepositException("There are no items in the deposit");
		return ResponseEntity.ok(prods);
	}
	
	@GetMapping("/reserve-stock/{_id}")
	public ResponseEntity<Boolean> checkReserveStock(@PathVariable ("_id") String _id) throws ProductException{
		boolean allOk = depServ.checkReserveStock(_id);
		if(!allOk) throw new ProductException("No item found for this id: " + _id);
		return ResponseEntity.ok(allOk);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Product> findByName(@PathVariable String name) throws ProductException{
			Product prod;
		try {
			prod = depServ.findByName(name);
			return ResponseEntity.ok(prod);
		}catch(ProductException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/remaining-stock")
	public ResponseEntity<List<Pair<String, Integer>>> showAllStock() throws EmptyDepositException{
		List<Pair<String, Integer>> allStock;
		try {
			allStock = depServ.showAllStock();
		}catch(EmptyDepositException ex) {
			allStock = null;
		}
		return ResponseEntity.ok(allStock);
	}
	

	@GetMapping("/products/{_id}")
	public ResponseEntity<Product> findById(@PathVariable ("_id") String _id){
		try {
			return ResponseEntity.ok(depServ.findById(_id));
		} catch (ProductException e) {
			return ResponseEntity.notFound().build();
		}
    }

	@PostMapping("/products")
	public ResponseEntity<List<String>> saveProduct(@RequestBody @Valid Product p) throws InvalidDataException{
		try {
			depServ.saveProduct(p);
			List<String> added = new ArrayList<String>();
			added.add("New product succesfuly added");
			return ResponseEntity.ok().body(added);
		}catch(InvalidDataException ex) {
			List<String> messages = new ArrayList<String>();
			messages.add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
		}
		
	}
	
	@PutMapping("/products/{_id}")
	public ResponseEntity<List<String>> updateProduct(@RequestBody @Valid Product p, @PathVariable ("_id") String _id) throws InvalidDataException, ProductException{
		try {
			@SuppressWarnings("unused")
			Product aux = this.depServ.findById(_id);
		}catch(ProductException e){
			List<String>error = new ArrayList<String>();
			error.add(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		try {
			depServ.updateProduct(_id, p);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			List<String> messages = new ArrayList<String>();
			messages.add(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
		}
	}
	
	@DeleteMapping("/products/{_id}")
	public ResponseEntity<ProductException> deleteProduct(@PathVariable ("_id") String _id) throws ProductException{
		try {
			depServ.deleteProduct(_id);
			return ResponseEntity.noContent().build();
		}catch(ProductException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
		

	}
	
}
