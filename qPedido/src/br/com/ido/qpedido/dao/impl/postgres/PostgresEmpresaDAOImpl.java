package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.dao.IEmpresaDAO;
import br.com.ido.qpedido.entity.qpedido.Empresa;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresEmpresaDAOImpl extends GenericDAOImpl<Empresa, Integer>
		implements IEmpresaDAO {

}
