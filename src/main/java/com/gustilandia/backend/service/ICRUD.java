package com.gustilandia.backend.service;


public interface ICRUD<T> {
	
	Response registrar(T t);
	
	Response actualizar(T t);
	
	Response eliminar(Long id);
	
	Response buscarId(Long id);
	
	Response listar();

}
