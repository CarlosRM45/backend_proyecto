package com.gustilandia.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import com.gustilandia.backend.modelexample.ModelProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gustilandia.backend.dto.DTOProducto;
import com.gustilandia.backend.service.ProductoService;
import com.gustilandia.backend.service.Response;

@CrossOrigin(origins = "*"/*origins= "http://localhost:4200"*/,maxAge = 3600)
@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping()
	public ResponseEntity<Response> listarProductos() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id) {
		
		Response producto = service.buscarId(id);
		if(!producto.isSuccess())
			return new ResponseEntity<>(producto, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(producto, HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarProducto(@RequestBody @Valid DTOProducto producto){
													 //@RequestParam("imageFile") MultipartFile multipartFile) throws IOException {
		return new ResponseEntity<>(service.registrar(producto), HttpStatus.OK);
	}

	@PostMapping("/uploadImage")
    public ResponseEntity<Response> uploadFile(@RequestParam("imageFile") MultipartFile file) throws Exception {

		return new ResponseEntity<Response>(service.uploadImageProducto(file), HttpStatus.OK);
    }
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> actualizarProducto(@RequestBody @Valid DTOProducto producto) {
		
		Response _producto = service.buscarId(producto.getIdProducto());

		if(_producto.getResult() == null)
			return new ResponseEntity<>(new Response(false, null, "El producto no existe"),HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.actualizar(producto), HttpStatus.OK);	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> eliminarProducto(@PathVariable("id") Long id) {
		
		Response _response = service.buscarId(id);

		if(_response.getResult() == null)
			return new ResponseEntity<>(new Response(false, null, "El producto no existe"),HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.eliminar(id), HttpStatus.OK);
		
	}

	@GetMapping(value = "/listarproductosmvl")
	public ResponseEntity<List<ModelProducto>> ListarProductosMvl(){
		List<ModelProducto> lista = service.listarProductosMvl();
		
		if (lista == null) {
			lista = new ArrayList<ModelProducto>();
		}
		
		return new ResponseEntity<List<ModelProducto>>(lista, HttpStatus.OK);
	}

	@GetMapping(value = "/listProductsByName/{name}")
	public ResponseEntity<List<ModelProducto>> ListProductsByName(@PathVariable("name") String name){
		List<ModelProducto> lista = service.listarProductsByName(name);
		
		if (lista == null) {
			lista = new ArrayList<ModelProducto>();
		}
		
		return new ResponseEntity<List<ModelProducto>>(lista, HttpStatus.OK);
	}

	@GetMapping(value = "/listProductsByCategory/{id}")
	public ResponseEntity<List<ModelProducto>> ListProductsByCategory(@PathVariable("id") Long id){
		List<ModelProducto> lista = service.listarProductsByCategory(id);
		
		if (lista == null) {
			lista = new ArrayList<ModelProducto>();
		}
		
		return new ResponseEntity<List<ModelProducto>>(lista, HttpStatus.OK);
	}

	
	@GetMapping(value = "/{id}/validarstock/{cant}")
	public ResponseEntity<Response> validarStock(@PathVariable("id") Long id, @PathVariable("cant") int cant){
		
		return new ResponseEntity<Response>(service.validarStock(id, cant), HttpStatus.OK);
		
	}

}
