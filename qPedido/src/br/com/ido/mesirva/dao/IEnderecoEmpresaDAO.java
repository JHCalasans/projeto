package br.com.ido.mesirva.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.mesirva.entity.qpedido.EnderecoEmpresa;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public interface IEnderecoEmpresaDAO extends GenericDAO<EnderecoEmpresa, Integer> {

	static CriterioOrdenacao BY_CODIGO_ASC = CriterioOrdenacao.asc("codigo");

	public List<EnderecoEmpresa> obterEnderecosFiliais(EntityManager em) throws ExcecaoBanco;
	
	public List<EnderecoEmpresa> obterporSigla(String sigla,EntityManager em) throws ExcecaoBanco;

	public List<EnderecoEmpresa> obterporDistancia(double latitude, double longitude,EntityManager em) throws ExcecaoBanco;

}
