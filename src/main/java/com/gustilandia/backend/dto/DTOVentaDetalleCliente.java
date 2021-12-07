package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTOVentaDetalleCliente {
	
	@Value("#{target.producto.producto}")
	String getProducto();
	
	@Value("#{target.producto.imagen}")
	String getImagen();
	
	Integer getCantidad();
	
	String getPrecio();

}
