package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;

import org.springframework.data.util.Pair;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.EmptyDepositException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;

/**
 * @author fbalsas
 */

public interface DepositService {

	public List<Product> getAllProducts();
	
	public Product findById(String id) throws ProductException;
	
	public Product findByName(String name) throws ProductException;
	
	public List<Pair<String, Integer>> showAllStock() throws EmptyDepositException;
	
	public Product saveProduct(Product p);
	
	public void deleteProduct(String id);
	
	public void updateProduct(Product p);
	
	public boolean checkReserveStock(String id) throws ProductException;
}
