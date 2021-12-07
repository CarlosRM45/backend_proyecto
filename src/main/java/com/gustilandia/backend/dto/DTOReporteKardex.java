package com.gustilandia.backend.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface DTOReporteKardex {
	
	@Value("#{target.id_producto}")
	Long getIdProducto();
	
	String getProducto();
	
	@Value("#{target.tipo_movimiento}")
	String getTipoMovimiento();
	
	@Value("#{target.id_movimiento}")
	String getIdMovimiento();
	
	@Value("#{target.id_categoria}")
	Long getIdCategoria();
	
	String getCategoria();
	
	@Value("#{target.id_unidad_medida}")
	Long getIdUnidadMedida();
	
	@Value("#{target.unidad_medida}")
	String getUnidadMedida();
	
	Long getCantidad();
	
	Date getFecha();

}
