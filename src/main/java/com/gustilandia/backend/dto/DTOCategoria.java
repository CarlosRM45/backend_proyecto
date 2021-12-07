package com.gustilandia.backend.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOCategoria {
	
	private Long idCategoria;
	
	@NotNull(message = "Debe ingresar una categoria")
	@Size(min = 3, message = "Debe ingresar un nombre con minimo   3 caracteres")
	private String categoria;
	
	private Date fechaCrea;
	
	private Long UsuarioCrea;
	
	private Date fechaEdita;
	
	private Long UsuarioEdita;
	
	private Long idEstado;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Long getUsuarioCrea() {
		return UsuarioCrea;
	}

	public void setUsuarioCrea(Long usuarioCrea) {
		UsuarioCrea = usuarioCrea;
	}

	public Date getFechaEdita() {
		return fechaEdita;
	}

	public void setFechaEdita(Date fechaEdita) {
		this.fechaEdita = fechaEdita;
	}

	public Long getUsuarioEdita() {
		return UsuarioEdita;
	}

	public void setUsuarioEdita(Long usuarioEdita) {
		UsuarioEdita = usuarioEdita;
	}

	public Long getEstado() {
		return idEstado;
	}

	public void setEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public DTOCategoria(Long idCategoria,
			@NotNull(message = "Debe ingresar una categoria") @Size(min = 3, message = "Debe ingresar un nombre con minimo 3 caracteres") String categoria,
			Date fechaCrea, Long usuarioCrea, Date fechaEdita, Long usuarioEdita, Long idEstado) {
		this.idCategoria = idCategoria;
		this.categoria = categoria;
		this.fechaCrea = fechaCrea;
		UsuarioCrea = usuarioCrea;
		this.fechaEdita = fechaEdita;
		UsuarioEdita = usuarioEdita;
		this.idEstado = idEstado;
	}

	public DTOCategoria() {
	}
	
	

}
