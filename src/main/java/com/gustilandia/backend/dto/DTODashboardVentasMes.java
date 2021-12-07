package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardVentasMes {
	
	String getMes();
	
	String getTotal();

	@Value("#{target.mesName}")
	String getMesName();

}
