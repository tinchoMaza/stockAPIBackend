/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Supplier> findAll() {
		return supRepo.findAll();
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

	@Override
	public Supplier saveSupplier(Supplier sup) throws InvalidDataException {
		List<String> errorMsgs = ParamValidator.supplierParamsValidation(sup);
		if(this.supRepo.findByName(sup.getName()).isPresent()) {
			errorMsgs.add("Duplicated supplier! This one already exists in Database. You can update it instead");
		}
		if(!errorMsgs.isEmpty()) {
			throw new InvalidDataException(errorMsgs);
		}
		Optional<Supplier> savedSupplier = this.supRepo.save(sup);
		if(!Optional.ofNullable(savedSupplier).isPresent()) {
			errorMsgs.add("Error! Could not create supplier in DB. Please try again.");
			throw new InvalidDataException(errorMsgs);
		}
		return savedSupplier.get();
	}

	@Override
	public void delete(String id) throws SupplierException {
		Optional<Supplier> supplier = this.supRepo.findById(id);
		if(!supplier.isPresent()) throw new SupplierException("The supplier to delete was not found, id: " + id);
		this.supRepo.delete(id);
	}

	@Override
	public void update(Supplier updatedSupplier) throws InvalidDataException {
		List<String> errorMsgs = ParamValidator.supplierParamsValidation(updatedSupplier);
		Optional<Supplier> oldSupplier = this.supRepo.findById(updatedSupplier.getId());
		if(!oldSupplier.isPresent()) errorMsgs.add("Supplier not found, id: " + updatedSupplier.getId());
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		this.supRepo.update(updatedSupplier);
	}
	
	@Override
	public void update(String oldSupplierId, Supplier newData) throws InvalidDataException {

		List<String> errorMsgs = ParamValidator.supplierParamsValidation(newData);
		Optional<Supplier> oldSupplier = this.supRepo.findById(oldSupplierId);
		if(!oldSupplier.isPresent()) errorMsgs.add("Supplier not found, id: " + oldSupplierId);
		if(!errorMsgs.isEmpty()) throw new InvalidDataException(errorMsgs);
		this.supRepo.update(oldSupplierId, newData);
		
	}
}
