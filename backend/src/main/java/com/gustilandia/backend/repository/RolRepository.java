package com.gustilandia.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
	
	Optional<Rol> findByNombreRol(String nombreRol);

}
