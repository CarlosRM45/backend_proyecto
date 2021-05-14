package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.repository.RolRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.service.ClienteService;
import com.gustilandia.backend.service.Response;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repocliente;
	
	@Autowired
	private RolRepository reporol;
	
	@Autowired
	private UsuarioRepository repousuario;

	@Override
	public Response registrar(Cliente cliente) {
		
		if(cliente.getNombreCompleto() == null && cliente.getNombreCompleto().trim().length() <= 0){
			return null;
		}

		if(cliente.getIdDocumentoIdentidad() == null && cliente.getIdDocumentoIdentidad() == 0L){
			
		}

		Usuario usuario = new Usuario();
		usuario.setIdUsuario(0L);
		usuario.setUsuario(cliente.getUsuario().getUsuario());
		usuario.setContrasenia(cliente.getUsuario().getContrasenia());
		usuario.setRol(reporol.findById(2L).get());
		usuario = repousuario.save(usuario);
		
		cliente.setIdCliente(0L);
		cliente.setUsuario(usuario);
		cliente.setFechaCreacion(new Date(System.currentTimeMillis()));


		return new Response(true, repocliente.save(cliente), "");
	}

	@Override
	public Response actualizar(Cliente cliente) {

		Response response = new Response();

		Optional<Cliente> clie = repocliente.findById(cliente.getIdCliente());

		if(clie != null) {
			response.setResult(repocliente.save(cliente));
			response.setSuccess(true);
			return response;
		}

		response.setMessage("El cliente no existe.");
		return response;
	}

	@Override
	public Response eliminar(Long id) {
		return new Response();
	}

	@Override
	public Response buscarId(Long id) {
		return new Response(true, repocliente.findById(id).get(), "");
	}

	@Override
	public Response listar() {
		return new Response(true, repocliente.findAll(), "");
	}

}
