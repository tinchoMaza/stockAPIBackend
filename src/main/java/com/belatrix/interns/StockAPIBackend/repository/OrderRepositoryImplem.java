package com.belatrix.interns.StockAPIBackend.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.entities.Status;

@Repository
public class OrderRepositoryImplem implements OrderRepository{

	private final MongoOperations mongoOp;

	@Autowired
	public OrderRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

	public Optional<List<Order>> getAllOrders() {
		LookupOperation lookupOperation = LookupOperation.newLookup().
				from("products").
				localField("orderedProducts").
				foreignField("_id").
				as("orderedProducts");
		Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
		List<Order> results = mongoOp.aggregate(aggregation, "orders", Order.class).getMappedResults();
		return Optional.ofNullable(results);
	}
	private int getLastNumber() {
		Optional<List<Order>> aux = this.getAllOrders();
		List<Order> orders = aux.get();
		int max = 0; //si no hay orders, devuelve 0
		max = Collections.max(orders, Comparator.comparing(s -> s.getNumber())).getNumber(); //el primer getNumber es para comparar. Eso me devuelve el objeto completo y uso el segundo getNumber para tener el numero
		return max;

	}

	public Order saveOrder(Order o) {
		int number = this.getLastNumber();
		o.setNumber(number+1);
		o.setStatus(Status.On_Hold);
		this.mongoOp.save(o);
		return this.findById(o.getId()).get();
	}

	public Optional<Order> findById(String id) {
		ObjectId _id = new ObjectId(id);
		Order o = this.mongoOp.findOne(new Query(Criteria.where("_id").is(_id)), Order.class);
		return Optional.ofNullable(o);
	}

}