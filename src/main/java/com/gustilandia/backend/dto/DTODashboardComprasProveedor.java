package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardComprasProveedor {
	
	@Value("#{target.id_proveedor}")
	Long getIdProveedor();
	
	@Value("#{target.razon_comercial}")
	String getRazonComercial();
	
	Integer getCantidad();

}
