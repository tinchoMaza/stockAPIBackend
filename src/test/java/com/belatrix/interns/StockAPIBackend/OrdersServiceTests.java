package com.belatrix.interns.StockAPIBackend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Status;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.OrderException;
import com.belatrix.interns.StockAPIBackend.services.OrdersService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersServiceTests {

	@Autowired
	OrdersService ordService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Rule
	public ExpectedException saveRule = ExpectedException.none();
	@SuppressWarnings("deprecation")
	@Test
	public void saveOrder_WithInvalidParameteres() throws InvalidDataException {
		saveRule.expect(InvalidDataException.class);
		List<Product> prods = new ArrayList<Product>();
		Product prod = new Product("½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷s", -8, -4);
		prods.add(prod);
		Date date = new Date();
		Date depDate = date;
		depDate.setYear(2019);
		ObjectId empId = new ObjectId("5da08689f9452a17c445a4f7");
		this.ordService.save(empId, prods, Status.R, date, Optional.ofNullable(depDate));
	}

	
	@Rule
	public ExpectedException updateRule = ExpectedException.none();
	@Test
	public void updateOrder_WithInvalidParameters() throws InvalidDataException {
		updateRule.expect(InvalidDataException.class);
		List<Product> prods = new ArrayList<Product>();
		Product prod = new Product("½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷s", -8, -4);
		prods.add(prod);
		ObjectId empId = new ObjectId("5da08689f9452a17c445a4f7");
		Order ord = new Order(Status.A, empId, prods, new Date());
		
		ObjectId prevOrd = new ObjectId("5d9f4c815e8b3c272cc09076");
		this.ordService.update(prevOrd, ord);
	}
	
	@Rule
	public ExpectedException deleteRule = ExpectedException.none();
	@Test
	public void deleteOrder_WithInvalidId() throws OrderException {
		deleteRule.expect(OrderException.class);
		ObjectId id = new ObjectId("5da08689f9452a17c445a4f7");
		this.ordService.delete(id);
	}
}
