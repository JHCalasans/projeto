package br.com.ido.qpedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.qpedido.entity.qpedido.UsuarioEnderecoEmpresa;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public interface IUsuarioEnderecoEmpresaDAO extends GenericDAO<UsuarioEnderecoEmpresa, Integer> {

	static CriterioOrdenacao BY_DT_CRIACAO_ASC = CriterioOrdenacao.asc("dataCriacao");
	
	public List<UsuarioEnderecoEmpresa> obterPorUsuario(Integer codUsuario,EntityManager em) throws ExcecaoBanco;
}
