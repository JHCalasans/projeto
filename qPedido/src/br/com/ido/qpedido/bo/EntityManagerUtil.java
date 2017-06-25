package br.com.ido.qpedido.bo;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoConexao;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoEntidadeReferenciada;
import br.com.ido.impl.MyEntityManager;
import br.com.ido.qpedido.dao.impl.postgres.GenericDAOImplBOUtil;
import br.com.ido.qpedido.dao.impl.postgres.PostgresGenericDAOImplBO;

public class EntityManagerUtil {

	private static final HashMap<String, Object> propriedadesEntityManager = new HashMap<String, Object>();

	private static final HashMap<String, EntityManagerFactory> mapaEntityManagerFactory = new HashMap<String, EntityManagerFactory>();

	private EntityManagerFactory entityManagerFactory;

	private static ThreadLocal<MyEntityManager> threadEntityManager = new ThreadLocal<MyEntityManager>();

	private final String persistenceUnitName;
	
	private final String persistenceUnitNameFixo = "postgresqlPU";

	public static final String MSG_ERRO_GERAL = "Erro indeterminado na camada de persistência";

	public static final String MSG_ERRO_ARGUMENTOS = "Erro de argumentos";

	public static final String MSG_ERRO_PERSISTENCIA = "Erro de persistência";

	static {
		propriedadesEntityManager.put("javax.persistence.cache.retrieveMode",
				CacheRetrieveMode.BYPASS);
		propriedadesEntityManager.put("javax.persistence.cache.storeMode",
				CacheStoreMode.REFRESH);
		propriedadesEntityManager.put("hibernate.connection.isolation",
				Connection.TRANSACTION_READ_COMMITTED);
	}

	public EntityManagerUtil() {
		persistenceUnitName = readPersistenceUnitName();
	}

	public final synchronized EntityManager getEntityManager() {
		return createEntityManager();
	}

	private MyEntityManager createEntityManager() {
		if (this.entityManagerFactory == null) {
			entityManagerFactory = getEntityManagerFactory(persistenceUnitName);
		}

		MyEntityManager em3 = threadEntityManager.get();
		if (em3 == null || !em3.isOpen()) {
			EntityManager em2 = entityManagerFactory
					.createEntityManager(propriedadesEntityManager);
			PostgresGenericDAOImplBO genericDAO = GenericDAOImplBOUtil
					.getGenericDAOImplBO();
			em3 = new MyEntityManager(em2, genericDAO, 10 * 60 * 1000);
		}

		try {
			em3.getConnection().setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExcecaoBanco e) {
			e.printStackTrace();
		}		
		
		return em3;
	}

	private String readPersistenceUnitName() {
		PersistenceContext anotacaoPersistenceContext = getClass()
				.getAnnotation(PersistenceContext.class);

		if (anotacaoPersistenceContext != null) {
			return anotacaoPersistenceContext.unitName();
		}
		Field[] atributos = getClass().getDeclaredFields();
		for (Field atributo : atributos) {
			anotacaoPersistenceContext = atributo
					.getAnnotation(PersistenceContext.class);
			if (anotacaoPersistenceContext != null) {
				return anotacaoPersistenceContext.unitName();
			}
		}
		return null;
	}

	private static EntityManagerFactory getEntityManagerFactory(
			String persistenceUnitName) {
		EntityManagerFactory entityManagerFactory = mapaEntityManagerFactory
				.get(persistenceUnitName);
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(
					persistenceUnitName, propriedadesEntityManager);

			mapaEntityManagerFactory.put(persistenceUnitName,
					entityManagerFactory);
		}
		return entityManagerFactory;
	}

	public final synchronized void closeEntityManager(
			EntityManager aEntityManager) {
		try {
			aEntityManager.close();
		} catch (Exception e) {
			/*
			 * log.error(
			 * "Erro ao fechar EntityManager capturado; passando direto.", e);
			 */
		}
	}

	public final void rollbackTransaction(EntityTransaction transaction) {
		try {
			transaction.rollback();
		} catch (Exception e2) {
			// log.error("Erro de rollback capturado; passando direto.", e2);
		}
	}

	public final void commitTransaction(EntityTransaction transacao)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		try {
			transacao.commit();
		} catch (RollbackException rollbackException) {
			if (rollbackException.getCause() instanceof PersistenceException) {
				if (rollbackException.getCause().getCause() instanceof ConstraintViolationException) {
					throw new ExcecaoBancoEntidadeReferenciada("".getClass()
							.getName(), rollbackException.getCause().getCause()
							.getCause());
				}
				throw new ExcecaoBanco(MSG_ERRO_PERSISTENCIA, rollbackException
						.getCause().getCause());
			}
			throw new ExcecaoBanco(MSG_ERRO_GERAL, rollbackException.getCause());
		} catch (Exception e) {
			if (e instanceof PersistenceException) {
				if (e.getCause() instanceof JDBCConnectionException) {
					throw new ExcecaoBancoConexao(e.getCause().getCause());
				}
				throw new ExcecaoBanco(MSG_ERRO_PERSISTENCIA, e.getCause());
			} else {
				throw new ExcecaoBanco(MSG_ERRO_GERAL, e);
			}
		}
	}

}
