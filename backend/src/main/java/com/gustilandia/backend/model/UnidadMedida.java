package com.gustilandia.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "unidad_medida")
public class UnidadMedida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUnidadMedida;
	
	@Column(name = "unidad_medida")
	private String unidadMedida;
	
	@Column(name = "codigo_sunat")
	private String codigoSunat;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_crea")
	private Date fechaCrea;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_crea")
	private Usuario usuarioCrea;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_edita")
	private Date fechaEdita;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_edita")
	private Usuario usuarioEdita;
	
	@Column(name = "id_estado")
	private int estado;

	public Long getIdUnidadMedida() {
		return idUnidadMedida;
	}

	public void setIdUnidadMedida(Long idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getCodigoSunat() {
		return codigoSunat;
	}

	public void setCodigoSunat(String codigoSunat) {
		this.codigoSunat = codigoSunat;
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


	

}
