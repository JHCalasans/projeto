package br.com.ido.qpedido.dao.impl.postgres;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import br.com.ido.dao.CriterioOrdenacao;
import br.com.ido.dao.Entidade;
import br.com.ido.dao.GenericDAO;
import br.com.ido.dao.PageList;
import br.com.ido.dao.Transacao;
import br.com.ido.excecao.excecaobanco.ExcecaoBanco;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoConexao;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoEntidadeNaoEncontrada;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoEntidadeReferenciada;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoValorColuna;
import br.com.ido.excecao.excecaobanco.ExcecaoBancoViolacaoIntegridade;
import br.com.ido.impl.GenericDAOImpl;
import br.com.ido.impl.MyEntityManager;

public class GenericDAOImplNoOperation<E extends Entidade, ID extends Serializable>
		implements GenericDAO<E, ID> {

	public static final String MSG_ERRO_GERAL = GenericDAOImpl.MSG_ERRO_GERAL;
	private final String MSG_OPERACAO_NAO_SUPORTADA;
	private final GenericDAOImpl<E, ID> gDao;

	private final Class<E> persistentClass;
	private final Class<ID> keyClass;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAOImplNoOperation() {
		MSG_OPERACAO_NAO_SUPORTADA = "Operação não suportada para a entidade "
				+ getEntityClass();
		Class classe = getClass();
		Class entidade = null;
		Class identificador = null;
		boolean testarPai = true;
		while (testarPai) {
			try {
				entidade = ((Class) ((java.lang.reflect.ParameterizedType) classe
						.getGenericSuperclass()).getActualTypeArguments()[0]);
				identificador = ((Class) ((java.lang.reflect.ParameterizedType) classe
						.getGenericSuperclass()).getActualTypeArguments()[1]);
				testarPai = false;
			} catch (java.lang.ClassCastException cce) {
				classe = classe.getSuperclass();
			}
		}
		this.persistentClass = entidade;
		this.keyClass = identificador;
		gDao = new GenericDAOImpl<E, ID>(persistentClass, identificador) {
			@PersistenceContext(unitName = "cachePU")
			EntityManager foo;
		};
	}

	@Override
	public Class<E> getEntityClass() {
		return persistentClass;
	}

	@Override
	public Class<ID> getEntityIdClass() {
		return keyClass;
	}

	public EntityManager getEntityManager() {
		return gDao.getEntityManager();
	}

	public void closeEntityManager(EntityManager em) {
		gDao.closeEntityManager(em);
	}

	public void commitTransaction(EntityTransaction transacao)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		gDao.commitTransaction(transacao);
	}

	public void rollbackTransaction(EntityTransaction transacao) {
		gDao.rollbackTransaction(transacao);
	}

	public Connection getConnection(EntityManager em) throws ExcecaoBanco {
		return ((MyEntityManager) em).getConnection();
	}

	@Override
	@Deprecated	
	public E findById(ID id) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findAll(CriterioOrdenacao... criteriosOrdenacao)
			throws ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<E> findAll(int first, int max, boolean count,
			CriterioOrdenacao... criteriosOrdenacao)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByExample(E instanciaExemplo,
			CriterioOrdenacao... criteriosOrdenacao)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<E> findByExample(E instanciaExemplo, int first, int max,
			boolean count, CriterioOrdenacao... criteriosOrdenacao)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByNamedQuery(String nomeQuery,
			Object[] valoresDosParametrosNumerados) throws ExcecaoBancoConexao,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByNamedQuery(String nomeQuery,
			Object[] valoresDosParametrosNumerados, CriterioOrdenacao[] orders)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<E> findByNamedQuery(String nomeQuery,
			Object[] valoresDosParametrosNumerados, int first, int max,
			boolean count) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<E> findByNamedQuery(String name, Object[] params,
			int first, int max, boolean count, CriterioOrdenacao[] orders)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<?>[] findByNamedQuery(String name, Object[] params,
			Class<?>... classes) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<?>[] findByNamedQuery(String name, Object[] params,
			CriterioOrdenacao[] orders, Class<?>... classes)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<?>[] findByNamedQuery(String name, Object[] params,
			int first, int max, boolean count, CriterioOrdenacao[] orders,
			Class<?>... classes) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<?>[] findByNamedQuery(String name, Object[] params,
			int first, int max, boolean count, Class<?>... classes)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByNamedQueryAndNamedParams(String nomeQuery,
			Map<String, ? extends Object> nomesEValoresDosParametros)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	public PageList<E> findByNamedQueryAndNamedParams(String nomeQuery,
			Map<String, ? extends Object> nomesEValoresDosParametros,
			int first, int max, boolean count) throws ExcecaoBancoConexao,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<?>[] findByNamedQueryAndNamedParams(String name,
			Map<String, ? extends Object> params, Class<?>... classes)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<?>[] findByNamedQueryAndNamedParams(String name,
			Map<String, ? extends Object> params, int first, int max,
			boolean count, Class<?>... classes) throws ExcecaoBancoConexao,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByNamedQueryAndNamedParams(String nomeQuery,
			Map<String, ? extends Object> nomesEValoresDosParametros,
			CriterioOrdenacao[] orders) throws ExcecaoBancoConexao,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<E> findByNamedQueryAndNamedParams(String nomeQuery,
			Map<String, ? extends Object> nomesEValoresDosParametros,
			int first, int max, boolean count, CriterioOrdenacao[] orders)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<?>[] findByNamedQueryAndNamedParams(String name,
			Map<String, ? extends Object> params, CriterioOrdenacao[] orders,
			Class<?>... classes) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public E saveAtomico(E entidade) throws ExcecaoBancoValorColuna,
			ExcecaoBancoConexao, ExcecaoBancoViolacaoIntegridade, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> saveListaAtomico(List<E> listaEntidades)
			throws ExcecaoBancoValorColuna, ExcecaoBancoConexao,
			ExcecaoBancoViolacaoIntegridade, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public void deleteAtomico(E entidade) throws ExcecaoBancoConexao,
			ExcecaoBancoEntidadeReferenciada, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public void deleteListaAtomico(List<E> listaEntidades)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> saveAndDeleteListaAtomico(List<E> listaEntidadesSave,
			List<E> listaEntidadesDelete, boolean deleteFirst)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public E refresh(E entidade) throws ExcecaoBancoEntidadeNaoEncontrada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public E save(E entidade, Transacao t) throws ExcecaoBancoValorColuna,
			ExcecaoBancoConexao, ExcecaoBancoViolacaoIntegridade, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public Transacao getTransacao() throws ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> saveLista(List<E> listaEntidades, Transacao transacao)
			throws ExcecaoBancoValorColuna, ExcecaoBancoViolacaoIntegridade,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public void delete(E entidade, Transacao transacao)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public void deleteLista(List<E> listaEntidades, Transacao transacao)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> saveAndDeleteLista(List<E> listaEntidadesSave,
			List<E> listaEntidadesDelete, boolean deleteFirst,
			Transacao transacao) throws ExcecaoBancoConexao,
			ExcecaoBancoEntidadeReferenciada, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findAll(Transacao transacao,
			CriterioOrdenacao... criteriosOrdenacao) throws ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public void delete(E arg0, EntityManager arg1) throws ExcecaoBancoConexao,
			ExcecaoBancoEntidadeReferenciada, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);		
	}

	@Override
	@Deprecated
	public int deleteByExample(E arg0) throws ExcecaoBancoConexao,
			ExcecaoBancoEntidadeReferenciada, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public int deleteByExample(E arg0, EntityManager arg1)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public void deleteLista(List<E> arg0, EntityManager arg1)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);		
	}

	@Override
	@Deprecated
	public int executeQueryByHql(String arg0, Map<String, ? extends Object> arg1)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public int executeQueryByHql(String arg0,
			Map<String, ? extends Object> arg1, EntityManager arg2)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public int executeQueryByNamedQuery(String arg0,
			Map<String, ? extends Object> arg1) throws ExcecaoBancoConexao,
			ExcecaoBancoEntidadeReferenciada, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public int executeQueryByNamedQuery(String arg0,
			Map<String, ? extends Object> arg1, EntityManager arg2)
			throws ExcecaoBancoConexao, ExcecaoBancoEntidadeReferenciada,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByExample(E arg0, EntityManager arg1,
			CriterioOrdenacao... arg2) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public E findById(ID arg0, EntityManager arg1) throws ExcecaoBancoConexao,
			ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByNamedQueryAndNamedParams(String arg0,
			Map<String, ? extends Object> arg1, EntityManager arg2)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> findByNamedQueryAndNamedParams(String arg0,
			Map<String, ? extends Object> arg1, CriterioOrdenacao[] arg2,
			EntityManager arg3) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<E> findByNamedQueryAndNamedParams(String arg0,
			Map<String, ? extends Object> arg1, int arg2, int arg3,
			boolean arg4, CriterioOrdenacao[] arg5, EntityManager arg6)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public PageList<?>[] findByNamedQueryAndNamedParams(String arg0,
			Map<String, ? extends Object> arg1, int arg2, int arg3,
			boolean arg4, CriterioOrdenacao[] arg5, EntityManager arg6,
			Class<?>... arg7) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public E save(E arg0, EntityManager arg1) throws ExcecaoBancoValorColuna,
			ExcecaoBancoConexao, ExcecaoBancoViolacaoIntegridade, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public List<E> saveLista(List<E> arg0, EntityManager arg1)
			throws ExcecaoBancoValorColuna, ExcecaoBancoConexao,
			ExcecaoBancoViolacaoIntegridade, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public int updateByExample(E arg0, E arg1) throws ExcecaoBancoConexao,
			ExcecaoBancoEntidadeReferenciada, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	@Deprecated
	public int updateByExample(E arg0, E arg1, EntityManager arg2)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	public List<E> findAll(EntityManager em, CriterioOrdenacao... criteriosOrdenacao)
			throws ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	public List<?>[] findByNamedQueryAndNamedParams(String name,
			Map<String, ? extends Object> params, EntityManager em, Class<?>... classes)
			throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}

	@Override
	public List<?>[] findByNamedQueryAndNamedParams(String name,
			Map<String, ? extends Object> params, CriterioOrdenacao[] orders, EntityManager em,
			Class<?>... classes) throws ExcecaoBancoConexao, ExcecaoBanco {
		throw new ExcecaoBanco(MSG_OPERACAO_NAO_SUPORTADA, null);
	}
}
