package br.com.ido.qpedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.GenericDAO;
import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;

public interface IMesaEnderecoEmpresaDAO  extends GenericDAO<MesaEnderecoEmpresa, Long>{

	static CriterioOrdenacao BY_NUM_ASC = CriterioOrdenacao.asc("numero");
	
	public List<MesaEnderecoEmpresa> obterPorEnderecoEmpresa(Integer codEnderecoEmpresa ,EntityManager em) throws ExcecaoBanco;
}
