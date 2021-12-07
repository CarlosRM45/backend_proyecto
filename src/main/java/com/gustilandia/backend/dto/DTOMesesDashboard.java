package com.gustilandia.backend.dto;

import javax.validation.constraints.NotNull;

public class DTOMesesDashboard {
	
	@NotNull(message = "id Meses no admite valores nulos")
	private String idMeses;

	public String getIdMeses() {
		return idMeses;
	}

	public void setIdMeses(String idMeses) {
		this.idMeses = idMeses;
	}
	
	

}
