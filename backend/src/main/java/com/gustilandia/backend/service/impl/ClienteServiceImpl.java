package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCliente;
import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.service.ClienteService;
import com.gustilandia.backend.service.Response;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repocliente;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Response registrar(DTOCliente clienteDto) {
		
		Response response = new Response();
		Cliente cliente = new Cliente();

		try {

			cliente = repocliente.save(mappingCliente(clienteDto));
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
		Cliente cliente = mappingCliente(clienteDto);

		try {

			Optional<Cliente> clie = repocliente.findById(clienteDto.getIdCliente());
			Cliente _cliente = clie.get();

			_cliente.setNombreCompleto(cliente.getNombreCompleto());
			_cliente.setCelular(cliente.getCelular());
			_cliente.setCorreo(cliente.getCorreo());
			_cliente.setDireccion(cliente.getDireccion());
			_cliente.setReferencia(cliente.getReferencia());
			_cliente.setDistrito(cliente.getDistrito());
	
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
			
			repocliente.deleteCliente(id);
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

	
	private Cliente mappingCliente(DTOCliente clienteDto){

		Cliente cliente = mapper.map(clienteDto, Cliente.class);
		cliente.getEstado().setIdEstado(1L);


		if(clienteDto.getIdCliente() == 0)
			cliente.setFechaCreacion(new Date(System.currentTimeMillis()));
		
		
		return cliente;
	}
}
