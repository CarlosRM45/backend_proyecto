package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCategoria;
import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.repository.CategoriaRepository;
import com.gustilandia.backend.service.CategoriaService;
import com.gustilandia.backend.service.Response;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Response registrar(DTOCategoria categoria) {
		
		Response response = new Response();
		
		try {
			Categoria _categoria = mappingDtoCategoria(categoria);
			response.setResult(repository.save(_categoria));
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
		Categoria catUpdate = mappingDtoCategoria(dtoCategoria);
		
		try {
			Optional<Categoria> cat = repository.findById(dtoCategoria.getIdCategoria());
			Categoria _categoria = cat.get();

			_categoria.setCategoria(catUpdate.getCategoria());
			_categoria.setFechaEdita(catUpdate.getFechaEdita());
			_categoria.setUsuarioEdita(catUpdate.getUsuarioEdita());
			
			response.setResult(repository.save(_categoria));
			response.setSuccess(true);
			response.setMessage("La Categoria ha sido actualizada");
			return response;
		} catch (Exception e) {

			response.setMessage("La categoria no existe.");
		}
		
		return response;
	}

	@Override
	public Response eliminar(Long id) {
		
		Response response = new Response();

		try {
			
			repository.deleteCategoria(id);
			response.setSuccess(true);
			response.setMessage("Categoria eliminada");

		} catch (Exception e) {
			response.setMessage("Hubo un error al eliminar el producto: " + e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response buscarId(Long id) {
		
		Optional<Categoria> categoria = repository.findById(id);
		if(!categoria.isPresent()) {
			return new Response(false, null, "No existe categoria con ese id");
		}
		
		return new Response(true, categoria.get(), "");
	}

	@Override
	public Response listar() {

		return new Response(true, repository.findAll(), "");
	}
	
	@Override
	public Response listarProductoCategoria(Long id) {
		
		Optional<Categoria> cat = repository.findById(id);

		if(!cat.isPresent()) {
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
