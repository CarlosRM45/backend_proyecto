package com.gustilandia.backend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gustilandia.backend.model.Usuario;

public class UsuarioJWT implements UserDetails{
	
	private String nombre;
	private String usuario;
	private String contrasenia;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UsuarioJWT(String nombre, String usuario, String contrasenia,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.authorities = authorities;
	}
	
	public static UsuarioJWT build(Usuario usuario) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombreRol()));
		return new UsuarioJWT(usuario.getUsuario(), usuario.getUsuario(), usuario.getContrasenia(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return contrasenia;
	}
	
	@Override
	public String getUsername() {
		return usuario;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	
	

}
