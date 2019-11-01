/**
 * 
 */
package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;

import java.util.List;
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
import org.springframework.data.util.Pair;
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
		Optional<Product> product = this.DepRepository.findById("5d9f4b875e8b3c272cc09074");
		assertTrue("There is not any product with id 84", !product.isPresent());
	}
	
	@Test
	public final void testFindById_WhenTheProductDoesExist() {
		Optional<Product> product = this.DepRepository.findById("5da882f250a76a293cafb119");
		assertTrue("There product has been found", product.isPresent());
	}
	
	@Test
	public final void testShowStockOfAProduct() {
		String idProduct = "5da880b8a9240d3cbc8aa45c";
		int expectedStock = 4;
		int actualStock = this.DepRepository.showStockOfAProduct(idProduct);
		assertTrue("Here it is, the product´s stock", expectedStock == actualStock);
	}
	
	public final void testCheckStock() {
		Product testProd = new Product(new ObjectId(), "Orange Juice", "For those thirsty bois", 8, 4);
		assertTrue("There are 8 orange juices in stock, so check stock should return true", this.DepRepository.checkReserveStock(testProd));
	}
	
	@Test
	public final void testStockAll() {
		List<Pair<String,Integer>> testList = this.DepRepository.showAllStock();
		if(!testList.isEmpty()) {
			for(Pair<String,Integer> prod : testList) {
				System.out.print("Product: " + prod.getFirst() + "/ Stock: " + prod.getSecond());
			}
		}
		assertTrue("There are elements in DB, so List should not be empty", !testList.isEmpty());
	}
	
	@Test
	public final void testGetAll() {
		Optional<List<Product>> testList = this.DepRepository.getAllProducts();
		assertTrue("There are elements in DB, so this list should be present", testList.isPresent());
	}
	
	@Test
	public final void testFindByName_WhenProductExists() {
		Optional<Product> testProd = this.DepRepository.findByName("Hojas de impresion tamano A4");
		assertTrue("The product exists in the DB, so testProd should be present", testProd.isPresent());
	}
	
	@Test
	public final void testFindByName_WhenProductDoesntExists() {
		Optional<Product> testProd = this.DepRepository.findByName("Manos para que Tincho deje de ser hijo de Agus en el PingPong");
		assertTrue("The product doesnt exist in the DB, so testProd should not be present", !testProd.isPresent());
	}
	
	@Test
	public final void testAMD() {
		Product testProd = new Product(new ObjectId(), "Orange Juice", "For those thirsty bois", 8, 4);
		int errorCount = 0;
		
		Optional<Product> checkProd = this.DepRepository.saveProduct(testProd);
		if(checkProd.get() != testProd) {
			System.out.print("Error in save method");
			errorCount++;
		}
		
		checkProd.get().setName("Facturas");
		checkProd.get().setDescription("For those hungry bois");
		this.DepRepository.updateProduct(checkProd.get().getId().toString(), checkProd.get());
		Optional<Product> aux = this.DepRepository.findByName("Facturas");
		if((!aux.isPresent()) || (aux.get().getId().compareTo(checkProd.get().getId().toString()) != 0)) {
			System.out.print("Error in update method");
			errorCount++;
		}
		
		this.DepRepository.deleteProduct(checkProd.get().getId().toString());
		aux = this.DepRepository.findById(checkProd.get().getId().toString());
		if(aux.isPresent()) {
			System.out.print("Error in delete method");
			errorCount++;
		}
		
		assertTrue("In order to pass this test, all three methods should work perfectly", errorCount == 0);
	}
	
	@Test
	public final void testShowProductsWithLowStock() {
		List<Product> lowStockProducts = this.DepRepository.showProductsWithLowStock();
		assertTrue("There is no product with low stock in the db", lowStockProducts.isEmpty());
	}

}
