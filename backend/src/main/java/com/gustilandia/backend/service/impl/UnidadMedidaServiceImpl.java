package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.UnidadMedida;
import com.gustilandia.backend.repository.UnidadMedidaRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.service.UnidadMedidaService;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService{
	
	@Autowired
	private UnidadMedidaRepository repository;
	
	@Autowired
	private UsuarioRepository repositoryUsuario;

	@Override
	public Response registrar(UnidadMedida unidadMedida) {
		unidadMedida.setIdUnidadMedida(0L);
		unidadMedida.setUsuarioCrea(repositoryUsuario.findById(unidadMedida.getUsuarioCrea().getIdUsuario()).get());
		unidadMedida.setUsuarioEdita(repositoryUsuario.findById(unidadMedida.getUsuarioCrea().getIdUsuario()).get());
		unidadMedida.setFechaCrea(new Date(System.currentTimeMillis()));
		unidadMedida.setFechaEdita(new Date(System.currentTimeMillis()));

		return new Response(true, repository.save(unidadMedida), "");
	}

	@Override
	public Response actualizar(UnidadMedida unidadMedida) {
		Optional<UnidadMedida> unimed = repository.findById(unidadMedida.getIdUnidadMedida());
		if(unimed != null) {
			unidadMedida.setFechaEdita(new Date(System.currentTimeMillis()));
			return new Response();
		}			
		return new Response();
	}

	@Override
	public Response eliminar(Long id) {
		Optional<UnidadMedida> unimed = repository.findById(id);
		if(unimed != null) {
			repository.delete(unimed.get());
			return new Response();
		}
		return new Response();
	}

	@Override
	public Response buscarId(Long id) {
		return new Response();
	}

	@Override
	public Response listar() {
		return new Response();
	}

}
