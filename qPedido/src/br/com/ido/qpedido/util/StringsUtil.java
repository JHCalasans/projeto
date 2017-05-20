package br.com.ido.qpedido.util;

import java.text.Collator;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.text.MaskFormatter;

public final class StringsUtil {

	private static Locale pt_BR = new Locale("pt", "BR");

	public static int compararStringsSemAcentos(String s1, String s2) {
		Collator collator = Collator.getInstance(pt_BR);
		collator.setStrength(Collator.PRIMARY); // importante!
		return collator.compare(s1, s2);
	}

	public static String formatarString(String texto, String mascara)
			throws ParseException {
		MaskFormatter mf = new MaskFormatter(mascara);
		mf.setValueContainsLiteralCharacters(false);
		return mf.valueToString(texto);
	}

	public static String removeAcentos(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}
	
	public static String completarBrancoFinal(String object, int tamanho) {
		return completarFinal(object, tamanho, ' ');
	}
	
	public static String completarComZeroInicio(String object, int tamanho) {
		return completarInicio(object, tamanho, '0');
	}

	public static String completarComZeroInicio(Object object, int tamanho) {
		return completarInicio(object, tamanho, '0');
	}

	public static String completarInicio(Object object, int tamanho,
			char toComplete) {
		String string = "";
		if (object != null)
			string = object.toString();
		int size = string.length();
		StringBuffer buff = new StringBuffer();
		while (size < tamanho) {
			buff.append(toComplete);
			size++;
		}
		buff.append(string);
		return buff.toString();
	}

	public static String completarFinal(Object object, int tamanho,
			char toComplete) {
		String string = "";
		if (object != null)
			string = object.toString();
		int size = string.length();
		StringBuffer buff = new StringBuffer();
		buff.append(string);
		while (size < tamanho) {
			buff.append(toComplete);
			size++;
		}
		return buff.toString();
	}

}
