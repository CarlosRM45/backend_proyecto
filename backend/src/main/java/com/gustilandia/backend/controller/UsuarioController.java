package com.gustilandia.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOJwt;
import com.gustilandia.backend.dto.DTOLogin;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.model.Venta;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return new ResponseEntity<>(service.listarUsuarios(), HttpStatus.OK);
	}
	
	
	/*
	 * @PostMapping("/registrar") public ResponseEntity<Usuario>
	 * registrarUsuario(@RequestBody Usuario usuario) {
	 * 
	 * usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
	 * 
	 * return new ResponseEntity<>(service.registrarUsuario(usuario),
	 * HttpStatus.OK); }
	 */
	
	@PostMapping("/login")
	public ResponseEntity<DTOJwt> Login(@Valid @RequestBody DTOLogin login){
		
		/*
		 * Authentication authentication = authenticationManager.authenticate(new
		 * UsernamePasswordAuthenticationToken(login.getUsuario(),
		 * login.getContrasenia()));
		 * SecurityContextHolder.getContext().setAuthentication(authentication);
		 * 
		 * String jwt = "Bearer " + jwtProvider.generateToken(authentication);
		 * UserDetails userDetails = (UserDetails) authentication.getPrincipal(); DTOJwt
		 * dtoJwt = new DTOJwt(jwt, userDetails.getUsername(),
		 * userDetails.getAuthorities().stream().findFirst().get());
		 */
		return new ResponseEntity<DTOJwt>(service.login(login), HttpStatus.OK);
		
	}
	

}
