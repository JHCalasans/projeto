package br.com.ido.qpedido.dao;


import br.com.ido.qpedido.entity.qpedido.UsuarioPerfil;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;

public interface IUsuarioPerfilDAO extends GenericDAO<UsuarioPerfil, Integer> {

	static CriterioOrdenacao BY_DT_INICIO_ASC = CriterioOrdenacao.asc("dataInicio");
	
}
