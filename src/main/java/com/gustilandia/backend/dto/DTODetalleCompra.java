package com.gustilandia.backend.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DTODetalleCompra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCompraDetalle;
	
	@NotNull(message = "Debe seleccionar un producto")
	@Min(value = 1, message = "Debe seleccionar un producto")
	private Long idProducto;
	
	@Min(value = 1, message = "La cantidad minima de pedido es 1")
	private int cantidad;
	
	@NotNull(message = "Debe ingresar la fecha de vencimiento")
	private Date fech_venc;
	
	@DecimalMin(value = "0.01", message = "Debe ingresar un valor mayor a 0.00 para el precio")
	private Double precioCompra;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Date getFech_venc() {
		return fech_venc;
	}

	public void setFech_venc(Date fech_venc) {
		this.fech_venc = fech_venc;
	}

	public Long getIdCompraDetalle() {
		return idCompraDetalle;
	}

	public void setIdCompraDetalle(Long idCompraDetalle) {
		this.idCompraDetalle = idCompraDetalle;
	}


	
	
	
	

}
