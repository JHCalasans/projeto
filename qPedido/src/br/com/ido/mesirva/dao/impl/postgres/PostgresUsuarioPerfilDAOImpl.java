package br.com.ido.mesirva.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.IUsuarioPerfilDAO;
import br.com.ido.mesirva.entity.qpedido.UsuarioPerfil;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresUsuarioPerfilDAOImpl extends GenericDAOImpl<UsuarioPerfil, Integer>
implements IUsuarioPerfilDAO{

}
