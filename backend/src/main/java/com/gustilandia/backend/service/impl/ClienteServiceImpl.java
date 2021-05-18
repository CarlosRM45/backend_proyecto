package com.gustilandia.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCliente;
import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.model.Distrito;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.service.ClienteService;
import com.gustilandia.backend.service.Response;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repocliente;

	@Override
	public Response registrar(DTOCliente clienteDto) {
		
		Response response = new Response();
		Cliente cliente = new Cliente();

		if(clienteDto.getNombreCompleto() == null || clienteDto.getNombreCompleto().trim().length() <= 0){
			response.setMessage("Ingrese sus nombres y apellidos");
			return response;
		}

		if(clienteDto.getIdDocumentoIdentidad() == null || clienteDto.getIdDocumentoIdentidad() == 0L){
			response.setMessage("Debe seleccionar un documento de identidad");
			return response;
		}

		if(clienteDto.getNumeroDocumentoIdentidad() == null || clienteDto.getNumeroDocumentoIdentidad().trim().length() <= 0L){
			response.setMessage("Debe ingresar su DNI");
			return response;
		}

		if(clienteDto.getNumeroDocumentoIdentidad().trim().length() != 8){
			response.setMessage("El DNI ingresado es inválido.");
			return response;
		}

		if(clienteDto.getCorreo().trim().length() <= 0  ){
			response.setMessage("Debe ingresar un correo electrónico");
			return response;
		}

		try {

			cliente = repocliente.save(mappingCliente(clienteDto, null));
			response.setResult(cliente);
			response.setSuccess(true);
			
		} catch (Exception e) {
			response.setMessage("Hubo un error al guardar el cliente: " + e.getMessage());
		}


		return response;
	}

	@Override
	public Response actualizar(DTOCliente clienteDto) {

		Response response = new Response();

		try {

			Optional<Cliente> clie = repocliente.findById(clienteDto.getIdCliente());
			Cliente _cliente = clie.get();

			_cliente = mappingCliente(clienteDto, _cliente);

			response.setResult(repocliente.save(_cliente));
			response.setSuccess(true);

		} catch (Exception e) {
			response.setMessage("Hubo un error al actualizar el cliente: "+ e.getMessage());
		}

		return response;
	}

	@Override
	public Response eliminar(Long id) {

		Response response = new Response();

		try {
			
			Optional<Cliente> clienteOp = repocliente.findById(id);
			Cliente _cliente = clienteOp.get();

			// _cliente.getEstado().setIdEstado(2L);

			// repocliente.save(_cliente);


			int d = repocliente.deleteCliente(id);

			response.setSuccess(true);
			response.setMessage("El cliente fue eliminado exitosamente.");

		} catch (Exception e) {
			response.setMessage("Hubo un error al eliminar el cliente: " + e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response buscarId(Long id) {		

		Cliente cliente = repocliente.findById(id).get();

		if(cliente.getEstado().getIdEstado() != 1)
			return new Response(false, null, "El cliente no existe.");

		return new Response(true, cliente, "");
	}

	@Override
	public Response listar() {

		List<Cliente> listadoClientes = repocliente.findAll()
				.stream()
				.filter(cliente -> cliente.getEstado().getIdEstado() == 1)
				.collect(Collectors.toList());

		return new Response(true, listadoClientes , "");
	}


	private Cliente mappingCliente(DTOCliente clienteDto , @Nullable Cliente clienteUpdate){

		Cliente cliente = new Cliente();

		if(clienteUpdate != null)
			cliente = clienteUpdate;

		cliente.setNombreCompleto(clienteDto.getNombreCompleto());
		cliente.setCelular(clienteDto.getCelular());
		cliente.setCorreo(clienteDto.getCorreo());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setReferencia(clienteDto.getReferencia());

		Distrito distrito = new Distrito();
		distrito.setIdDistrito(clienteDto.getIdDistrito());
		cliente.setDistrito(distrito);

		Estado estado = new Estado();
		estado.setIdEstado(1L);
		cliente.setEstado(estado);

		if(clienteDto.getIdCliente() == 0)
			cliente.setFechaCreacion(new Date(System.currentTimeMillis()));
		
		
		return cliente;
	}


}
