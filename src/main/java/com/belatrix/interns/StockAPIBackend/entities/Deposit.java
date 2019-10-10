package com.belatrix.interns.StockAPIBackend.entities;

import java.util.List;

/**
 * @author fbalsas
 */

public class Deposit {
	private List<Product> stockedProducts;

	public List<Product> getStockedProducts() {
		return stockedProducts;
	}

	public void setStockedProducts(List<Product> stockedProducts) {
		this.stockedProducts = stockedProducts;
	}
}
