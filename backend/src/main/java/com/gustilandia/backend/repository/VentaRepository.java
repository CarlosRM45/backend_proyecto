package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustilandia.backend.model.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Long>{

    @Query(value = "exec dbo.sp_insertVenta :idcliente, :idproducto ", nativeQuery = true)
    Long insertVentaCarrito(@Param("idcliente") Long idcliente, @Param("idproducto") Long idproducto);

    @Query(value = "exec dbo.sp_aumentarCantidadProducto :idproducto, :idcliente, :cantidad ", nativeQuery = true)
    Long aumentarCantidadProducto(@Param("idcliente") Long idcliente, @Param("idproducto") Long idproducto, @Param("cantidad") int cantidad);
}
