package com.belatrix.interns.StockAPIBackend.entities;

public class Cliente{
	private int id;
	private String nombre;
	private int limiteCredito;
	private int facturacion;
	private long cuentaCorriente;
	private String email;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getLimiteCredito() {
		return limiteCredito;
	}
	
	public void setLimiteCredito(int limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	
	public int getFacturacion() {
		return facturacion;
	}
	
	public void setFacturacion(int facturacion) {
		this.facturacion = facturacion;
	}
	
	public long getCuentaCorriente() {
		return cuentaCorriente;
	}
	
	public void setCuentaCorriente(long cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
