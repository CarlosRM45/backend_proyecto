package com.gustilandia.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOMesesDashboard;
import com.gustilandia.backend.service.DashboardService;
import com.gustilandia.backend.service.Response;

@CrossOrigin()
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService service;
	
	@PostMapping(path = "/general/productos" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardProductosVC(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardProductoVC(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/general/vcpormes" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardVentasComprasPorMes(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardVCMes(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/general/totales" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardTotales(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardTotales(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	
	@PostMapping(path = "/venta/cliente" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardVentasCliente(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardVentaCliente(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/venta/producto" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardVentasProducto(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardVentaProducto(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/venta/mes" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardVentasMes(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardVentaMes(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/venta/categoria" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardVentasCategoria(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardVentaCategoria(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/venta/total" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardVentasTotal(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardVentaTotal(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	
	@PostMapping(path = "/compra/proveedor" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardComprasProveedor(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardCompraProveedor(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/compra/producto" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardComprasProducto(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardCompraProducto(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/compra/mes" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardComprasMes(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardCompraMes(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/compra/categoria" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardComprasCategoria(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardCompraCategoria(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/compra/total" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> dashboardComprasTotal(@RequestBody @Valid DTOMesesDashboard dtoMeses){
		
		return new ResponseEntity<Response>(service.DashboardCompraTotal(dtoMeses.getIdMeses()), HttpStatus.OK);
		
	}
}
