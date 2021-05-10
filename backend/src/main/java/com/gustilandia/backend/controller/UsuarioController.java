package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return new ResponseEntity<>(service.listarUsuarios(), HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<>(service.registrarUsuario(usuario), HttpStatus.OK);
	}

}
