package com.belatrix.interns.StockAPIBackend.exceptions;

public class OrderException extends Exception{

	private static final long serialVersionUID = -1598927685829872785L;

	public OrderException(String msg) {
		super(msg);
	}
}