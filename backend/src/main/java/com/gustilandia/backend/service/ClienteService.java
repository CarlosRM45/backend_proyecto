package com.gustilandia.backend.service;


import com.gustilandia.backend.dto.DTOCliente;
import com.gustilandia.backend.dto.DTODireccion;
import com.gustilandia.backend.model.Cliente;

public interface ClienteService extends ICRUD<DTOCliente>{
	
	Response actualizarDireccion(DTODireccion dtoDireccion);
	
}
