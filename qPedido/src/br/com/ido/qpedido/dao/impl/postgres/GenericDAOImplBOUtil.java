package br.com.ido.qpedido.dao.impl.postgres;


public class GenericDAOImplBOUtil {	
	
	public static PostgresGenericDAOImplBO getGenericDAOImplBO(){
		PostgresFabricaDAO fabrica = PostgresFabricaDAO.getInstance();
		return fabrica.getGenericDAOImplBO();
	}
}
