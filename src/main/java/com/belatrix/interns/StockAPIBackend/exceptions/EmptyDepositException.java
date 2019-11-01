package com.belatrix.interns.StockAPIBackend.exceptions;

public class EmptyDepositException extends Exception{
	
	private static final long serialVersionUID = 6440031812802139929L;

	public EmptyDepositException(String msg) {
		super(msg);
	}

}
