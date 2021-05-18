package com.gustilandia.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCliente;
import com.gustilandia.backend.dto.DTOProducto;
import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Marca;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.UnidadMedida;
import com.gustilandia.backend.model.Usuario;
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
	private ModelMapper mapper;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private UnidadMedidaRepository repoUniMed;
	
	@Override
	public Response registrar(DTOProducto productoDto) {
		
		Response response = new Response();
		Producto producto = new Producto();
/*
		if(productoDto.getProducto() == null || productoDto.getProducto().trim().length() <= 0){
			response.setMessage("Ingrese el nombre del producto.");
			return response;
		}

		if(productoDto.getDescripcion() == null || productoDto.getDescripcion().trim().length() <= 0){
			response.setMessage("Ingrese una descripcion del producto.");
			return response;
		}

		if(productoDto.getPrecio() == null || productoDto.getPrecio() <= 0){
			response.setMessage("Ingrese un precio mayor a 0.");
			return response;
		}

		if(productoDto.getIdCategoria() == null || productoDto.getIdCategoria() == 0){
			response.setMessage("Seleccione una categoria.");
			return response;
		}

		if(productoDto.getIdMarca() == null || productoDto.getIdMarca() == 0){
			response.setMessage("Seleccione una marca.");
			return response;
		}

		if(productoDto.getIdUnidadMedida() == null || productoDto.getIdUnidadMedida() == 0){
			response.setMessage("Seleccione una unidad de medida.");
			return response;
		}
*/
		
		try {

			producto = repository.save(mappingProducto(productoDto, null));
			response.setResult(producto);
			response.setSuccess(true);
			
		} catch (Exception e) {
			response.setMessage("Hubo un error al guardar el producto: " + e.getMessage());
		}

		return response;
	}

	@Override
	public Response actualizar(DTOProducto productoDto) {

		Response response = new Response();

		try {

			Optional<Producto> prod = repository.findById(productoDto.getIdProducto());
			Producto _producto = prod.get();

			_producto = mappingProducto(productoDto, _producto);

			response.setResult(repository.save(_producto));
			response.setSuccess(true);

		} catch (Exception e) {
			response.setMessage("Hubo un error al actualizar el producto: "+ e.getMessage());
		}

		return response;
	}

	@Override
	public Response eliminar(Long id) {

		Response response = new Response();

		try {
			
			Optional<Producto> productoOp = repository.findById(id);
			Producto _producto = productoOp.get();

			_producto.getEstado().setIdEstado(2L);

			repository.save(_producto);

			response.setResult(true);
			response.setMessage("El producto fue eliminado exitosamente.");

		} catch (Exception e) {
			response.setMessage("Hubo un error al eliminar el producto: " + e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response buscarId(Long id) {

		Producto producto = repository.findById(id).get();

		if(producto.getEstado().getIdEstado() != 1)
			return new Response(false, null, "El Producto no existe.");

		return new Response(true, producto, "");
	}

	@Override
	public Response listar() {
		
		List<Producto> listadoProductos = repository.findAll().stream()
				.filter(producto -> producto.getEstado().getIdEstado() == 1)
				.collect(Collectors.toList());

		return new Response(true, listadoProductos , "");
	}

	
	private Producto mappingProducto(DTOProducto productoDto , @Nullable Producto productoUpdate){

		/*Producto producto = new Producto();

		if(productoUpdate != null)
			producto = productoUpdate;

		producto.setProducto(productoDto.getProducto());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setPrecio(productoDto.getPrecio());
		producto.setStock(productoDto.getStock());
		
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(productoDto.getIdCategoria());

		Marca marca = new Marca();
		marca.setIdMarca(productoDto.getIdMarca());

		UnidadMedida unidadMedida = new UnidadMedida();
		unidadMedida.setIdUnidadMedida(productoDto.getIdUnidadMedida());

		Usuario usuario = new Usuario();
		usuario.setIdUsuario(productoDto.getIdUsuarioCrea());

		Estado estado = new Estado();
		estado.setIdEstado(1L);*/
		
		Producto producto = mapper.map(productoDto, Producto.class);
		producto.getEstado().setIdEstado(1L);


		if(productoDto.getIdProducto() != 0){

			//usuario.setIdUsuario(productoDto.getIdUsuarioEdita());
			producto.setFechaEdita(new Date(System.currentTimeMillis()));
			//producto.setUsuarioEdita(usuario);
		}
		else{

			producto.setFechaCrea(new Date(System.currentTimeMillis()));
			//producto.setUsuarioCrea(usuario);
		}

		//producto.setCategoria(categoria);
		//producto.setMarca(marca);
		//producto.setUnidadMedida(unidadMedida);
		//producto.setEstado(estado);

		return producto;
	}


}
