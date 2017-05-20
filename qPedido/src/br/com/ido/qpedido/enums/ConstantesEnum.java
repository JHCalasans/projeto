package br.com.ido.qpedido.enums;

public enum ConstantesEnum implements IEnum {

	CHAVE_SESSION_END_EMPRESA(1, "qPedido.codEnderecoEmpresa");

	private final String descricao;
	private final Integer codigo;

	private ConstantesEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

	@Override
	public Integer getCodigo() {
		return codigo;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public static ConstantesEnum valueOf(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (ConstantesEnum parametroEnum : values()) {
			if (parametroEnum.getCodigo().equals(codigo)) {
				return parametroEnum;
			}
		}
		return null;
	}

}