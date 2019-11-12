package com.belatrix.interns.StockAPIBackend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.dbo.OrderDBO;
import com.belatrix.interns.StockAPIBackend.dto.OrderDTO;
import com.belatrix.interns.StockAPIBackend.dto.OrderView;
import com.belatrix.interns.StockAPIBackend.entities.Email;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOnHold;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;
import com.belatrix.interns.StockAPIBackend.repository.OrderRepository;

@Service("orderService")
@Transactional
public class OrderServiceImplem implements OrderService{

	private OrderRepository ordRepo;
	private DepositService depositService;
	private EmployeeService employeeService;

	@Autowired
	public OrderServiceImplem(OrderRepository ordRepo, DepositService depositService, EmployeeService employeeService) {
		this.ordRepo = ordRepo;
		this.depositService = depositService;
		this.employeeService = employeeService;
	}

	public List<OrderDTO> getAllOrders() throws ProductException, EmployeeException {
		Optional<List<OrderDBO>> results = ordRepo.getAllOrders();
		List<OrderDBO> aux = new ArrayList<OrderDBO>();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		if(results.isPresent()) {
			aux = results.get();
			for(OrderDBO odb : aux)
				orders.add(this.convertToDomain(odb).toDTO());
			return orders;
		}
		else return orders;
	}

	public OrderDTO findById(String id) throws OrderException, ProductException, EmployeeException {
		Optional<OrderDBO> order = ordRepo.findById(id);
		if (order.isPresent()) {
			OrderDBO odb = order.get();
			return this.convertToDomain(odb).toDTO();
		}
		else
			throw new OrderException("Order with id: " + id + " not found. Please try another id.");
	}
	
	public OrderDTO findByNumber(int number) throws OrderException, ProductException, EmployeeException {
		Optional<OrderDBO> order = ordRepo.findByNumber(number);
		if (order.isPresent()) {
			OrderDBO odb = order.get();
			return this.convertToDomain(odb).toDTO();
		}
		else
			throw new OrderException("Order number: " + number + " not found. Please try another number.");
	}

	private Order convertToDomain(OrderDBO odb) throws ProductException, EmployeeException {
		Order o = new Order();
		o.set_id(odb.get_id());
		o.setNumber(odb.getNumber());
		o.setStatus(odb.getStateOrderStatus());
		o.setEmployee(employeeService.findById(odb.getId_employee().toHexString()));
		for(ObjectId p : odb.getOrderedProducts()) {
			o.addProductToOrder(depositService.findById(p.toHexString()));
		}
		o.setArrival_date(odb.getArrival_date());
		o.setDeparture_date(odb.getDeparture_date());
		return o;
	}

	public OrderDTO saveOrder(OrderView ord) throws ProductException, EmployeeException, Exception {
		Order o = new Order();
		for (String p : ord.getOrderedProducts()) {
			o.addProductToOrder(depositService.findById(p));
		}
		o.setEmployee(employeeService.findById(ord.getId_Employee()));
		o.setStatus(new StateOnHold());
		o.setArrival_date(new Date());
		o.setDeparture_date(new Date());
		Optional<OrderDBO> odb = this.ordRepo.saveOrder(o);
		if(odb.isPresent())
			return this.convertToDomain(odb.get()).toDTO();
		else
			throw new Exception("Ups.. Fail to save order. Please contact technical support.");
	}

	public boolean existsAnother(OrderView ov) throws ProductException, EmployeeException {
		Order o = new Order();
		o.setEmployee(employeeService.findById(ov.getId_Employee()));
		for(String p : ov.getOrderedProducts()) {
			o.addProductToOrder(depositService.findById(p));
		}
		List<OrderDTO> orders = this.getAllOrders();
		for (OrderDTO ordDto : orders) {
			Order aux = ordDto.toDomain();
			if((aux.equalsDuplicate(o)) && (!(aux.getStatus().getStateOrderString().equalsIgnoreCase("COMPLETED"))))
				return true;
		}
		//recorri todas las ordenes y no hay ninguna igual en progreso
		return false;
	}

	public OrderDTO acceptOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException {
		//una orden solo puede actualizar su estado. Si quiero cambiar los productos debo cancelarla y crear otra.
		Optional<OrderDBO> odb = this.ordRepo.findById(_id);
		if(!odb.isPresent())
			throw new OrderException("Order with id: " + _id + " not found. Please retry with another id.");
		else {
			OrderDBO o = odb.get();
			Order order = this.convertToDomain(o);
			order.getStatus().accept(order);
			Optional<OrderDBO> oodb = ordRepo.updateOrder(order);
			if(!oodb.isPresent())
				throw new OrderException("Order with id: " + _id + " not found. Something went wrong. We should find the recent updated order.");
			else {
				OrderDBO aux = oodb.get();
				return this.convertToDomain(aux).toDTO();
			}
		}
	}

	public OrderDTO rejectOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException {
		//una orden solo puede actualizar su estado. Si quiero cambiar los productos debo cancelarla y crear otra.
		Optional<OrderDBO> odb = this.ordRepo.findById(_id);
		if(!odb.isPresent())
			throw new OrderException("Order with id: " + _id + " not found. Please retry with another id.");
		else {
			OrderDBO o = odb.get();
			Order order = this.convertToDomain(o);
			order.getStatus().reject(order);
			Optional<OrderDBO> oodb = ordRepo.updateOrder(order);
			if(!oodb.isPresent())
				throw new OrderException("Order with id: " + _id + " not found. Something went wrong. We should find the recent updated order.");
			else {
				OrderDBO aux = oodb.get();
				return this.convertToDomain(aux).toDTO();
			}
		}
	}

	public OrderDTO cancelOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException {
		//una orden solo puede actualizar su estado. Si quiero cambiar los productos debo cancelarla y crear otra.
		Optional<OrderDBO> odb = this.ordRepo.findById(_id);
		if(!odb.isPresent())
			throw new OrderException("Order with id: " + _id + " not found. Please retry with another id.");
		else {
			OrderDBO o = odb.get();
			Order order = this.convertToDomain(o);
			order.getStatus().cancel(order);
			Optional<OrderDBO> oodb = ordRepo.updateOrder(order);
			if(!oodb.isPresent())
				throw new OrderException("Order with id: " + _id + " not found. Something went wrong. We should find the recent updated order.");
			else {
				OrderDBO aux = oodb.get();
				return this.convertToDomain(aux).toDTO();
			}
		}
	}

	public OrderDTO updateOrder(String _id) throws OrderException, ProductException, EmployeeException, StateOrderException {
		//una orden solo puede actualizar su estado. Si quiero cambiar los productos debo cancelarla y crear otra.
		Optional<OrderDBO> odb = this.ordRepo.findById(_id);
		if(!odb.isPresent())
			throw new OrderException("Order with id: " + _id + " not found. Please retry with another id.");
		else {
			OrderDBO o = odb.get();
			Order order = this.convertToDomain(o);
			order.getStatus().update(order);
			Optional<OrderDBO> oodb = ordRepo.updateOrder(order);
			if(!oodb.isPresent())
				throw new OrderException("Order with id: " + _id + " not found. Something went wrong. We should find the recent updated order.");
			else {
				OrderDBO aux = oodb.get();
				return this.convertToDomain(aux).toDTO();
			}
		}
	}

	public void deleteOrder(String _id) {
		this.ordRepo.deleteOrder(_id);
	}

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