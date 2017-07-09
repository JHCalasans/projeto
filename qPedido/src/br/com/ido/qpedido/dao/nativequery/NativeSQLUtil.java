package br.com.ido.qpedido.dao.nativequery;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.com.ido.qpedido.dao.nativequery.OperatorCondition.OperatorEnum;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public class NativeSQLUtil {

	private EntityManager em;
	private Filtro filtro;
	private CondicaoConsulta condicao;
	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public NativeSQLUtil(EntityManager em) {
		super();
		this.em = em;
	}

	public NativeSQLUtil(EntityManager em, Filtro filtro) {
		super();
		this.em = em;
		this.filtro = filtro;
		condicao = null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> executeQuery(String sql, Map<String, Object> params,
			Class<T> returnType) throws ExcecaoBanco {
		try {
			Session session = em.unwrap(Session.class);
			SQLQuery query = session.createSQLQuery(sql);
			boolean wrapperType = isWrapperType(returnType);			
			query.setResultTransformer(wrapperType ? Criteria.ALIAS_TO_ENTITY_MAP : new AliasToBeanResultTransformer(returnType));
			
			if (params != null) {
				List<String> keys = new ArrayList<String>(params.keySet());
				for (String paramName : keys) {
					Object value = params.get(paramName);
					if (value instanceof List) {
						List<Object> list = (ArrayList<Object>) value;
						query.setParameterList(paramName, list);
					} else
						query.setParameter(paramName, value);
				}
			}
			setQueryPagination(query);
			List<T> result = null;
			if (wrapperType) {
				List list = query.list();
				result = new ArrayList<T>();				
				result.addAll(list);
			} else {
				result = query.list();
			}
			return result;
		} catch (Throwable t) {
			throw new ExcecaoBanco("Falha ao tentar executar query nativa", t);
		}
	}

	private static boolean isWrapperType(Class<?> clazz) {
		return WRAPPER_TYPES.contains(clazz);
	}

	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(BigInteger.class);
		ret.add(BigDecimal.class);
		return ret;
	}
	
	private void setQueryPagination(SQLQuery query) {
		if (this.filtro != null && this.filtro.getPagination() != null) {
			query.setFirstResult(this.filtro.getPagination().getFirstResult());
			query.setMaxResults(this.filtro.getPagination().getMaxResults());
		}
	}
	
	public int executeUpdate(String sql, Map<String, Object> params)
			throws ExcecaoBanco {
		try {
			Session session = em.unwrap(Session.class);
			SQLQuery query = session.createSQLQuery(sql);
			if (params != null) {
				List<String> keys = new ArrayList<String>(params.keySet());
				for (String paramName : keys) {
					Object value = params.get(paramName);
					query.setParameter(paramName, value);					
				}
			}
			return query.executeUpdate();
		} catch (Throwable t) {
			throw new ExcecaoBanco("Falha ao tentar executar query nativa", t);
		}
	}

	public <T> List<T> executeQuery(String sql, Class<T> returnType)
			throws ExcecaoBanco {
		return executeQuery(sql, null, returnType);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> executeQuery(String sql) throws ExcecaoBanco {
		sql = completeQuery(sql);
		Map<String, Object> params = condicao != null ? condicao.parametros
				: null;
		return (List<T>) executeQuery(sql, params,
				filtro != null ? filtro.getReturnType() : null);
	}

	private String completeQuery(String sql) throws ExcecaoBanco {
		if (filtro != null) {
			Map<String, String> sqlNames = filtro.getSQLNames();
			if (sqlNames == null)
				sqlNames = new HashMap<String, String>();
			condicao = obterCondicao(sqlNames);
			if (condicao.getCondicoes() != null)
				sql += " " + condicao.getCondicoes().toString();
			if (filtro.getOrderByCriteria() != null)
				sql += " " + filtro.getOrderByCriteria();
		}
		return sql;
	}

	public String getMessageOperadorLike(String fieldName, Object object) {
		return "Falha ao tentar utilizar o operador like. Era esperado um objeto "
				+ "do tipo 'String', no campo '"
				+ fieldName
				+ "', contudo o objeto passado foi do tipo: "
				+ object.getClass();
	}
	
	private boolean isIgnoreValue(Field field, Object filtro) throws IllegalAccessException {
		String valueIgnore = null;
		if (field.isAnnotationPresent(Condition.class))
			valueIgnore = field.getAnnotation(Condition.class).valueIgnore();
		else if (field.isAnnotationPresent(Conditions.class))
			valueIgnore = field.getAnnotation(Conditions.class).valueIgnore();
		
		field.setAccessible(true);
		Object fieldValue = field.get(filtro);
		return (!valueIgnore.isEmpty() && valueIgnore.equals(fieldValue));
	}
	
	private Map<String, String> obterSqlNamesPorAnotacao()
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, String> result = new HashMap<String, String>();
		Field[] fields = filtro.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Condition.class)) {
				if (isIgnoreValue(field, filtro)) {
					continue;
				}
				String key = field.getName();
				String value = field.getAnnotation(Condition.class).sqlName();
				result.put(key, value);
			} else if (field.isAnnotationPresent(Conditions.class)) {// Mais de uma @Condition
				if (isIgnoreValue(field, filtro)) {
					continue;
				}
				Condition[] conditions = field.getAnnotation(Conditions.class).value();
				for (Condition condition : conditions) {
					String value = null;
					if (!condition.valueConditionSqlName().isEmpty()) {
						String valueCondition = condition.valueConditionSqlName();
						String fieldCondition = condition.fieldConditionSqlName();
						Object fieldValue = null;
						if(fieldCondition.isEmpty())
							fieldValue = field.get(filtro);
						else
							fieldValue = findFieldFilter(fieldCondition).get(filtro);
						
						if (valueCondition.equals(fieldValue))
							value = condition.sqlName();
					} else {
						value = condition.sqlName();
					}
					if (value != null) {
						String key = field.getName();
						result.put(key, value);
						break;
					}
				}
			}
		}
		return result;
	}

	public CondicaoConsulta obterCondicao(final Map<String, String> sqlNames) throws ExcecaoBanco {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder condicaoSQL = new StringBuilder();
		try {
			Map<String, String> sqlNamesAnnotation = obterSqlNamesPorAnotacao();
			sqlNames.putAll(sqlNamesAnnotation);
			Set<String> keySet = sqlNames.keySet();
			for (String key : keySet) {
				Object object = null;
				Field field = null;
				String name = sqlNames.get(key);				
				String primeiro = "";
				if (key.contains(".")) {
					LastField last = obterObjeto(filtro, key);
					primeiro = key.substring(0, key.indexOf("."));
					field = localizarCampo(filtro, primeiro);
					key = last.fieldName;
					object = last.object;
				} else {
					field = filtro.getClass().getDeclaredField(key);
					field.setAccessible(true);
					object = field.get(filtro);
				}
				montarCondicao(params, condicaoSQL, key, name, object, primeiro, field);
			}

		} catch (IllegalAccessException | NoSuchFieldException
				| SecurityException e) {
			throw new ExcecaoBanco(
					"Falha ao tentar acessar um atributo do filtro do relatório",
					e);
		}
		CondicaoConsulta condicao = new CondicaoConsulta();
		condicao.setParametros(params);
		condicao.setCondicoes(condicaoSQL);
		return condicao;
	}

	@SuppressWarnings("unchecked")
	private void montarCondicao(Map<String, Object> params,
			StringBuilder condicaoSQL, String fieldName, String name,
			Object object, String concat, Field field) throws ExcecaoBanco {
		if (object != null && name != null) {
			String key = concat + "_" + fieldName;
			boolean parentesis = name.startsWith("(") && name.endsWith(")");
			name = parentesis ? name.replace("(", "").replace(")", "") : name;
			if (object.getClass() != java.util.ArrayList.class) {
				OperatorEnum operator = getOperator(field);
				if (operator == OperatorEnum.LIKE) {
					if (!(object instanceof String))
						throw new ExcecaoBanco(getMessageOperadorLike(key,
								object), null);
					if (object == null || ((String) object).isEmpty())
						return;
					else
						params.put(key, "%" + object + "%");
				} else if (operator == OperatorEnum.CONDITION) {
					condicaoSQL.append(" and " + (parentesis ? "(" : ""))
							.append(name);
					return;
				} else
					params.put(key, object);

				condicaoSQL.append(" and " + (parentesis ? "(" : ""))
						.append(name).append(operator.getValue()).append(":")
						.append(key + (parentesis ? "(" : ""));
			} else {
				List<Object> list = (ArrayList<Object>) object;
				if (!list.isEmpty()) {
					params.put(key, list);
					condicaoSQL.append(" and " + (parentesis ? "(" : ""))
							.append(name).append(" in(").append(":")
							.append(key).append(")" + (parentesis ? ")" : ""));
				}
			}
		}
	}

	private LastField obterObjeto(Object field, String name)
			throws IllegalArgumentException, IllegalAccessException {
		if (!name.contains(".")) {
			Field f = localizarCampo(field, name);
			Object o = f.get(field);
			return new LastField(name, o);
		}
		int pos = name.indexOf(".");
		String nivel_1 = name.substring(0, pos);
		String nivel_2 = name.substring(pos + 1, name.length());
		Field f = localizarCampo(field, nivel_1);
		return obterObjeto(f.get(field), nivel_2);
	}

	private Field localizarCampo(Object object, String nome) {
		Field[] fields = object.getClass().getDeclaredFields();
		boolean achou = false;
		int i = 0;
		Field loc = null;
		while (!achou && i < fields.length) {
			Field f = fields[i++];
			if (f.getName().equals(nome)) {
				achou = true;
				f.setAccessible(true);
				loc = f;
			}
		}
		return loc;
	}
	
	private Field findFieldFilter(String name) {
		return localizarCampo(filtro, name);
	}
	
	private OperatorEnum getOperator(Field field) {
		//Obtendo operador através da anotação
		if (field.isAnnotationPresent(OperatorCondition.class)) {
			return field.getAnnotation(OperatorCondition.class).operator();
		}
		//Obtendo operador através do nome do campo
		String fieldName = field.getName();
		if (fieldName.contains("Inicio"))
			return OperatorEnum.GREATER_EQUAL;
		else if (fieldName.contains("Fim"))
			return OperatorEnum.LESS_EQUAL;
		else if (fieldName.contains("Like"))
			return OperatorEnum.LIKE;
		else if (fieldName.contains("Condition"))
			return OperatorEnum.CONDITION;
		else
			return OperatorEnum.EQUAL;
	}

	public class CondicaoConsulta {

		private Map<String, Object> parametros;

		private StringBuilder condicoes;

		public Map<String, Object> getParametros() {
			return parametros;
		}

		public void setParametros(Map<String, Object> parametros) {
			this.parametros = parametros;
		}

		public StringBuilder getCondicoes() {
			return condicoes;
		}

		public void setCondicoes(StringBuilder condicoes) {
			this.condicoes = condicoes;
		}
	}
	
	class LastField {

		String fieldName;
		Object object;

		public LastField(String fieldName, Object object) {
			super();
			this.fieldName = fieldName;
			this.object = object;
		}

	}
}
