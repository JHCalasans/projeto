package br.com.ido.qpedido.dao;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.qpedido.entity.qpedido.Usuario;

public interface IUsuarioDAO extends GenericDAO<Usuario, Integer> {

	static CriterioOrdenacao BY_NOME_ASC = CriterioOrdenacao.asc("nome");
}
