/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.exceptions;

import java.util.List;

/**
 * @author aluna
 *
 */
public class StateOrderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8629921645567495320L;
    private List<String> messages;
	/**
	 * 
	 */
	public StateOrderException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public StateOrderException(List<String> message) {
		this.messages = message;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @param cause
	 */
	public StateOrderException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public StateOrderException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StateOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
