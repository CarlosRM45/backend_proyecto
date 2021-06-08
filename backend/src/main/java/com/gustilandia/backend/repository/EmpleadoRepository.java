package com.gustilandia.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update empleado set id_estado = 2 where id_empleado =:id",nativeQuery = true)
    int deleteEmpleado(@Param("id") Long id);
	
	Optional<Empleado> findByCorreo(String correo);
	
	boolean existsByCorreo(String correo);
	
	boolean existsByNumeroDocumentoIdentidad(String numero);

}
