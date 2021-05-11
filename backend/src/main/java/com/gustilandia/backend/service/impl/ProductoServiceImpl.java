package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.UnidadMedida;
import com.gustilandia.backend.repository.CategoriaRepository;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.UnidadMedidaRepository;
import com.gustilandia.backend.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository repository;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private UnidadMedidaRepository repoUniMed;
	
	@Override
	public Producto registrar(Producto producto) {
		producto.setIdProducto(0L);
		producto.setCategoria(repoCategoria.getOne(producto.getCategoria().getIdCategoria()));
		producto.setUnidadMedida(repoUniMed.getOne(producto.getUnidadMedida().getIdUnidadMedida()));
		producto.setFechaCrea(new Date(System.currentTimeMillis()));
		producto.setFechaEdita(new Date(System.currentTimeMillis()));
		return repository.save(producto);
	}

	@Override
	public Producto actualizar(Producto producto) {
		Optional<Producto> prod = repository.findById(producto.getIdProducto());
		if(prod != null) {
			producto.setFechaEdita(new Date(System.currentTimeMillis()));
			return repository.save(producto);
		}			
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Producto> prod = repository.findById(id);
		if(prod != null) {
			repository.delete(prod.get());
			return true;
		}
		return false;
	}

	@Override
	public Producto buscarId(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Producto> listar() {
		return repository.findAll();
	}

}
