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
public class StateRejected implements StateOrder {

	/**
	 * 
	 */
	public StateRejected() {
		super();
	}

	@Override
	public void accept() throws StateOrderException{
		List<String> message = new ArrayList<String>();
		message.add("The order has already been rejected by Admin, action that can not be undone.");
		throw new StateOrderException(message);
	}


	@Override
	public void update() throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been rejected by Admin, action that can not be undone.");
		throw new StateOrderException(message);
	}

	@Override
	public void reject(Order order) {
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been rejected by Admin.");
		throw new StateOrderException(message);
	}

}
