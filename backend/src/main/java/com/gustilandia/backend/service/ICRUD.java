package com.gustilandia.backend.service;

import java.util.List;

public interface ICRUD<T> {
	
	T registrar(T t);
	
	T actualizar(T t);
	
	boolean eliminar(Long id);
	
	T buscarId(Long id);
	
	List<T> listar();

}
