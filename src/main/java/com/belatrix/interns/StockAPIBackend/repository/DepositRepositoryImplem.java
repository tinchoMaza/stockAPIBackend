package com.belatrix.interns.StockAPIBackend.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.Pair;

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
		Product prod = this.mongoOp.findOne(new Query(Criteria.where("name").is(name)), Product.class);
		return Optional.ofNullable(prod);
	}

	@Override
	public Product saveProduct(Product p) {
		this.mongoOp.save(p);
		return findById(p.getId()).get();
	}

	@Override
	public void deleteProduct(String id) {
		ObjectId _id = new ObjectId(id);
		this.mongoOp.findAndRemove(new Query(Criteria.where("_id").is(_id)), Product.class);
	}

	@Override
	public void updateProduct(Product p) {
		//el save tambien hace el update. HAY QUE PASARLE EL ID SI O SI !!
		this.mongoOp.save(p);
	}

	@Override
	public List<Pair<String, Integer>> showAllStock() {
		List<Product> allProd = getAllProducts().get();
		List<Pair<String,Integer>> allStock = new ArrayList<Pair<String, Integer>>();
		for(Product prod : allProd) {
			allStock.add(Pair.of(prod.getName(), prod.getStock()));
		}
		return allStock;
	}

}