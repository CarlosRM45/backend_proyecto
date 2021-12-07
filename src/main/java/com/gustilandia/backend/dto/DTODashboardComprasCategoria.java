package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardComprasCategoria {
	
	@Value("#{target.id_categoria}")
	Long getIdCategoria();
	
	String getCategoria();
	
	@Value("#{target.cantidad_total}")
	Integer getCantidadTotal();

}
