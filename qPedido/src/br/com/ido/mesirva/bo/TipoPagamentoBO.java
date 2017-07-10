package br.com.ido.mesirva.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.ido.mesirva.dao.ITipoPagamentoDAO;
import br.com.ido.mesirva.dao.ITipoPagamentoEnderecoEmpresaDAO;
import br.com.ido.mesirva.entity.qpedido.TipoPagamento;
import br.com.ido.mesirva.entity.qpedido.TipoPagamentoEnderecoEmpresa;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;
import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;

public class TipoPagamentoBO extends MeSirvaBO {

	private static TipoPagamentoBO instance;

	private TipoPagamentoBO() {

	}

	public static TipoPagamentoBO getInstance() {
		if (instance == null)
			instance = new TipoPagamentoBO();

		return instance;
	}
	
	public List<TipoPagamento> obterTiposPagamento() throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoDAO();
			List<TipoPagamento> result = tipoPagamentoDAO.obterTiposPagamentoAtivos(em);			
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter tipos de pagamento.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public List<TipoPagamentoEnderecoEmpresa> obterTiposPagamentoEmpresa(Integer codEnderecoEmpresa)
			throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoEnderecoEmpresaDAO tipoPagamentoEnderecoEmpresaDAO = fabricaDAO.getPostgresTipoPagamentoEnderecoEmpresaDAO();
			ITipoPagamentoDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoDAO();
			List<TipoPagamento> lista = tipoPagamentoDAO.obterTiposPagamentoAtivos(em);		
			List<TipoPagamentoEnderecoEmpresa> result = tipoPagamentoEnderecoEmpresaDAO.obterTiposPagamentoEmpresa(codEnderecoEmpresa,em);
			List<TipoPagamentoEnderecoEmpresa> retorno = new ArrayList<TipoPagamentoEnderecoEmpresa>();
			boolean achou ;
			TipoPagamentoEnderecoEmpresa nvTipoPagEndEmp;
			//Verifica quais tipos de pagamentos estão inclusos na empresa e ajusta lista para apresentar na tela
			for(TipoPagamento tipoPagamento : lista){
				achou = false;	
				for(TipoPagamentoEnderecoEmpresa tipoPagEndEmpresa : result){
					if(tipoPagEndEmpresa.getTipoPagamento().getCodigo() == tipoPagamento.getCodigo()){
						achou = true;
						retorno.add(tipoPagEndEmpresa);
						result.remove(tipoPagEndEmpresa);
						break;
					}
				}
				if(!achou){
					nvTipoPagEndEmp = new TipoPagamentoEnderecoEmpresa();
					nvTipoPagEndEmp.setTipoPagamento(tipoPagamento);
					nvTipoPagEndEmp.setAtivo(false);
					retorno.add(nvTipoPagEndEmp);
				}
			}
			emUtil.commitTransaction(transaction);
			return retorno;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter tipos de pagamento da empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public TipoPagamentoEnderecoEmpresa salvarTipoPagamentoEnderecoEmpresa(TipoPagamentoEnderecoEmpresa tipoPagamento)
			throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoEnderecoEmpresaDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoEnderecoEmpresaDAO();
			if (tipoPagamento.getCodigo() == null) {
				tipoPagamento.setDataCriacao(new Date());
				tipoPagamento.setAtivo(true);
			}else if(tipoPagamento.isAtivo())
				tipoPagamento.setDataDesativacao(null);
			else
				tipoPagamento.setDataDesativacao(new Date());
			
			tipoPagamento = tipoPagamentoDAO.save(tipoPagamento, em);
			emUtil.commitTransaction(transaction);
			return tipoPagamento;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar salvar tipo de pagamento.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

}
