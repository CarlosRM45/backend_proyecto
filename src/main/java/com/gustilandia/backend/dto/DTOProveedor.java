package com.gustilandia.backend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOProveedor {
	
	private Long idProveedor;
	
	@Min(value = 1, message = "Seleccione un tipo de documento de identidad")
	private Long idDocumentoIdentidad;
	
	@NotNull(message = "Ingreser un numero de documento de identidad")
    //@Size(min = 8, max = 8, message = "El DNI ingresado no es valido")
	private String numeroDocumentoIdentidad;
	
	@NotNull(message = "Ingrese su Razon Comercial")
    @Size(min = 1, message = "Ingrese su Razon Comercial")
	private String razonComercial;
	
    @Email(message = "Ingrese un correo electronico valido")
    @NotNull(message = "Ingrese un correo electronico")
	private String correo;
    
    private String celular;

	private String direccion;

	private String referencia;

    private Long idDistrito;

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
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

	public String getRazonComercial() {
		return razonComercial;
	}

	public void setRazonComercial(String razonComercial) {
		this.razonComercial = razonComercial;
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

	public Long getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Long idDistrito) {
		this.idDistrito = idDistrito;
	}

	public DTOProveedor(Long idProveedor,
			@Min(value = 1, message = "Seleccione un tipo de documento de identidad") Long idDocumentoIdentidad,
			@NotNull(message = "Ingreser un numero de documento de identidad") @Size(min = 8, max = 8, message = "El DNI ingresado no es valido") String numeroDocumentoIdentidad,
			@NotNull(message = "Ingrese su Razon Comercial") @Size(min = 1, message = "Ingrese su Razon Comercial") String razonComercial,
			@Email(message = "Ingrese un correo electronico valido") @NotNull(message = "Ingrese un correo electronico") String correo,
			String celular, String direccion, String referencia, Long idDistrito) {
		this.idProveedor = idProveedor;
		this.idDocumentoIdentidad = idDocumentoIdentidad;
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
		this.razonComercial = razonComercial;
		this.correo = correo;
		this.celular = celular;
		this.direccion = direccion;
		this.referencia = referencia;
		this.idDistrito = idDistrito;
	}

	public DTOProveedor() {
	}
    
    

}
