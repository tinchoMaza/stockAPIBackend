package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.test.context.junit4.SpringRunner;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Status;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOnHold;
import com.belatrix.interns.StockAPIBackend.entities.stateOrder.StateOrder;
import com.belatrix.interns.StockAPIBackend.repository.OrdersRepository;

/**
 * 
 * @author fbalsas
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersRepositoryTests {
	
	@Autowired
	OrdersRepository ordRepository;
	
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
	public void storeOrder_andFindIt() {
		List<Product> prods = new ArrayList<Product>();
		Product prod = new Product("Orange Juice", "For those thirsty bois", 8, 4);
		prods.add(prod);
		ObjectId id = new ObjectId("5d9f3b4904cb5731f4643883");
		Order ord = new Order(Status.A, id, prods, new Date());
		
		this.ordRepository.save(ord);
		Optional<Order> findOrder = this.ordRepository.findById(new ObjectId(ord.getId()));
		if(!findOrder.isPresent()) fail();
		
		this.ordRepository.delete(new ObjectId(ord.getId()));
		assertTrue("The order was found", findOrder.isPresent());
	}
	
	@Test
	public void deleteOrder() {
		List<Product> prods = new ArrayList<Product>();
		Product prod = new Product("Orange Juice", "For those thirsty bois", 8, 4);
		prods.add(prod);
		ObjectId id = new ObjectId("5d9f3b4904cb5731f4643883");
		Order ord = new Order(Status.A, id, prods, new Date());
		
		this.ordRepository.save(ord);
		ObjectId orderId = new ObjectId(ord.getId());
		Optional<Order> findOrder = this.ordRepository.findById(orderId);
		this.ordRepository.delete(orderId);
		
		findOrder = null;
		findOrder = this.ordRepository.findById(orderId);
		
		assertTrue("The order should not be found", !findOrder.isPresent());
	}
	
	@Test
	public void updateOrder() {
		List<Product> prods = new ArrayList<Product>();
		Product prod = new Product("Orange Juice", "For those thirsty bois", 8, 4);
		prods.add(prod);
		ObjectId id = new ObjectId("5db075df3860df3bb8684b7b");
		Order ord = new Order(Status.A, id, prods, new Date());
		
		this.ordRepository.save(ord);
		ObjectId orderId = new ObjectId(ord.getId());
		
		prods.remove(prod);
		prod.setStock(30);
		prods.add(prod);
		ord.setOrderedProducts(prods);
		
		this.ordRepository.update(orderId, ord);
		Optional<Order> findOrder = this.ordRepository.findById(orderId);
		if(!findOrder.isPresent()) fail();
		boolean condition = true;
		
		for(Product p : findOrder.get().getOrderedProducts()) {
			if(p.getStock() == 8) condition = false;
		}
		
		assertTrue("Value of stock in the order was updated", condition);

	}

	@Test
	public void findExistingOrderById() {
		ObjectId id = new ObjectId("5d9f4c815e8b3c272cc09076");
		Optional<Order> ord = this.ordRepository.findById(id);
		assertTrue("The order should be found in the database", ord.isPresent());
	}
	
	
	@Test
	public void findNonExistingOrderById() {
		ObjectId id = new ObjectId("5d9f3b4904cb5731f4643883");
		Optional<Order> ord = this.ordRepository.findById(id);
		assertTrue("The order should not be found in the database", !ord.isPresent());
	}

	@Test
	public void findAllOrders() {
		List<Order> orders = this.ordRepository.findAll();
		assertTrue("The collection in database is populated, so there must exist orders", !orders.isEmpty());
	}
}
