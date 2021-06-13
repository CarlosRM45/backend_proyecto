package com.gustilandia.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOCompra;
import com.gustilandia.backend.service.CompraService;
import com.gustilandia.backend.service.Response;

@CrossOrigin()
@RestController
@RequestMapping("/compra")
public class ComprasController {
	
	@Autowired
	private CompraService service;
	
	@GetMapping
	public ResponseEntity<Response> listarCompras(){
		
		return new ResponseEntity<Response>(service.listar(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id){
		
		Response response = service.buscarId(id);
		if(!response.isSuccess()) {
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarCompra(@RequestBody DTOCompra dtoCompra){
		
		return new ResponseEntity<Response>(service.registrar(dtoCompra), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/anular/{id}")
	public ResponseEntity<Response> anularCompra(@PathVariable("id") Long id){
		return new ResponseEntity<Response>(service.eliminar(id), HttpStatus.OK);
	}

}
