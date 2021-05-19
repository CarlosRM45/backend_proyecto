package com.gustilandia.backend.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class DTOTarjeta {
	
	private String nroTarjeta;
	private String cvv;
	private Date fechaVencimiento;
	private String correo;
	
	public DTOTarjeta(String nroTarjeta, String cvv, Date fechaVencimiento, String correo) {
		this.nroTarjeta = nroTarjeta;
		this.cvv = cvv;
		this.fechaVencimiento = fechaVencimiento;
		this.correo = correo;
	}
	
	public DTOTarjeta() {
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
