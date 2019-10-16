/**
 * 
 */
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

import com.belatrix.interns.StockAPIBackend.entities.Product;
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
	public final void testFindById_WhenTheProductDoesNotExist(){
		Product product = null;
		try {
			product = this.depService.findById("5d9f4b875e8b3c272cc09074");
		} catch (ProductException e) {
		}
		assertTrue("There is no product with id 5d9f4b875e8b3c272cc09074 ", product == null);
	}
	
	
	@Test
	public final void testFindById_WhenTheProductDoesExist() throws ProductException {
		Product product = this.depService.findById("5d9f4b875e8b3c272cc09075");
		assertTrue("There product with id 5d9f4b875e8b3c272cc09075 has been found", product != null);
	}
	
	@Test
	public final void showStockOfAProduct() {
		String idProduct = "5d9f4b875e8b3c272cc09075";
		int expectedStock = 2000;
		int actualStock = this.depService.showStockOfAProduct(idProduct);
		assertTrue("There product has been found", expectedStock == actualStock);
	}
	

	

}
