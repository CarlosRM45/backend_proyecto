package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.repository.CategoriaRepository;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.UnidadMedidaRepository;
import com.gustilandia.backend.service.ProductoService;
import com.gustilandia.backend.service.Response;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository repository;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private UnidadMedidaRepository repoUniMed;
	
	@Override
	public Response registrar(Producto producto) {
		producto.setIdProducto(0L);
		producto.setCategoria(repoCategoria.getOne(producto.getCategoria().getIdCategoria()));
		producto.setUnidadMedida(repoUniMed.getOne(producto.getUnidadMedida().getIdUnidadMedida()));
		producto.setFechaCrea(new Date(System.currentTimeMillis()));
		producto.setFechaEdita(new Date(System.currentTimeMillis()));

		return new Response(true,repository.save(producto), "");
	}

	@Override
	public Response actualizar(Producto producto) {
		Response response = new Response();
		Optional<Producto> prod = repository.findById(producto.getIdProducto());
		if(prod != null) {
			producto.setFechaEdita(new Date(System.currentTimeMillis()));
			response.setResult(repository.save(producto));
			response.setSuccess(true);

			return response;
		}

		return response;
	}

	@Override
	public Response eliminar(Long id) {
		Optional<Producto> prod = repository.findById(id);
		if(prod != null) {
			repository.delete(prod.get());
			return new Response();
		}
		return new Response();
	}

	@Override
	public Response buscarId(Long id) {
		return new Response();
	}

	@Override
	public Response listar() {
		return new Response();
	}

}
