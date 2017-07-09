package br.com.ido.qpedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.qpedido.entity.qpedido.TipoPagamentoEnderecoEmpresa;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public interface ITipoPagamentoEnderecoEmpresaDAO extends GenericDAO<TipoPagamentoEnderecoEmpresa, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

	public List<TipoPagamentoEnderecoEmpresa> obterTiposPagamentoEmpresa(EntityManager em)
			throws ExcecaoBanco;

}
