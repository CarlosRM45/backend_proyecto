package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOVentas;
import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.Venta;
import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	private VentaService service;
	
	@GetMapping()
	public ResponseEntity<Response> listarVentas() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id) {
		
		Response venta = service.buscarId(id);
		if(venta == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(venta, HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarVenta(@RequestBody DTOVentas venta) {
		return new ResponseEntity<>(service.registrar(venta), HttpStatus.OK);
	}
	

}
