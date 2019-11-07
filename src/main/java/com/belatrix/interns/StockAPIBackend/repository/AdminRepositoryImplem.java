package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.entities.Admin;
import com.belatrix.interns.StockAPIBackend.entities.Order;

@Repository
public class AdminRepositoryImplem implements AdminRepository {

private final MongoOperations mongoOp;
	
	
	@Autowired
	public AdminRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}


	@Override
	public Optional<Admin> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Admin a = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Admin.class);
		return Optional.ofNullable(a);
	}


	@Override
	public Optional<Admin> findByName(String name) {
		Admin a = this.mongoOp.findOne(new Query(Criteria.where("name").is(name)), Admin.class);
		return Optional.ofNullable(a);
	}


	@Override
	public Optional<Admin> findByMail(String mail) {
		Admin a = this.mongoOp.findOne(new Query(Criteria.where("mail").is(mail)), Admin.class);
		return Optional.ofNullable(a);
	}


	@Override
	public Admin save(Admin a) {
		this.mongoOp.save(a);
		return findById(a.getId()).get();
	}


	@Override
	public void delete(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Admin.class);
	}


	@Override
	public void update(String id, Admin updatedAdm) {
		Admin a = this.findById(id).get();
		a.setMail(updatedAdm.getMail());
		a.setName(updatedAdm.getName());
		a.setPassword(updatedAdm.getPassword());
		a.setOrdersToBuy(updatedAdm.getOrdersToBuy());
		this.mongoOp.save(a, "admins");
	}


	@Override
	public void storeOrdersToBuy(Admin a, List<Order> ordersToBuy) {
		a.setOrdersToBuy(ordersToBuy);
		this.mongoOp.save(a, "admins");
	}


	@Override
	public List<Order> showOrdersToBuy(Admin a) {
		return a.getOrdersToBuy();
	}
	
	
	
}
