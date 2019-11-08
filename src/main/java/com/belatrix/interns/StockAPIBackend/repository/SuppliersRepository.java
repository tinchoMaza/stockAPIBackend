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
	
	public List<Supplier> findAll();
	
	public Optional<Supplier> findById(String id);
	
	public Optional<Supplier> findByName(String name);

	Optional<Supplier> save(Supplier sup);

	void delete(String id);

	void update(Supplier sup);

	void update(String orderId, Supplier newData);

}
