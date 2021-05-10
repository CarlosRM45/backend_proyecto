package com.gustilandia.backend.service;

import java.util.List;

import com.gustilandia.backend.model.Usuario;

public interface UsuarioService {
	
	Usuario registrarUsuario(Usuario usuario);
	
	List<Usuario> listarUsuarios();

}
