package com.belatrix.interns.StockAPIBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belatrix.interns.StockAPIBackend.entities.Admin;
import com.belatrix.interns.StockAPIBackend.entities.Order;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.repository.AdminRepository;

@Service("adminService")
@Transactional
public class AdminServiceImplem implements AdminService {
	
	private AdminRepository admRepo;
	
	@Autowired
	public AdminServiceImplem(AdminRepository admRepo) {
		this.admRepo = admRepo;
	}

	@Override
	public Admin findById(String id) throws InvalidDataException {
		Optional<Admin> a = this.admRepo.findById(id);
		if(a.isPresent()) return a.get();
		List<String> msg = new ArrayList<String>();
		msg.add("Invalid admin ID. Please, check the data and try again");
		throw new InvalidDataException(msg);
	}

	@Override
	public Admin findByName(String name) throws InvalidDataException {
		Optional<Admin> a = this.admRepo.findByName(name);
		if(a.isPresent()) return a.get();
		List<String> msg = new ArrayList<String>();
		msg.add("Invalid admin name");
		throw new InvalidDataException(msg);
	}

	@Override
	public Admin findByMail(String mail) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin save(String id) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String id, Admin updatedAdm) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeOrdersToBuy(String id, List<Order> ordersToBuy) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> showOrdersToBuy(String id) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

}
