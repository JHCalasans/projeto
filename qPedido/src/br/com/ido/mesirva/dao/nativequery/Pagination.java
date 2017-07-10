package br.com.ido.mesirva.dao.nativequery;

/**
 * Classe que representa os valores de paginação para as consultas que utilizam
 * NativeQuery.
 * 
 * @author Rodrigo Barros
 */
public class Pagination {

	private int firstResult;

	private int maxResults;

	public Pagination(int firstResult, int maxResults) {
		super();
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}
}
