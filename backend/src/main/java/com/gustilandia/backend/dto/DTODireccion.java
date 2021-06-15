package com.gustilandia.backend.dto;

import javax.validation.constraints.Min;

public class DTODireccion {
	
	private String direccion;

	private String referencia;

	@Min(value = 1, message = "Debe seleccionar un distrito")
    private Long idDistrito;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Long getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Long idDistrito) {
		this.idDistrito = idDistrito;
	}
    
    

}
