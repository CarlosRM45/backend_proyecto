package com.gustilandia.backend.service;

import com.gustilandia.backend.dto.DTOVentaCarrito;
import com.gustilandia.backend.dto.DTOVentas;

public interface VentaService extends ICRUD<DTOVentas>{

    Response insertVentaCarrito(DTOVentaCarrito dtoVentaCarrito);
    Response aumentarCantidadProducto(DTOVentaCarrito dtoVentaCarrito);
}
