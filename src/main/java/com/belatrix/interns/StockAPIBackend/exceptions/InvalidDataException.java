package com.belatrix.interns.StockAPIBackend.exceptions;

import java.util.List;

public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1252468466392150300L;
	private List<String> messages;
	
	public InvalidDataException(List<String> messages) {
		this.messages = messages;
	}
	
	public List<String> getMessages(){
		return this.messages;
	}

}
