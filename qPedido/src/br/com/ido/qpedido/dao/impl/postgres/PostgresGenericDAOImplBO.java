package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.qpedido.entity.qpedido.GenericEntity;

@PersistenceContext(unitName = "postgresqlPU")
public class PostgresGenericDAOImplBO extends
		GenericDAOImpl<GenericEntity, Integer> {

}
