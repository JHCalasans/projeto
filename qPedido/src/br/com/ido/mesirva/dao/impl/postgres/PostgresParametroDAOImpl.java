package br.com.ido.mesirva.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.IParametroDAO;
import br.com.ido.mesirva.entity.qpedido.Parametro;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.impl.GenericDAOImpl;


@PersistenceContext(unitName = "postgresPU")
class PostgresParametroDAOImpl extends GenericDAOImpl<Parametro, Long> implements
		IParametroDAO {

	PostgresParametroDAOImpl() {
		super();
		setOrdenacaoPadrao(new CriterioOrdenacao[] { BY_DSCHAVE_ASC });
	}

	
}
