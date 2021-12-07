package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTOReporteVentas {
	
	@Value("#{target.numero_venta}")
	String getNumeroVenta();
	
	@Value("#{target.id_cliente}")
	Long getIdCliente();
	
	@Value("#{target.nombre_completo}")
	String getNombreCompleto();
	
	@Value("#{target.id_producto}")
	Long getIdProducto();
	
	String getProducto();
	
	String getCantidad();
	
	String getPrecio();
	
	String getTotal();

	@Value("#{target.id_estado}")
	Long getIdEstado();
	
	String getEstado();

}
