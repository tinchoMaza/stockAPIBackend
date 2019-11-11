package com.belatrix.interns.StockAPIBackend;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTests {

	@Autowired
	EmployeeRepository empRepo;
	
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
	public final void testFindById_whenEmployeeExists() {
		Optional<Employee> e = this.empRepo.findById("5da9ba0050a76a293cafb123");
		assertTrue("El empleado fue encontrado", e.isPresent());
	}
	
	@Test
	public final void testFindById_whenEmployeeDoesntExist() {
		Optional<Employee> e = this.empRepo.findById("5da08689f9452a17c445a4f7");
		assertTrue("El empleado no fue encontrado", !e.isPresent());
	}
	
	@Test
	public final void testFindByMail_whenEmployeeExists() {
		Optional<Employee> e = this.empRepo.findByMail("harry@gmail.com");
		assertTrue("El empleado fue encontrado", e.isPresent());
	}
	
	@Test
	public final void testFindByMail_whenEmployeeDoesntExist() {
		Optional<Employee> e = this.empRepo.findByMail("harry.elsucio@gmail.com");
		assertTrue("El empleado no fue encontrado", !e.isPresent());
	}
	
	@Test
	public final void testFindByName_whenEmployeeExists() {
		Optional<Employee> e = this.empRepo.findByName("Alfred");
		assertTrue("El empleado fue encontrado", e.isPresent());
	}
	
	@Test
	public final void testFindByName_whenEmployeeDoesntExist() {
		Optional<Employee> e = this.empRepo.findByName("Marito Baracus");
		assertTrue("El empleado no fue encontrado", !e.isPresent());
	}
	
	@Test
	public final void testFindByArea_whenEmployeeExists() {
		Optional<List<Employee>> e = this.empRepo.findByArea("Soporte Tecnico");
		assertTrue("El empleado fue encontrado", e.isPresent());
	}
	
	@Test
	public final void testFindByArea_whenEmployeeDoesntExist() {
		Optional<List<Employee>> e = this.empRepo.findByArea("Cocina");
		assertTrue("No existe el area de trabajo, la lista debe estar vacia", e.get().isEmpty());
	}
	
	public final void testFindByAll() {
		Optional<List<Employee>> e = this.empRepo.getAllEmployees();
		assertTrue("Hay empleados en la base de datos, la prueba debe pasar", e.isPresent());
	}
	
	@Test
	public final void testSaveAndDeleteEmployee_withValidParameters() {
		List<String> errorCounter = new ArrayList<String>();
		
		Employee emp = new Employee(new ObjectId(), "Jhonny Cage", "Seguridad", "j.cage@gmail.com", "fatality");
		Employee e = this.empRepo.saveEmployee(emp);
		if(!e.getNombre().equalsIgnoreCase(emp.getNombre())){
			errorCounter.add("Employee was not saved");
		}
		
		this.empRepo.deleteEmployee(e.getId());
		Optional<Employee> deletedEmp = this.empRepo.findById(e.getId());
		if(deletedEmp.isPresent()) errorCounter.add("Employee was not deleted");
		if(!errorCounter.isEmpty()) {
			System.out.println(errorCounter);
		}
		assertTrue("Employee was added and deleted with no issues", errorCounter.isEmpty());
	}
	
	@Test
	public final void testUpdateEmployee_withValidParameters() {
		Employee e = new Employee(new ObjectId(), "Scorpio", "Seguridad", "mortal.kombat@gmail.com", "getOverHere");
		Employee check = this.empRepo.saveEmployee(e);
		
		Employee emp = new Employee(new ObjectId(), "Jhonny Cage", "Seguridad", "j.cage@gmail.com", "fatality");
		this.empRepo.updateEmployee(e.getId(), emp);
		boolean condition = !check.getNombre().equalsIgnoreCase(emp.getNombre());
		this.empRepo.deleteEmployee(e.getId());
		
		assertTrue("The employee was successfuly updated", condition);
	}
	
	
}
