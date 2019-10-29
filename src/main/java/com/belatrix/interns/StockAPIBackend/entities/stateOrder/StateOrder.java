package com.belatrix.interns.StockAPIBackend.entities.stateOrder;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;

/**
 * @author fbalsas
 */

public interface StateOrder {

	public void accept() throws StateOrderException;
	
	public void reject(Order order);
	
	public void cancel(Order order) throws StateOrderException;
	
	public void update() throws StateOrderException;
	
}
