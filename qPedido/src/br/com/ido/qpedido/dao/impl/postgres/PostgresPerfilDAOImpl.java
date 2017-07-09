package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IPerfilDAO;
import br.com.ido.qpedido.entity.qpedido.Perfil;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresPerfilDAOImpl extends GenericDAOImpl<Perfil, Integer>
implements IPerfilDAO{

}
