package com.gustilandia.backend.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gustilandia.backend.validation.Stock;

public class DTODetalleVenta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Debe seleccionar un producto")
	@Min(value = 1, message = "Debe seleccionar un producto")
	private Long idProducto;
	
	private Double porcentajeDescuento;
	
	@Min(value = 1, message = "La cantidad minima de pedido es 1")
	private int cantidad;
	
	public DTODetalleVenta() {
	}
	
	public DTODetalleVenta(Long idProducto, Double porcentajeDescuento, int cantidad) {
		this.idProducto = idProducto;
		this.porcentajeDescuento = porcentajeDescuento;
		this.cantidad = cantidad;
	}
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
