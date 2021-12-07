package com.gustilandia.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DTOPerfil {
	
	String getIdCliente();
	
	String getNombreCompleto();
	
	@Value("#{target.documentoIdentidad?.idDocumentoIdentidad?: '0'}")
	String getIdDocumentoIdentidad();
	
	@Value("#{target.documentoIdentidad?.documentoIdentidad?: ' '}")
	String getNombreDocumentoIdentidad();
	
	String getNumeroDocumentoIdentidad();
	
	String getCorreo();
	
	String getCelular();
	
	String getDireccion();
	
	String getReferencia();
	
	@Value("#{target.distrito?.idDistrito ?: '0'}")
	String getIdDistrito();
	
	@Value("#{target.distrito?.distrito?: ' '}")
	String getNombreDistrito();

}
