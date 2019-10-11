package com.belatrix.interns.StockAPIBackend.dao;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

public class DepositDAOImpl implements DepositDAO{

	/**
	 * @author fbalsas
	 */
	@Override
	public boolean existReserveStock(Product producto) {
		if(producto.getStock() > producto.getMinReserveStock()) return true;
		return false;
	}

}