package com.gustilandia.backend.service;

import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.dto.DTOProducto;

public interface ProductoService extends ICRUD<DTOProducto>{

    Response buscarPorNombre(String nombre);

}
