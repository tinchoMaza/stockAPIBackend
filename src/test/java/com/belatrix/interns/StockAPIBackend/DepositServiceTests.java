/**
 * 
 */
package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;

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

import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.services.DepositService;

/**
 * @author aluna
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepositServiceTests {
	
	@Autowired
	DepositService depService;
	
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
	public final void testCheckStock_WhenProductExists() throws ProductException {
		assertTrue("The product exists and there are plenty of stock, so method should return true", this.depService.checkReserveStock("5d9f4b875e8b3c272cc09075"));
	}
	
	@Rule
	public ExpectedException exRule = ExpectedException.none();
	
	@Test
	public final void testCheckStock_WhenProductDoesntExists() throws ProductException{
		exRule.expect(ProductException.class);
		exRule.expectMessage("\"No product stored for this id: 12");
		this.depService.checkReserveStock("12");
	}
}
