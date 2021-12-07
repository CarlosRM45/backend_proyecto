package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardComprasTotal {
	
	@Value("#{target.total_compra}")
	String getTotalCompras();

}
