package br.com.ido.mesirva.enums;

import br.com.minhaLib.enums.IEnum;

public enum TipoRelatorioEnum implements IEnum {

	OFICIO_RPV("/relatorios/etiquetas.jasper", "OficioRPV");
	

	private final String descricao;
	private final String codigo;

	private TipoRelatorioEnum(String codigo, String descricao) {
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

	public static TipoRelatorioEnum valueOf(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (TipoRelatorioEnum parametroEnum : values()) {
			if (parametroEnum.getCodigo().equals(codigo)) {
				return parametroEnum;
			}
		}
		return null;
	}

}