package com.belatrix.interns.StockAPIBackend.repository;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public interface DepositRepository{
	
	public boolean existReserveStock(Product producto);
	
}