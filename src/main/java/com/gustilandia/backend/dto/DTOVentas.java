package com.gustilandia.backend.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class DTOVentas implements Serializable{
	
	private Long idVenta;
	
	@NotNull(message = "Seleccione un tipo de comprobante")
	@Min(value = 1, message = "Seleccione un tipo de comprobante")
	private Long idTipoComprobanteSunat;
	
	private DTOTarjeta tarjeta;
	
	private List<@Valid DTODetalleVenta> detalleVenta;
	
	public DTOVentas() {
	}
	

	public DTOVentas(Long idVenta,
			@NotNull(message = "Seleccione un tipo de comprobante") @Min(value = 1, message = "Seleccione un tipo de comprobante") Long idTipoComprobanteSunat,
			 DTOTarjeta tarjeta, List<DTODetalleVenta> detalleVenta) {
		this.idVenta = idVenta;
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
		this.tarjeta = tarjeta;
		this.detalleVenta = detalleVenta;
	}


	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public List<DTODetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(List<DTODetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	public Long getIdTipoComprobanteSunat() {
		return idTipoComprobanteSunat;
	}
	public void setIdTipoComprobanteSunat(Long idTipoComprobanteSunat) {
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
	}
	public DTOTarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(DTOTarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	

}


