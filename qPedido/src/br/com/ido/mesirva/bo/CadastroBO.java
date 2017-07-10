package br.com.ido.mesirva.bo;

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

public class CadastroBO extends MeSirvaBO {

	private static CadastroBO instance;

	private CadastroBO() {

	}

	public static CadastroBO getInstance() {
		if (instance == null)
			instance = new CadastroBO();

		return instance;
	}



	/*public void desativarTipoPagamento(Integer codigoTipoPagEndEmpresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoEnderecoEmpresaDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoEnderecoEmpresaDAO();
			TipoPagamentoEnderecoEmpresa tipoPag = tipoPagamentoDAO.findById(codigoTipoPagEndEmpresa, em);
			tipoPag.setDataDesativacao(new Date());
			tipoPag.setAtivo(false);			
			tipoPagamentoDAO.save(tipoPag, em);
			emUtil.commitTransaction(transaction);
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar desativar tipo de pagamento.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public void excluirTipoPagamento(Integer codigoTipoPagEndEmpresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoEnderecoEmpresaDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoEnderecoEmpresaDAO();
			TipoPagamentoEnderecoEmpresa tipoPag = tipoPagamentoDAO.findById(codigoTipoPagEndEmpresa, em);
			tipoPagamentoDAO.delete(tipoPag, em);
			emUtil.commitTransaction(transaction);
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar excluir tipo de pagamento.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}



	public TipoPagamentoEnderecoEmpresa obterTipoPagamentoEmpresa(Integer codigoTipoPagEndEmpresa)
			throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoEnderecoEmpresaDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoEnderecoEmpresaDAO();
			TipoPagamentoEnderecoEmpresa result = tipoPagamentoDAO.findById(codigoTipoPagEndEmpresa, em);
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter tipo de pagamento da empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

	public TipoPagamento obterTipoPagamento(Integer codigoTipo) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			ITipoPagamentoDAO tipoPagamentoDAO = fabricaDAO.getPostgresTipoPagamentoDAO();
			TipoPagamento result = tipoPagamentoDAO.findById(codigoTipo, em);
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter tipo de pagamento.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}*/


}