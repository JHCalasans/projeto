package br.com.ido.qpedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;

public interface IEnderecoEmpresaDAO extends GenericDAO<EnderecoEmpresa, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

	public List<EnderecoEmpresa> obterEnderecosFiliais(EntityManager em) throws ExcecaoBanco;
	
	public List<EnderecoEmpresa> obterporSigla(String sigla,EntityManager em) throws ExcecaoBanco;

}
