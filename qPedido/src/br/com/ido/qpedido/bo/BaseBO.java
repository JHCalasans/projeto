package br.com.ido.qpedido.bo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.ido.qpedido.dao.FabricaDAO;
import br.com.ido.qpedido.dao.IEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.Empresa;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;
import br.com.ido.qpedido.util.FacesUtil;

public class BaseBO {

	protected FabricaDAO fabricaDAO;
	protected EntityManagerUtil emUtil;
	private static Logger log = Logger.getLogger(BaseBO.class);

	public EntityManagerUtil getEmUtil() {
		return emUtil;
	}

	public void setEmUtil(EntityManagerUtil emUtil) {
		this.emUtil = emUtil;
	}

	public BaseBO() {
		super();
		fabricaDAO = FabricaDAO.getFabricaDAO();
		emUtil = new EntityManagerUtil();
	}

	protected EnderecoEmpresa getEnderecoEmpresa(EntityManager em) throws ExcecaoNegocio {
		try {
			IEnderecoEmpresaDAO enderecoEmpDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			return enderecoEmpDAO.findById(FacesUtil.getCodEnderecoEmpresa(), em);
		} catch (ExcecaoBanco e) {
			throw new ExcecaoNegocio("Falha ao tentar obter empresa.", e);
		}
	}

	protected EnderecoEmpresa getEnderecoEmpresa() throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transacao = em.getTransaction();
		try {
			transacao.begin();
			EnderecoEmpresa enderecoEmp = getEnderecoEmpresa(em);
			emUtil.commitTransaction(transacao);
			return enderecoEmp;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transacao);
			throw new ExcecaoNegocio("Falha ao tentar obter empresa.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	protected Empresa getEmpresa() throws ExcecaoNegocio {
		return getEnderecoEmpresa().getEmpresa();
	}
}