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
    
    public Email(String _bodyMessage) {
    	super();
    	this.remitter = "";
    	this.password = "";
    	this.addressee = "danebrosio@gmail.com";
    	this.subject = "New order has been created";
    	this.bodyMessage = _bodyMessage;
    }

	/**
	 * @return the remitter
	 */
	public String getRemitter() {
		return remitter;
	}

	/**
	 * @param remitter the remitter to set
	 */
	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the addressee
	 */
	public String getAddressee() {
		return addressee;
	}

	/**
	 * @param addressee the addressee to set
	 */
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the bodyMessage
	 */
	public String getBodyMessage() {
		return bodyMessage;
	}

	/**
	 * @param bodyMessage the bodyMessage to set
	 */
	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}
}