package com.gustilandia.backend.service;

import com.gustilandia.backend.dto.DTORequestReporteCompras;
import com.gustilandia.backend.dto.DTORequestReporteVentas;

public interface ReporteService {
	
	Response reporteClientes(String fechaInicio, String fechaFin);
	
	Response reporteKardex(Integer idUnidadMedida, Integer idCategoria, String fechaInicio, String fechaFin);

	Response reporteVentas(Integer idEstado, Integer idCliente, String fechaInicio, String fechaFin);
	
	Response reporteCompras(Integer idProveedor, Integer idProducto, String fechaInicio, String fechaFin);
	
}
