package br.com.ido.qpedido.enums;

import br.com.minhaLib.enums.IEnum;

public enum CharsetEnum implements IEnum {
	ISO("ISO-8859-1", "ISO-8859-1"), 
 	UTF8("UTF-8", "UTF-8");
	
	private final String descricao;
	private final String codigo;

	private CharsetEnum(String codigo, String descricao) {
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

	public static CharsetEnum valueOf(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (CharsetEnum charsetEnum : values()) {
			if (charsetEnum.getCodigo().equals(codigo)) {
				return charsetEnum;
			}
		}
		return null;
	}

}