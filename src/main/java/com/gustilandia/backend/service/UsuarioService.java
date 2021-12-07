package com.gustilandia.backend.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.gustilandia.backend.dto.DTOJwt;
import com.gustilandia.backend.dto.DTOLogin;
import com.gustilandia.backend.dto.DTOPerfil;
import com.gustilandia.backend.dto.DTORefreshToken;
import com.gustilandia.backend.model.Usuario;

public interface UsuarioService {
	
	Usuario registrarUsuario(Usuario usuario);
	
	List<Usuario> listarUsuarios();
	
	Optional<Usuario> buscarPorNombreUsuario(String usuario);

	DTOJwt login(DTOLogin dtoLogin);
	
	DTORefreshToken refresh(DTORefreshToken dtoRefresh) throws ParseException;

	DTOPerfil verPerfilCliente();
	
}
