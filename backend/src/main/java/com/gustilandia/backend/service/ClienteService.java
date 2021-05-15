package com.gustilandia.backend.service;

import com.gustilandia.backend.dto.DTOCliente;

public interface ClienteService extends ICRUD<DTOCliente>{

    Response iniciarSesion(String correo, String contrasenia);
}
