package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.repository.DepositRepository;

/**
 * @author fbalsas
 */
@Service("depositService")
@Transactional
public class DepositServiceImplem implements DepositService {

	private DepositRepository depRepo;
	
	@Autowired
	public DepositServiceImplem(DepositRepository depRepo) {
		this.depRepo = depRepo;
	}
	
	@Override
	public List<Product> getAllProducts() {
		Optional<List<Product>> prods = depRepo.getAllProducts();
		return prods.get();
	}

	@Override
	public Product findById(String id) throws ProductException {
		Optional<Product> prod = depRepo.findById(id);
		if(!prod.isPresent()) throw new ProductException("No product stored for this id: " + id);
		return prod.get();
	}

	@Override
	public Product findByName(String name) throws ProductException {
		Optional<Product> prod = depRepo.findByName(name);
		if(!prod.isPresent()) throw new ProductException("There is no " + name + " in stock");
		return prod.get();
	}

	@Override
	public Product saveProduct(Product p) {
		return depRepo.saveProduct(p);
	}

	@Override
	public void deleteProduct(String id) {
		depRepo.deleteProduct(id);
	}

	@Override
	public void updateProduct(Product p) {
		depRepo.updateProduct(p);	
	}

	@Override
	public boolean checkReserveStock(String id) throws ProductException {
		Optional<Product> prod = depRepo.findById(id);
		if(!prod.isPresent()) throw new ProductException("No product stored for this id: " + id);
		return depRepo.checkReserveStock(prod.get());
	}

}
