package br.com.ido.mesirva.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.mesirva.dao.IEmpresaDAO;
import br.com.ido.mesirva.entity.qpedido.Empresa;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresEmpresaDAOImpl extends GenericDAOImpl<Empresa, Integer>
		implements IEmpresaDAO {

}
