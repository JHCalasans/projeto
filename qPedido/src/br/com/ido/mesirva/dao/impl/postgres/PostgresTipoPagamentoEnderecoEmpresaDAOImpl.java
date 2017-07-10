package br.com.ido.mesirva.dao.impl.postgres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.ITipoPagamentoEnderecoEmpresaDAO;
import br.com.ido.mesirva.entity.qpedido.TipoPagamentoEnderecoEmpresa;
import br.com.ido.mesirva.util.FacesUtil;
import br.com.minhaLib.dao.impl.GenericDAOImpl;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

@PersistenceContext(unitName = "postgresPU")
public class PostgresTipoPagamentoEnderecoEmpresaDAOImpl extends GenericDAOImpl<TipoPagamentoEnderecoEmpresa, Integer>
		implements ITipoPagamentoEnderecoEmpresaDAO {

	@Override
	public List<TipoPagamentoEnderecoEmpresa> obterTiposPagamentoEmpresa(Integer codEnderecoEmpresa,EntityManager em) throws ExcecaoBanco {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codEnderecoEmpresa", codEnderecoEmpresa);
		return findByNamedQueryAndNamedParams("TipoPagamentoEnderecoEmpresa.obterPorEnderecoEmpresa", params, em);
	}

}
