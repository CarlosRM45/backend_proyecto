package com.gustilandia.backend.service;

import com.gustilandia.backend.model.Categoria;

public interface CategoriaService extends ICRUD<Categoria>{

    Response listarProductoCategoria(Long id);
}
