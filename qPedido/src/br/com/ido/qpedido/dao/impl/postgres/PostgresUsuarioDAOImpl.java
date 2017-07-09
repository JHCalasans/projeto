package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IUsuarioDAO;
import br.com.ido.qpedido.entity.qpedido.Usuario;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresUsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO {
	
	

}
