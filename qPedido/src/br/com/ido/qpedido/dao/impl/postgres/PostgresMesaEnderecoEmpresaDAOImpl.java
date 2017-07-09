package br.com.ido.qpedido.dao.impl.postgres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.dao.IMesaEnderecoEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.minhaLib.dao.impl.GenericDAOImpl;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresMesaEnderecoEmpresaDAOImpl extends GenericDAOImpl<MesaEnderecoEmpresa, Long>
implements IMesaEnderecoEmpresaDAO{

	@Override
	public List<MesaEnderecoEmpresa> obterPorEnderecoEmpresa(Integer codEnderecoEmpresa, EntityManager em)
			throws ExcecaoBanco {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codEnderecoEmpresa", codEnderecoEmpresa);
		return findByNamedQueryAndNamedParams("MesaEnderecoEmpresa.obterMesasPorEnderecoEmpresa", params, em);

	}

}
