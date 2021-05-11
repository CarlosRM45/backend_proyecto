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

import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.UnidadMedida;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarId(@PathVariable("id") Long id) {
		
		Categoria cat = service.buscarId(id);
		if(cat == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}/productos")
	public ResponseEntity<List<Producto>> buscarProductosCategoria(@PathVariable("id") Long id) {
		
		Categoria cat = service.buscarId(id);
		if(cat == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Producto>>(cat.getProductos(), HttpStatus.OK);
		
	}
	

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria cat) {
		return new ResponseEntity<>(service.registrar(cat), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> actualizarId(@RequestBody Categoria categoria) {
		
		Categoria _cat = service.buscarId(categoria.getIdCategoria());
		if(_cat == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Categoria>(service.actualizar(categoria), HttpStatus.OK);
		
	}
	
	

}
