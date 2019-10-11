package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public interface DepositRepository{
	
	public boolean checkReserveStock(Product producto);

	public Optional<List<Product>> getAllProducts();

	public Optional<Product> findById(String id);

	public Optional<Product> findByName(String name);

	public Product saveProduct(Product p);

	public Object deleteProduct(String id);

	public Object updateProduct(Product p);
	
}