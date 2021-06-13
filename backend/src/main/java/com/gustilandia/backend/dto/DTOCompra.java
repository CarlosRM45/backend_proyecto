package com.gustilandia.backend.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DTOCompra {
	
	private Long idCompra;
	
	@NotNull(message = "Seleccione un tipo de comprobante")
	@Min(value = 1, message = "Seleccione un tipo de comprobante")
	private Long idTipoComprobanteSunat;
	
	@NotNull(message = "Seleccione un proveedor")
	@Min(value = 1, message = "Seleccione un proveedor")
	private Long idProveedor;
	
	private List<@Valid DTODetalleCompra> detalleCompra;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getIdTipoComprobanteSunat() {
		return idTipoComprobanteSunat;
	}

	public void setIdTipoComprobanteSunat(Long idTipoComprobanteSunat) {
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public List<DTODetalleCompra> getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(List<DTODetalleCompra> detalleCompra) {
		this.detalleCompra = detalleCompra;
	}
	
	

}
