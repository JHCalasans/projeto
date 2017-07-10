package br.com.ido.mesirva.bo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import br.com.ido.mesirva.dao.FabricaDAO;
import br.com.ido.mesirva.dao.IEnderecoEmpresaDAO;
import br.com.ido.mesirva.entity.qpedido.Empresa;
import br.com.ido.mesirva.entity.qpedido.EnderecoEmpresa;
import br.com.ido.mesirva.util.FacesUtil;
import br.com.minhaLib.bo.BaseBO;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;
import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.minhaLib.util.EntityManagerUtil;

public class MeSirvaBO extends BaseBO{
	
	protected FabricaDAO fabricaDAO;
	protected EntityManagerUtil emUtil;
	private static Logger log = Logger.getLogger(BaseBO.class);

	public EntityManagerUtil getEmUtil() {
		return emUtil;
	}

	public void setEmUtil(EntityManagerUtil emUtil) {
		this.emUtil = emUtil;
	}

	public MeSirvaBO() {
		super();
		fabricaDAO = FabricaDAO.getFabricaDAO();
		emUtil = new EntityManagerUtil("postgresPU");
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
