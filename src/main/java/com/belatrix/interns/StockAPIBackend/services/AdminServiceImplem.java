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
import com.belatrix.interns.StockAPIBackend.paramValidations.*;

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
		msg.add("Invalid admin name. Please, check the data and try again");
		throw new InvalidDataException(msg);
	}

	@Override
	public Admin findByMail(String mail) throws InvalidDataException {
		Optional<Admin> a = this.admRepo.findByMail(mail);
		if(a.isPresent()) return a.get();
		List<String> msg = new ArrayList<String>();
		msg.add("Invalid admin mail. Please, check the data and try again");
		throw new InvalidDataException(msg);
	}

	@Override
	public Admin save(Admin a) {
		return this.admRepo.save(a);
	}

	@Override
	public void delete(String id) throws InvalidDataException {
		Optional<Admin> a = this.admRepo.findById(id);
		if(!a.isPresent()) {
			List<String> msg = new ArrayList<String>();
			msg.add("Invalid admin ID. Please, check the data and try again");
			throw new InvalidDataException(msg);
		}
		this.admRepo.delete(id);
	}

	@Override
	public void update(String id, Admin updatedAdm) throws InvalidDataException {
		List<String> msgs = new ArrayList<String>();
		msgs = paramValidations.adminParamsValidator(updatedAdm);
		if(!msgs.isEmpty()) throw new InvalidDataException(msgs);
		
		Optional<Admin> a = this.admRepo.findById(id);
		if(!a.isPresent()) {
			msgs.add("Invalid admin ID. Please, check the data and try again");
			throw new InvalidDataException(msgs);
		}
		
		this.admRepo.update(id, updatedAdm);
		
	}

	@Override
	public void storeOrdersToBuy(String id, List<Order> ordersToBuy) throws InvalidDataException {
		List<String> msgs = new ArrayList<String>();
		
		Optional<Admin> a = this.admRepo.findById(id);
		if(!a.isPresent()) {
			msgs.add("Invalid admin ID. Please, check the data and try again");
		}
		
		if(ordersToBuy.isEmpty()) {
			msgs.add("There are no reposition order/to buy orders");
		}
		
		if(!msgs.isEmpty()) throw new InvalidDataException(msgs);
		
		this.admRepo.storeOrdersToBuy(a.get(), ordersToBuy);
	}

	@Override
	public List<Order> showOrdersToBuy(String id) throws InvalidDataException {
		Optional<Admin> a = this.admRepo.findById(id);
		if(!a.isPresent()) {
			List<String> msg = new ArrayList<String>();
			msg.add("Invalid admin ID. Please, check the data and try again");
			throw new InvalidDataException(msg);
		}
		return this.admRepo.showOrdersToBuy(a.get());
	}

}
