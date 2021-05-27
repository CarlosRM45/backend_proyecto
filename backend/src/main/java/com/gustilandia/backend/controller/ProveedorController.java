package com.gustilandia.backend.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOProveedor;
import com.gustilandia.backend.service.ProveedorService;
import com.gustilandia.backend.service.Response;

@CrossOrigin(origins = "*"/*origins= "http://localhost:4200"*/,maxAge = 3600)
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService service;
	
	@GetMapping()
	public ResponseEntity<Response> listarProveedor(){
		return new ResponseEntity<Response>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id){
		Response response = service.buscarId(id);
		if(!response.isSuccess()) 
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarProveedor(@RequestBody @Valid DTOProveedor proveedor){
		return new ResponseEntity<Response>(service.registrar(proveedor), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> actualizarProveedor(@RequestBody @Valid DTOProveedor proveedor){
		
		Response response = service.buscarId(proveedor.getIdProveedor());
		if(response.getResult() == null) {
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response>(service.actualizar(proveedor), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> eliminarProveedor(@PathVariable("id") Long id){
		
		Response response = service.buscarId(id);
		if(response.getResult() == null) {
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response>(service.eliminar(id), HttpStatus.OK);
		
	}
	

}
