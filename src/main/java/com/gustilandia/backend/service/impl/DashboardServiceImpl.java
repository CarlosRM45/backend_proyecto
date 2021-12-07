package com.gustilandia.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustilandia.backend.dto.DTODashboardCVMes;
import com.gustilandia.backend.dto.DTODashboardComprasCategoria;
import com.gustilandia.backend.dto.DTODashboardComprasMes;
import com.gustilandia.backend.dto.DTODashboardComprasProducto;
import com.gustilandia.backend.dto.DTODashboardComprasProveedor;
import com.gustilandia.backend.dto.DTODashboardComprasTotal;
import com.gustilandia.backend.dto.DTODashboardProductoVC;
import com.gustilandia.backend.dto.DTODashboardTotales;
import com.gustilandia.backend.dto.DTODashboardVentaProducto;
import com.gustilandia.backend.dto.DTODashboardVentasCategoria;
import com.gustilandia.backend.dto.DTODashboardVentasCliente;
import com.gustilandia.backend.dto.DTODashboardVentasMes;
import com.gustilandia.backend.dto.DTODashboardVentasTotal;
import com.gustilandia.backend.repository.CompraRepository;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.VentaRepository;
import com.gustilandia.backend.service.DashboardService;
import com.gustilandia.backend.service.Response;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	private ProductoRepository productorepo;
	
	@Autowired
	private VentaRepository ventarepo;
	
	@Autowired
	private CompraRepository comprarepo;
	
	
	@Override
	public Response DashboardProductoVC(String idMeses) {
		
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardProductoVC> lista = productorepo.dashboardProductoVC(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardVCMes(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardCVMes> lista = ventarepo.dashboardCVMes(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardTotales(String idMeses) {
		
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardTotales> lista = ventarepo.dashboardTotales(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardVentaCliente(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardVentasCliente> lista = ventarepo.dashboardVentasCliente(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardVentaProducto(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardVentaProducto> lista = ventarepo.dashboardVentasProducto(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardVentaMes(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardVentasMes> lista = ventarepo.dashboardVentasMes(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardVentaCategoria(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardVentasCategoria> lista = ventarepo.dashboardVentasCategoria(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardVentaTotal(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardVentasTotal> lista = ventarepo.dashboardVentasTotal(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardCompraProveedor(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardComprasProveedor> lista = comprarepo.dashboardComprasProveedor(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardCompraProducto(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardComprasProducto> lista = comprarepo.dashboardComprasProducto(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardCompraMes(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardComprasMes> lista = comprarepo.dashboardComprasMes(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardCompraCategoria(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardComprasCategoria> lista = comprarepo.dashboardComprasCategoria(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}


	@Override
	public Response DashboardCompraTotal(String idMeses) {
		Response response = new Response();
		if(idMeses.trim().isEmpty())
			idMeses = "1,2,3,4,5,6,7,8,9,10,11,12";
		
		try {
			List<DTODashboardComprasTotal> lista = comprarepo.dashboardComprasTotal(idMeses);
			response.setSuccess(true);
			response.setResult(lista);
			response.setMessage("");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error con meses del dashboard");
		}
		
		return response;
	}

	
	

}
