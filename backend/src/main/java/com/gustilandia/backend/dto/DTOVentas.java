package com.gustilandia.backend.dto;

import java.io.Serializable;
import java.util.List;

public class DTOVentas implements Serializable{
	
	private Long idVenta;
	private int nroVenta;
	private Long idTipoComprobanteSunat;
	private String correlativoComprobante;
	private Long idCliente;
	private DTOTarjeta tarjeta;
	private List<DTODetalleVenta> detalleVenta;
	
	public DTOVentas() {
	}
	



	public DTOVentas(Long idVenta, int nroVenta, Long idTipoComprobanteSunat, String correlativoComprobante,
			Long idCliente, DTOTarjeta tarjeta, List<DTODetalleVenta> detalleVenta) {
		this.idVenta = idVenta;
		this.nroVenta = nroVenta;
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
		this.correlativoComprobante = correlativoComprobante;
		this.idCliente = idCliente;
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
	public int getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}
	public Long getIdTipoComprobanteSunat() {
		return idTipoComprobanteSunat;
	}
	public void setIdTipoComprobanteSunat(Long idTipoComprobanteSunat) {
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
	}
	public String getCorrelativoComprobante() {
		return correlativoComprobante;
	}
	public void setCorrelativoComprobante(String correlativoComprobante) {
		this.correlativoComprobante = correlativoComprobante;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public DTOTarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(DTOTarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	

}
