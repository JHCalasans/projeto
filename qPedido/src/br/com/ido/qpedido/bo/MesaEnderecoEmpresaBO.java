package br.com.ido.qpedido.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.ido.qpedido.dao.IEnderecoEmpresaDAO;
import br.com.ido.qpedido.dao.IMesaEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;
import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;

public class MesaEnderecoEmpresaBO extends MeSirvaBO {

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

	public MesaEnderecoEmpresa alterarMesa(MesaEnderecoEmpresa mesa, EnderecoEmpresa enderecoEmpresa)
			throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMesaEnderecoEmpresaDAO mesaDAO = fabricaDAO.getPostgresMesaEnderecoEmpresaDAO();
			MesaEnderecoEmpresa mesaTemp = new MesaEnderecoEmpresa();
			mesaTemp.setEnderecoEmpresa(enderecoEmpresa);
			mesaTemp.setNumero(mesa.getNumero());
			List<MesaEnderecoEmpresa> lista = mesaDAO.findByExample(mesaTemp, em);
			if (lista != null && lista.size() > 0) {
				emUtil.commitTransaction(transaction);
				throw new ExcecaoNegocio("Já existe mesa cadastrada com esse número.!");
			}
			mesa.setOcupada(false);
			mesa = mesaDAO.save(mesa, em);
			emUtil.commitTransaction(transaction);
			return mesa;
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar alterar mesa", e);
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
			MesaEnderecoEmpresa mesaTemp = new MesaEnderecoEmpresa();
			mesaTemp.setNumero(mesa.getNumero());
			List<MesaEnderecoEmpresa> lista = mesaDAO.findByExample(mesaTemp, em);
			if (lista != null && lista.size() > 0) {
				emUtil.commitTransaction(transaction);
				throw new ExcecaoNegocio("Já existe mesa cadastrada com esse número.!");
			}
			mesa.setCodigo(null);
			mesa.setOcupada(false);
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

	public void excluirMesa(MesaEnderecoEmpresa mesa) throws ExcecaoNegocio {
		if (mesa.isOcupada())
			throw new ExcecaoNegocio("Mesa ocupada no momento, não foi possível excluir!");

		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMesaEnderecoEmpresaDAO mesaDAO = fabricaDAO.getPostgresMesaEnderecoEmpresaDAO();
			mesaDAO.delete(mesa, em);
			emUtil.commitTransaction(transaction);
		} catch (ExcecaoBanco e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar excluir mesa", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

	public MesaEnderecoEmpresa obterPorCod(Long codMesa) throws ExcecaoNegocio {
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

	public MesaEnderecoEmpresa leituraQRCode(Long codMesa, Integer codUsuario) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMesaEnderecoEmpresaDAO mesaDAO = fabricaDAO.getPostgresMesaEnderecoEmpresaDAO();
			MesaEnderecoEmpresa mesa = mesaDAO.obterPorCodigo(codMesa, em);
			if (mesa.isOcupada()) {
				emUtil.rollbackTransaction(transaction);
				throw new ExcecaoNegocio("Já existe uma conta aberta para esta mesa no mmomento.!");
			}
			emUtil.commitTransaction(transaction);
			return mesa;
		}catch(ExcecaoNegocio e){
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio(e.getMessage());
		}catch (Exception e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar buscar mesa", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}

}
