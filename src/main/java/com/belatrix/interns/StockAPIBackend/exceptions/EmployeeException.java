package com.belatrix.interns.StockAPIBackend.exceptions;

import org.springframework.core.NestedRuntimeException;

public class EmployeeException extends NestedRuntimeException{

	private static final long serialVersionUID = 6166199834703247366L;

	public EmployeeException(String msg) {
		super(msg);
	}

}
