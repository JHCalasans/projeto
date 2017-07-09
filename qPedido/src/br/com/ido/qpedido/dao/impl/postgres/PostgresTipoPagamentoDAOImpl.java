package br.com.ido.qpedido.dao.impl.postgres;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.ITipoPagamentoDAO;
import br.com.ido.qpedido.entity.qpedido.TipoPagamento;
import br.com.minhaLib.dao.impl.GenericDAOImpl;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresTipoPagamentoDAOImpl extends GenericDAOImpl<TipoPagamento, Integer> implements ITipoPagamentoDAO {

	@Override
	public List<TipoPagamento> obterTiposPagamentoAtivos(EntityManager em) throws ExcecaoBanco {
		return findByNamedQueryAndNamedParams("TipoPagamento.obterTiposPagamentoAtivos",
				Collections.<String, Object>emptyMap(), em);
	}

}
