package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Long>{

    @Query(value = "exec dbo.sp_insertVenta :idcliente, :idproducto ", nativeQuery = true)
    Long insertVentaCarrito(@Param("idcliente") Long idcliente, @Param("idproducto") Long idproducto);

    @Query(value = "exec dbo.sp_aumentarCantidadProducto :idproducto, :idcliente, :cantidad ", nativeQuery = true)
    Long aumentarCantidadProducto(@Param("idcliente") Long idcliente, @Param("idproducto") Long idproducto, @Param("cantidad") int cantidad);

    @Query(value = "exec dbo.cambiarEstadoVenta :idVenta, :idRepartidor, :idEstado ", nativeQuery = true)
    Long cambiarEstadoVenta(@Param("idVenta") Long idVenta, @Param("idRepartidor") Long idRepartidor, @Param("idEstado") Long idEstado);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update venta set id_estado = 5 where id_venta =:id",nativeQuery = true)
    int anular(@Param("id") Long id);
    
    @Query(value = "select max(correlativo_comprobante) from venta where id_tipo_comprobante_sunat =:id", nativeQuery = true)
    int correlativo(@Param("id") Long id);

}
