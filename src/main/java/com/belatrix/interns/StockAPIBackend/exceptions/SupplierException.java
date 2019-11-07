/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.exceptions;

/**
 * @author aluna
 *
 */
public class SupplierException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 579220861893593925L;
	
	private String messages;

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

	/**
	 * 
	 */
	public SupplierException() {
		super();
	}

	/**
	 * @param message
	 */
	public SupplierException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public SupplierException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SupplierException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SupplierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
