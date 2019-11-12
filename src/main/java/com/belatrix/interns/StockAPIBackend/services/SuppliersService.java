/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.services;

import java.util.List;

import com.belatrix.interns.StockAPIBackend.entities.Supplier;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.exceptions.SupplierException;

/**
 * @author aluna
 *
 */
public interface SuppliersService  {

	List<Supplier> findAll();

	Supplier findById(String id) throws SupplierException;

	Supplier findByName(String name) throws SupplierException;

	Supplier saveSupplier(Supplier sup) throws InvalidDataException;

	void delete(String id) throws SupplierException;

	void update(String oldSupplierId, Supplier newData) throws InvalidDataException;

	void update(Supplier updatedSupplier) throws InvalidDataException;
	
	
	

}
