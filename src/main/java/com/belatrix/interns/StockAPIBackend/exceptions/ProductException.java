package com.belatrix.interns.StockAPIBackend.exceptions;

public class ProductException extends Exception {

	private static final long serialVersionUID = -8922831068940985215L;
	
	private String messages;

	public ProductException(String msg) {
		this.messages = msg;
	}

	/**
	 * @return the messages
	 */
	public String getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
}
