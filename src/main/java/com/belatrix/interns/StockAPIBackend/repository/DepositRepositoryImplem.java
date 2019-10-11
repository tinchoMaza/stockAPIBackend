package com.belatrix.interns.StockAPIBackend.repository;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public class DepositRepositoryImplem implements DepositRepository{

	/**
	 * @author fbalsas
	 */
	@Override
	public boolean existReserveStock(Product producto) {
		if(producto.getStock() > producto.getMinReserveStock()) return true;
		return false;
	}

}