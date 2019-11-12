package com.belatrix.interns.StockAPIBackend.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.dbo.OrderDBO;
import com.belatrix.interns.StockAPIBackend.entities.Order;

@Repository
public class OrderRepositoryImplem implements OrderRepository{

	private final MongoOperations mongoOp;

	@Autowired
	public OrderRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

	public Optional<List<OrderDBO>> getAllOrders() {
		List<OrderDBO> results = mongoOp.find(new Query(), OrderDBO.class);
		return Optional.ofNullable(results);
	}
	private int getLastNumber() {
		Optional<List<OrderDBO>> aux = this.getAllOrders();
		List<OrderDBO> orders = aux.get();
		int max = 0; //si no hay orders, devuelve 0
		max = Collections.max(orders, Comparator.comparing(s -> s.getNumber())).getNumber(); //el primer getNumber es para comparar. Eso me devuelve el objeto completo y uso el segundo getNumber para tener el numero
		return max;

	}

	public Optional<OrderDBO> saveOrder(Order o) {
		int number = this.getLastNumber();
		o.setNumber(number+1);
		o.set_id(new ObjectId());
		this.mongoOp.save(o.toDBO());
		return this.findById(o.get_id().toHexString());
	}

	public Optional<OrderDBO> findById(String id) {
		ObjectId _id = new ObjectId(id);
		OrderDBO o = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), OrderDBO.class);
		return Optional.ofNullable(o);
	}
	
	public Optional<OrderDBO> findByNumber(int number) {
		OrderDBO o = this.mongoOp.findOne(new Query(Criteria.where("number").is(number)), OrderDBO.class);
		return Optional.ofNullable(o);
	}

	public Optional<OrderDBO> updateOrder(Order o) {
		OrderDBO odb = this.mongoOp.save(o.toDBO());
		return Optional.ofNullable(odb);
	}

	public void deleteOrder(String _id) {
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), OrderDBO.class);
	}

}