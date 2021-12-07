package com.gustilandia.backend.service;

import com.gustilandia.backend.dto.DTOCategoria;

public interface CategoriaService extends ICRUD<DTOCategoria>{

    Response listarProductoCategoria(Long id);

}
