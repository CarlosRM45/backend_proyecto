package com.gustilandia.backend.service;

import com.gustilandia.backend.service.Response;

import java.util.List;

import com.gustilandia.backend.dto.DTOProducto;

public interface ProductoService extends ICRUD<DTOProducto>{

    Response buscarPorNombre(String nombre);

    List<DTOProducto> listarProductosMvl();
    
    Response validarStock(Long id, int cantidad);

}
