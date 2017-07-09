package br.com.ido.qpedido.dao;

import br.com.ido.qpedido.entity.qpedido.Empresa;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;

public interface IEmpresaDAO extends GenericDAO<Empresa, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

}
