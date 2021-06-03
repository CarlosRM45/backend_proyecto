package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOMarca;
import com.gustilandia.backend.model.Marca;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.EmpleadoRepository;
import com.gustilandia.backend.repository.MarcaRepository;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.security.TokenClientInterceptor;
import com.gustilandia.backend.service.MarcaService;
import com.gustilandia.backend.service.Response;

@Service
public class MarcaServiceImpl implements MarcaService{
	
	@Autowired
	private MarcaRepository repository;
	
	@Autowired
	private EmpleadoRepository repoEmpleado;
	
	@Autowired
	private JwtProvider jwtProvider;
	
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
		Marca marUpdate = mappingMarca(dtoMarca);
	
		try {

			Optional<Marca> mar = repository.findById(dtoMarca.getIdMarca());
			Marca _marca = mar.get();
		
			
			_marca.setFechaEdita(marUpdate.getFechaEdita());
			_marca.setUsuarioEdita(marUpdate.getUsuarioEdita());
			_marca.setNombreMarca(marUpdate.getNombreMarca());
			
			response.setResult(repository.save(_marca));
			response.setSuccess(true);
			response.setMessage("La Marca ha sido actualizada");
			return response;

		} catch (Exception e) {
			response.setMessage("La marca no existe.");
		}

		return response;
	}

	@Override
	public Response eliminar(Long id) {
		
		Response response = new Response();
		Optional<Marca> cat = repository.findById(id);

		if(cat != null) {

			repository.deleteMarca(id);
			response.setResult(true);
			response.setMessage("Marca eliminada");
			return response;
		}
		
		return new Response(false, null, "La marca no existe");
	}

	@Override
	public Response buscarId(Long id) {
		
		Optional<Marca> marca = repository.findById(id);
		
		if(marca == null) {
			return new Response(false, null, "No existe marca on ese id");
		}
		
		return new Response(true, marca.get(), "");
	}

	@Override
	public Response listar() {

		return new Response(true, repository.findAll(), "");
	}
	
    
	public Marca mappingMarca(DTOMarca dtoMarca) {
		
		Marca marca = mapper.map(dtoMarca, Marca.class);
		
		String token = TokenClientInterceptor.token;
		token = token.replace("Bearer ", "");
		String correo = jwtProvider.getUsuarioToken(token);
		
		Usuario usuario = repoEmpleado.findByCorreo(correo).get().getUsuario();

		if(marca.getIdMarca() != 0L){
            marca.setFechaEdita(new Date(System.currentTimeMillis()));
			marca.setUsuarioEdita(usuario);
			
		}
		else{
			marca.setFechaCrea(new Date(System.currentTimeMillis()));
			marca.setUsuarioCrea(usuario);
		}
		

		return marca;
		
	}
	
}
