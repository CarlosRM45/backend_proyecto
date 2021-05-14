package com.gustilandia.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.service.ClienteService;
import com.gustilandia.backend.service.Response;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repocliente;

	@Override
	public Response registrar(Cliente cliente) {
		
		Response response = new Response();

		if(cliente.getNombreCompleto() == null || cliente.getNombreCompleto().trim().length() <= 0){
			response.setMessage("Ingrese sus nombres y apellidos");
			return response;
		}

		if(cliente.getIdDocumentoIdentidad() == null || cliente.getIdDocumentoIdentidad() == 0L){
			response.setMessage("Debe seleccionar un documento de identidad");
			return response;
		}

		if(cliente.getNumeroDocumentoIdentidad() == null || cliente.getNumeroDocumentoIdentidad().trim().length() <= 0L){
			response.setMessage("Debe ingresar su DNI");
			return response;
		}

		if(cliente.getNumeroDocumentoIdentidad().trim().length() != 8){
			response.setMessage("El DNI ingresado es inválido.");
			return response;
		}

		if(cliente.getCorreo().trim().length() <= 0  ){
			response.setMessage("Debe ingresar un correo electrónico");
			return response;
		}

		// Usuario usuario = new Usuario();
		// usuario.setIdUsuario(0L);
		
		// usuario.setUsuario(cliente.getUsuario().getUsuario());
		// usuario.setContrasenia(cliente.getUsuario().getContrasenia());
		// usuario.setRol(reporol.findById(2L).get());
		// usuario = repousuario.save(usuario);
		//cliente.setIdCliente(0L);

		cliente.setFechaCreacion(new Date(System.currentTimeMillis()));

		return new Response(true, repocliente.save(cliente), "");
	}

	@Override
	public Response actualizar(Cliente cliente) {

		Response response = new Response();

		Optional<Cliente> clie = repocliente.findById(cliente.getIdCliente());
		Cliente _cliente = clie.get();

		try {

			_cliente.setNombreCompleto(cliente.getNombreCompleto());
			_cliente.setCelular(cliente.getCelular());
			_cliente.setCorreo(cliente.getCorreo());
			_cliente.setDireccion(cliente.getDireccion());
			_cliente.setIdDistrito(cliente.getIdDistrito());
			_cliente.setReferencia(cliente.getReferencia());

			
			response.setResult(repocliente.save(_cliente));
			response.setSuccess(true);

		} catch (Exception e) {
			response.setMessage("Hubo un error al actualizar el cliente: "+ e.getMessage());
		}

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

		List<Cliente> listadoClientes = repocliente.findAll();
		List<Cliente> nuevaListaClientes = new ArrayList<>();

		for (Cliente cliente : listadoClientes) {

			if(cliente.getIdEstado() == 1){
				nuevaListaClientes.add(cliente);
			}
		}

		return new Response(true, nuevaListaClientes , "");
	}

	@Override
	public Response iniciarSesion(String correo, String contrasenia) {
		
		

		return null;
	}

}
