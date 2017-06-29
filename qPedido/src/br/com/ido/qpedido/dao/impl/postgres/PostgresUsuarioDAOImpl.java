package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.dao.IUsuarioDAO;
import br.com.ido.qpedido.entity.qpedido.Usuario;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresUsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO {
	
	

}
