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
public class StateAccepted implements StateOrder {

	/**
	 * 
	 */
	public StateAccepted() {
		super();
	}

	@Override
	public void accept(Order order) throws StateOrderException {
		//I use this method to complete an accepted order
		order.setStatus(new StateCompleted());
	}

	@Override
	public void reject(Order order) throws StateOrderException {
		//This can change in case we decide to start handling
		List<String> message = new ArrayList<String>();
		message.add("The order has already been accepted and is about to be dispatched, you can not reject it.");
		throw new StateOrderException(message);
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		//This can change in case we decide to start handling
		List<String> message = new ArrayList<String>();
		message.add("The order has already been accepted and is about to be dispatched, you can not cancel it.");
		throw new StateOrderException(message);
	}

	@Override
	public void update(Order order) throws StateOrderException {
		//This can change in case we decide to start handling
		List<String> message = new ArrayList<String>();
		message.add("The order has already been accepted and is about to be dispatched, you can not update it.");
		throw new StateOrderException(message);
	}

}
