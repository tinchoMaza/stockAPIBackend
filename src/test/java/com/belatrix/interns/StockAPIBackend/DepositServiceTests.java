/**
 * 
 */
package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;

import java.util.List;
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
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.EmptyDepositException;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
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
	

	

	@Test
	public final void testCheckStock_WhenProductExists() throws ProductException {
		assertTrue("The product exists and there are plenty of stock, so method should return true", this.depService.checkReserveStock(this.depService.findByName("Hierba Medicinal").getName()));
	}
	
	@Rule
	public ExpectedException exRule = ExpectedException.none();
	
	@Test
	public final void testCheckStock_WhenProductDoesntExists() throws ProductException{
		exRule.expect(ProductException.class);
		exRule.expectMessage("\"No product stored for this id: 12");
		this.depService.checkReserveStock("12");
	}

	
	@Rule
	public ExpectedException emptyRule = ExpectedException.none();
	@Test
	public final void testShowAllStock() throws EmptyDepositException{
		try {
			List<Pair<String,Integer>> testList = this.depService.showAllStock();
			assertTrue("The DB has at least one test element, so list should not be empty", !testList.isEmpty());
		}catch(EmptyDepositException ex) {
			emptyRule.expect(EmptyDepositException.class);
			emptyRule.expectMessage("There are no items stored in the deposit");
		}
	}
	
	@Test
	public final void testGetAll() {
		List<Product> testList = this.depService.getAllProducts();
		assertTrue("Since there are test data in DB, list should not be empty", !testList.isEmpty());
	}
	
	@Rule
	public ExpectedException nameRule = ExpectedException.none();
	@Test
	public final void testFindByName_WhenProductDoesntExists() throws ProductException{
		nameRule.expect(ProductException.class);
		nameRule.expectMessage("There is no Manos para que Agus juegue bien al Ping Pong in stock");
		this.depService.findByName("Manos para que Agus juegue bien al Ping Pong");
	}
	
	@Rule
	public ExpectedException saveRule = ExpectedException.none();
	@Test
	public final void testSaveProduct_WithInvalidFields(){
		saveRule.expect(InvalidDataException.class);
		Product testProd = new Product("12345", "½¼≤√ⁿ²ƒ±₧÷", -8, -5);
		try {
			this.depService.saveProduct(testProd);
		}catch(InvalidDataException ex) {
			if(!ex.getMessages().isEmpty()) assertTrue("There should be five errors", ex.getMessages().size() == 5);
		}
	}
	
	@Rule
	public ExpectedException deleteRule = ExpectedException.none();
	@Test
	public final void testDeleteProduct_WhenProductDoesntExists() throws ProductException{
		String id = "12";
		deleteRule.expect(ProductException.class);
		deleteRule.expectMessage("No product stored for this id: 12");
		this.depService.deleteProduct(id);
	}
	
	@Rule
	public ExpectedException invalidIdRule = ExpectedException.none();
	@Test
	public final void testUpdateProduct_WithInvalidId() throws ProductException, InvalidDataException{
		String id = "12";
		Product prod = this.depService.findByName("Hierba Medicinal");
		invalidIdRule.expect(ProductException.class);
		invalidIdRule.expectMessage("No product stored for this id: 12");
		this.depService.updateProduct(id, prod);
	}
	
	@Rule
	public ExpectedException invalidProductRule = ExpectedException.none();
	@Test
	public final void testUpdateProduct_WithInvalidProduct() throws ProductException, InvalidDataException{
		invalidProductRule.expect(InvalidDataException.class);
		Product prod = this.depService.findByName("Hierba Medicinal");
		Product testProd = new Product("12345", "½¼≤√ⁿ²ƒ±₧÷", -8, -5);
		try {
			this.depService.updateProduct(prod.getId(), testProd);
		}catch(InvalidDataException ex) {
			if(!ex.getMessages().isEmpty()) assertTrue("There should be five errors", ex.getMessages().size() == 5);
		}
	}
	
	@Test
	public final void testShowProductsWithLowStock() {
		List<Product> lowStockProducts = this.depService.showProductsWithLowStock();
		assertTrue("There is no product with low stock in the db", lowStockProducts.isEmpty());
	}
	
	

}
