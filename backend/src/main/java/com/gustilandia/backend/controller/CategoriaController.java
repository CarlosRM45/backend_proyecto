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

import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategoria() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria cat) {
		return new ResponseEntity<>(service.registrar(cat), HttpStatus.OK);
	}

}
