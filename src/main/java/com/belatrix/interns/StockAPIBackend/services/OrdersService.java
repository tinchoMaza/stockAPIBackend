package com.belatrix.interns.StockAPIBackend.services;

import com.belatrix.interns.StockAPIBackend.entities.Email;

public interface OrdersService {
	
	public boolean sendEmail(Email email);

}
