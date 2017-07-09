package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IParametroDAO;
import br.com.ido.qpedido.entity.qpedido.Parametro;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.impl.GenericDAOImpl;




@PersistenceContext(unitName = "cachePU")
class PostgresParametroDAOImpl extends GenericDAOImpl<Parametro, Long> implements
		IParametroDAO {

	PostgresParametroDAOImpl() {
		super();
		setOrdenacaoPadrao(new CriterioOrdenacao[] { BY_DSCHAVE_ASC });
	}

	
}
