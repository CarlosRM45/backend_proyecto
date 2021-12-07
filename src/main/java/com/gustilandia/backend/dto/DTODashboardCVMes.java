package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTODashboardCVMes {
	
	Integer getMes();

	@Value("#{target.mesName}")
	String getMesName();
	
	@Value("#{target.total_venta}")
	String getTotalVenta();
	
	@Value("#{target.total_compra}")
	String getTotalCompra();
	
	String getDiferencia();

}
