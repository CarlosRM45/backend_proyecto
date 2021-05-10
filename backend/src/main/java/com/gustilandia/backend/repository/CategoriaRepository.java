package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
