package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardVentaProducto {
	
	@Value("#{target.id_producto}")
	Long getIdProducto();
	
	String getProducto();
	
	@Value("#{target.cantidad_total}")
	Integer getCantidadTotal();

}
