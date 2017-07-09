package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IValorParametroDAO;
import br.com.ido.qpedido.entity.qpedido.ValorParametro;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.impl.GenericDAOImpl;


@PersistenceContext(unitName = "postgresPU")
class PostgresValorParametroDAOImpl extends
		GenericDAOImpl<ValorParametro, Integer> implements
		IValorParametroDAO {

	PostgresValorParametroDAOImpl() {
		super();
		setOrdenacaoPadrao(new CriterioOrdenacao[] { BY_VLPARAMETRO_ASC });
		
	}

	
}
