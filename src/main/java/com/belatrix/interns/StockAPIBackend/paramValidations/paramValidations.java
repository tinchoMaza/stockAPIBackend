package com.belatrix.interns.StockAPIBackend.paramValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.belatrix.interns.StockAPIBackend.entities.Admin;
import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Status;

public final class paramValidations {
	
	/**
	 * @param toExamine
	 * @return
	 */
	public static boolean nameContainsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[0123456789½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°,.:;/!$~#@*+%&()=¿{}<>\\[\\\\]|\"\\_^]");
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
	
	public static boolean mailContainsIllegalCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	
	public static List<String> productFieldsValidator(Product prod) {
		// Checks if the fields of the kinship are null or not
		List<String> messages = new ArrayList<String>();
		
		if ((prod.getName().compareTo("") == 0) || descriptionContainsIllegalsCharacters(prod.getName())) {
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
	
	public static List<String> orderParamsValidator(Order order){
		List<String> msg = new ArrayList<String>();
		Status status = order.getStatus();
		
		if((status != Status.A) && (status != Status.OH) && (status != Status.R)) {
			msg.add("Invalid status report");
		}
		
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
	
	public static List<String> adminParamsValidator(Admin a){
		List<String> msg = new ArrayList<String>();
		
		if(nameContainsIllegalsCharacters(a.getName()) || a.getName().isEmpty()) {
			msg.add("Invalid data name format");
		}
		
		if(mailContainsIllegalCharacters(a.getMail()) || a.getMail().isEmpty()) {
			msg.add("Invalid data mail format");
		}
		
		if(descriptionContainsIllegalsCharacters(a.getPassword()) || a.getPassword().isEmpty()) {
			msg.add("Invalid password format");
		}
		return msg;
	}
	
	public static List<String> empParamsValidator(Employee e){
		List<String> msg = new ArrayList<String>();
		
		if(nameContainsIllegalsCharacters(e.getNombre()) || e.getNombre().isEmpty()) {
			msg.add("Invalid data name format");
		}
		
		if(mailContainsIllegalCharacters(e.getMail()) || e.getMail().isEmpty()) {
			msg.add("Invalid data mail format");
		}
		
		if(descriptionContainsIllegalsCharacters(e.getPassword()) || e.getPassword().isEmpty()) {
			msg.add("Invalid password format");
		}
		
		if(descriptionContainsIllegalsCharacters(e.getArea()) || e.getArea().isEmpty()) {
			msg.add("Invalid area format");
		}
		return msg;
	}
}
