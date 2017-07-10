package br.com.ido.mesirva.dao;


import br.com.ido.mesirva.entity.qpedido.Usuario;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;

public interface IUsuarioDAO extends GenericDAO<Usuario, Integer> {

	static CriterioOrdenacao BY_NOME_ASC = CriterioOrdenacao.asc("nome");
}
