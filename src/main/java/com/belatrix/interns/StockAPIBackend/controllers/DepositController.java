package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProducts() throws EmptyDepositException{
		List<Product> prods = depServ.getAllProducts();
		boolean empty = prods.isEmpty();
		if(empty) throw new EmptyDepositException("There are no items in the deposit");
		return ResponseEntity.ok(prods);
	}
	
	@GetMapping("/stock/{_id}")
	public ResponseEntity<Boolean> checkReserveStock(@PathVariable ("_id") String _id) throws ProductException{
		boolean allOk = depServ.checkReserveStock(_id);
		if(!allOk) throw new ProductException("No item found for this id: " + _id);
		return ResponseEntity.ok(allOk);
	}
	
	@GetMapping("/stock/{name}")
	public ResponseEntity<Product> findByName(@PathVariable String name) throws ProductException{
			Product prod;
		try {
			prod = depServ.findByName(name);
		}catch(ProductException ex) {
			prod = null;
		}
		return ResponseEntity.ok(prod);
	}
	
	@GetMapping("/stock/all")
	public ResponseEntity<List<Pair<String, Integer>>> showAllStock() throws EmptyDepositException{
		List<Pair<String, Integer>> allStock;
		try {
			allStock = depServ.showAllStock();
		}catch(EmptyDepositException ex) {
			allStock = null;
		}
		return ResponseEntity.ok(allStock);
	}
	

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> findById(@PathVariable ("id") String id){
		try {
			return ResponseEntity.ok(depServ.findById(id));
		} catch (ProductException e) {
			return ResponseEntity.notFound().build();
		}
    }

	@PostMapping("/")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product p){
		// no hace falta pasar el id, mongo lo asigna solo y lo devuelve solo
		return ResponseEntity.ok(depServ.saveProduct(p));
	}
	
	@PutMapping("/")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product p){
		//hay que mandarle el id, si no te crea uno nuevo con otro id
		return ResponseEntity.ok(depServ.saveProduct(p));
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String _id){
		depServ.deleteProduct(_id);
		return ResponseEntity.noContent().build();

	}
	
}
