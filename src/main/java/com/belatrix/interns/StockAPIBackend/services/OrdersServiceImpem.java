package com.belatrix.interns.StockAPIBackend.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.belatrix.interns.StockAPIBackend.entities.Email;

public class OrdersServiceImpem implements OrdersService{

	@Override
	public boolean sendEmail(Email email) {
		Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.user", email.getRemitter());
        propiedad.setProperty("smpt.user.auth","true");
        Session sesion = Session.getDefaultInstance(propiedad);
        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress (email.getRemitter()));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (email.getAddressee()));
            mail.setSubject(email.getSubject());
            mail.setText(email.getBodyMessage());
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(email.getRemitter(),email.getPassword());
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            
            
        } catch (AddressException ex) {
        	return false;
            
        } catch (MessagingException ex) {
        	return false;
        }
        
		return true;
	}

}
