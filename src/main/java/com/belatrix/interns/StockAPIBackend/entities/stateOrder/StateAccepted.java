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
	public void accept() {
	}

	@Override
	public void reject(Order order){
		order.setStatus(new StateRejected());
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been accepted by Admin, so the delivery is arranged. You can not cancel it.");
		throw new StateOrderException(message);

	}

	@Override
	public void update() throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been accepted by Admin, so the delivery is arranged. You can create a new order with the new data, but the old one will arrive");
		throw new StateOrderException(message);

	}

}
