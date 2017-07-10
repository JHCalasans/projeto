package br.com.ido.mesirva.dao;


import br.com.ido.mesirva.entity.qpedido.Perfil;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;

public interface IPerfilDAO extends  GenericDAO<Perfil, Integer>{

	static CriterioOrdenacao BY_DESC_ASC = CriterioOrdenacao.asc("descricao");
}
