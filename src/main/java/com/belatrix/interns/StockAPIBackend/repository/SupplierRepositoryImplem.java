package com.belatrix.interns.StockAPIBackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierRepositoryImplem implements SupplierRepository{

	private final MongoOperations mongoOp;
	
	@Autowired
	public SupplierRepositoryImplem(MongoOperations mongoOperations) {
		this.mongoOp = mongoOperations;
	}

}
