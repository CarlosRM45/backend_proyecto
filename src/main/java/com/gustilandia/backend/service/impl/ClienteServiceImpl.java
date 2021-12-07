package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCliente;
import com.gustilandia.backend.dto.DTODireccion;
import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Rol;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.repository.DistritoRepository;
import com.gustilandia.backend.repository.RolRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.security.TokenClientInterceptor;
import com.gustilandia.backend.service.ClienteService;
import com.gustilandia.backend.service.Response;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repocliente;
	
	@Autowired
	private RolRepository reporol;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UsuarioRepository repousuario;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DistritoRepository repodistrito;

	@Override
	public Response registrar(DTOCliente clienteDto) {
		
		Response response = new Response();
		Cliente cliente = new Cliente();

		try {

			if(repocliente.validateDNI(clienteDto.getNumeroDocumentoIdentidad()) > 0){
				response.setMessage("Ya existe un cliente con este DNI");
				return response;
			}

			if(repocliente.validateEmail(clienteDto.getCorreo()) > 0){
				response.setMessage("Ya existe un cliente con este Email");
				return response;
			}

			// if(repocliente.ValidateDNIandEmail(clienteDto.getCorreo(), clienteDto.getNumeroDocumentoIdentidad()) > 0){
			// 	response.setMessage("Ya existe un cliente con esta informacion");
			// 	return response;
			// }
			

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
			
			if(!clie.isPresent())
				return new Response(false, null, "El cliente no existe.");
			
			Cliente _cliente = clie.get();

			_cliente.setNombreCompleto(cliente.getNombreCompleto());
			_cliente.setCelular(cliente.getCelular());
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

		Optional<Cliente> cliente = repocliente.findById(id);

		if(!cliente.isPresent())
			return new Response(false, null, "El cliente no existe.");
		if(cliente.get().getEstado().getIdEstado() != 1)
			return new Response(false, null, "El cliente no existe.");

		return new Response(true, cliente.get(), "");
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

		Estado estado = new Estado();
		estado.setIdEstado(1L);

		Cliente cliente = mapper.map(clienteDto, Cliente.class);
		cliente.setEstado(estado);


		if(clienteDto.getIdCliente() == 0) {
			
			cliente.setFechaCreacion(new Date(System.currentTimeMillis()));
			
			Rol rol = reporol.findById(8L).get();
			
			Usuario usuario = new Usuario();
			usuario.setUsuario(clienteDto.getCorreo());
			usuario.setContrasenia(passwordEncoder.encode(clienteDto.getNumeroDocumentoIdentidad()));
			usuario.setRol(rol);
			
			cliente.setUsuario(usuario);
		}
		
		return cliente;
	}

	@Override
	public Response actualizarDireccion(DTODireccion dtoDireccion) {
		
		Response response = new Response();
		
		try {
			String token = TokenClientInterceptor.token;
			token = token.replace("Bearer ", "");
			String correo = jwtProvider.getUsuarioToken(token);
			Cliente cliente = repocliente.findByCorreo(correo).get();
			
			cliente.setDistrito(repodistrito.findById(dtoDireccion.getIdDistrito()).get());
			cliente.setDireccion(dtoDireccion.getDireccion());
			cliente.setReferencia(dtoDireccion.getReferencia());
			
			cliente = repocliente.save(cliente);
			
			response.setSuccess(true);
			response.setMessage("Direccion actualizada exitosamente");
			response.setResult(cliente);
			
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error al actualizar la direccion");
		}

		
		return response;
	}

}
