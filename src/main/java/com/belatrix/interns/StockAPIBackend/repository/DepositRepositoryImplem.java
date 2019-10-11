package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.belatrix.interns.StockAPIBackend.entities.Product;

/**
 * @author fbalsas
 */

@Repository
public class DepositRepositoryImplem implements DepositRepository{

	private final MongoOperations mongoOp;
	
	@Autowired
	public DepositRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}
	
	/**
	 * @author fbalsas
	 */
	@Override
	public boolean checkReserveStock(Product producto) {
		if(producto.getStock() > producto.getMinReserveStock()) return true;
		return false;
	}

	@Override
	public Optional<List<Product>> getAllProducts() {
		List<Product> prods = this.mongoOp.find(new Query(), Product.class);
		return  Optional.ofNullable(prods);
	}

	@Override
	public Optional<Product> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product saveProduct(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteProduct(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateProduct(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

}