package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardProductoVC {
	
	@Value("#{target.id_producto}")
	Long getIdProducto();
	
	String getProducto();
	
	@Value("#{target.total_ventas}")
	Integer getTotalVentas();
	
	@Value("#{target.total_compras}")
	Integer getTotalCompras();

}
