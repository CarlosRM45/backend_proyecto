package com.gustilandia.backend.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface DTOVentaCliente {
	
	@Value("#{target.id_venta}")
	Long getIdVenta();
	
	@Value("#{target.numero_venta}")
	String getNumeroVenta();
	
	String getTotal();
	
	String getEstado();
	
	@Value("#{target.fecha_venta_guardada}")
	Date getFechaVentaGuardada();
	
	@Value("#{target.fecha_entrega}")
	Date getFechaEntrega();
	
	Integer getCantidad();
	
	String getDireccion();

}
