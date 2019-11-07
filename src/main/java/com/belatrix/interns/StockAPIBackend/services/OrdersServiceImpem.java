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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.paramValidations.*;
import com.belatrix.interns.StockAPIBackend.repository.EmployeeRepository;
import com.belatrix.interns.StockAPIBackend.repository.OrdersRepository;

@Service("ordersService")
@Transactional
public class OrdersServiceImpem implements OrdersService{
	
	private OrdersRepository ordRepo;

	private EmployeeRepository empRepo;
	
	@Autowired
	public void EmployeeServiceImplem(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	
	@Autowired
	public void OrdersServiceImplem(OrdersRepository ordRepo) {
		this.ordRepo = ordRepo;
	}

	@Override
	public void save(ObjectId empId, List<Product> orderedProds, StateOrder status, Date arrival, Optional<Date> departure) throws InvalidDataException {
		List<String> errorMsgs = new ArrayList<String>();
		for(Product prod : orderedProds) {
			errorMsgs = ParamValidator.productFieldsValidator(prod);
			if(!errorMsgs.isEmpty()) break;
		}
		if(!this.empRepo.findById(empId.toString()).isPresent()) errorMsgs.add("Employee not found in Database");
		if(status.toString().matches("StateOnHold") && departure.isPresent()) errorMsgs.add("On Hold orders cannot have known departure dates");
		if(orderedProds.isEmpty()) errorMsgs.add("No products have been selected");
		if(errorMsgs.size() > 0) throw new InvalidDataException(errorMsgs);
		
		Order o = new Order(status, empId, orderedProds, arrival);
		if(departure.isPresent()) o.setDeparture_date(departure.get());
		this.ordRepo.save(o);
	}

	@Override
	public List<Order> findAll() {
		return this.ordRepo.findAll();
	}

	@Override
	public Order findById(ObjectId id) throws OrderException{
		Optional<Order> order = this.ordRepo.findById(id);
		if(!order.isPresent()) throw new OrderException("Order not found, id: " + id.toString());
		return order.get();
	}

	@Override
	public void update(ObjectId toUpdateOrd, Order latestOrder) throws InvalidDataException {

		List<String> errorMsgs = ParamValidator.orderParamsValidator(latestOrder);
		Optional<Order> oldOrder = this.ordRepo.findById(toUpdateOrd);
		if(!oldOrder.isPresent()) errorMsgs.add("Order not found, id: " + toUpdateOrd.toString());
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		this.ordRepo.update(toUpdateOrd, latestOrder);
		
	}

	@Override
	public void delete(ObjectId id) throws OrderException {
		Optional<Order> ord = this.ordRepo.findById(id);
		if(!ord.isPresent()) throw new OrderException("Order not found, id: " + id);
		this.ordRepo.delete(id);
	}

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
        	ex.printStackTrace();
        	return false;
            
        } catch (MessagingException ex) {
        	ex.printStackTrace();
        	return false;
        }
        
		return true;
	}

}
