package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTOCompra;
import com.gustilandia.backend.model.Compra;
import com.gustilandia.backend.model.CompraDetalle;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.TipoComprobanteSunat;
import com.gustilandia.backend.model.Usuario;
import com.gustilandia.backend.model.VentaDetalle;
import com.gustilandia.backend.repository.CompraRepository;
import com.gustilandia.backend.repository.EmpleadoRepository;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.security.TokenClientInterceptor;
import com.gustilandia.backend.service.CompraService;
import com.gustilandia.backend.service.Response;

@Service
public class CompraServiceImpl implements CompraService{
	
	@Autowired
	private CompraRepository repocompra;
	
	@Autowired
	private ProductoRepository productorepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private EmpleadoRepository repoEmpleado;

	@Transactional
	@Override
	public Response registrar(DTOCompra dtoCompra) {
		
		Response response = new Response();
		Compra compra = mappingDtoCompra(dtoCompra);
		
		try {
			
			double subtotal = 0.0;
			double dettotal = 0.0;
			
			for (CompraDetalle detalle : compra.getCompraDetalle()) {
				Producto producto = productorepo.findById(detalle.getProducto().getIdProducto()).get();
				detalle.setCompra(compra);
				detalle.setProducto(producto);
				dettotal = detalle.getPrecioCompra() * detalle.getCantidad();
				subtotal += dettotal;
			}
			
			compra.setSubtotal(subtotal);
			compra.setImpuesto(subtotal * 0.18);
			compra.setTotal(compra.getSubtotal() + compra.getImpuesto());
			
			compra = repocompra.save(compra);
			
			cambiarStock(compra.getCompraDetalle(), true);
			
			response.setSuccess(true);
			response.setMessage("La compra fue registrada exitosamente");
			response.setResult(compra);
			
			
		} catch (Exception e) {
			response.setMessage("Hubo un error al registrar la venta: " + e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response actualizar(DTOCompra t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response eliminar(Long id) {
		
		Response response = new Response();
		
		try {
			Optional<Compra> optCompra = repocompra.findById(id);
			if(!optCompra.isPresent()) {
				return new Response(false, null, "No existe compra con el id: " + id);
			}
			
			repocompra.anular(id);
			
			String token = TokenClientInterceptor.token;
			token = token.replace("Bearer ", "");
			String correo = jwtProvider.getUsuarioToken(token);
			Usuario usuario = repoEmpleado.findByCorreo(correo).get().getUsuario();
			
			Compra compra = repocompra.findById(id).get();
			compra.setFechaEdita(new Date(System.currentTimeMillis()));
			compra.setUsuarioEdita(usuario);
			
			repocompra.save(compra);
			
			response.setSuccess(true);
			response.setMessage("La compra fue anulada");
			
			cambiarStock(optCompra.get().getCompraDetalle(), false);
			
		} catch (Exception e) {
			response.setMessage("Hubo un error al anular la venta: " + e.getMessage());
		}
		return response;
	}

	@Override
	public Response buscarId(Long id) {
		Optional<Compra> optCompra = repocompra.findById(id);
		if(!optCompra.isPresent()) {
			return new Response(false, null, "No existe compra con el id: " + id);
		}
		return new Response(true, optCompra.get(),"");
	}

	@Override
	public Response listar() {

		return new Response(true, repocompra.findAll(), "");
	}
	
	public Compra mappingDtoCompra(DTOCompra dtoCompra) {
		
		Compra compra = mapper.map(dtoCompra, Compra.class);
		
		Estado estado = new Estado();
		estado.setIdEstado(1L);
		
		TipoComprobanteSunat tipoComprobanteSunat = new TipoComprobanteSunat();
		tipoComprobanteSunat.setIdTipoComprobanteSunat(dtoCompra.getIdTipoComprobanteSunat());
		
		compra.setEstado(estado);
		compra.setTipoComprobanteSunat(tipoComprobanteSunat);
		
		String token = TokenClientInterceptor.token;
		token = token.replace("Bearer ", "");
		String correo = jwtProvider.getUsuarioToken(token);
		Usuario usuario = repoEmpleado.findByCorreo(correo).get().getUsuario();
		
		if(compra.getIdCompra() != 0L){
			compra.setFechaEdita(new Date(System.currentTimeMillis()));
			compra.setUsuarioEdita(usuario);
		}
		else{
			compra.setFechaCrea(new Date(System.currentTimeMillis()));
			compra.setUsuarioCrea(usuario);
		}
		
		return compra;
	}
	
	public void cambiarStock(List<CompraDetalle> listado, boolean aumentar) {
		
		if(!aumentar) {
			for (CompraDetalle detalle : listado) {
				Producto prod = productorepo.findById(detalle.getProducto().getIdProducto()).get();
				prod.setStock(prod.getStock() - detalle.getCantidad());
				productorepo.save(prod);
			}
		}else {
			for (CompraDetalle detalle : listado) {
				Producto prod = productorepo.findById(detalle.getProducto().getIdProducto()).get();
				prod.setStock(prod.getStock() + detalle.getCantidad());
				productorepo.save(prod);
			}
		}
		
	}

}
