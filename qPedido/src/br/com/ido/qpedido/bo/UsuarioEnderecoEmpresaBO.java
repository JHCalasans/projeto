package br.com.ido.qpedido.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.ido.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.ido.qpedido.dao.IUsuarioDAO;
import br.com.ido.qpedido.dao.IUsuarioEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.Usuario;
import br.com.ido.qpedido.entity.qpedido.UsuarioEnderecoEmpresa;
import br.com.ido.qpedido.util.FuncoesUtil;

public class UsuarioEnderecoEmpresaBO extends BaseBO {

	private static UsuarioEnderecoEmpresaBO instance;

	private UsuarioEnderecoEmpresaBO() {

	}

	public static UsuarioEnderecoEmpresaBO getInstance() {
		if (instance == null)
			instance = new UsuarioEnderecoEmpresaBO();

		return instance;
	}

	
	
	public UsuarioEnderecoEmpresa obterUsuarioEnderecoEmpresaAtivoPorLoginESenha(String login, String senha) throws ExcecaoNegocio{
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		UsuarioEnderecoEmpresa result = null;
		try {
			if(!transaction.isActive())
				transaction.begin();
			
			IUsuarioDAO usuarioDAO = fabricaDAO.getPostgresUsuarioDAO();
			Usuario usuario = new Usuario();
			usuario.setAtivo(true);
			usuario.setSenha(FuncoesUtil.criptografarSenha(senha));
			usuario.setLogin(login);
			List<Usuario> lista = usuarioDAO.findByExample(usuario, em);
			if(lista != null && lista.size() > 0){
				IUsuarioEnderecoEmpresaDAO usuarioEnderecoEmpresaDAO = fabricaDAO.getPostgresUsuarioEnderecoEmpresaDAO();	
				List<UsuarioEnderecoEmpresa> lista2 = usuarioEnderecoEmpresaDAO.obterPorUsuario(lista.get(0).getCodigo(), em);
				if(lista2 != null && lista2.size() > 0)
					result = lista2.get(0);
			}
			emUtil.commitTransaction(transaction);
			return result;

		}catch(Exception e){
			emUtil.rollbackTransaction(transaction);
			e.printStackTrace();
			throw new ExcecaoNegocio("Falha ao tentar obter usuário com login - " + login, e);
		}finally {
			emUtil.closeEntityManager(em);
		}
	}
}
