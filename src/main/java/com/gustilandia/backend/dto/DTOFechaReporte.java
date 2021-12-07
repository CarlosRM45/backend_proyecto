package com.gustilandia.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DTOFechaReporte {
	
	@NotBlank(message = "Debe seleccionar una fecha de inicio")
	@NotNull(message = "Debe seleccionar una fehca de inicio")
	String fechaInicio;
	
	@NotBlank(message = "Debe seleccionar una fecha de fin")
	@NotNull(message = "Debe seleccionar una fehca de fin")
	String fechaFin;
	
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
