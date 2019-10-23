/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.entities;

/**
 * @author aluna
 *
 */
public class Email {
	String remitter;
    String password;
    String addressee;
    String subject;
    String bodyMessage;
    
    public Email(String _remitter, String _password, String _addressee, String _subject, String _bodyMessage) {
    	super();
    	this.remitter = _remitter;
    	this.password = _password;
    	this.addressee = _addressee;
    	this.subject = _subject;
    	this.bodyMessage = _bodyMessage;
    }
}