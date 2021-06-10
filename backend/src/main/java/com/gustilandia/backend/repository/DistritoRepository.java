package com.gustilandia.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gustilandia.backend.dto.DTODistrito;
import com.gustilandia.backend.model.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Long>{
	
	@Query(value =  "select id_distrito, distrito from distrito where id_provincia = 11", nativeQuery = true)
	List<DTODistrito> listarDistritos();

}
