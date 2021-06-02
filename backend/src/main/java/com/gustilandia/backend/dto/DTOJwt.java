package com.gustilandia.backend.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class DTOJwt {
	
	private String token;
	private String usuario;
	private String rol;
	private Long id;
	private String nombre;
	
	public DTOJwt() {
	}

	public DTOJwt(String token, String usuario, GrantedAuthority authorities) {
		this.token = token;
		this.usuario = usuario;
		this.rol = authorities.toString();
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	
	
	

}
