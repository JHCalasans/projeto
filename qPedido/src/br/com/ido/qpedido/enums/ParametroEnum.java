package br.com.ido.qpedido.enums;

import br.com.minhaLib.enums.IEnum;

public enum ParametroEnum implements IEnum {

	IP_SERVIDOR("IP_SERVIDOR", "IP_SERVIDOR");
	
	private final String descricao;
	private final String codigo;

	private ParametroEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public static ParametroEnum valueOf(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (ParametroEnum parametroEnum : values()) {
			if (parametroEnum.getCodigo().equals(codigo)) {
				return parametroEnum;
			}
		}
		return null;
	}

}