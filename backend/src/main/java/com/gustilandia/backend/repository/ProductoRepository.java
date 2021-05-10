package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
