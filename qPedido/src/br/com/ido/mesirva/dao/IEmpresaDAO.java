package br.com.ido.mesirva.dao;

import br.com.ido.mesirva.entity.qpedido.Empresa;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;

public interface IEmpresaDAO extends GenericDAO<Empresa, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

}
