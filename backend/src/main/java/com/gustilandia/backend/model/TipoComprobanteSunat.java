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
}
