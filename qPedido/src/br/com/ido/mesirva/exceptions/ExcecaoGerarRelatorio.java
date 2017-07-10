package br.com.ido.mesirva.exceptions;

import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;

public class ExcecaoGerarRelatorio extends ExcecaoNegocio {

	private static final long serialVersionUID = -3717573326189298859L;

	public ExcecaoGerarRelatorio(String mensagem) {
		super(mensagem);
	}

	public ExcecaoGerarRelatorio(Throwable causa) {
		super(causa);
	}

	public ExcecaoGerarRelatorio(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
