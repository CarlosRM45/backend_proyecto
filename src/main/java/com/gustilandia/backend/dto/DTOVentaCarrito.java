package com.gustilandia.backend.dto;

public class DTOVentaCarrito {
    
    private Long idCliente;
    private Long idProducto;
    private int cantidad;

    public DTOVentaCarrito() {
    }

    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    
}
