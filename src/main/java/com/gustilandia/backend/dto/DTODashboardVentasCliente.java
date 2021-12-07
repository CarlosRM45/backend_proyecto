package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardVentasCliente {
	
	@Value("#{target.id_cliente}")
	Long getIdCliente();
	
	@Value("#{target.nombre_completo}")
	String getNombreCompleto();
	
	Integer getCantidad();

}
