package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.belatrix.interns.StockAPIBackend.entities.Email;
import com.belatrix.interns.StockAPIBackend.services.OrdersService;

/**
 * @author aluna
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
	
	@Autowired
	OrdersService orderService;
	
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
	

	@Test
	public final void testSendEmail() {
		Email email = new Email("Juan 3:16");
		assertTrue("The mail has been sent", orderService.sendEmail(email));
	}
	

}
