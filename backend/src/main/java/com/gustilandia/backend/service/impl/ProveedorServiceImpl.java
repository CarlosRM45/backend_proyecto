package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOProveedor;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Proveedor;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.ProveedorRepository;
import com.gustilandia.backend.service.ProveedorService;
import com.gustilandia.backend.service.Response;

@Service
public class ProveedorServiceImpl implements ProveedorService{
	
	@Autowired
	private ProveedorRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Response registrar(DTOProveedor dtoProveedor) {
		
		Response response = new Response();
		Proveedor proveedor = mappingProveedor(dtoProveedor);
		
		try {
			proveedor = repository.save(proveedor);
			response.setSuccess(true);
			response.setResult(proveedor);
			response.setMessage("Proveedor guardado exitosamente");
		} catch (Exception e) {
			response.setMessage("Hubo un error al guardar el proveedor");
			response.setSuccess(false);
		}
		
		return response;
	}

	@Override
	public Response actualizar(DTOProveedor dtoProveedor) {
		
		Response response = new Response();
		Proveedor proveedor = mappingProveedor(dtoProveedor);
		
		try {
			Optional<Proveedor> optProveedor = repository.findById(dtoProveedor.getIdProveedor());
			
			if(!optProveedor.isPresent()) {
				response.setSuccess(false);
				response.setMessage("El producto no existe");
				return response;
			}
			
			Proveedor _proveedor = optProveedor.get();
			_proveedor.setRazonComercial(proveedor.getRazonComercial());
			_proveedor.setCorreo(proveedor.getCorreo());
			_proveedor.setCelular(proveedor.getCelular());
			_proveedor.setDireccion(proveedor.getDireccion());
			_proveedor.setReferencia(proveedor.getReferencia());
			_proveedor.setDistrito(proveedor.getDistrito());
			_proveedor.setUsuarioEdita(proveedor.getUsuarioEdita());
			_proveedor.setFechaEdita(proveedor.getFechaEdita());
			
			response.setSuccess(true);
			response.setResult(repository.save(_proveedor));
			response.setMessage("Proveedor actualizado exitosamente");
			
		} catch (Exception e) {
			response.setMessage("Error al actualizar el proveedor");
			response.setSuccess(false);
		}
		
		return response;
	}

	@Override
	public Response eliminar(Long id) {
		Response response = new Response();
		
		try {
			repository.deleteProveedor(id);
			response.setSuccess(true);
			response.setMessage("Proveedor elimiando exitosamente");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error al eliminar el proveedor");
		}
		
		return response;
	}

	@Override
	public Response buscarId(Long id) {
		
		Optional<Proveedor> optProveedor = repository.findById(id);
		
		if(!optProveedor.isPresent())
			return new Response(false, null, "El Proveedor no existe");
		if(optProveedor.get().getEstado().getIdEstado() != 1)
			return new Response(false, null, "El Proveedor no existe");
		
		return new Response(true, optProveedor.get(), "");
	}

	@Override
	public Response listar() {
		
		List<Proveedor> listadoProveedor = repository.findAll().stream()
				.filter(proveedor -> proveedor.getEstado().getIdEstado() == 1)
				.collect(Collectors.toList());
		return new Response(true, listadoProveedor, "");
	}
	
	private Proveedor mappingProveedor(DTOProveedor dtoProveedor) {
		
		Estado estado = new Estado();
		estado.setIdEstado(1L);
		
		Proveedor proveedor = mapper.map(dtoProveedor, Proveedor.class);
		proveedor.setEstado(estado);
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1L);
		
		if(dtoProveedor.getIdProveedor() == 0 || dtoProveedor.getIdProveedor() == null) {
			proveedor.setFechaCrea(new Date(System.currentTimeMillis()));
			proveedor.setUsuarioCrea(usuario);
		}else {
			proveedor.setFechaEdita(new Date(System.currentTimeMillis()));
			proveedor.setUsuarioEdita(usuario);
		}
		
		return proveedor;
		
	}

}
