package br.com.ido.mesirva.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.mesirva.entity.qpedido.TipoPagamento;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public interface ITipoPagamentoDAO extends GenericDAO<TipoPagamento, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

	public List<TipoPagamento> obterTiposPagamentoAtivos(EntityManager em) throws ExcecaoBanco;

}
