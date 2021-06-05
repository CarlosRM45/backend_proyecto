package com.gustilandia.backend.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOJwt;
import com.gustilandia.backend.dto.DTOLogin;
import com.gustilandia.backend.dto.DTOPerfil;
import com.gustilandia.backend.dto.DTORefreshToken;
import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.model.Empleado;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.repository.EmpleadoRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.security.TokenClientInterceptor;
import com.gustilandia.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private ClienteRepository repoCliente;
	
	@Autowired
	private EmpleadoRepository repoEmpleado;

	@Override
	public Usuario registrarUsuario(Usuario usuario) {

		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		return repository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		
		return repository.findAll();
	}

	@Override
	public Optional<Usuario> buscarPorNombreUsuario(String usuario) {
		
		return repository.findByUsuario(usuario);
	}
	
	@Override
	public DTOJwt login(DTOLogin login) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getContrasenia()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = "Bearer " + jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		DTOJwt dtoJwt = new DTOJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities().stream().findFirst().get());
	
		Optional<Empleado> _empleado = repoEmpleado.findByCorreo(dtoJwt.getUsuario());
		Optional<Cliente> _cliente = repoCliente.findByCorreo(dtoJwt.getUsuario());
		if(_empleado.isPresent()) {
			dtoJwt.setId(_empleado.get().getIdEmpleado());
			dtoJwt.setNombre(_empleado.get().getNombres() + " " + _empleado.get().getApellidos());
		}else {
			dtoJwt.setId(_cliente.get().getIdCliente());
			dtoJwt.setNombre(_cliente.get().getNombreCompleto());
		}
		
		return dtoJwt;
	}

	@Override
	public DTORefreshToken refresh(DTORefreshToken dtoRefresh) throws ParseException {
		
		String token = dtoRefresh.getToken().replace("Bearer ", "");
		token = "Bearer " + jwtProvider.refresh(token);
		DTORefreshToken refreshToken = new DTORefreshToken();
		refreshToken.setToken(token);
		
		return refreshToken;
	}

	@Override
	public DTOPerfil verPerfilCliente() {
		
		String token = TokenClientInterceptor.token;
		token = token.replace("Bearer ", "");
		String correo = jwtProvider.getUsuarioToken(token);
		
		Optional<Cliente> _cliente = repoCliente.findByCorreo(correo);
		Cliente cliente = _cliente.get();
		
		ProjectionFactory pf = new SpelAwareProxyProjectionFactory();
		DTOPerfil dtoPerfil = pf.createProjection(DTOPerfil.class, cliente);
		
		return dtoPerfil;
	}
	

}
