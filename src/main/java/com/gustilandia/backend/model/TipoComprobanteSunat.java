package com.gustilandia.backend.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_comprobante_sunat")
public class TipoComprobanteSunat {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoComprobanteSunat;

    @Column(name = "tipo_comprobante_sunat")
	private String tipoComprobanteSunat;

    @Column(name = "codigo_sunat")
	private String codigoSunat;

	@Column(name = "serie")
	private String serie;

	public Long getIdTipoComprobanteSunat() {
		return idTipoComprobanteSunat;
	}

	public void setIdTipoComprobanteSunat(Long idTipoComprobanteSunat) {
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
	}

	public String getTipoComprobanteSunat() {
		return tipoComprobanteSunat;
	}

	public void setTipoComprobanteSunat(String tipoComprobanteSunat) {
		this.tipoComprobanteSunat = tipoComprobanteSunat;
	}

	public String getCodigoSunat() {
		return codigoSunat;
	}

	public void setCodigoSunat(String codigoSunat) {
		this.codigoSunat = codigoSunat;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public TipoComprobanteSunat(Long idTipoComprobanteSunat, String tipoComprobanteSunat, String codigoSunat,
			String serie) {
		this.idTipoComprobanteSunat = idTipoComprobanteSunat;
		this.tipoComprobanteSunat = tipoComprobanteSunat;
		this.codigoSunat = codigoSunat;
		this.serie = serie;
	}

	public TipoComprobanteSunat() {
	}
	
	
	
}
