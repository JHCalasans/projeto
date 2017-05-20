package br.com.ido.qpedido.dao;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.qpedido.entity.qpedido.Empresa;

public interface IEmpresaDAO extends GenericDAO<Empresa, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

}
