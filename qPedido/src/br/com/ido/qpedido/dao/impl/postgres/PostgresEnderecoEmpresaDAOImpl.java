package br.com.ido.qpedido.dao.impl.postgres;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.dao.IEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;
import br.com.ido.qpedido.util.FacesUtil;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresEnderecoEmpresaDAOImpl extends GenericDAOImpl<EnderecoEmpresa, Integer>
		implements IEnderecoEmpresaDAO {

	@Override
	public List<EnderecoEmpresa> obterEnderecosFiliais(EntityManager em) throws ExcecaoBanco {
		EnderecoEmpresa endEmpresa = findById(FacesUtil.getCodEnderecoEmpresa(), em);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codEmpresa", endEmpresa.getEmpresa().getCodigo());
		return findByNamedQueryAndNamedParams("EnderecoEmpresa.obterEnderecoFiliais",
				Collections.<String, Object>emptyMap(), em);
	}

	@Override
	public List<EnderecoEmpresa> obterporSigla(String sigla, EntityManager em) throws ExcecaoBanco {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sigla", sigla);
		return findByNamedQueryAndNamedParams("EnderecoEmpresa.obterPorEstado", params, em);

	}

}
