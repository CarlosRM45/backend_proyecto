package com.gustilandia.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.model.Venta;
import com.gustilandia.backend.model.VentaDetalle;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.VentaRepository;
import com.gustilandia.backend.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{
	
	@Autowired
	private VentaRepository ventarepo;
	
	@Autowired
	private ProductoRepository productorepo;

	@Transactional
	@Override
	public Venta registrar(Venta venta) {
		venta.setIdVenta(0L);
		
		double subtotal = 0.0;
		
		double dettotal = 0.0;
		
		for(VentaDetalle det:venta.getVentaDetalle()) {
			det.setVenta(venta);
			det.setProducto(productorepo.findById(det.getProducto().getIdProducto()).get());
			det.setPrecio(det.getProducto().getPrecio());
			dettotal = det.getPrecio() * det.getCantidad();
			subtotal += dettotal;
		}
		
		venta.setSubtotal(subtotal);
		venta.setIgv(subtotal * 0.18);
		venta.setTotal(venta.getSubtotal() + venta.getIgv());		
		
		venta = ventarepo.save(venta);
		
		return venta;
	}

	@Override
	public Venta actualizar(Venta venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Venta buscarId(Long id) {
		return ventarepo.findById(id).get();
	}

	@Override
	public List<Venta> listar() {
		return ventarepo.findAll();
	}

}
