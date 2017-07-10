package br.com.ido.mesirva.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.IUsuarioDAO;
import br.com.ido.mesirva.entity.qpedido.Usuario;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresUsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO {
	
	

}
