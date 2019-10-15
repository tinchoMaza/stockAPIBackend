/**
 * 
 */
package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.bson.types.ObjectId;
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
import com.belatrix.interns.StockAPIBackend.repository.DepositRepository;

/**
 * @author aluna
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepositRepositoryTests {
	
	@Autowired
	DepositRepository DepRepository;
	
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
	public final void testFindById_WhenTheProductDoesNotExist() {
		Optional<Product> product = this.DepRepository.findById("84");
		assertTrue("There is not any product with id 84", !product.isPresent());
	}
	
	@Test
	public final void testFindById_WhenTheProductDoesExist() {
		Optional<Product> product = this.DepRepository.findById("5d9f4b875e8b3c272cc09075");
		assertTrue("There product has been found", product.isPresent());
	}
	
	@Test
	public final void testCheckStock() {
		ObjectId id = new ObjectId("12");
		Product testProd = new Product(id, "Orange Juice", "For those thirsty bois", 8, 4);
		assertTrue("There are 8 orange juices in stock, so check stock should return true", this.DepRepository.checkReserveStock(testProd));
	}

}
