package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.repository.CategoriaRepository;
import com.gustilandia.backend.repository.UsuarioRepository;
import com.gustilandia.backend.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private UsuarioRepository repositoryUsuario;

	@Override
	public Categoria registrar(Categoria categoria) {
		categoria.setIdCategoria(0L);
		categoria.setUsuarioCrea(repositoryUsuario.findById(categoria.getUsuarioCrea().getIdUsuario()).get());
		categoria.setUsuarioEdita(repositoryUsuario.findById(categoria.getUsuarioCrea().getIdUsuario()).get());
		categoria.setFechaCrea(new Date(System.currentTimeMillis()));;
		categoria.setFechaEdita(new Date(System.currentTimeMillis()));
		return repository.save(categoria);
	}

	@Override
	public Categoria actualizar(Categoria categoria) {
		Optional<Categoria> cat = repository.findById(categoria.getIdCategoria());
		if(cat != null) {
			categoria.setFechaEdita(new Date(System.currentTimeMillis()));
			return repository.save(categoria);
		}
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Categoria> cat = repository.findById(id);
		if(cat != null) {
			repository.delete(cat.get());
			return true;
		}
		return false;
	}

	@Override
	public Categoria buscarId(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Categoria> listar() {
		return repository.findAll();
	}
	
	public List<Producto> listarProductoCategoria(Long id) {
		Optional<Categoria> cat = repository.findById(id);
		if(cat != null) {
			return cat.get().getProductos();
		}
		return null;
	}

}
