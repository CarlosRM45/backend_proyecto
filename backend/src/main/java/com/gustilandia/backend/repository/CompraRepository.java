package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update compra set id_estado = 5 where id_compra =:id",nativeQuery = true)
    int anular(@Param("id") Long id);

}
