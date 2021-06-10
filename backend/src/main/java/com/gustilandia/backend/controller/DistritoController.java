package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTODistrito;
import com.gustilandia.backend.service.DistritoService;
import com.gustilandia.backend.service.Response;

@CrossOrigin()
@RestController
@RequestMapping("/distrito")
public class DistritoController {
	
	@Autowired
	private DistritoService service;
	
	@GetMapping
	public ResponseEntity<List<DTODistrito>> listarDistritos() {
		return new ResponseEntity<>(service.listarDistritos(), HttpStatus.OK);
	}

}
