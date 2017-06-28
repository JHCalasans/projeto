package br.com.ido.qpedido.dao;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.qpedido.entity.qpedido.Perfil;

public interface IPerfilDAO extends  GenericDAO<Perfil, Integer>{

	static CriterioOrdenacao BY_DESC_ASC = CriterioOrdenacao.asc("descricao");
}
