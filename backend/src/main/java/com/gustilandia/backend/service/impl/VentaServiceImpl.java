package com.gustilandia.backend.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTODetalleVenta;
import com.gustilandia.backend.dto.DTOTarjeta;
import com.gustilandia.backend.dto.DTOVentaCarrito;
import com.gustilandia.backend.dto.DTOVentaEstado;
import com.gustilandia.backend.dto.DTOVentas;
import com.gustilandia.backend.model.Cliente;
import com.gustilandia.backend.model.Estado;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.Tarjeta;
import com.gustilandia.backend.model.TipoComprobanteSunat;
import com.gustilandia.backend.model.Venta;
import com.gustilandia.backend.model.VentaDetalle;
import com.gustilandia.backend.repository.ClienteRepository;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.TipoComprobanteSunatRepository;
import com.gustilandia.backend.repository.VentaRepository;
import com.gustilandia.backend.security.JwtProvider;
import com.gustilandia.backend.security.TokenClientInterceptor;
import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

	@Autowired
	private VentaRepository ventarepo;

	@Autowired
	private ProductoRepository productorepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private ClienteRepository clienterepo;

	@Autowired
	private TipoComprobanteSunatRepository tipocomprobanterepo;

	@Transactional
	@Override
	public Response registrar(DTOVentas dtoVenta) {

		Response response = new Response();
		Venta venta = mappingDtoVenta(dtoVenta);
		boolean stock = true;
		List<String> errorStock = new ArrayList<String>();

		try {

			double subtotal = 0.0;

			double dettotal = 0.0;

			for (VentaDetalle det : venta.getVentaDetalle()) {
				Producto producto = productorepo.findById(det.getProducto().getIdProducto()).get();
				det.setVenta(venta);
				det.setProducto(producto);
				det.setPrecio(det.getProducto().getPrecio());
				dettotal = det.getPrecio() * det.getCantidad();
				subtotal += dettotal;
				if (det.getCantidad() > producto.getStock()) {
					stock = false;
					errorStock.add("No hay suficiente Stock de " + producto.getProducto());
				}
			}

			if (!stock) {
				return new Response(false, errorStock, "No hay suficiente stock de algunos productos");
			} else {
				cambiarStock(venta.getVentaDetalle(), false);
			}

			venta.setSubtotal(subtotal);
			venta.setIgv(subtotal * 0.18);
			venta.setTotal(venta.getSubtotal() + venta.getIgv());

			venta.setCorrelativoComprobante(
					ventarepo.correlativo(venta.getTipoComprobanteSunat().getIdTipoComprobanteSunat()) + 1);
			venta.setNumeroVenta(
					tipocomprobanterepo.findById(venta.getTipoComprobanteSunat().getIdTipoComprobanteSunat()).get()
							.getSerie() + " - " + String.format("%06d", venta.getCorrelativoComprobante()));

			venta = ventarepo.save(venta);

			response.setResult(venta);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage("Hubo un error al registrar la venta: " + e.getMessage());
		}

		return response;
	}

	@Override
	public Response actualizar(DTOVentas dtoVenta) {
		Response response = new Response();
		/*
		 * Venta venta = mappingDtoVenta(dtoVenta); try { Optional<Venta> ventaOpt =
		 * ventarepo.findById(venta.getIdVenta()); Venta _venta = ventaOpt.get();
		 * _venta.setNroVenta(venta.getNroVenta());
		 * //_venta.setIdTipoComprobanteSunat(venta.getIdTipoComprobanteSunat());
		 * _venta.setCorrelativoComprobante(venta.getCorrelativoComprobante());
		 * _venta.setCliente(venta.getCliente()); _venta.setTarjeta(venta.getTarjeta());
		 * _venta.setVentaDetalle(venta.getVentaDetalle());
		 * 
		 * response.setResult(ventarepo.save(_venta)); response.setSuccess(true);
		 * response.setMessage("Venta actualizada correctamente"); } catch (Exception e)
		 * { response.setMessage("Hubo un error al actualizar la venta: " +
		 * e.getMessage()); }
		 */
		return response;
	}

	@Override
	public Response eliminar(Long id) {

		Response response = new Response();

		try {
			Optional<Venta> ventaOpt = ventarepo.findById(id);

			if (ventaOpt != null) {
				Venta venta = ventaOpt.get();
				// venta.setIdEstado(2L);
				ventarepo.save(venta);

				response.setSuccess(true);
				response.setMessage("La venta ha sido eliminada");
			} else {
				response.setMessage("La venta no existe");
			}

		} catch (Exception e) {
			response.setMessage("Hubo un error al eliminar la venta: " + e.getMessage());
		}
		return response;
	}

	@Override
	public Response anular(Long id) {

		Response response = new Response();

		try {

			Optional<Venta> optVenta = ventarepo.findById(id);
			if (!optVenta.isPresent())
				return new Response(false, null, "No existe venta con el id: " + id);

			ventarepo.anular(id);
			response.setSuccess(true);
			response.setMessage("La venta fue anulada.");

			cambiarStock(optVenta.get().getVentaDetalle(), true);

		} catch (Exception e) {
			response.setMessage("Hubo un error al anular la venta: " + e.getMessage());
		}

		return response;
	}

	@Override
	public Response buscarId(Long id) {

		Venta venta = ventarepo.findById(id).get();
		return new Response(true, venta, "");
	}

	@Override
	public Response listar() {
		return new Response(true, ventarepo.findAll(), "");
	}

	@Override
	public Response listarVentasPorRol(Long idRol, Long idUsuario) {

		List<Venta> listadoVentas = ventarepo.findAll();

		if (idRol == 2) {
			listadoVentas = listadoVentas.stream().filter(venta -> venta.getEstado().getIdEstado() == 6)
					.collect(Collectors.toList());
		}

		if (idRol == 5) {
			listadoVentas = listadoVentas.stream().filter(
					venta -> venta.getEstado().getIdEstado() == 7 && venta.getRepartidor().getIdEmpleado() == idUsuario)
					.collect(Collectors.toList());
		}

		return new Response(true, listadoVentas, "");
	}

	@Override
	public Response insertVentaCarrito(DTOVentaCarrito dtoVentaCarrito) {

		Response response = new Response();

		try {

			Long idVenta = ventarepo.insertVentaCarrito(dtoVentaCarrito.getIdCliente(),
					dtoVentaCarrito.getIdProducto());

			response.setResult(idVenta);
			response.setSuccess(true);

		} catch (Exception e) {
			response.setMessage("Ocurrio un error al crear la venta del carrito: " + e.getMessage());
		}

		return response;
	}

	@Override
	public Response aumentarCantidadProducto(DTOVentaCarrito dtoVentaCarrito) {

		Response response = new Response();

		try {

			Long idVenta = ventarepo.aumentarCantidadProducto(dtoVentaCarrito.getIdCliente(),
					dtoVentaCarrito.getIdProducto(), dtoVentaCarrito.getCantidad());

			response.setResult(idVenta);
			response.setSuccess(true);

		} catch (Exception e) {
			response.setMessage("Ocurrio un error al aumentar la cantidad del carrito: " + e.getMessage());
		}

		return response;
	}

	@Override
	public Response cambiarEstadoVenta(DTOVentaEstado dtoVentaEstado) {

		Response response = new Response();

		try {

			ventarepo.cambiarEstadoVenta(dtoVentaEstado.getIdVenta(), dtoVentaEstado.getIdRepartidor(),
					dtoVentaEstado.getIdEstado());
			Venta venta = ventarepo.findById(dtoVentaEstado.getIdVenta()).get();
			response.setResult(venta);
			response.setMessage("Se ha actualizado el estado de la venta.");
			response.setSuccess(true);

		} catch (Exception e) {
			response.setMessage("Ocurrio un error al crear la venta del carrito: " + e.getMessage());
		}

		return response;
	}

	public Venta mappingDtoVenta(DTOVentas dtoVenta) {

		Venta venta = mapper.map(dtoVenta, Venta.class);

		Estado estado = new Estado();
		estado.setIdEstado(4L);

		TipoComprobanteSunat tipoComprobanteSunat = new TipoComprobanteSunat();
		tipoComprobanteSunat.setIdTipoComprobanteSunat(dtoVenta.getIdTipoComprobanteSunat());

		venta.setTipoComprobanteSunat(tipoComprobanteSunat);
		venta.setEstado(estado);
		venta.setFechaVentaGuardada(new Date(System.currentTimeMillis()));
		venta.setFechaCreacion(new Date(System.currentTimeMillis()));

		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 7);
		venta.setFechEntrega(c.getTime());

		String token = TokenClientInterceptor.token;
		token = token.replace("Bearer ", "");
		String correo = jwtProvider.getUsuarioToken(token);
		Cliente cliente = clienterepo.findByCorreo(correo).get();

		venta.setCliente(cliente);

		return venta;

	}

	public VentaDetalle mappginDtoDetalle(DTODetalleVenta dtoDetalle) {

		VentaDetalle detalle = mapper.map(dtoDetalle, VentaDetalle.class);
		return detalle;

	}

	public Tarjeta mappingDtoTarjeta(DTOTarjeta dtoTarjeta) {

		Tarjeta tarjeta = mapper.map(dtoTarjeta, Tarjeta.class);
		return tarjeta;

	}

	public void cambiarStock(List<VentaDetalle> listado, boolean aumentar) {

		if (!aumentar) {
			for (VentaDetalle detalle : listado) {
				Producto prod = productorepo.findById(detalle.getProducto().getIdProducto()).get();
				prod.setStock(prod.getStock() - detalle.getCantidad());
				productorepo.save(prod);
			}
		} else {
			for (VentaDetalle detalle : listado) {
				Producto prod = productorepo.findById(detalle.getProducto().getIdProducto()).get();
				prod.setStock(prod.getStock() + detalle.getCantidad());
				productorepo.save(prod);
			}
		}

	}

}
