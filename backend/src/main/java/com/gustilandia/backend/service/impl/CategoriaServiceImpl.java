package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Categoria;
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

	@Override
	public Response registrar(Categoria categoria) {
		categoria.setIdCategoria(0L);
		categoria.setUsuarioCrea(repositoryUsuario.findById(categoria.getUsuarioCrea().getIdUsuario()).get());
		categoria.setUsuarioEdita(repositoryUsuario.findById(categoria.getUsuarioCrea().getIdUsuario()).get());
		categoria.setFechaCrea(new Date(System.currentTimeMillis()));;
		categoria.setFechaEdita(new Date(System.currentTimeMillis()));
		  
		return new Response(true, repository.save(categoria), "");
	}

	@Override
	public Response actualizar(Categoria categoria) {

		Response response = new Response();

		Optional<Categoria> cat = repository.findById(categoria.getIdCategoria());

		if(cat != null) {

			cat.get().setFechaEdita(new Date(System.currentTimeMillis()));

			response.setResult(repository.save(cat.get()));
			response.setSuccess(true);
			return response;
		}

		response.setMessage("La categoria no existe.");

		return response;
	}

	@Override
	public Response eliminar(Long id) {

		Optional<Categoria> cat = repository.findById(id);

		if(cat != null) {
			repository.delete(cat.get());
			return new Response();
		}
		return new Response();
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
		return new Response();
	}

}
