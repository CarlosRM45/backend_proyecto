package com.gustilandia.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "venta")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVenta;
	
	@Column(name = "nro_venta")
	private int nroVenta;
	
	@Column(name = "id_tipo_comprobante_sunat")
	private Long idTipoComprobanteSunat;
	
	@Column(name = "correlativo_comprobante")
	private String correlativoComprobante;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_tarjeta")
	private Tarjeta tarjeta;
	
	@Column(name = "subtotal")
	private Double subtotal;
	
	@Column(name = "igv")
	private Double igv;
	
	@Column(name = "total")
	private Double total;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_venta_guardada")
	private Date fechaVentaGuardada;
	
	@Column(name = "id_estado")
	private Long idEstado;
	
	
	@OneToMany(mappedBy = "venta", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<VentaDetalle> ventaDetalle;

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public int getNroVenta() {
		return nroVenta;
	}

	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}

	public Long getIdTipoComprobanteSunat() {
		return idTipoComprobanteSunat;
	}

	public void setIdTipoComprobanteSunat(Long idTipoComprobanteSunat) {
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
	}

	public String getCorrelativoComprobante() {
		return correlativoComprobante;
	}

	public void setCorrelativoComprobante(String correlativoComprobante) {
		this.correlativoComprobante = correlativoComprobante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIgv() {
		return igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFechaVentaGuardada() {
		return fechaVentaGuardada;
	}

	public void setFechaVentaGuardada(Date fechaVentaGuardada) {
		this.fechaVentaGuardada = fechaVentaGuardada;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public List<VentaDetalle> getVentaDetalle() {
		return ventaDetalle;
	}

	public void setVentaDetalle(List<VentaDetalle> ventaDetalle) {
		this.ventaDetalle = ventaDetalle;
	}
	
	

}
