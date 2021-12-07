package com.gustilandia.backend.dto;

import javax.validation.constraints.NotBlank;

public class DTOLogin {
	
	@NotBlank(message = "Debe ingresar un usuario y contrasenia")
	private String usuario;
	
	@NotBlank(message = "Debe ingresar un usuario y contrasenia")
	private String contrasenia;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	

}
