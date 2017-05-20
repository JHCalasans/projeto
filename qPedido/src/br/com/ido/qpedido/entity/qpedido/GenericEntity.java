package br.com.ido.qpedido.entity.qpedido;

import br.com.ido.dao.Entidade;

public class GenericEntity extends Entidade {

	private static final long serialVersionUID = 6751345311550906104L;

	private Integer identificador;

	@Override
	public Integer getIdentificador() {
		return identificador;
	}

}
