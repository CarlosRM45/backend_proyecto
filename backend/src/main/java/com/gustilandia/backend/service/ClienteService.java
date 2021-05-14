package com.gustilandia.backend.service;

import com.gustilandia.backend.model.Cliente;

public interface ClienteService extends ICRUD<Cliente>{

    Response iniciarSesion(String correo, String contrasenia);
}
