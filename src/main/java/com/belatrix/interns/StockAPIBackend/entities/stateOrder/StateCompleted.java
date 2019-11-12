/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.entities.stateOrder;

import java.util.ArrayList;
import java.util.List;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;

/**
 * @author aluna
 *
 */
public class StateCompleted implements StateOrder {

	/**
	 * 
	 */
	public StateCompleted() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return "StateCompleted";
	}



	@Override
	public void accept(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been dispatched, it is completed.");
		throw new StateOrderException(message);
		
	}

	@Override
	public void reject(Order order) throws StateOrderException{
		List<String> message = new ArrayList<String>();
		message.add("The order has already been dispatched.");
		throw new StateOrderException(message);
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been dispatched.");
		throw new StateOrderException(message);

	}

	@Override
	public void update(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been dispatched.");
		throw new StateOrderException(message);

	}

	public StateOrder getState() {
		return new StateCompleted();
	}

	public String getStateOrderString() {
		return "COMPLETED";
	}

}