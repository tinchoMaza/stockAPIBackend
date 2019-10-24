package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.entities.Order;

@Repository
public class OrdersRepositoryImplem implements OrdersRepository {
	
	private final MongoOperations mongoOp;
	
	@Autowired
	public OrdersRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

	/**
	 * 
	 */
	@Override
	public void save(Order order) {
		this.mongoOp.save(order, "orders");
		// TODO call method to send notification of new order
	}

	@Override
	public void delete(ObjectId id) {
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(id)), Order.class);	
	}

	@Override
	public List<Order> findAll() {
		List<Order> orders = this.mongoOp.find(new Query(), Order.class);
		return orders;
	}

	@Override
	public Optional<Order> findById(ObjectId id) {
		Order order = this.mongoOp.findOne(new Query(Criteria.where("_id").is(id)), Order.class);
		return Optional.ofNullable(order);
	}

	@Override
	public void update(ObjectId orderId, Order lastOrder) {
		Order o = findById(orderId).get();
		o.setDeparture_date(lastOrder.getDeparture_date());
		o.setOrderedProducts(lastOrder.getOrderedProducts());
		o.setStatus(lastOrder.getStatus());
		this.mongoOp.save(o, "orders");
	}


}
