package br.com.ido.mesirva.dao;

import br.com.ido.mesirva.entity.qpedido.Parametro;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public interface IParametroDAO extends GenericDAO<Parametro, Long> {

	public static CriterioOrdenacao BY_DSCHAVE_ASC = CriterioOrdenacao.asc("chave");



}
