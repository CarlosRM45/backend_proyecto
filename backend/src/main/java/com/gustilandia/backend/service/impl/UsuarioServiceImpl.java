package com.gustilandia.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	

	@Override
	public Usuario registrarUsuario(Usuario usuario) {

		return repository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		
		return repository.findAll();
	}
	
	

}
