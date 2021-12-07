package com.gustilandia.backend.service;

import com.gustilandia.backend.dto.DTOVentaCarrito;
import com.gustilandia.backend.dto.DTOVentaEstado;
import com.gustilandia.backend.dto.DTOVentas;

public interface VentaService extends ICRUD<DTOVentas>{

    Response insertVentaCarrito(DTOVentaCarrito dtoVentaCarrito);
    Response aumentarCantidadProducto(DTOVentaCarrito dtoVentaCarrito);
    Response cambiarEstadoVenta(DTOVentaEstado dtoVentaEstado);
    Response listarVentasPorRol(Long idRol, Long idUsuario);
    Response anular(Long id);
    Response listarVentaPorCliente();
    
    Response VentaPorCliente();
    Response DetalleVentaCliente(Long idVenta);
    
}
