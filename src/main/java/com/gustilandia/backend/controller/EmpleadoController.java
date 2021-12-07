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

import com.gustilandia.backend.dto.DTOEmpleado;
import com.gustilandia.backend.service.EmpleadoService;
import com.gustilandia.backend.service.Response;

@CrossOrigin()
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService service;
	
	@GetMapping
	public ResponseEntity<Response> listarEmpleados(){
		return new ResponseEntity<Response>(service.listar(), HttpStatus.OK);
	}

	@GetMapping(value = "/rol")
	public ResponseEntity<Response> listarRol(){
		return new ResponseEntity<Response>(service.listarRol(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarEmpleado(@RequestBody @Valid DTOEmpleado dtoEmpleado){
		
		return new ResponseEntity<Response>(service.registrar(dtoEmpleado), HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id){
		
		Response empleado = service.buscarId(id);
		if(!empleado.isSuccess()) {
			return new ResponseEntity<Response>(empleado, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response>(empleado, HttpStatus.OK);		
	}
	
	@PutMapping
	public ResponseEntity<Response> actualizarEmpleado(@RequestBody @Valid DTOEmpleado dtoEmpleado){
		
		Response response = service.buscarId(dtoEmpleado.getIdEmpleado());
		if(!response.isSuccess()) {
			return new ResponseEntity<Response>(new Response(false, null, "El empleado no existe"),HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response>(service.actualizar(dtoEmpleado), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> eliminarEmpleado(@PathVariable("id") Long id){
		
		Response response = service.buscarId(id);
		if(!response.isSuccess()) {
			return new ResponseEntity<Response>(new Response(false, null, "El empleado no existe"),HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response>(service.eliminar(id), HttpStatus.OK);	
	}
	

}
