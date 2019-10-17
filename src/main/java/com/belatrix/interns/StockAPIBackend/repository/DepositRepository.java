package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Pair;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public interface DepositRepository{
	
	public boolean checkReserveStock(Product producto);
	
	public int showStockOfAProduct(String id);

	public Optional<List<Product>> getAllProducts();

	public Optional<Product> findById(String id);

	public Optional<Product> findByName(String name);
	
	public List<Pair<String, Integer>> showAllStock();

	public Product saveProduct(Product p);

	public void deleteProduct(String id);

	public void updateProduct(String id, Product newProductInfo);
	
	public List<Product> showProductsWithLowStock();
	
}