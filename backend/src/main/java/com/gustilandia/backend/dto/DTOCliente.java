package com.gustilandia.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOCliente {
    
    
    private Long idCliente;

    @NotNull(message = "Ingrese sus nombres y apellidos")
    @Size(min = 3, message = "Ingrese sus nombres y apellidos, debe tener minimo 3 caracteres")
    private String nombreCompleto;

    @Min(value = 1, message = "Seleccione un tipo de documento de identidad")
	private Long idDocumentoIdentidad;
    
    @NotNull(message = "Ingreser un numero de documento de identidad")
    @Size(min = 8, max = 8, message = "El DNI ingresado no es valido")
	private String numeroDocumentoIdentidad;
	
    @Email(message = "Ingrese un correo electronico valido")
    @NotNull(message = "Ingrese un correo electronico")
	private String correo;

	private String celular;

	private String direccion;

	private String referencia;

    private Long idDistrito;

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    private Long idEstado;

    public Long getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Long idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Long getIdDocumentoIdentidad() {
        return idDocumentoIdentidad;
    }

    public void setIdDocumentoIdentidad(Long idDocumentoIdentidad) {
        this.idDocumentoIdentidad = idDocumentoIdentidad;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


    
   
}