package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Producto;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto buscarId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listar() {
		return repository.findAll();
	}

}
