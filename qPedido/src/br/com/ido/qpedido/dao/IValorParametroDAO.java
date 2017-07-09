package br.com.ido.qpedido.dao;

import br.com.ido.qpedido.entity.qpedido.ValorParametro;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;


public interface IValorParametroDAO extends GenericDAO<ValorParametro, Integer> {

	public static CriterioOrdenacao BY_VLPARAMETRO_ASC = CriterioOrdenacao
			.asc("valor");

	

}
