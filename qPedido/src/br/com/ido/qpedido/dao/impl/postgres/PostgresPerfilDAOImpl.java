package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.dao.IPerfilDAO;
import br.com.ido.qpedido.entity.qpedido.Perfil;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresPerfilDAOImpl extends GenericDAOImpl<Perfil, Integer>
implements IPerfilDAO{

}
