package com.belatrix.interns.StockAPIBackend.entities.stateOrder;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;

/**
 * @author fbalsas
 */

public interface StateOrder {

	public void accept(Order order) throws StateOrderException;
	
	public void reject(Order order) throws StateOrderException;
	
	public void cancel(Order order) throws StateOrderException;
	
	public void update(Order order) throws StateOrderException;
	
}
