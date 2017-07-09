package br.com.ido.qpedido.dao.impl.postgres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IUsuarioEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.UsuarioEnderecoEmpresa;
import br.com.minhaLib.dao.impl.GenericDAOImpl;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

@PersistenceContext(unitName = "postgresPU")
public class PostgresUsuarioEnderecoEmpresaDAOImpl extends GenericDAOImpl<UsuarioEnderecoEmpresa, Integer>
implements IUsuarioEnderecoEmpresaDAO{

	@Override
	public List<UsuarioEnderecoEmpresa> obterPorUsuario(Integer codUsuario, EntityManager em) throws ExcecaoBanco {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codUsuario", codUsuario);
		return findByNamedQueryAndNamedParams("UsuarioEnderecoEmpresa.obterPorUsuario", params, em);
	}

}
