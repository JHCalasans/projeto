package br.com.ido.qpedido.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * 05/09/2012 Conversor de CNPJ E CPF OBS: NÃO VALIDA, APENAS CONVERTE
 * FORMATAÇÕES! * mesmo a classe sendo 'CnpjConverter', pode-se utilizá-la tanto
 * para cpf quanto cnpj. * *
 */
public class CnpjConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		/*
		 * Converte CNPJ formatado para um valor sem pontos, sem barra e sem
		 * traço. Ex.: 03.847.655/0001-98 torna-se 03847655000198. Irá converter
		 * CPF formatado para um sem pontos e traço. Ex.: 355.245.198-87
		 * torna-se 35524519887.
		 */
		String aux = value;
		String cpf;
		String cnpj;
		if (value != null && !value.equals("")) {
			if (aux.length() == 18) { // CNPJ
				cnpj = value.replaceAll("\\.", "").replaceAll("/", "")
						.replaceAll("\\-", "");
				aux = cnpj;
			} else if (aux.length() == 14) {
				cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
				aux = cpf;
			}
		}
		return aux;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		/*
		 * Converte CNPJ não formatado para um valor com pontos, com barra e com
		 * traço. Ex.: 03847655000198 torna-se 03.847.655/0001-98. Irá converter
		 * CPF não formatado para um com pontos e traço. Ex.: 35524519887
		 * torna-se 355.245.198-87.
		 */
		String aux;
		String cpf;
		String cnpj;
		if (value instanceof String) {
			aux = (String) value;
			cpf = (String) value;
			cnpj = (String) value;
		} else {
			aux = value.toString();
			cpf = value.toString();
			cnpj = value.toString();
		}
		if (aux != null) {
			if (aux.length() == 11) { // CPF
				cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
						+ cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
				aux = cpf;
			} else if (aux.length() == 14) { // CNPJ
				cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "."
						+ cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12)
						+ "-" + cnpj.substring(12, 14);
				aux = cnpj;
			}
		}
		return aux;
	}
}