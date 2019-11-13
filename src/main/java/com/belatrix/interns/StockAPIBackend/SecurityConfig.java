package com.belatrix.interns.StockAPIBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.belatrix.interns.StockAPIBackend.services.UserService;

@Configuration
@EnableWebSecurity(debug = false)//Para todos los request de HTTP, es una configuration class
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService usDetServ;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usDetServ).passwordEncoder(bcrypt);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{//Autenticacion de cualquier peticion entrante para visualizar respuesta
		http
			.csrf().disable()//Deshabilita el csrf, medida de seguridad que dependiendo los proyectos puede causar mas problemas que ventajas si no se configura tokens
			.authorizeRequests()
				.antMatchers("/orders/**", "/deposit/**", "/suppliers/**").hasRole("ADMIN")//
				.antMatchers("/products", "/orders-status", "/create-order").access("hasRole('ADMIN') or hasRole('USER')")//
				.antMatchers("/home", "/profile/**", "/webjars/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/home").permitAll()
				.defaultSuccessUrl("/profile")
				.failureUrl("/loginPage?error")
				.usernameParameter("mail").passwordParameter("password")
			.and()
				.logout().logoutSuccessUrl("/loginPage?logout");
				
	}
}
