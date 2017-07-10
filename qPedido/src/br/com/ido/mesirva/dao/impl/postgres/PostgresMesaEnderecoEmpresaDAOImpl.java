package br.com.ido.mesirva.dao.impl.postgres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.IMesaEnderecoEmpresaDAO;
import br.com.ido.mesirva.entity.qpedido.MesaEnderecoEmpresa;
import br.com.minhaLib.dao.impl.GenericDAOImpl;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

@PersistenceContext(unitName = "postgresPU")
public class PostgresMesaEnderecoEmpresaDAOImpl extends GenericDAOImpl<MesaEnderecoEmpresa, Long>
implements IMesaEnderecoEmpresaDAO{

	@Override
	public List<MesaEnderecoEmpresa> obterPorEnderecoEmpresa(Integer codEnderecoEmpresa, EntityManager em)
			throws ExcecaoBanco {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codEnderecoEmpresa", codEnderecoEmpresa);
		return findByNamedQueryAndNamedParams("MesaEnderecoEmpresa.obterMesasPorEnderecoEmpresa", params, em);

	}

	@Override
	public MesaEnderecoEmpresa obterPorCodigo(Long codMesa, EntityManager em) throws ExcecaoBanco {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codMesa", codMesa);
		MesaEnderecoEmpresa result = null;
		List<MesaEnderecoEmpresa> lista = findByNamedQueryAndNamedParams("MesaEnderecoEmpresa.obterMesasPorCod", params, em);
		if(lista != null && lista.size() > 0)
			result = lista.get(0);
		return result;
	}

}
