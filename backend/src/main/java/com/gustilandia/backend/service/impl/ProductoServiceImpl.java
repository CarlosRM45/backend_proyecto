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

import com.gustilandia.backend.dto.DTOProducto;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.service.ProductoService;
import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.modelexample.ModelProducto;
import com.gustilandia.backend.repository.UnidadMedidaRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Response registrar(DTOProducto productoDto) {
		
		Response response = new Response();
		Producto producto = new Producto();
		
		try {

			producto = repository.save(mappingProducto(productoDto));
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
		Producto producto = mappingProducto(productoDto);

		try {

			Optional<Producto> prod = repository.findById(productoDto.getIdProducto());
			Producto _producto = prod.get();

			_producto.setDescripcion(producto.getDescripcion());
			_producto.setProducto(producto.getProducto());
			_producto.setImagen(producto.getImagen());
			_producto.setPrecio(producto.getPrecio());
			_producto.setCategoria(producto.getCategoria());
			_producto.setMarca(producto.getMarca());
			_producto.setStock(producto.getStock());
			_producto.setUnidadMedida(producto.getUnidadMedida());
			_producto.setUsuarioEdita(producto.getUsuarioEdita());
			_producto.setFechaEdita(producto.getFechaEdita());

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
			
			repository.deleteProducto(id);
			response.setSuccess(true);
			response.setMessage("El producto fue eliminado exitosamente.");

		} catch (Exception e) {
			response.setMessage("Hubo un error al eliminar el producto: " + e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response buscarId(Long id) {

		Optional<Producto> producto = repository.findById(id);
		
		if(!producto.isPresent())
			return new Response(false, null, "El Producto con el id: " + id + " no existe.");
		
		if(producto.get().getEstado().getIdEstado() != 1)
			return new Response(false, null, "El Producto con el id: " + id + " no existe.");

		return new Response(true, producto.get(), "");
	}

	@Override
	public Response listar() {
		
		List<Producto> listadoProductos = repository.findAll().stream()
				.filter(producto -> producto.getEstado().getIdEstado() == 1)
				.collect(Collectors.toList());

		return new Response(true, listadoProductos , "");
	}

	@Override
	public Response buscarPorNombre(String nombre) {
		
		List<Producto> listadoProductos = repository.buscarPorNombre(nombre).stream()
				.filter(producto -> producto.getEstado().getIdEstado() == 1)
				.collect(Collectors.toList());
		
		if(listadoProductos.isEmpty())
			return new Response(false, listadoProductos, "No existen productos");

		return new Response(true, listadoProductos , "");
	}

	@Override
	public Response validarStock(Long id, int cantidad) {
		
		Optional<Producto> producto = repository.findById(id);
		
		if(!producto.isPresent())
			return new Response(false, null, "El Producto con el id: " + id + " no existe.");
		
		if(producto.get().getEstado().getIdEstado() != 1)
			return new Response(false, null, "El Producto con el id: " + id + " no existe.");
		
		Producto _producto = producto.get();
		
		if(_producto.getStock()<cantidad)
			return new Response(false, null, "No hay stock suficiente del producto");
		
		return new Response(true, null, "");
	}

	@Override
	public List<ModelProducto> listarProductosMvl() {		

		List<ModelProducto> producto = new ArrayList<ModelProducto>();
		List<Object[]> lista =  repository.listarProductosMvl();
		for(Object[] obj : lista) {
			
			ModelProducto p = new ModelProducto();
			p.setIdProducto(Long.valueOf(obj[0].toString()));
			p.setNameProduct(obj[1].toString());
			p.setDescripcion(obj[2].toString());
			p.setPrecio(Double.parseDouble(obj[3].toString()));
			p.setImagen(obj[4].toString());
			p.setStock(Integer.parseInt(obj[5].toString()));
			p.setIdUnidadMedida(Long.valueOf(obj[6].toString()));
			p.setIdMarca(Long.valueOf(obj[7].toString()));
			p.setUnidadMedida(obj[8].toString());
			p.setNameMarca(obj[9].toString());
			
			producto.add(p);
		}

		return producto;
	}

	@Override
	public List<ModelProducto> listarProductsByCategory(Long id) {
		
		List<ModelProducto> producto = new ArrayList<ModelProducto>();
		List<Object[]> lista =  repository.listarProductsByCategory(id);
		for(Object[] obj : lista) {
			
			ModelProducto p = new ModelProducto();
			p.setIdProducto(Long.valueOf(obj[0].toString()));
			p.setNameProduct(obj[1].toString());
			p.setDescripcion(obj[2].toString());
			p.setPrecio(Double.parseDouble(obj[3].toString()));
			p.setImagen(obj[4].toString());
			p.setStock(Integer.parseInt(obj[5].toString()));
			p.setIdUnidadMedida(Long.valueOf(obj[6].toString()));
			p.setIdMarca(Long.valueOf(obj[7].toString()));
			p.setUnidadMedida(obj[8].toString());
			p.setNameMarca(obj[9].toString());
			
			producto.add(p);
		}

		return producto;
	}

	private Producto mappingProducto(DTOProducto productoDto){

		Producto producto = mapper.map(productoDto, Producto.class);
		Estado estado = new Estado();
		estado.setIdEstado(1L);
		producto.setEstado(estado);


		if(productoDto.getIdProducto() != 0){
			producto.setFechaEdita(new Date(System.currentTimeMillis()));
			Usuario usuarioedita = new Usuario();
			usuarioedita.setIdUsuario(1L);
			producto.setUsuarioEdita(usuarioedita);
		}
		else{
			producto.setFechaCrea(new Date(System.currentTimeMillis()));
		}
		
		return producto;
	}

}
