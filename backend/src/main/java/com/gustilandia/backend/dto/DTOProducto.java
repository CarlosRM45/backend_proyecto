package com.gustilandia.backend.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

public class DTOProducto {
    
    private Long idProducto;

    @NotNull(message = "Debe ingresar un nombre del producto")
    @Size(min = 3, message = "El nombre debe tener minimo 3 caracteres")
	private String producto;
	
    @NotNull(message = "Debe ingresar una descripcion")
    @Size(min = 3, message = "La descripcion debe contener minimo 3 caracteres")
	private String descripcion;
	
    @NotNull(message = "Debe ingresar un precio")
    @DecimalMin(value = "0.01", message = "Debe ingresar un valor mayor a 0.00 para el precio")
	private Double precio;
	
    @NotNull(message = "Seleccione una categoria")
    @Min(value = 1, message = "Seleccione una categoria")
	private Long idCategoria;
	
    @NotNull(message = "Seleccione una marca")
    @Min(value = 1, message = "Seleccione una marca")
	private Long idMarca;

    @NotNull(message = "Seleccione una Unidad de Medida")
    @Min(value = 1, message = "Seleccione una Unidad de Medida")
    private Long idUnidadMedida;
	
	private int stock;

	private Long idUsuarioCrea;

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

    public Long getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(Long idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }
    
}
