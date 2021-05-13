package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.model.UnidadMedida;
import com.gustilandia.backend.service.UnidadMedidaService;

@RestController
@RequestMapping("/unimed")
public class UnidadMedidaController {
	
	@Autowired
	private UnidadMedidaService service;
	
	@GetMapping()
	public ResponseEntity<List<UnidadMedida>> listarUnidades() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UnidadMedida> buscarId(@PathVariable("id") Long id) {
		
		UnidadMedida unimed = service.buscarId(id);
		if(unimed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<UnidadMedida>(unimed, HttpStatus.OK);		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnidadMedida> registrarUnidades(@RequestBody UnidadMedida unimed) {
		return new ResponseEntity<>(service.registrar(unimed), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnidadMedida> actualizarId(@RequestBody UnidadMedida unimed) {
		
		UnidadMedida _unimed = service.buscarId(unimed.getIdUnidadMedida());
		if(_unimed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<UnidadMedida>(service.actualizar(unimed), HttpStatus.OK);		
	}

}
