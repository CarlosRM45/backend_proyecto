package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTOReporteCompras {
	
	@Value("#{target.id_compra}")
	Long getIdCompra();
	
	@Value("#{target.id_proveedor}")
	Long getIdProveedor();
	
	@Value("#{target.razon_comercial}")
	String getRazonComercial();
	
	@Value("#{target.id_producto}")
	Long getIdProducto();
	
	String getProducto();
	
	Long getCantidad();
	
	@Value("#{target.precio_compra}")
	String getPrecioCompra();
	
	String getTotal();

	@Value("#{target.id_estado}")
	Long getIdEstado();
	
	String getEstado();
	

}
