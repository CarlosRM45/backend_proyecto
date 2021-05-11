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

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repocliente;
	
	@Autowired
	private RolRepository reporol;
	
	@Autowired
	private UsuarioRepository repousuario;

	@Override
	public Cliente registrar(Cliente cliente) {
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(0L);
		usuario.setUsuario(cliente.getUsuario().getUsuario());
		usuario.setContrasenia(cliente.getUsuario().getContrasenia());
		usuario.setRol(reporol.findById(2L).get());
		usuario = repousuario.save(usuario);
		
		cliente.setIdCliente(0L);
		cliente.setUsuario(usuario);
		cliente.setFechaCreacion(new Date(System.currentTimeMillis()));
		return repocliente.save(cliente);
	}

	@Override
	public Cliente actualizar(Cliente cliente) {
		Optional<Cliente> clie = repocliente.findById(cliente.getIdCliente());
		if(clie != null) {
			return repocliente.save(cliente);
		}			
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		return false;
	}

	@Override
	public Cliente buscarId(Long id) {
		return repocliente.findById(id).get();
	}

	@Override
	public List<Cliente> listar() {
		return repocliente.findAll();
	}

}
