package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardComprasProducto {
	
	@Value("#{target.id_producto}")
	Long getIdProducto();
	
	String getProducto();
	
	Integer getCantidad();

}
