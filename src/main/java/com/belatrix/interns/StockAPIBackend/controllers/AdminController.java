package com.belatrix.interns.StockAPIBackend.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.belatrix.interns.StockAPIBackend.services.AdminService;
import com.belatrix.interns.StockAPIBackend.entities.Admin;
import com.belatrix.interns.StockAPIBackend.exceptions.InvalidDataException;
import com.belatrix.interns.StockAPIBackend.paramValidations.*;

@RestController
@RequestMapping("/stockAPI/administration")
public class AdminController {
	
	private final AdminService admServ;
	//private Admin a;
	
	@Autowired
	public AdminController(AdminService admServ) {
		this.admServ = admServ;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showLogin(ModelMap model) {
		return "home";
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Serializable> save(@RequestBody @Valid Admin a){
		List<String> ex = paramValidations.adminParamsValidator(a);
		if(!ex.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid format, please check the fields again");
		Admin ad;
		try {
			ad = this.admServ.save(a);
			return ResponseEntity.status(HttpStatus.CREATED).body(ad);
		}catch(DuplicateKeyException exep) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("The indicated email is already registered");
		}catch(Exception exept) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error. We'll look into it. This is the error: " + exept.getMessage());
		}
	}
	
	@PutMapping("/{_id}")
	public ResponseEntity<List<String>> update(@PathVariable("_id") String _id, @RequestBody @Valid Admin a){
		try {
			this.admServ.update(_id, a);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessages());
		}
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<List<String>> delete(@PathVariable("_id") String _id){
		try {
			this.admServ.delete(_id);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessages());
		}
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<String>> findById(@RequestBody @Valid String _id){
		try {
			this.admServ.findById(_id);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessages());
		}
	}
	
	@GetMapping("/find-name")
	public ResponseEntity<List<String>> findByName(@RequestBody @Valid String name){
		try {
			this.admServ.findByName(name);
			return ResponseEntity.ok().body(new ArrayList<String>());
		}catch(InvalidDataException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessages());
		}
	}
	
}
