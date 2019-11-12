package com.belatrix.interns.StockAPIBackend.exceptions;

public class NoOrdersException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2748583362534506276L;
	
	public NoOrdersException(String msg) {
		super(msg);
	}

}
