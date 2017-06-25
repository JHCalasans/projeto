package br.com.ido.qpedido.bo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.ido.qpedido.dao.IEmpresaDAO;
import br.com.ido.qpedido.dao.IEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.Empresa;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;

public class EmpresaBO extends BaseBO {

	private static EmpresaBO instance;

	private EmpresaBO() {

	}

	public static EmpresaBO getInstance() {
		if (instance == null)
			instance = new EmpresaBO();

		return instance;
	}

	public Empresa salvarEmpresa(Empresa empresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IEmpresaDAO empresaDAO = fabricaDAO.getPostgresEmpresaDAO();
			if (empresa.getCodigo() == null) {
				empresa.setDataCriacao(new Date());
				empresa.setAtivo(true);
			}
			empresa = empresaDAO.save(empresa, em);
			emUtil.commitTransaction(transaction);
			return empresa;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar salvar dados da empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

	public EnderecoEmpresa salvarEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IEnderecoEmpresaDAO empresaDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			enderecoEmpresa.setEmpresa(getEmpresa());
			enderecoEmpresa = empresaDAO.save(enderecoEmpresa, em);
			emUtil.commitTransaction(transaction);
			return enderecoEmpresa;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar salvar dados do endereço empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

	public Empresa obterEmpresa() throws ExcecaoNegocio {
		return getEmpresa();
	}

	public List<EnderecoEmpresa> obterEnderecosFiliais() throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IEnderecoEmpresaDAO enderecoEmpresaDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			List<EnderecoEmpresa> result = enderecoEmpresaDAO.obterEnderecosFiliais(em);
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter endereço(s) da empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

	public EnderecoEmpresa obterEnderecoEmpresa(Integer codigoEnderecoEmpresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IEnderecoEmpresaDAO enderecoEmpresaDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			EnderecoEmpresa result = enderecoEmpresaDAO.findById(codigoEnderecoEmpresa, em);
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter endereço da empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public List<EnderecoEmpresa> obterEnderecoEmpresaPorSigla(String sigla) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IEnderecoEmpresaDAO enderecoEmpresaDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			List<EnderecoEmpresa> result = enderecoEmpresaDAO.obterporSigla(sigla, em);		
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter endereço da empresa pela sigla.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

	public void excluirFilialEmpresa(Integer codigoEnderecoEmpresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IEnderecoEmpresaDAO enderecoEmpresaDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			EnderecoEmpresa enderecoEmpresa = enderecoEmpresaDAO.findById(codigoEnderecoEmpresa, em);
			if (!enderecoEmpresa.isFilial()) {
				throw new ExcecaoNegocio("Código do endereço da empresa não pertençe a uma filial.");
			}
			enderecoEmpresaDAO.delete(enderecoEmpresa, em);
			emUtil.commitTransaction(transaction);
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar excluir filial.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

}