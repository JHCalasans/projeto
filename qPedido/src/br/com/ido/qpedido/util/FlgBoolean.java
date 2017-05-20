package br.com.ido.qpedido.util;

public enum FlgBoolean {
	S(true), N(false);

	private final boolean valorLogico;

	private FlgBoolean(boolean valorLogico) {
		this.valorLogico = valorLogico;
	}

	public boolean valorLogico() {
		return valorLogico;
	}

	public static FlgBoolean valueOf(Boolean valorLogico) {
		if (valorLogico == null) {
			return null;
		}
		return valorLogico ? S : N;
	}

	public static String valueStringOf(Boolean valorLogico) {
		if (valorLogico == null) {
			return null;
		}
		return valorLogico ? "S" : "N";
	}
}
