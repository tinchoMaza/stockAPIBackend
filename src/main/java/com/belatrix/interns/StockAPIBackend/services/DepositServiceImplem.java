package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.EmptyDepositException;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.ProductException;
import com.belatrix.interns.StockAPIBackend.repository.DepositRepository;
import com.belatrix.interns.StockAPIBackend.paramValidations.*;

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
		if(paramValidations.descriptionContainsIllegalsCharacters(id)) throw new ProductException("No product stored for this id: " + id);
		Optional<Product> prod = depRepo.findById(id);
		if(!prod.isPresent()) throw new ProductException("No product stored for this id: " + id);
		return prod.get();
	}

	@Override
	public Product findByName(String name) throws ProductException {
		if(paramValidations.containsIllegalsCharacters(name)) throw new ProductException("There is no " + name + " in stock");
		Optional<Product> prod = depRepo.findByName(name);
		if(!prod.isPresent()) {
			throw new ProductException("There is no " + name + " in stock");
		}
		return prod.get();
	}

	@Override
	public Product saveProduct(Product p) throws InvalidDataException{
		List<String> errorMsgs = paramValidations.productFieldsValidator(p);
		if(this.depRepo.findByName(p.getName()).isPresent()) errorMsgs.add("Duplicated product! This one already exists in Database. You can update it instead");
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		Optional<Product> checkProd = this.depRepo.saveProduct(p);
		if(!Optional.ofNullable(checkProd).isPresent()) {
			errorMsgs.add("Error! Could store item in DB");
			throw new InvalidDataException(errorMsgs);
		}
		return checkProd.get();
	}

	@Override
	public void deleteProduct(String id) throws ProductException {
		if(paramValidations.descriptionContainsIllegalsCharacters(id) || !this.depRepo.findById(id).isPresent()) throw new ProductException("No product stored for this id");
		depRepo.deleteProduct(id);
	}

	@Override
	public void updateProduct(String id, Product p) throws InvalidDataException{
		List<String> errorMsgs = paramValidations.productFieldsValidator(p);
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		if(!this.depRepo.findById(id).isPresent()) {
			errorMsgs.add("Invalid id, cannot update this product");
			throw new InvalidDataException(errorMsgs);
		}
		depRepo.updateProduct(id, p);	
	}

	@Override
	public boolean checkReserveStock(String id) throws ProductException {
		Optional<Product> prod = depRepo.findById(id);
		if(!prod.isPresent()) throw new ProductException("No product stored for this id: " + id);
		return depRepo.checkReserveStock(prod.get());
	}

	@Override
	public List<Pair<String, Integer>> showAllStock() throws EmptyDepositException {
		List<Pair<String, Integer>> allProd = depRepo.showAllStock();
		if(allProd.isEmpty()) throw new EmptyDepositException("There are no items stored in the deposit");
		return allProd;
	}

	@Override
	public int showStockOfAProduct(String id) {
		return this.depRepo.showStockOfAProduct(id);
	}

	@Override
	public List<Product> showProductsWithLowStock() {
		return this.depRepo.showProductsWithLowStock();
	}

}
