package com.belatrix.interns.StockAPIBackend.exceptions;

import org.springframework.core.NestedRuntimeException;

public class OrderException extends NestedRuntimeException{

	private static final long serialVersionUID = -1598927685829872785L;

	public OrderException(String msg) {
		super(msg);
	}

}
