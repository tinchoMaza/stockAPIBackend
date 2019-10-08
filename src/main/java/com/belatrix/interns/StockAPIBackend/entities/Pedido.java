package com.belatrix.interns.StockAPIBackend.entities;

import java.util.List;

public class Pedido {
	private int idCliente;
	private List<Producto> productosPedidos;
	private int cantidadPedido;
	private int totalPedido;
	
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	
	public int getCantidadPedido() {
		return cantidadPedido;
	}
	
	public void setCantidadPedido(int cantidadPedido) {
		this.cantidadPedido = cantidadPedido;
	}
	
	public int getTotalPedido() {
		return totalPedido;
	}
	
	public void setTotalPedido(int totalPedido) {
		this.totalPedido = totalPedido;
	}

	public List<Producto> getProductos() {
		return productosPedidos;
	}

	public void setProductos(List<Producto> productosPedidos) {
		this.productosPedidos = productosPedidos;
	}
	
	
}
