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
public class StateCancelled implements StateOrder {

	/**
	 * 
	 */
	public StateCancelled() {
		super();
	}
	
	@Override
	public String toString() {
		return "StateCancelled";
	}

	@Override
	public void accept(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been cancelled by the client.");
		throw new StateOrderException(message);
	}


	

	@Override
	public void reject(Order order) throws StateOrderException {
		//Cuando el cliente la cancela, yo diría de directamente borrarla y ver si se puede avisar
		//al admin hablando al mismo mail en el cual se le avisa que se creó, en vez de
		//cancelarla, no avisarle al admin, y que cuando él/ella presione botón de rechazar o aceptar 
		//le salga un cartel recién ahí de que ha sido borrada. Mientras tanto implemento la última
		//opción mencionada en este párrafo
		List<String> message = new ArrayList<String>();
		message.add("The order has already been cancelled.");
		throw new StateOrderException(message);
	}

	@Override
	public void cancel(Order order) throws StateOrderException {
		//There is no logic. The order has already been cancelled so it is in that state now
	}
	
	@Override
	public void update(Order order) throws StateOrderException {
		List<String> message = new ArrayList<String>();
		message.add("The order has already been cancelled, you can not update it.");
		throw new StateOrderException(message);

	}

	public StateOrder getState() {
		return new StateCancelled();
	}

	public String getStateOrderString() {
		return "CANCELLED";
	}

}