package com.gustilandia.backend.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface DTOListadoVenta {
	
	@Value("#{target.id_venta}")
	String getIdVenta();

	@Value("#{target.numero_venta}")
	String getNumeroVenta();
	
	@Value("#{target.nombre_completo}")
	String getNombreCompleto();
	
	@Value("#{target.total}")
	Double getTotal();
	
	@Value("#{target.fecha_entrega}")
	Date getFechaEntrega();
	
	@Value("#{target.fecha_venta_guardada}")
	Date getFechaVentaGuardada();

	@Value("#{target.fecha_creacion}")
	Date getFechaCreacion();
	
	@Value("#{target.id_estado}")
	Long getIdEstado();
	
	@Value("#{target.estado}")
	String getEstado();

	@Value("#{target.id_empleado}")
	Long getIdEmpleado();

}
