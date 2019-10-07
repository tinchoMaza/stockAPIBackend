package com.belatrix.interns.StockAPIBackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stockAPI")
public class ControladorPrueba {
	
	@GetMapping("")
	public String getTest() {
		return "La API de stock esta funcionando 10 puntos";
	}

}
