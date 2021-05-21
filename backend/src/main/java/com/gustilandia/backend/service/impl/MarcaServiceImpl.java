package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOMarca;
import com.gustilandia.backend.model.Marca;
import com.gustilandia.backend.repository.MarcaRepository;
import com.gustilandia.backend.service.MarcaService;
import com.gustilandia.backend.service.Response;

@Service
public class MarcaServiceImpl implements MarcaService{
	
	@Autowired
	private MarcaRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Response registrar(DTOMarca marca) {
		
		Response response = new Response();
		
		try {
			Marca _marca = mappingMarca(marca);
			response.setResult(repository.save(_marca));
			response.setSuccess(true);
			response.setMessage("La marca ha sido registrada");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error al registrar la marca");
		}
		
		return response;
	}

	@Override
	public Response actualizar(DTOMarca dtoMarca) {

		Response response = new Response();

		Optional<Marca> mar = repository.findById(dtoMarca.getIdMarca());

		if(mar != null) {
			
			Marca marca = mar.get();
			Marca marUpdate = mappingMarca(dtoMarca);
			
			marca.setFechaEdita(marUpdate.getFechaEdita());
			marca.setUsuarioEdita(marUpdate.getUsuarioEdita());
			
			response.setResult(repository.save(marca));
			response.setSuccess(true);
			response.setMessage("La Marca ha sido actualizada");
			return response;
		}

		response.setMessage("La marca no existe.");

		return response;
	}

	@Override
	public Response eliminar(Long id) {
		
		Response response = new Response();
		Optional<Marca> cat = repository.findById(id);

		if(cat != null) {

			Marca marca = cat.get();
			repository.save(marca);
			response.setResult(true);
			response.setMessage("Marca eliminada");
			return response;
		}
		
		return new Response(false, null, "La marca no existe");
	}

	@Override
	public Response buscarId(Long id) {
		
		return new Response(true, repository.findById(id).get(), "");
	}

	@Override
	public Response listar() {

		return new Response(true, repository.findAll(), "");
	}
	
    
	public Marca mappingMarca(DTOMarca dtoMarca) {
		
		Marca marca = mapper.map(dtoMarca, Marca.class);
		//marca.setIdEstado(1L);
		
		if(marca.getIdMarca() == 0L || marca.getIdMarca() == null)
            marca.setFechaEdita(new Date(System.currentTimeMillis()));
		else
			marca.setFechaCrea(new Date(System.currentTimeMillis()));

		return marca;
		
	}
	
}
