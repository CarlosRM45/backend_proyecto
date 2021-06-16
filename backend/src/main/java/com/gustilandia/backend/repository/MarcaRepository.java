package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import com.gustilandia.backend.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update marca set id_estado = 2 where id_marca =:id",nativeQuery = true)
    int deleteMarca(@Param("id") Long id);

    boolean existsByNombreMarca(String nombreMarca);
}
