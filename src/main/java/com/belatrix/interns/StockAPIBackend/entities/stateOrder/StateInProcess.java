/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.entities.stateOrder;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;

/**
 * @author aluna
 *
 */
public class StateInProcess implements StateOrder {

	/**
	 * 
	 */
	public StateInProcess() {
		super();
	}
	
	

	@Override
	public String toString() {
		return "StateInProcess";
	}



	@Override
	public void accept(Order order) throws StateOrderException {
		order.setStatus(new StateAccepted());
	}

	@Override
	public void reject(Order order) throws StateOrderException {
		order.setStatus(new StateRejected());
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		order.setStatus(new StateCancelled());
	}

	@Override
	public void update(Order order) throws StateOrderException {
		order.setStatus(new StateOnHold());
	}

	public StateOrder getState() {
		return new StateInProcess();
	}

	public String getStateOrderString() {
		return "IN_PROCESS";
	}

}