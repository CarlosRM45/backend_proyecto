package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTOReporteClientes {
	
	@Value("#{target.id_cliente}")
	Long getIdCliente();
	
	@Value("#{target.nombre_completo}")
	String getNombreCliente();
	
	@Value("#{target.numero_documento_identidad}")
	String getNumeroDocumentoIdentidad();
	
	String getCorreo();
	
	@Value("#{target.cantidad_ventas}")
	Long getCantidadVentas();
	
	@Value("#{target.total_ventas}")
	String getTotalVentas();

}
