package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardTotales {
	
	@Value("#{target.total_ventas}")
	String getTotalVentas();
	
	@Value("#{target.total_compras}")
	String getTotalCompras();
	
	String getDiferencia();

}
