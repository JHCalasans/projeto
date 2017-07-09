package br.com.ido.qpedido.dao.impl.postgres;

import javax.persistence.PersistenceContext;

import br.com.ido.qpedido.entity.qpedido.GenericEntity;
import br.com.minhaLib.dao.impl.GenericDAOImpl;

@PersistenceContext(unitName = "postgresPU")
public class PostgresGenericDAOImplBO extends
		GenericDAOImpl<GenericEntity, Integer> {

}
