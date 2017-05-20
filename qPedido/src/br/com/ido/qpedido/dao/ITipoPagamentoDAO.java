package br.com.ido.qpedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.qpedido.entity.qpedido.TipoPagamento;

public interface ITipoPagamentoDAO extends GenericDAO<TipoPagamento, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

	public List<TipoPagamento> obterTiposPagamentoAtivos(EntityManager em) throws ExcecaoBanco;

}
