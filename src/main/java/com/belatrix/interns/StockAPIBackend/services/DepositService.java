package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;

/**
 * @author fbalsas
 */

public interface DepositService {

	List<Product> getAllProducts();
	
	Product findById(String id) throws ProductException;
	
	Product findByName(String name) throws ProductException;
	
	public Product saveProduct(Product p);
	
	public void deleteProduct(String id);
	
	public void updateProduct(Product p);
	
	public boolean checkReserveStock(String id) throws ProductException;
}
