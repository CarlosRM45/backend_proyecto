package com.gustilandia.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String nombreusuario) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.buscarPorNombreUsuario(nombreusuario).get();
		
		return UsuarioJWT.build(usuario);
	}

}
