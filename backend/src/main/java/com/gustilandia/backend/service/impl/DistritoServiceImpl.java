package com.gustilandia.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTODistrito;
import com.gustilandia.backend.repository.DistritoRepository;
import com.gustilandia.backend.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService{
	
	@Autowired
	private DistritoRepository repository;

	@Override
	public List<DTODistrito> listarDistritos() {
		
		return repository.listarDistritos();
	}

}
