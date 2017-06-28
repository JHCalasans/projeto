package br.com.ido.qpedido.dao;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.qpedido.entity.qpedido.UsuarioPerfil;

public interface IUsuarioPerfilDAO extends GenericDAO<UsuarioPerfil, Integer> {

	static CriterioOrdenacao BY_DT_INICIO_ASC = CriterioOrdenacao.asc("dataInicio");
	
}
