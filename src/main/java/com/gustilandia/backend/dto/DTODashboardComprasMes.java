package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardComprasMes {
	
	String getMes();
	
	String getTotal();

	@Value("#{target.mesName}")
	String getMesName();

}
