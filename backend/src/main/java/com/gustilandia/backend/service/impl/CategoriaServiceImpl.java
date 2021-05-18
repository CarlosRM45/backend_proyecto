package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCategoria;
import com.gustilandia.backend.dto.DTOVentas;
import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.model.Venta;
import com.gustilandia.backend.repository.CategoriaRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.service.CategoriaService;
import com.gustilandia.backend.service.Response;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Response registrar(DTOCategoria categoria) {
		
		Response response = new Response();
		
		try {
			Categoria _categoria = mappingDtoCategoria(categoria);
			_categoria = repository.save(_categoria);
			response.setResult(_categoria);
			response.setSuccess(true);
			response.setMessage("La categoria ha sido registrada");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error al registrar la categoria");
		}
		
		return response;
	}

	@Override
	public Response actualizar(DTOCategoria dtoCategoria) {

		Response response = new Response();

		Optional<Categoria> cat = repository.findById(dtoCategoria.getIdCategoria());

		if(cat != null) {
			
			Categoria categoria = cat.get();
			Categoria catUpdate = mappingDtoCategoria(dtoCategoria);
			
			categoria.setCategoria(catUpdate.getCategoria());
			categoria.setFechaEdita(catUpdate.getFechaEdita());
			categoria.setUsuarioEdita(catUpdate.getUsuarioEdita());
			
			response.setResult(repository.save(categoria));
			response.setSuccess(true);
			response.setMessage("La Categoria ha sido actualizada");
			return response;
		}

		response.setMessage("La categoria no existe.");

		return response;
	}

	@Override
	public Response eliminar(Long id) {
		
		Response response = new Response();
		Optional<Categoria> cat = repository.findById(id);

		if(cat != null) {
			Categoria categoria = cat.get();
			categoria.setIdEstado(2L);
			repository.save(categoria);
			response.setResult(true);
			response.setMessage("Categoria eliminada");
			return response;
		}
		
		return new Response(false, null, "La categoria no existe");
	}

	@Override
	public Response buscarId(Long id) {
		
		return new Response(true, repository.findById(id).get(), "");
	}

	@Override
	public Response listar() {

		return new Response(true, repository.findAll(), "");
	}
	
	@Override
	public Response listarProductoCategoria(Long id) {
		
		Optional<Categoria> cat = repository.findById(id);

		if(cat != null) {
			return new Response(true, cat.get().getProductos(), "");
		}
		return new Response(false, null, "La categoria no existe");
	}

	public Categoria mappingDtoCategoria(DTOCategoria dtoCategoria) {
		
		Categoria categoria = mapper.map(dtoCategoria, Categoria.class);
		categoria.setIdEstado(1L);
		
		if(categoria.getIdCategoria() == 0L || categoria.getIdCategoria() == null)
			categoria.setFechaEdita(new Date(System.currentTimeMillis()));
		else
			categoria.setFechaCrea(new Date(System.currentTimeMillis()));

		return categoria;
		
	}
	
}
