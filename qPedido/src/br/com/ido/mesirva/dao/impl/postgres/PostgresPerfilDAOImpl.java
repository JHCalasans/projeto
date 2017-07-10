package br.com.ido.mesirva.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.IPerfilDAO;
import br.com.ido.mesirva.entity.qpedido.Perfil;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresPerfilDAOImpl extends GenericDAOImpl<Perfil, Integer>
implements IPerfilDAO{

}
