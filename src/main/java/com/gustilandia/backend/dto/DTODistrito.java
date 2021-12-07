package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODistrito {
	
	@Value("#{target.id_distrito}")
	Long getIdDistrito();
	
	String getDistrito();

}
