package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardVentasTotal {
	
	@Value("#{target.total_ventas}")
	String getTotalVentas();

}
