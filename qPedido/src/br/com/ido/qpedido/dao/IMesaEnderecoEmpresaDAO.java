package br.com.ido.qpedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public interface IMesaEnderecoEmpresaDAO  extends GenericDAO<MesaEnderecoEmpresa, Long>{

	static CriterioOrdenacao BY_NUM_ASC = CriterioOrdenacao.asc("numero");
	
	public List<MesaEnderecoEmpresa> obterPorEnderecoEmpresa(Integer codEnderecoEmpresa ,EntityManager em) throws ExcecaoBanco;
	
	public MesaEnderecoEmpresa obterPorCodigo(Long codMesa ,EntityManager em) throws ExcecaoBanco;
}
