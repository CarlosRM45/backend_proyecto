package com.gustilandia.backend.modelexample;

public class ModelProducto{

    private Long idProducto;
    private String nameProduct;
    private String descripcion;
    private double precio;
    private String imagen;
    private int stock;
    private Long idUnidadMedida;
    private Long idMarca;
    private String unidadMedida;
    private String nameMarca;
    
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
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
    public Long getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }
    public String getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    public String getNameMarca() {
        return nameMarca;
    }
    public void setNameMarca(String nameMarca) {
        this.nameMarca = nameMarca;
    }

}
