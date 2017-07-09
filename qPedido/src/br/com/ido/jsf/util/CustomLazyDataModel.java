package br.com.ido.jsf.util;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.minhaLib.dao.CriterioOrdenacao;



public abstract class CustomLazyDataModel<E> extends LazyDataModel<E> {

	private static final long serialVersionUID = 946862068160093627L;

	public static enum TipoConsulta {
		NAMED_QUERY, EXAMPLE
	};

	/**
	 * Método que auxilia a formação de um {@link CriterioOrdenacao}.
	 * 
	 * @param sortField
	 *            Parâmetro sortField passado no método {@code load(...)}
	 * @param sortOrder
	 *            Parâmetro sortOrder passado no método {@code load(...)}
	 * @param tipoConsulta
	 *            Parâmetro que indica se a consulta usada no método
	 *            {@code load(...)} é um {@code findByExample(...)} ou
	 *            {@code findByNamedQuery(...)}
	 * @return Um {@link CriterioOrdenacao} configurado baseado nos parâmetros,
	 *         ou {@code null} caso o parâmetro {@code null} sortField seja
	 *         {@code null}.
	 */
	public CriterioOrdenacao montarCriterio(String sortField,
			SortOrder sortOrder, TipoConsulta tipoConsulta) {
		CriterioOrdenacao order = null;
		if (sortField != null) {
			if (tipoConsulta == TipoConsulta.EXAMPLE) {
				String[] caminho = sortField.split("\\.", -1);
				String campo, alias, prop = null;
				if (caminho.length == 1) {
					campo = sortField;
					alias = null;
					prop = null;
				} else {
					campo = caminho[caminho.length - 1];
					prop = caminho[0];
					alias = prop;
					for (int i = 1; i < caminho.length - 1; i++) {
						prop += ".";
						prop += caminho[i];
						alias = caminho[i];
					}
					campo = alias.concat(".").concat(campo);
				}

				switch (sortOrder) {
				case ASCENDING: {
					order = CriterioOrdenacao.asc(campo);
					break;
				}
				case DESCENDING: {
					order = CriterioOrdenacao.desc(campo);
				}
				default:
					break;
				}
				if (order != null && alias != null) {
					order.addAlias(prop, alias);
				}

			} else {
				switch (sortOrder) {
				case ASCENDING: {
					order = CriterioOrdenacao.asc(sortField);
					break;
				}
				case DESCENDING: {
					order = CriterioOrdenacao.desc(sortField);
				}
				default:
					break;
				}
			}
		}
		return order;
	}

	/**
	 * Previne um bug do primefaces 3.5 que causava divisão por zero.
	 */
	@Override
	public final void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}
}
