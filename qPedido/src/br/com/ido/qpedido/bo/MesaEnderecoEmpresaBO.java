package br.com.ido.qpedido.bo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.ido.qpedido.dao.IEmpresaDAO;
import br.com.ido.qpedido.dao.IEnderecoEmpresaDAO;
import br.com.ido.qpedido.dao.IMesaEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.Empresa;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.ido.qpedido.util.FuncoesUtil;
import br.com.ido.qpedido.util.QRCodeUtil;

public class MesaEnderecoEmpresaBO extends BaseBO{

	
	private static MesaEnderecoEmpresaBO instance;

	private MesaEnderecoEmpresaBO() {

	}

	public static MesaEnderecoEmpresaBO getInstance() {
		if (instance == null)
			instance = new MesaEnderecoEmpresaBO();

		return instance;
	}
	
	public List<MesaEnderecoEmpresa> obterMesasPorEnderecoEmpresa(Integer codEnderecoEmpresa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		EnderecoEmpresa enderecoEmpresa = null;
		try {
			transaction.begin();
			IEnderecoEmpresaDAO enderecoEmpresaDAO = fabricaDAO.getPostgresEnderecoEmpresaDAO();
			enderecoEmpresa = enderecoEmpresaDAO.findById(codEnderecoEmpresa, em);
			
			IMesaEnderecoEmpresaDAO mesaEnderecoEmpresaDAO = fabricaDAO.getPostgresMesaEnderecoEmpresaDAO();
			List<MesaEnderecoEmpresa> result = mesaEnderecoEmpresaDAO.obterPorEnderecoEmpresa(codEnderecoEmpresa, em);
			emUtil.commitTransaction(transaction);
			return result;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter mesas da empresa: " + enderecoEmpresa.getDescricao(), e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public MesaEnderecoEmpresa salvarMesa(MesaEnderecoEmpresa mesa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMesaEnderecoEmpresaDAO mesaDAO = fabricaDAO.getPostgresMesaEnderecoEmpresaDAO();		
			mesa.setOcupada(false);
			//mesa.setQrcode(QRCodeUtil.gerarQRCode());
			mesa = mesaDAO.save(mesa, em);
			emUtil.commitTransaction(transaction);
			return mesa;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar registrar mesa", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public MesaEnderecoEmpresa obterPorCod(Integer codMesa) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMesaEnderecoEmpresaDAO mesaDAO = fabricaDAO.getPostgresMesaEnderecoEmpresaDAO();		
			MesaEnderecoEmpresa mesa = mesaDAO.findById(codMesa, em);
			emUtil.commitTransaction(transaction);
			return mesa;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar buscar mesa", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
}
