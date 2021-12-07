package com.gustilandia.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOFechaReporte;
import com.gustilandia.backend.dto.DTORequestKardex;
import com.gustilandia.backend.dto.DTORequestReporteCompras;
import com.gustilandia.backend.dto.DTORequestReporteVentas;
import com.gustilandia.backend.service.ReporteService;
import com.gustilandia.backend.service.Response;

@CrossOrigin()
@RestController
@RequestMapping("/reporte")
public class ReporteController {
	
	@Autowired
	private ReporteService service;
	
	@PostMapping(path = "/clientes" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> reporteClientes(@RequestBody @Valid DTOFechaReporte dtoFechaReporte) {
		
		return new ResponseEntity<Response>(service.reporteClientes(dtoFechaReporte.getFechaInicio(), dtoFechaReporte.getFechaFin()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/kardex" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> reporteKardex(@RequestBody @Valid DTORequestKardex dtoRequestKardex) {
		
		return new ResponseEntity<Response>(service.reporteKardex(dtoRequestKardex.getIdUnidadMedida(), dtoRequestKardex.getIdCategoria(), dtoRequestKardex.getFechaInicio(), dtoRequestKardex.getFechaFin()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/ventas" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> reporteVentas(@RequestBody @Valid DTORequestReporteVentas dtoRequestReporteVentas) {
		
		return new ResponseEntity<Response>(service.reporteVentas(dtoRequestReporteVentas.getIdEstado(), dtoRequestReporteVentas.getIdCliente(), dtoRequestReporteVentas.getFechaInicio(), dtoRequestReporteVentas.getFechaFin()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/compras" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> reporteCompras(@RequestBody @Valid DTORequestReporteCompras dtoRequestReporteCompras) {
		
		return new ResponseEntity<Response>(service.reporteCompras(dtoRequestReporteCompras.getIdProveedor(), dtoRequestReporteCompras.getIdProducto(), dtoRequestReporteCompras.getFechaInicio(), dtoRequestReporteCompras.getFechaFin()), HttpStatus.OK);
		
	}

}
