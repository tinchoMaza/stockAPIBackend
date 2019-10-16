package com.belatrix.interns.StockAPIBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.exceptions.EmptyDepositException;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
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
	
	/**
	 * @param toExamine
	 * @return
	 */
	public boolean containsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[0123456789½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°,.:;/!$~#@*+%&()=¿{}<>\\[\\\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	
	/**
	 * @param toExamine
	 * @return
	 */
	public boolean descriptionContainsIllegalsCharacters(String toExamine) {
		Pattern pattern = Pattern.compile("[½¼≤√ⁿ²ƒ±₧÷'£╛╜╧⌐╕ªº°!$~#@*+%&=¿{}<>\\[\\\\]|\"\\_^]");
		Matcher matcher = pattern.matcher(toExamine);
		return matcher.find();
	}
	
	private List<String> productFieldsValidator(Product prod) {
		// Checks if the fields of the kinship are null or not
		List<String> messages = new ArrayList<String>();
		
		if ((prod.getName().compareTo("") == 0) || containsIllegalsCharacters(prod.getName())) {
			messages.add("Invalid/Empty name field for product");
		}
		
		if ((prod.getDescription().compareTo("") == 0) || descriptionContainsIllegalsCharacters(prod.getDescription())) {
			messages.add("Empty/Invalid characters in product´s description");
		} 
		
		if (prod.getStock() < 0) {
			messages.add("Invalid value for stock, it can´t be less than 0");
		}
		
		if (prod.getMinReserveStock() < 0) {
			messages.add("Invalid value for reserve stock, it can´t be less than 0");
		}
		
		if (prod.getArrivalDate().compareTo(prod.getDepartureDate()) > 0) {
			messages.add("A product cannot be delivered before it arrives");
		}
		
		return messages;
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
	public Product saveProduct(Product p) throws InvalidDataException{
		List<String> errorMsgs = productFieldsValidator(p);
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		return depRepo.saveProduct(p);
	}

	@Override
	public void deleteProduct(String id) {
		depRepo.deleteProduct(id);
	}

	@Override
	public void updateProduct(String id, Product p) throws InvalidDataException{
		List<String> errorMsgs = productFieldsValidator(p);
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
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
		return null;
	}

	@Override
	public int showStockOfAProduct(String id) {
		return this.depRepo.showStockOfAProduct(id);
	}

}
