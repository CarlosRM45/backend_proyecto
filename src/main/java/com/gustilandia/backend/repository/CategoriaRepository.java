package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update categoria set id_estado = 2 where id_categoria =:id",nativeQuery = true)
    int deleteCategoria(@Param("id") Long id);

    boolean existsByCategoria(String categoria);
}
