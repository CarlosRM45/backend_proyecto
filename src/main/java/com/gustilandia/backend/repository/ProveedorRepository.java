package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update proveedor set id_estado = 2 where id_proveedor =:id",nativeQuery = true)
    int deleteProveedor (@Param("id") Long id);

    boolean existsByNumeroDocumentoIdentidad(String numeroDocumentoIdentidad);

}
