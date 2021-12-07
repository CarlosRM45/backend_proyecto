package com.gustilandia.backend.service;

public interface DashboardService {
	
	Response DashboardProductoVC(String idMeses);
	
	Response DashboardVCMes(String idMeses);
	
	Response DashboardTotales(String idMeses);
	
	Response DashboardVentaCliente(String idMeses);
	
	Response DashboardVentaProducto(String idMeses);
	
	Response DashboardVentaMes(String idMeses);
	
	Response DashboardVentaCategoria(String idMeses);
	
	Response DashboardVentaTotal(String idMeses);
	
	Response DashboardCompraProveedor(String idMeses);
	
	Response DashboardCompraProducto(String idMeses);
	
	Response DashboardCompraMes(String idMeses);
	
	Response DashboardCompraCategoria(String idMeses);
	
	Response DashboardCompraTotal(String idMeses);

}
