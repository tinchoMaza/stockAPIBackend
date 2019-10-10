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

@Repository
public class EmployeeRepositoryImplem implements EmployeeRepository {
	
	private final MongoOperations mongoOp;
	
	@Autowired
	public EmployeeRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

	public Optional<List<Employee>> getAllEmployees() {
		List<Employee> players = this.mongoOp.find(new Query(), Employee.class);
		return Optional.ofNullable(players);
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
	
	public void updateEmployee(Employee e) {
		//el save tambien hace el update. HAY QUE PASARLE EL ID SI O SI !!
		this.mongoOp.save(e);
	}

}
