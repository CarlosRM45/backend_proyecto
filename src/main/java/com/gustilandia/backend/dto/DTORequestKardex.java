package com.gustilandia.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DTORequestKardex {
	
	private Integer idUnidadMedida = null;
	private Integer idCategoria = null;
	
	@NotBlank(message = "Debe seleccionar una fecha de inicio")
	@NotNull(message = "Debe seleccionar una fehca de inicio")
	private String fechaInicio;
	
	@NotBlank(message = "Debe seleccionar una fecha de fin")
	@NotNull(message = "Debe seleccionar una fehca de fin")
	private String fechaFin;
	
	public Integer getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(Integer idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	

}
