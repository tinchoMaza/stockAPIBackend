package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.entities.Order;

@Repository
public class EmployeeRepositoryImplem implements EmployeeRepository {
	
	private final MongoOperations mongoOp;
	
	@Autowired
	public EmployeeRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

	public Optional<List<Employee>> getAllEmployees() {
		List<Employee> emps = this.mongoOp.find(new Query(), Employee.class);
		return Optional.ofNullable(emps);
	}

	public Optional<Employee> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Employee e = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Employee.class);
		return Optional.ofNullable(e);
	}

	public Optional<Employee> findByName(String name) {
		Employee e = this.mongoOp.findOne(new Query(Criteria.where("name").is(name)), Employee.class);
		return Optional.ofNullable(e);
	}

	public Optional<List<Employee>> findByArea(String area) {
		List<Employee> emps = this.mongoOp.find(new Query(Criteria.where("area").is(area)), Employee.class);
		return Optional.ofNullable(emps);
	}

	public Employee saveEmployee(Employee e) {
		this.mongoOp.save(e);
		return findById(e.getId()).get();
	}

	public void deleteEmployee(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Employee.class);
	}
	
	public void updateEmployee(String id, Employee e){
		Employee emp = this.findById(id).get();
		emp.setArea(e.getArea());
		emp.setMail(e.getMail());
		emp.setNombre(e.getNombre());
		emp.setPassword(e.getPassword());
		this.mongoOp.save(emp, "employees");
	}

	@Override
	public Optional<Employee> findByMail(String mail) {
		Employee e = this.mongoOp.findOne(new Query(Criteria.where("mail").is(mail)), Employee.class);
		return Optional.ofNullable(e);
	}

	@Override
	public Optional<List<Order>> inspectOrders(Employee e) {
		List<Order> empOrders = this.mongoOp.find(new Query(Criteria.where("id_employee").is(e.getId())), Order.class);
		return Optional.ofNullable(empOrders);
	}

}
