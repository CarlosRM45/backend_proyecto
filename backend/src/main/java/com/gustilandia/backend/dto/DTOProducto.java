package com.gustilandia.backend.dto;

import java.util.Date;

import org.springframework.lang.Nullable;

public class DTOProducto {
    
    private Long idProducto;

	private String producto;
	
	private String descripcion;
	
	private Double precio;
	
	private Long idCategoria;
	
	private Long idMarca;

    private Long idUnidadMedida;
	
	private int stock;
	
	private Date fechaCrea;
	
	private Long idUsuarioCrea;

    @Nullable
	private Date fechaEdita;
	
    @Nullable
	private Long idUsuarioEdita;

	private Long estado;
    

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Long idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Long getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(Long idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public Date getFechaEdita() {
        return fechaEdita;
    }

    public void setFechaEdita(Date fechaEdita) {
        this.fechaEdita = fechaEdita;
    }

    public Long getIdUsuarioEdita() {
        return idUsuarioEdita;
    }

    public void setIdUsuarioEdita(Long idUsuarioEdita) {
        this.idUsuarioEdita = idUsuarioEdita;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    
}
