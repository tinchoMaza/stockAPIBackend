package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public class DepositRepositoryImplem implements DepositRepository{

	/**
	 * @author fbalsas
	 */
	@Override
	public boolean checkReserveStock(Product producto) {
		if(producto.getStock() > producto.getMinReserveStock()) return true;
		return false;
	}

	@Override
	public Optional<List<Product>> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product saveProduct(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteProduct(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateProduct(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

}