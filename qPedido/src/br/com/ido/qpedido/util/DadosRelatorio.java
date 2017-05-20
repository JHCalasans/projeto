package br.com.ido.qpedido.util;

import java.util.Collection;
import java.util.Map;

import br.com.ido.qpedido.enums.TipoRelatorioEnum;

public class DadosRelatorio {

	private TipoRelatorioEnum tiposRel;

	private Collection<?> dados;

	private Map<String, Object> parametros;

	public TipoRelatorioEnum getTiposRel() {
		return tiposRel;
	}

	public void setTiposRel(TipoRelatorioEnum tiposRel) {
		this.tiposRel = tiposRel;
	}

	public Collection<?> getDados() {
		return dados;
	}

	public void setDados(Collection<?> dados) {
		this.dados = dados;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

}
