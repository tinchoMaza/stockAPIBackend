package com.belatrix.interns.StockAPIBackend.paramValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Supplier;

/**
 * @author aluna
 *
 */
public final class ParamValidator {
	
	//Attribute used for the mail validation
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	/**
	 * @param toExamine
	 * @return
	 */
	public static boolean containsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°,.:;/!$~#@*+%&()=¿{}<>\\[\\\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	/**
	 * @param toExamine
	 * @return
	 */
	public static boolean descriptionContainsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°!$~#@*+%&=¿{}<>\\[\\\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	/**
	 * @param emailStr
	 * @return
	 */	
	public static boolean emailIsCorrect(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
	/**
	 * @param prod
	 * @return
	 */
	public static List<String> productFieldsValidator(Product prod) {
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
		
		if (prod.getMinReserveStock() < 0) {
			messages.add("Invalid value for reserve stock, it can´t be less than 0");
		}
		
		return messages;
	}
	
	/**
	 * @param order
	 * @return
	 */
	public static List<String> orderParamsValidator(Order order){
		List<String> msg = new ArrayList<String>();
		
		
		if(!Optional.ofNullable(order.getIdEmployee()).isPresent()) {
			msg.add("EmployeeID not found");
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
	/**
	 * @param supplier
	 * @return
	 */
	public static List<String> supplierParamsValidation(Supplier supplier){
		List<String> msg = new ArrayList<String>();
		if(!emailIsCorrect(supplier.getMail()) || (supplier.getMail().compareTo("") == 0)) {
			msg.add("The email provided for the supplier is invalid or empty.");
		}
		if((supplier.getName().compareTo("") == 0)) {
			msg.add("The name of the supplier is empty.");
		}
		if( containsIllegalsCharacters(supplier.getName())) {
			msg.add("The name of the supplier contains illegal characters.");
		}
		if((supplier.getWebUrl().compareTo("") == 0)) {
			msg.add("The webUrl of the supplier is empty.");
		}
		if(supplier.getPhoneNumber().compareTo("") == 0) {
			msg.add("The phone number is empty");
		}
		return msg;
	}
}
