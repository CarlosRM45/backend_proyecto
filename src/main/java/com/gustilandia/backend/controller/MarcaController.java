

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

import com.gustilandia.backend.dto.DTOMarca;
import com.gustilandia.backend.service.MarcaService;
import com.gustilandia.backend.service.Response;


@CrossOrigin()
@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	private MarcaService service;
	
	@GetMapping()
	public ResponseEntity<Response> listarMarca() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id) {
		
		Response marca = service.buscarId(id);
		if(marca.getResult() == null)
			return new ResponseEntity<>(marca, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(marca, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarMarca(@RequestBody @Valid DTOMarca marca) {
		return new ResponseEntity<>(service.registrar(marca), HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> actualizarMarca(@RequestBody DTOMarca Marca) {
		
		Response _response = service.buscarId(Marca.getIdMarca());

		if(_response.getResult() == null)
			return new ResponseEntity<>(new Response(false, null, "El Marca no existe"),HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.actualizar(Marca), HttpStatus.OK);	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> eliminarMarca(@PathVariable("id") Long id) {
		
		Response _response = service.buscarId(id);

		if(_response.getResult() == null)
			return new ResponseEntity<>(new Response(false, null, "El Marca no existe"),HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.eliminar(id), HttpStatus.OK);
		
	}

}
