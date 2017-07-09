package br.com.ido.qpedido.bo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.ido.qpedido.dao.IUsuarioDAO;
import br.com.ido.qpedido.entity.qpedido.Usuario;
import br.com.ido.qpedido.util.FuncoesUtil;
import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;


public class UsuarioBO extends MeSirvaBO {

	private static UsuarioBO instance;

	private UsuarioBO() {

	}

	public static UsuarioBO getInstance() {
		if (instance == null)
			instance = new UsuarioBO();

		return instance;
	}
	
	public Usuario obterPorCodigo(Integer codUsuario) throws ExcecaoNegocio{
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			if(!transaction.isActive())
				transaction.begin();
			
			IUsuarioDAO usuarioDAO = fabricaDAO.getPostgresUsuarioDAO();
			Usuario result = usuarioDAO.findById(codUsuario, em);
			emUtil.commitTransaction(transaction);
			return result;

		}catch(Exception e){
			emUtil.rollbackTransaction(transaction);
			e.printStackTrace();
			throw new ExcecaoNegocio("Falha ao tentar obter Usuário.", e);
		}finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	
	
	
	public Usuario gravarUsuarioApp(Usuario usuario) throws ExcecaoNegocio{
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			if(!transaction.isActive())
				transaction.begin();
			
			IUsuarioDAO usuarioDAO = fabricaDAO.getPostgresUsuarioDAO();
			usuario.setAtivo(true);
			usuario.setDataCriacao(new Date());
			usuario.setSenha(FuncoesUtil.criptografarSenha(usuario.getSenha()));
			Usuario result = usuarioDAO.save(usuario, em);
			emUtil.commitTransaction(transaction);
			return result;

		}catch(Exception e){
			emUtil.rollbackTransaction(transaction);
			e.printStackTrace();
			throw new ExcecaoNegocio("Falha ao tentar gravar usuário.", e);
		}finally {
			emUtil.closeEntityManager(em);
		}
	}

}
