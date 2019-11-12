/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.entities.stateOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;
import com.belatrix.interns.StockAPIBackend.repository.DepositRepository;
import com.belatrix.interns.StockAPIBackend.repository.DepositRepositoryImplem;


/**
 * @author aluna
 *
 */
public class StateOnHold implements StateOrder {
	
	private DepositRepository depRepo;
	
	@Autowired
	public StateOnHold(DepositRepository depRepo) {
		this.depRepo = depRepo;
	}

	public StateOnHold() {
		super();
		this.depRepo = new DepositRepositoryImplem(null);
	}

	@Override
	public void accept(Order order) throws StateOrderException {
		List<Product> products = order.getOrderedProducts();
		if(products != null && (!products.isEmpty()))
			if(products.stream().anyMatch(prod -> !depRepo.checkReserveStock(prod))) {
				order.setStatus(new StateInProcess());
			}
			else {
				order.setStatus(new StateAccepted());
			}
	}


	@Override
	public void reject(Order order) {
		order.setStatus(new StateRejected());
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		order.setStatus(new StateCancelled());
	}



	@Override
	public void update(Order order) throws StateOrderException {
		//You do not do anything. If the product is updated it should go to the first/inicial state
		//which is the one you are now, so there is no logic for update method in this state.
	}

	@Override
	public StateOrder getState() {
		return new StateOnHold();
	}

	@Override
	public String getStateOrderString() {
		return "ON_HOLD";
	}

}
