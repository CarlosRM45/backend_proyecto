package com.gustilandia.backend.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gustilandia.backend.model.DocumentoIdentidad;
import com.gustilandia.backend.model.Usuario;

public class DTOEmpleado {
	
	private long idEmpleado;
	
	@Min(value = 1, message = "Seleccione un tipo de documento de identidad")
	private Long idDocumentoIdentidad;
	
	@NotNull(message = "Ingreser un numero de documento de identidad")
    @Size(min = 8, max = 8, message = "El DNI ingresado no es valido")
	private String numeroDocumentoIdentidad;
	
	@NotBlank(message = "Ingrese un correo electronico")
    @Email(message = "Ingrese un correo electronico valido")
    @NotNull(message = "Ingrese un correo electronico")
	private String correo;
	
	@NotNull(message = "Ingrese sus nombres")
    @Size(min = 3, message = "Ingrese sus nombres, debe tener minimo 3 caracteres")
	private String nombres;
	
	@NotNull(message = "Ingrese sus apellidos")
    @Size(min = 3, message = "Ingrese sus apellidos, debe tener minimo 3 caracteres")
	private String apellidos;
	
	private String codigoVendedor;
	
	private Long idRol;

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
	
	

}
