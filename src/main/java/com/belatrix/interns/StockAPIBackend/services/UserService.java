package com.belatrix.interns.StockAPIBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.belatrix.interns.StockAPIBackend.entities.Employee;
import com.belatrix.interns.StockAPIBackend.entities.Admin;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private EmployeeService empServ;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		List<GrantedAuthority> roles = new ArrayList<>();
		Employee emp = this.empServ.findByMail(mail);
		//Admin adm = this.admServ.findByMail(mail);
		
		if(Optional.ofNullable(emp).isPresent()) {
			roles.add(new SimpleGrantedAuthority("USER"));
			UserDetails userDet = new User(emp.getNombre(), emp.getPassword(), roles);
			return userDet;
		}/*else if(Optional.ofNullable(adm).isPresent()) {
			roles.add(new SimpleGrantedAuthority("ADMIN"));
			UserDetails userDet = new User(adm.getNombre(), adm.getPassword(), roles);
			return userDet;
		}*/else throw new UsernameNotFoundException("Mail not found, please check the data and try again");

	}

}