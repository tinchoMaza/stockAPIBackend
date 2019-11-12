/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.entities.Supplier;

/**
 * @author aluna
 *
 */
@Repository
public class SuppliersRepositoryImplem implements SuppliersRepository {

	/**
	 * 
	 */
	private final MongoOperations mongoOp;

	@Autowired
	public SuppliersRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}
	
	@Override
	public List<Supplier> findAll() {
		List<Supplier> suppliers = this.mongoOp.findAll(Supplier.class, "suppliers");
		return suppliers;
	}

	@Override
	public Optional<Supplier> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Supplier e = this.mongoOp.findById(_id, Supplier.class);
		return Optional.ofNullable(e);
	}

	@Override
	public Optional<Supplier> findByName(String name) {
		Supplier supplier = this.mongoOp.findOne(new Query(Criteria.where("name").is(name)), Supplier.class);
		return Optional.ofNullable(supplier);
	}

	@Override
	public Optional<Supplier> save(Supplier sup) {
		Supplier savedSup = this.mongoOp.save(sup);
		return findById(savedSup.getId());
	}

	@Override
	public void delete(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Supplier.class);
	}
	
	@Override
	public void update(Supplier sup) {
		this.mongoOp.findAndReplace(new Query(Criteria.where("name").is(sup.getName())), sup);
	}
	
	@Override
	public void update(String orderId, Supplier newData) {
		Supplier updatedSupplier = findById(orderId).get();
		updatedSupplier.setName(newData.getName());
		updatedSupplier.setPhoneNumber(newData.getPhoneNumber());
		updatedSupplier.setWebUrl(newData.getWebUrl());
		updatedSupplier.setMail(newData.getMail());
		this.mongoOp.save(updatedSupplier, "orders");
	}

}
