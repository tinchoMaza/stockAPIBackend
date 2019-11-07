/**
 * 
 */
package com.belatrix.interns.StockAPIBackend.repository;

import java.util.List;
import java.util.Optional;

import com.belatrix.interns.StockAPIBackend.entities.Supplier;

/**
 * @author aluna
 *
 */
public interface SuppliersRepository {
	
	public List<Supplier> findAllSuppliers();
	
	public Optional<Supplier> findById(String id);
	
	public Optional<Supplier> findByName(String name);
	
	public Optional<Supplier> saveSupplier(Supplier sup);
	
	public void deleteSupplier(String id);
	
	public void updateSupplier(Supplier sup);

}
