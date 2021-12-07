package com.gustilandia.backend.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface DTOVentaPorCliente {
	
	Long getIdVenta();
	
	String getNumeroVenta();
	
	String getTotal();
	
	@Value("#{target.estado.estado}")
	String getEstado();
	
	Date getFechaVentaGuardada();
	
	Date getFechaEntrega();
	
	@Value("#{target.ventaDetalle.size()}")
	Integer getCantidad();
	
	@Value("#{target.cliente.direccion}")
	String getDireccion();
	
	List<DTOVentaDetalleCliente> getVentaDetalle();

}
