package br.com.ido.mesirva.dao.impl.postgres;


public class GenericDAOImplBOUtil {	
	
	public static PostgresGenericDAOImplBO getGenericDAOImplBO(){
		PostgresFabricaDAO fabrica = PostgresFabricaDAO.getInstance();
		return fabrica.getGenericDAOImplBO();
	}
}
