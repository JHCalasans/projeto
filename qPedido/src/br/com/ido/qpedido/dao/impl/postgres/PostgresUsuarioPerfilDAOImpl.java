package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IUsuarioPerfilDAO;
import br.com.ido.qpedido.entity.qpedido.UsuarioPerfil;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresUsuarioPerfilDAOImpl extends GenericDAOImpl<UsuarioPerfil, Integer>
implements IUsuarioPerfilDAO{

}
