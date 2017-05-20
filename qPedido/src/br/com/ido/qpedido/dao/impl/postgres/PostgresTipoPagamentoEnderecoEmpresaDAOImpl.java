package br.com.ido.qpedido.dao.impl.postgres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.dao.ITipoPagamentoEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.TipoPagamentoEnderecoEmpresa;
import br.com.ido.qpedido.util.FacesUtil;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresTipoPagamentoEnderecoEmpresaDAOImpl extends GenericDAOImpl<TipoPagamentoEnderecoEmpresa, Integer>
		implements ITipoPagamentoEnderecoEmpresaDAO {

	@Override
	public List<TipoPagamentoEnderecoEmpresa> obterTiposPagamentoEmpresa(EntityManager em) throws ExcecaoBanco {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codEnderecoEmpresa", FacesUtil.getCodEnderecoEmpresa());
		return findByNamedQueryAndNamedParams("TipoPagamentoEnderecoEmpresa.obterPorEnderecoEmpresa", params, em);
	}

}
