package com.belatrix.interns.StockAPIBackend;


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

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.exceptions.EmployeeException;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.services.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTests {
	
	@Autowired
	EmployeeService empServ;
	
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
	public ExpectedException areaRule = ExpectedException.none();
	
	@Test
	public final void testFindByArea_whenEmployeeDoesntExist() {
		areaRule.expect(EmployeeException.class);
		this.empServ.findByArea("Cocina");
	}
	
	@Rule
	public ExpectedException nameRule = ExpectedException.none();
	@Test
	public final void testFindByName_whenEmployeeDoesntExist() {
		nameRule.expect(EmployeeException.class);
		this.empServ.findByName("Marito Baracus");
	}
	
	@Rule
	public ExpectedException mailRule = ExpectedException.none();
	@Test
	public final void testFindByMail_whenEmployeeDoesntExist() {
		mailRule.expect(EmployeeException.class);
		this.empServ.findByMail("harry.elsucio@gmail.com");
	}
	
	@Rule
	public ExpectedException exRule = ExpectedException.none();
	@Test
	public final void testFindById_whenEmployeeDoesntExist() {
		exRule.expect(EmployeeException.class);
		this.empServ.findById("5da08689f9452a17c445a4f7");
	}
	
	@Rule
	public ExpectedException saveRule = ExpectedException.none();
	@Test
	public final void testSaveEmployee_withInvalidParameters() throws InvalidDataException {
		saveRule.expect(InvalidDataException.class);
		Employee e = new Employee(new ObjectId(), "½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷");
		this.empServ.saveEmployee(e);
	}
	
	@Rule
	public ExpectedException deleteRule = ExpectedException.none();
	@Test
	public final void testDeleteEmployee_WhenEmployeeDoesntExists() throws EmployeeException{
		deleteRule.expect(EmployeeException.class);
		this.empServ.deleteEmployee("5da87f48a9240d3cbc8aa459");
	}
	
	@Rule
	public ExpectedException updateRule = ExpectedException.none();
	@Test
	public final void testUpdateEmployee_WithInvalidID() throws InvalidDataException {
		updateRule.expect(InvalidDataException.class);
		Employee e = new Employee(new ObjectId(), "Scorpio", "Seguridad", "mortal.kombat@gmail.com", "getOverHere");
		this.empServ.updateEmployee("5da87f48a9240d3cbc8aa459", e);
	}
	
	@Test
	public final void testUpdateEmployee_WithInvalidParams() throws InvalidDataException {
		updateRule.expect(InvalidDataException.class);
		Employee e = new Employee(new ObjectId(), "½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷", "½¼≤√ⁿ²ƒ±₧÷");
		this.empServ.updateEmployee("5da9ba0050a76a293cafb123", e);
	}
}
