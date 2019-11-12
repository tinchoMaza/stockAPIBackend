package com.belatrix.interns.StockAPIBackend.paramValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateAccepted;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOnHold;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateRejected;

public final class paramValidations {

	private paramValidations() {
		
	}
	
	/**
	 * @param toExamine
	 * @return
	 */
	public boolean containsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[0123456789½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°,.:;/!$~#@*+%&()=¿{}<>\\[\\\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	
	/**
	 * @param toExamine
	 * @return
	 */
	public boolean descriptionContainsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°!$~#@*+%&=¿{}<>\\[\\\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	
	public List<String> productFieldsValidator(Product prod) {
		// Checks if the fields of the kinship are null or not
		List<String> messages = new ArrayList<String>();
		
		if ((prod.getName().compareTo("") == 0) || containsIllegalsCharacters(prod.getName())) {
			messages.add("Invalid/Empty name field for product");
		}
		
		if ((prod.getDescription().compareTo("") == 0) || descriptionContainsIllegalsCharacters(prod.getDescription())) {
			messages.add("Empty/Invalid characters in product´s description");
		} 
		
		if (prod.getStock() < 0) {
			messages.add("Invalid value for stock, it can´t be less than 0");
		}
		
		if (prod.getMin_reserve_stock() < 0) {
			messages.add("Invalid value for reserve stock, it can´t be less than 0");
		}
		
		return messages;
	}
	
	public List<String> orderParamsValidator(Order order){
		List<String> msg = new ArrayList<String>();
		StateOrder status = order.getStatus();
		if((!(status.equals(new StateAccepted()))) && (!(status.equals(new StateOnHold()))) && (!(status.equals(new StateRejected())))) {
			msg.add("Invalid status report");
		}
		if(!Optional.ofNullable(order.getEmployee()).isPresent()) {
			msg.add("Employee not found");
		}
		if(order.getOrderedProducts().isEmpty()) {
			msg.add("No order has been made");
		}
		if(Optional.ofNullable(order.getDeparture_date()).isPresent()) {
			if(order.getArrival_date().compareTo(order.getDeparture_date()) > 0) {
				msg.add("Invalid departure date");
			}
		}
		return msg;
	}
}
