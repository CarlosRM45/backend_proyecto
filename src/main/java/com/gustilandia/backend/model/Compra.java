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
@Table(name = "compra")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCompra;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_comprobante_sunat", nullable = false)
	private TipoComprobanteSunat tipoComprobanteSunat;
	
	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false)
	private Proveedor proveedor;
	
	@Column(name = "subtotal")
	private Double subtotal;
	
	@Column(name = "impuesto")
	private Double impuesto;
	
	@Column(name = "total")
	private Double total;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_crea")
	private Date fechaCrea;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_crea", nullable = false)
	private Usuario usuarioCrea;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_edita")
	private Date fechaEdita;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_edita")
	private Usuario usuarioEdita;
	
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;
	
	@OneToMany(mappedBy = "compra", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private List<CompraDetalle> compraDetalle;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public TipoComprobanteSunat getTipoComprobanteSunat() {
		return tipoComprobanteSunat;
	}

	public void setTipoComprobanteSunat(TipoComprobanteSunat tipoComprobanteSunat) {
		this.tipoComprobanteSunat = tipoComprobanteSunat;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Usuario getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(Usuario usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Date getFechaEdita() {
		return fechaEdita;
	}

	public void setFechaEdita(Date fechaEdita) {
		this.fechaEdita = fechaEdita;
	}

	public Usuario getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(Usuario usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<CompraDetalle> getCompraDetalle() {
		return compraDetalle;
	}

	public void setCompraDetalle(List<CompraDetalle> compraDetalle) {
		this.compraDetalle = compraDetalle;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}


	
	

}
