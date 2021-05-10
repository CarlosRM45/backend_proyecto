package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findOneByUsuario(String usuario);

}
