package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import com.gustilandia.backend.dto.DTOEmpleado;
import com.gustilandia.backend.model.Empleado;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Rol;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.EmpleadoRepository;
import com.gustilandia.backend.repository.RolRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.security.TokenClientInterceptor;
import com.gustilandia.backend.service.EmpleadoService;
import com.gustilandia.backend.service.Response;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired
	private RolRepository reporol;

	@Autowired
	private EmpleadoRepository repoempleado;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UsuarioRepository repousuario;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Response registrar(DTOEmpleado dtoEmpleado) {
		
		Response response = new Response();
		Empleado empleado = new Empleado();
		
		try {
			
			if(repoempleado.existsByCorreo(dtoEmpleado.getCorreo())) {
				response.setMessage("Ya existe un empleado con ese correo");
				return response;
			}
			
			if(repoempleado.existsByNumeroDocumentoIdentidad(dtoEmpleado.getNumeroDocumentoIdentidad())) {
				response.setMessage("Ya existe un empleado con ese dni");
				return response;
			}
			
			empleado = repoempleado.save(mappingEmpleado(dtoEmpleado));
			response.setResult(empleado);
			response.setMessage("Empleado registrado satisfactioramente");
			response.setResult(true);
			
		} catch (Exception e) {
			response.setMessage("Error al registrar el empleado" + e.getMessage());
			response.setSuccess(false);
		}
		
		return response;
	}

	@Override
	public Response actualizar(DTOEmpleado dtoEmpleado) {
		
		Response response = new Response();
		Empleado empleado = mappingEmpleado(dtoEmpleado);
		
		try {
			
			Optional<Empleado> optEmpleado = repoempleado.findById(dtoEmpleado.getIdEmpleado());
			
			if(!optEmpleado.isPresent()) {
				return new Response(false,null,"El empleado no existe");
			}
			
			Empleado _empleado = optEmpleado.get();
			_empleado.setNombres(empleado.getNombres());
			_empleado.setApellidos(empleado.getApellidos());
			_empleado.setCodigoVendedor(empleado.getCodigoVendedor());
			_empleado.setCorreo(empleado.getCorreo());
			_empleado.getUsuario().setUsuario(empleado.getCorreo());
			
			response.setResult(repoempleado.save(_empleado));
			response.setSuccess(true);
			response.setMessage("Empleado actualizado");
			
		} catch (Exception e) {
			response.setMessage("Error al actualizar el empleado");
			response.setSuccess(false);
		}
		
		return response;
	}

	@Override
	public Response eliminar(Long id) {
		
		Response response = new Response();
		
		try {
			repoempleado.deleteEmpleado(id);
			response.setSuccess(true);
			response.setMessage("El empleado fue eliminado exitosamente");
		} catch (Exception e) {
			response.setMessage("Error al eliminar el empleado");
		}
		
		return response;
	}

	@Override
	public Response buscarId(Long id) {
		
		Optional<Empleado> optEmpleado = repoempleado.findById(id);
		
		if(!optEmpleado.isPresent())
			return new Response(false, null, "El empleado no existe");
		if(optEmpleado.get().getEstado().getIdEstado() != 1)
			return new Response(false, null, "El empleado no existe");
		
		return new Response(true, optEmpleado.get(), "");
	}

	@Override
	public Response listar() {

		List<Empleado> listadoProductos = repoempleado.findAll().stream()
				.filter(producto -> producto.getEstado().getIdEstado() == 1)
				.collect(Collectors.toList());

		return new Response(true, listadoProductos , "");
	}

	@Override
	public Response listarRol() {

		return new Response(true, reporol.findAll() , "");
	}


	
	private Empleado mappingEmpleado(DTOEmpleado dtoEmpleado) {
		
		Estado estado = new Estado();
		estado.setIdEstado(1L);
		
		Empleado empleado = mapper.map(dtoEmpleado, Empleado.class);
		empleado.setEstado(estado);
		
		String token = TokenClientInterceptor.token;
		token = token.replace("Bearer ", "");
		String correo = jwtProvider.getUsuarioToken(token);
		
		Usuario usuario = repoempleado.findByCorreo(correo).get().getUsuario();
		
		if(dtoEmpleado.getIdEmpleado() != 0) {
			empleado.setFechaEdita(new Date(System.currentTimeMillis()));
			empleado.setUsuarioEdita(usuario);
		}else {
			empleado.setFechaCrea(new Date(System.currentTimeMillis()));
			empleado.setUsuarioCrea(usuario);
			Rol rol = reporol.findById(dtoEmpleado.getIdRol()).get();
			
			Usuario nuevousuario = new Usuario();
			nuevousuario.setUsuario(dtoEmpleado.getCorreo());
			nuevousuario.setContrasenia(passwordEncoder.encode(dtoEmpleado.getNumeroDocumentoIdentidad()));
			nuevousuario.setRol(rol);
			empleado.setUsuario(nuevousuario);
		}
		
		return empleado;
	}

}
