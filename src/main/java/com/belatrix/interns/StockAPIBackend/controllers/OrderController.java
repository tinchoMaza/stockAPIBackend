package com.belatrix.interns.StockAPIBackend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belatrix.interns.StockAPIBackend.dto.OrderDTO;
import com.belatrix.interns.StockAPIBackend.dto.OrderView;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.exceptions.StateOrderException;
import com.belatrix.interns.StockAPIBackend.services.OrderService;

@RestController
@RequestMapping("/stockAPI/orders")
public class OrderController {

	private final OrderService ordService;

	@Autowired
	public OrderController(OrderService ordService) {
		this.ordService = ordService;
	}

	@GetMapping(path = "/", consumes = "application/json", produces = {"application/json"})
	public ResponseEntity<Object> getAllOrders() {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			orders = this.ordService.getAllOrders();
			return ResponseEntity.status(HttpStatus.OK).body(orders);
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. Please contact technical support. ProductException -> Error message: " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. Please contact technical support. EmployeeException -> Error message: " + e.getMessage());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + e.getMessage());
		}
	}

	@GetMapping(path = "/{_id}", produces = {"application/json"})
	public ResponseEntity<Object> getOrderById(@PathVariable("_id") String _id){
		if(_id.isBlank())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id is missing.");
		try {
			OrderDTO o = ordService.findById(_id);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		}
		catch(OrderException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(ProductException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. " + ex.getMessage());
		}
		catch(EmployeeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We coulnd't find the employee. " + ex.getMessage());
		}
		catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + ex.getMessage());
		}
	}
	
	@GetMapping(path = "/number/{number}", produces = {"application/json"})
	public ResponseEntity<Object> getOrderByNumber(@PathVariable("number") String num) {
		int number;
		try {
			number = Integer.parseInt(num);
		} catch (NumberFormatException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number is not in a valid format. " + ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + ex.getMessage());
		}
		try {
			OrderDTO o = ordService.findByNumber(number);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		}
		catch(OrderException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We coulnd't find the employee. " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + e.getMessage());
		}
	}

	@PostMapping(path = "/", consumes = "application/json", produces = {"application/json"})
	public ResponseEntity<Object> saveOrder (@RequestBody @Valid OrderView o) {
		if(o.getId_Employee().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee id is missing.");
		}
		if(o.getOrderedProducts().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must add at least 1 product to the order.");
		}
		try {
			if(ordService.existsAnother(o)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("There is another identical order in progress for the same employee with the same products");
			}
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We coulnd't find the employee. " + e.getMessage());
		}  catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + ex.getMessage());
		}
		try {
			OrderDTO ord = ordService.saveOrder(o);
			return ResponseEntity.status(HttpStatus.CREATED).body(ord);
		}  catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + ex.getMessage());
		}
	}

	@PutMapping(path = "/{id}/accept", produces = {"application/json"})
	public ResponseEntity<Object> acceptOrder (@PathVariable ("id") String _id) {
		if(_id.isBlank())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id is missing.");
		try {
			OrderDTO o = ordService.acceptOrder(_id);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		} catch (OrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. Please contact technical support. Error message: " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (StateOrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. The state of the order couldn't be updated. " + e.getMessages());
		}  catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + ex);
		}
	}
	
	@PutMapping(path = "/{id}/reject", produces = {"application/json"})
	public ResponseEntity<Object> rejectOrder (@PathVariable ("id") String _id) {
		if(_id.isBlank())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id is missing.");
		try {
			OrderDTO o = ordService.rejectOrder(_id);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		} catch (OrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. Please contact technical support. Error message: " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (StateOrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. The state of the order couldn't be updated. " + e.getMessages());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + e);
		}
	}
	
	@PutMapping(path = "/{id}/cancel", produces = {"application/json"})
	public ResponseEntity<Object> cancelOrder (@PathVariable ("id") String _id) {
		if(_id.isBlank())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id is missing.");
		try {
			OrderDTO o = ordService.cancelOrder(_id);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		} catch (OrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. Please contact technical support. Error message: " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (StateOrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. The state of the order couldn't be updated. " + e.getMessages());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + e);
		}
	}
	
	@PutMapping(path = "/{id}/update", produces = {"application/json"})
	public ResponseEntity<Object> updateOrder (@PathVariable ("id") String _id) {
		if(_id.isBlank())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id is missing.");
		try {
			OrderDTO o = ordService.updateOrder(_id);
			return ResponseEntity.status(HttpStatus.OK).body(o);
		} catch (OrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (ProductException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find at least one product. Please contact technical support. Error message: " + e.getMessage());
		} catch (EmployeeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. This sholudn't happen. We couldn't find the order. Please contact technical support. Error message: " + e.getMessage());
		} catch (StateOrderException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. The state of the order couldn't be updated. " + e.getMessages());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + e);
		}
	}

	@DeleteMapping(path = "/{_id}", produces = {"application/json"})
	public ResponseEntity<Object> deleteOrder (@PathVariable("_id") String _id) {
		if(_id.isBlank())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order id is missing.");
		try {
			ordService.deleteOrder(_id);
			return ResponseEntity.status(HttpStatus.OK).body("Order with id: " + _id + " deleted.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups.. Something went wrong. Error message: " + e.getMessage());
		}
	}
}