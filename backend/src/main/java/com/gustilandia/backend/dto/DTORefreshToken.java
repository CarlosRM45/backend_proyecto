package com.gustilandia.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DTORefreshToken {
	
	@NotBlank(message = "Token no valido")
	@NotNull(message = "Tokne no valido")
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
