/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Product;
import com.belatrix.interns.StockAPIBackend.entities.Supplier;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.SupplierException;
import com.belatrix.interns.StockAPIBackend.paramValidations.ParamValidator;
import com.belatrix.interns.StockAPIBackend.repository.SuppliersRepository;

/**
 * @author aluna
 *
 */
@Service("suppliersService")
@Transactional
public class SuppliersServiceImplem implements SuppliersService {

private SuppliersRepository supRepo;
	
	@Autowired
	public SuppliersServiceImplem(SuppliersRepository supRepo) {
		this.supRepo = supRepo;
	}

	@Override
	public List<Supplier> findAllSuppliers() {
		return supRepo.findAllSuppliers();
	}

	@Override
	public Supplier findById(String id) throws SupplierException {
		Optional<Supplier> sup = supRepo.findById(id);
		if (sup.isPresent())
			return sup.get();
		else
			throw new SupplierException("Supplier with id: " + id + " was not found. Please try another id.");
	}

	@Override
	public Supplier findByName(String name) throws SupplierException {
		Optional<Supplier> sup = supRepo.findByName(name);
		if (sup.isPresent())
			return sup.get();
		else
			throw new SupplierException("Supplier with name: " + name + " not found. Please try another name.");
	}

	public Supplier saveSupplier(Supplier sup) {
		List<String> errorMsgs = ParamValidator.productFieldsValidator(sup);
		if(this.supRepo.findByName(sup.getName()).isPresent()) errorMsgs.add("Duplicated product! This one already exists in Database. You can update it instead");
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		Optional<Supplier> savedSupplier = this.supRepo.saveSupplier(sup);
		if(!Optional.ofNullable(savedSupplier).isPresent()) {
			errorMsgs.add("Error! Could store item in DB");
			throw new InvalidDataException(errorMsgs);
		}
		return checkProd.get();
	}

	public void deleteSupplier(String id) {
		supRepo.deleteSupplier(id);
	}

	public void updateSupplier(Supplier sup) {
		supRepo.updateSupplier(sup);
	}
}
