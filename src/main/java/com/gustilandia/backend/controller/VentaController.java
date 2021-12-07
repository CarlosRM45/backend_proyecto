package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOVentaCarrito;
import com.gustilandia.backend.dto.DTOVentaEstado;
import com.gustilandia.backend.dto.DTOVentas;
import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.service.VentaService;

@CrossOrigin()
@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	private VentaService service;
	
	@GetMapping()
	public ResponseEntity<Response> listarVentas() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}

	@GetMapping(value = "/listarVentasPorRol")
	public ResponseEntity<Response> listarVentasPorRol(@RequestParam(value="idRol") Long idRol, @RequestParam(value="idUsuario") Long idUsuario) {
		return new ResponseEntity<>(service.listarVentasPorRol(idRol, idUsuario), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id) {
		
		Response venta = service.buscarId(id);
		if(!venta.isSuccess())
			return new ResponseEntity<>(venta, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(venta, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarVenta(@RequestBody DTOVentas venta) {
		return new ResponseEntity<Response>(service.registrar(venta), HttpStatus.OK);
	}

	@PostMapping(path = "/cambiarEstado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> cambiarEstadoVenta(@RequestBody DTOVentaEstado venta) {
		return new ResponseEntity<>(service.cambiarEstadoVenta(venta), HttpStatus.OK);
	}

	@PostMapping(path = "/registrarVentaCarrito", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarVentaCarrito(@RequestBody DTOVentaCarrito dtoVenta) {
		return new ResponseEntity<>(service.insertVentaCarrito(dtoVenta), HttpStatus.OK);
	}

	@PostMapping(path ="/aumentarCantidadProducto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> aumentarCantidadProducto(@RequestBody DTOVentaCarrito dtoVenta) {
		return new ResponseEntity<>(service.aumentarCantidadProducto(dtoVenta), HttpStatus.OK);
	}

	@DeleteMapping("/anular/{id}")
	public ResponseEntity<Response> anular(@PathVariable("id") Long id) {
		return new ResponseEntity<Response>(service.anular(id), HttpStatus.OK);
	}
	
	@GetMapping("/listarVentasCliente")
	public ResponseEntity<Response> listarVentasPorCliente(){
		return new ResponseEntity<Response>(service.listarVentaPorCliente(), HttpStatus.OK);
	}
	
	@GetMapping("/ventasCliente")
	public ResponseEntity<Response> VentasCliente(){
		return new ResponseEntity<Response>(service.VentaPorCliente(), HttpStatus.OK);
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Response> detalleVentaCliente(@PathVariable("id") Long id) {
		return new ResponseEntity<Response>(service.DetalleVentaCliente(id), HttpStatus.OK);
	}

}
