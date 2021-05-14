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

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;
	
	@Column(name = "producto")
	private String producto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio")
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;
	
	@Column(name = "stock")
	private int stock;
	
	@ManyToOne
	@JoinColumn(name = "id_unidad_medida", nullable = false)
	private UnidadMedida unidadMedida;
	
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
	@JoinColumn(name = "id_usuario_edita", nullable = false)
	private Usuario usuarioEdita;
	
	@Column(name = "id_estado")
	private int estado;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	
	
}
