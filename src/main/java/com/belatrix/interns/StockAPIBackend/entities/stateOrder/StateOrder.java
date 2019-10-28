package com.belatrix.interns.StockAPIBackend.entities.stateOrder;

import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;

/**
 * @author fbalsas
 */

public interface StateOrder {

	public void accept();
	
	public void reject();
	
	public void cancel() throws StateOrderException;
	
	public void update() throws StateOrderException;
	
}
