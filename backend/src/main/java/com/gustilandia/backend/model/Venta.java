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
	
	@Column(name = "numero_venta")
	private int numeroVenta;
	
	@ManyToOne
	@JoinColumn(name = "id_repartidor")
	private Empleado repartidor;
	
	@Column(name = "correlativo_comprobante")
	private String correlativoComprobante;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "id_tipo_comprobante_sunat")
	private TipoComprobanteSunat tipoComprobanteSunat;

	

	
	@OneToMany(mappedBy = "venta", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<VentaDetalle> ventaDetalle;


	public Empleado getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Empleado repartidor) {
		this.repartidor = repartidor;
	}

	public Date getFechEntrega() {
		return fechaEntrega;
	}

	public void setFechEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public int getNroVenta() {
		return numeroVenta;
	}

	public void setNroVenta(int numeroVenta) {
		this.numeroVenta = numeroVenta;
	}

	// public Long getIdTipoComprobanteSunat() {
	// 	return idTipoComprobanteSunat;
	// }

	// public void setIdTipoComprobanteSunat(Long idTipoComprobanteSunat) {
	// 	this.idTipoComprobanteSunat = idTipoComprobanteSunat;
	// }

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


	public List<VentaDetalle> getVentaDetalle() {
		return ventaDetalle;
	}

	public void setVentaDetalle(List<VentaDetalle> ventaDetalle) {
		this.ventaDetalle = ventaDetalle;
	}

	public int getNumeroVenta() {
		return numeroVenta;
	}

	public void setNumeroVenta(int numeroVenta) {
		this.numeroVenta = numeroVenta;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public TipoComprobanteSunat getTipoComprobanteSunat() {
		return tipoComprobanteSunat;
	}

	public void setTipoComprobanteSunat(TipoComprobanteSunat tipoComprobanteSunat) {
		this.tipoComprobanteSunat = tipoComprobanteSunat;
	}
	
	

}
