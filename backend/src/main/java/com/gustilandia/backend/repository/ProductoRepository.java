package com.gustilandia.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.gustilandia.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.producto like '%' + :name + '%'")
	List<Producto> buscarPorNombre(@Param("name") String nombre);

	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update producto set id_estado = 2 where id_producto =:id",nativeQuery = true)
    int deleteProducto(@Param("id") Long id);

}
