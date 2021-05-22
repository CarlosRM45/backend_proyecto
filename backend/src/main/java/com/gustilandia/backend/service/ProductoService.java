package com.gustilandia.backend.service;

import com.gustilandia.backend.service.Response;

import java.util.List;

import com.gustilandia.backend.dto.DTOProducto;
import com.gustilandia.backend.modelexample.ModelProducto;

public interface ProductoService extends ICRUD<DTOProducto>{

    Response buscarPorNombre(String nombre);

    List<ModelProducto> listarProductosMvl();

    List<ModelProducto> listarProductsByCategory(Long id);
    
    Response validarStock(Long id, int cantidad);

}
