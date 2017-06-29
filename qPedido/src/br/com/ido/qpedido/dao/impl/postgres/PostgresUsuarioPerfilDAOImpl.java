package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.dao.IUsuarioPerfilDAO;
import br.com.ido.qpedido.entity.qpedido.UsuarioPerfil;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresUsuarioPerfilDAOImpl extends GenericDAOImpl<UsuarioPerfil, Integer>
implements IUsuarioPerfilDAO{

}
