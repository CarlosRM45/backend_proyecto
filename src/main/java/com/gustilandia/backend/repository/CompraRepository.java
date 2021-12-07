package com.gustilandia.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.dto.DTODashboardComprasCategoria;
import com.gustilandia.backend.dto.DTODashboardComprasMes;
import com.gustilandia.backend.dto.DTODashboardComprasProducto;
import com.gustilandia.backend.dto.DTODashboardComprasProveedor;
import com.gustilandia.backend.dto.DTODashboardComprasTotal;
import com.gustilandia.backend.dto.DTOReporteCompras;
import com.gustilandia.backend.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update compra set id_estado = 5 where id_compra =:id",nativeQuery = true)
    int anular(@Param("id") Long id);
	
	//Reportes
	
	@Query(value = "exec dbo.sp_reporte_compras :idproveedor, :idproducto, :fechainicio, :fechafin" , nativeQuery = true)
	List<DTOReporteCompras> reporteCompras(@Param("idproveedor") Integer idProveedor, @Param("idproducto") Integer idProducto, @Param("fechainicio") String fechaInicio, @Param("fechafin") String fechaFin);

	//Dashboard
	
	@Query(value = "exec dbo.sp_dashboard_compras_proveedor :idmeses" , nativeQuery = true)
	List<DTODashboardComprasProveedor> dashboardComprasProveedor(@Param("idmeses") String idMeses);
	
	@Query(value = "exec dbo.sp_dashboard_compras_producto :idmeses" , nativeQuery = true)
	List<DTODashboardComprasProducto> dashboardComprasProducto(@Param("idmeses") String idMeses);	

	@Query(value = "exec dbo.sp_dashboard_compras_mes :idmeses" , nativeQuery = true)
	List<DTODashboardComprasMes> dashboardComprasMes(@Param("idmeses") String idMeses);
	
	@Query(value = "exec dbo.sp_dashboard_compras_categoria :idmeses" , nativeQuery = true)
	List<DTODashboardComprasCategoria> dashboardComprasCategoria(@Param("idmeses") String idMeses);
	
	@Query(value = "exec dbo.sp_dashboard_compras_totales :idmeses" , nativeQuery = true)
	List<DTODashboardComprasTotal> dashboardComprasTotal(@Param("idmeses") String idMeses);
	
}
