package com.belatrix.interns.StockAPIBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.paramValidations.paramValidations;
import com.belatrix.interns.StockAPIBackend.repository.SupplierRepository;

@Service("supplierService")
@Transactional
public class SupplierServiceImplem implements SupplierService {

	private SupplierRepository supRepo;
	private paramValidations valid;
	
	@Autowired
	public SupplierServiceImplem(SupplierRepository supRepo) {
		this.supRepo = supRepo;
	}
}
