package com.gustilandia.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gustilandia.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.producto like %?1")
	List<Producto> buscarPorNombre(String nombre);

}
