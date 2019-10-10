package com.belatrix.interns.StockAPIBackend.entities;

/**
 * @author fbalsas
 */

public enum Status {
	A("Aproved"),
	R("Rejected"),
	OH("On Hold");
	
	private final String description;
	
	private Status(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
