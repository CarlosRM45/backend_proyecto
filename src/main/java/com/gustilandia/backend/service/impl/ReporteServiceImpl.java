package com.gustilandia.backend.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTORequestReporteCompras;
import com.gustilandia.backend.dto.DTORequestReporteVentas;
import com.gustilandia.backend.repository.CompraRepository;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.VentaRepository;
import com.gustilandia.backend.service.ReporteService;
import com.gustilandia.backend.service.Response;

@Service
public class ReporteServiceImpl implements ReporteService{
	
	@Autowired
	private VentaRepository repoVentas;
	
	@Autowired
	private ProductoRepository repoProductos;
	
	@Autowired
	private CompraRepository repoCompras;

	@Override
	public Response reporteClientes(String fechaInicio, String fechaFin) {
		
		try {
			fechaInicio = formatoFecha(fechaInicio) + " 00:00:00";
			fechaFin = formatoFecha(fechaFin) + " 23:59:59";
		} catch (ParseException e) {
			return new Response(false, null, "Error con la fecha enviadas");
		}
				
		return new Response(true, repoVentas.reporteClientes(fechaInicio, fechaFin), "");
	}

	@Override
	public Response reporteKardex(Integer idUnidadMedida, Integer idCategoria, String fechaInicio, String fechaFin) {
		
		try {
			fechaInicio = formatoFecha(fechaInicio) + " 00:00:00";
			fechaFin = formatoFecha(fechaFin) + " 23:59:59";
		} catch (ParseException e) {
			return new Response(false, null, "Error con la fecha enviadas");
		}
		
		if(idUnidadMedida == 0)
			idUnidadMedida = null;
		
		if(idCategoria == 0)
			idCategoria = null;
		
		return new Response(true, repoProductos.reporteKardex(idUnidadMedida, idCategoria, fechaInicio, fechaFin),"");
	}

	
	public String formatoFecha(String fechaInicial) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formato.parse(fechaInicial);
		
		formato.applyPattern("yyyy/MM/dd");
		String fechaFormato = formato.format(date);
		
		return fechaFormato;
		
	}

	@Override
	public Response reporteVentas(Integer idEstado, Integer idCliente, String fechaInicio, String fechaFin) {
		
		try {
			fechaInicio = formatoFecha(fechaInicio) + " 00:00:00";
			fechaFin = formatoFecha(fechaFin) + " 23:59:59";
		} catch (ParseException e) {
			return new Response(false, null, "Error con la fecha enviadas");
		}
		
		if(idEstado == 0)
			idEstado = null;
		
		if(idCliente == 0)
			idCliente = null;
		
		return new Response(true, repoVentas.reporteVentas(idEstado, idCliente, fechaInicio, fechaFin), "");
	}

	@Override
	public Response reporteCompras(Integer idProveedor, Integer idProducto, String fechaInicio, String fechaFin) {
		
		try {
			fechaInicio = formatoFecha(fechaInicio) + " 00:00:00";
			fechaFin = formatoFecha(fechaFin) + " 23:59:59";
		} catch (ParseException e) {
			return new Response(false, null, "Error con la fecha enviadas");
		}
		
		if(idProveedor == 0)
			idProveedor = null;
		
		if(idProducto == 0)
			idProducto = null;
		
		return new Response (true, repoCompras.reporteCompras(idProveedor, idProducto, fechaInicio, fechaFin),"");
	}
	
}
