package com.gustilandia.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "compra_detalle")
public class CompraDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCompraDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fech_venc")
	private Date fech_venc;
	
	@Column(name = "precio_compra")
	private Double precioCompra;

	public Long getIdCompraDetalle() {
		return idCompraDetalle;
	}

	public void setIdCompraDetalle(Long idCompraDetalle) {
		this.idCompraDetalle = idCompraDetalle;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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


	
	

}
