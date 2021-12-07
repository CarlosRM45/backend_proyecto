package com.gustilandia.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.dto.DTOListadoVenta;
import com.gustilandia.backend.dto.DTODashboardCVMes;
import com.gustilandia.backend.dto.DTODashboardTotales;
import com.gustilandia.backend.dto.DTODashboardVentaProducto;
import com.gustilandia.backend.dto.DTODashboardVentasCategoria;
import com.gustilandia.backend.dto.DTODashboardVentasCliente;
import com.gustilandia.backend.dto.DTODashboardVentasMes;
import com.gustilandia.backend.dto.DTODashboardVentasTotal;
import com.gustilandia.backend.dto.DTODetallVentaCliente;
import com.gustilandia.backend.dto.DTOReporteClientes;
import com.gustilandia.backend.dto.DTOReporteVentas;
import com.gustilandia.backend.dto.DTOVentaCliente;
import com.gustilandia.backend.dto.DTOVentaPorCliente;
import com.gustilandia.backend.model.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query(value = "exec dbo.sp_insertVenta :idcliente, :idproducto ", nativeQuery = true)
    Long insertVentaCarrito(@Param("idcliente") Long idcliente, @Param("idproducto") Long idproducto);

    @Query(value = "exec dbo.sp_aumentarCantidadProducto :idproducto, :idcliente, :cantidad ", nativeQuery = true)
    Long aumentarCantidadProducto(@Param("idcliente") Long idcliente, @Param("idproducto") Long idproducto,
            @Param("cantidad") int cantidad);

    @Query(value = "exec dbo.cambiarEstadoVenta :idVenta, :idRepartidor, :idEstado ", nativeQuery = true)
    Long cambiarEstadoVenta(@Param("idVenta") Long idVenta, @Param("idRepartidor") Long idRepartidor,
            @Param("idEstado") Long idEstado);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update venta set id_estado = 5 where id_venta =:id", nativeQuery = true)
    int anular(@Param("id") Long id);

    @Query(value = "select isnull(max(correlativo_comprobante), 0) from venta where id_tipo_comprobante_sunat =:id", nativeQuery = true)
    int correlativo(@Param("id") Long id);
    
    @Query(value = "exec dbo.sp_listVentas" , nativeQuery = true)
    List<DTOListadoVenta> listadoVentas();
    
    @Query(value = "exec dbo.sp_validar_stock :cantidad, :idProducto", nativeQuery = true)
    int validarStock(@Param("cantidad") int cantidad, @Param("idProducto") Long idProducto);

    /*@Query(value = "exec dbo.sp_actualizar_stock :cantidad, :idProducto")
    void actualizarStock(@Param("cantidad") int cantidad, @Param("idProducto") Long idProducto);
    */

    @Query(value = "select v from Venta v"
    		+ " where v.cliente.idCliente = ?1")
    List<DTOVentaPorCliente> findByCliente(Long idCliente);

    @Query(value = "exec dbo.sp_ventas_por_cliente :idcliente" , nativeQuery = true)
    List<DTOVentaCliente> ventaCliente(@Param("idcliente")Long idCliente);
    
    @Query(value = "exec dbo.sp_detalleventa_por_cliente :idventa" , nativeQuery = true)
    List<DTODetallVentaCliente> detalleVentaCliente(@Param("idventa")Long idVenta);
    
    
    //Reportes

    @Query(value = "exec dbo.sp_reporte_clientes :fechainicio, :fechafin" , nativeQuery = true)
    List<DTOReporteClientes> reporteClientes(@Param("fechainicio") String fechaInicio, @Param("fechafin") String fechaFin);

    @Query(value = "exec dbo.sp_reporte_ventas :idestado, :idcliente, :fechainicio, :fechafin", nativeQuery = true)
    List<DTOReporteVentas> reporteVentas(@Param("idestado") Integer idEstado, @Param("idcliente") Integer idCliente, @Param("fechainicio") String fechaInicio, @Param("fechafin") String fechaFin);

    //dashboard general
    
    @Query(value = "exec dbo.sp_dashboard_mes_vc :idmeses", nativeQuery = true)
    List<DTODashboardCVMes> dashboardCVMes(@Param("idmeses") String idMeses);
    
    @Query(value = "exec dbo.sp_dashboard_totales :idmeses", nativeQuery = true)
    List<DTODashboardTotales> dashboardTotales(@Param("idmeses") String idMeses);
    
    
    //dashboard ventas
    
    @Query(value = "exec dbo.sp_dashboard_ventas_cliente :idmeses", nativeQuery = true)
    List<DTODashboardVentasCliente> dashboardVentasCliente(@Param("idmeses") String idMeses);
    
    @Query(value = "exec dbo.sp_dashboard_ventas_producto :idmeses", nativeQuery = true)
    List<DTODashboardVentaProducto> dashboardVentasProducto(@Param("idmeses") String idMeses);
 
    @Query(value = "exec dbo.sp_dashboard_ventas_mes :idmeses", nativeQuery = true)
    List<DTODashboardVentasMes> dashboardVentasMes(@Param("idmeses") String idMeses);
 
    @Query(value = "exec dbo.sp_dashboard_venta_categoria :idmeses", nativeQuery = true)
    List<DTODashboardVentasCategoria> dashboardVentasCategoria(@Param("idmeses") String idMeses);
    
    @Query(value = "exec dbo.sp_dashboard_venta_total :idmeses", nativeQuery = true)
    List<DTODashboardVentasTotal> dashboardVentasTotal(@Param("idmeses") String idMeses);
}
