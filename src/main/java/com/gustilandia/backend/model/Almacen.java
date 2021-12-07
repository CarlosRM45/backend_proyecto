package com.gustilandia.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "almacen")
public class Almacen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAlmacen;
	
	@Column(name = "id_producto")
	private long idProducto;
	
	@Column(name = "id_compra_detalle")
	private long idCompraDetalle;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "fecha_venc")
	@Temporal(TemporalType.DATE)
	private Date fechaVenc;
	
	@Column(name = "id_estado")
	private long idEstado;

	public long getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(long idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public long getIdCompraDetalle() {
		return idCompraDetalle;
	}

	public void setIdCompraDetalle(long idCompraDetalle) {
		this.idCompraDetalle = idCompraDetalle;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getFechaVenc() {
		return fechaVenc;
	}

	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	public long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}
	
	

}
