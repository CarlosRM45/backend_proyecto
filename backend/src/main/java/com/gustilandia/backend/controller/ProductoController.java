package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping()
	public ResponseEntity<List<Producto>> listarUsuarios() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> registrarUsuario(@RequestBody Producto producto) {
		return new ResponseEntity<>(service.registrar(producto), HttpStatus.OK);
	}


}
