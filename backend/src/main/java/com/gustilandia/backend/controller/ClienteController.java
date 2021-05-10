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

import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping()
	public ResponseEntity<List<Cliente>> listarUsuarios() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(service.registrar(cliente), HttpStatus.OK);
	}

}
