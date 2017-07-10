package br.com.ido.mesirva.dao.nativequery;

import java.util.Map;

public interface Filtro {

	public Map<String, String> getSQLNames();
	
	public Class<?> getReturnType();
	
	public String getOrderByCriteria();
	
	public Pagination getPagination();
	
}
