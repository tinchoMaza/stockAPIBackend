package com.belatrix.interns.StockAPIBackend.dao;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public interface DepositDAO{
	
	public boolean existReserveStock(Product producto);
	
}