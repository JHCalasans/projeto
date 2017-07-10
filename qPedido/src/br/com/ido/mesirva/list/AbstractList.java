package br.com.ido.mesirva.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public abstract class AbstractList<E> {

	abstract public List<E> getListaTodos();

	abstract public List<E> getListaAtivos();

	abstract protected String objectLabel(E entidade);

	abstract protected Serializable objectValue(E entidade);

	public List<SelectItem> getItensTodos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<E> listaObj = getListaTodos();
		for (E e : listaObj) {
			lista.add(new SelectItem(objectValue(e), objectLabel(e)));
		}

		return lista;
	}

	public List<SelectItem> getItensAtivos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<E> listaObj = getListaAtivos();
		for (E e : listaObj) {
			lista.add(new SelectItem(objectValue(e), objectLabel(e)));
		}

		return lista;
	}

}
