package com.gustilandia.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.gustilandia.backend.dto.DTODashboardProductoVC;
import com.gustilandia.backend.dto.DTOReporteKardex;
import com.gustilandia.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update producto set id_estado = 2 where id_producto =:id",nativeQuery = true)
    int deleteProducto(@Param("id") Long id);

    @Query(value = "exec dbo.sp_listProductMovil", nativeQuery = true)
    List<Object[]> listarProductosMvl();
    
    @Query(value = "exec dbo.sp_listProductByCategory :id", nativeQuery = true)
    List<Object[]> listarProductsByCategory(@Param("id") Long id);

    @Query(value = "exec dbo.sp_listProductsByName :producto ", nativeQuery = true)
    List<Object[]> listarProductsByName(@Param("producto") String id);
    
    //reporte
	@Query(value = "exec dbo.sp_reporte_kardex :idunidadmedida, :idcategoria, :fechainicio, :fechafin" , nativeQuery = true)
	List<DTOReporteKardex> reporteKardex(@Param("idunidadmedida") Integer idUnidadMedida, @Param("idcategoria") Integer idCategoria ,@Param("fechainicio") String fechaInicio, @Param("fechafin") String fechaFin);

	//dashboard
	
	@Query(value = "exec dbo.sp_dashboard_producto_vc :idmeses", nativeQuery = true)
	List<DTODashboardProductoVC> dashboardProductoVC(@Param("idmeses") String idMeses);
}
