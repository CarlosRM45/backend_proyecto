package com.gustilandia.backend.dto;

import java.io.Serializable;


public class DTOVentaEstado implements Serializable{
	
    private Long idVenta;
    private Long idEstado;
    private Long idRepartidor;


    public Long getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
    public Long getIdEstado() {
        return idEstado;
    }
    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }
    public Long getIdRepartidor() {
        return idRepartidor;
    }
    public void setIdRepartidor(Long idRepartidor) {
        this.idRepartidor = idRepartidor;
    }
    
	
}


