package br.com.ido.funcoes;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import br.com.ido.qpedido.enums.IEnum;
import br.com.ido.qpedido.util.PdfUtil;

public class Funcoes {

	private static Logger log = Logger.getLogger(Funcoes.class);

	public Funcoes() {
	}

	public static double datediffd(Date data1, Date data2, String saida) {
		if (data1 == null || data2 == null) {
			return 0;
		}

		if ("mes".equalsIgnoreCase(saida) || "ano".equalsIgnoreCase(saida)) {
			Calendar d1 = Calendar.getInstance();
			d1.setTime(data1);
			Calendar d2 = Calendar.getInstance();
			d2.setTime(data2);
			int diffY = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);

			if ("mes".equalsIgnoreCase(saida)) {
				double diffMes = (d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH)) + 12d * diffY;
				d1.add(Calendar.MONTH, (int) diffMes);
				double diffDias = datediffd(d1.getTime(), data2, "dia");
				diffDias /= (diffDias >= 30) ? 31 : 30;
				diffMes += diffDias;
				return diffMes;
			} else {
				double diffAno = diffY;
				d1.add(Calendar.YEAR, diffY);
				double diffDias = datediffd(d1.getTime(), data2, "dia");
				diffDias /= (diffDias >= 365) ? 366 : 365;
				diffAno += diffDias;
				return diffAno;
			}
		}

		long diff = data2.getTime() - data1.getTime();
		if (saida == null || saida.length() == 0) {
			return diff;
		}
		double diffSeg = diff / 1000d;
		if ("seg".equalsIgnoreCase(saida)) {
			return diffSeg;
		}
		double diffMin = diffSeg / 60d;
		if ("min".equalsIgnoreCase(saida)) {
			return diffMin;
		}
		double diffHours = diffMin / 60d;
		if ("hor".equalsIgnoreCase(saida)) {
			return diffHours;
		}
		double diffDay = diffHours / 24d;
		if ("dia".equalsIgnoreCase(saida)) {
			return diffDay;
		}
		double diffSem = diffDay / 7d;
		if ("sem".equalsIgnoreCase(saida)) {
			return diffSem;
		}

		return diffDay;
	}

	public static long datediffi(Date data1, Date data2, String saida) {
		return (long) datediffd(data1, data2, saida);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Serializable sysEnum(String string) {
		String[] partes = string.split("\\.", -1);

		if (partes.length != 2) {
			log.warn("Enum \'" + string + "\' n„o v·lida.");
			return null;
		}

		if (partes.length == 2) {
			String className = "br.com.ido.qpedido.enums." + partes[0].trim();
			String enumName = partes[1].trim();
			try {
				Class enumClasse = Class.forName(className);
				Enum x = Enum.valueOf(enumClasse, enumName);
				if (x instanceof IEnum) {
					IEnum e = (IEnum) x;
					return e.getCodigo();
				} else {
					log.warn("Enum \'" + className + "\' n„o estende a interface \'" + IEnum.class.getName() + "\'.");
					return null;
				}
			} catch (ClassNotFoundException e) {
				log.warn("Classe \'" + className + "\' n„o encontrada.");
			} catch (IllegalArgumentException e) {
				log.warn("Constante \'" + enumName + "\' n„o encontrada na classe \'" + className + "\'.");
			} catch (Exception e) {
				log.warn("Erro geral ao avaliar ido:sysEnum(\'" + string + "\') : " + e.getMessage());
			}
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Serializable sysEnumDescricao(String string) {
		String[] partes = string.split("\\.", -1);

		if (partes.length != 2) {
			log.warn("Enum \'" + string + "\' n„o v·lida.");
			return null;
		}

		if (partes.length == 2) {
			String className = "br.com.ido.qpedido.enums." + partes[0].trim();
			String enumName = partes[1].trim();
			try {
				Class enumClasse = Class.forName(className);
				Enum x = Enum.valueOf(enumClasse, enumName);
				if (x instanceof IEnum) {
					IEnum e = (IEnum) x;
					return e.getDescricao();
				} else {
					log.warn("Enum \'" + className + "\' n„o estende a interface \'" + IEnum.class.getName() + "\'.");
					return null;
				}
			} catch (ClassNotFoundException e) {
				log.warn("Classe \'" + className + "\' n„o encontrada.");
			} catch (IllegalArgumentException e) {
				log.warn("Constante \'" + enumName + "\' n„o encontrada na classe \'" + className + "\'.");
			} catch (Exception e) {
				log.warn("Erro geral ao avaliar ido:sysEnum(\'" + string + "\') : " + e.getMessage());
			}
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes" })
	public static Serializable sysEnumDescricaoPorCodigo(String string, String codigo) {
		if (string != null) {
			String className = "br.com.ido.qpedido.enums." + string;
			try {
				Class enumClasse = Class.forName(className);
				Object[] values = enumClasse.getEnumConstants();
				for (Object value : values) {
					if (value instanceof IEnum) {
						IEnum e = (IEnum) value;
						if (codigo.equals(e.getCodigo().toString()))
							return e.getDescricao();
					} else {
						return null;
					}
				}
			} catch (ClassNotFoundException e) {
				log.warn("Classe \'" + className + "\' n„o encontrada.");
			} catch (Exception e) {
				log.warn(
						"Erro geral ao avaliar ido:sysEnumDescricaoPorCodigo(\'" + string + "\') : " + e.getMessage());
			}
		}

		return null;
	}

	public static Date toDate(String toParse, String format) {
		String f = format;
		if (format == null || format.length() == 0) {
			f = "yyyy-MM-dd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(f);
		Date x = null;
		try {
			x = formatter.parse(toParse);
		} catch (ParseException e) {

		}
		return x;
	}

	public static boolean estaNaLista(Object obj, Object objLista) {
		if (objLista == null) {
			return false;
		}
		Object o = obj;
		Collection<?> colecao = null;
		if (objLista instanceof Collection<?>) {
			colecao = (Collection<?>) objLista;
		} else if (objLista instanceof Object[]) {
			Object[] b = (Object[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof int[]) {
			int[] b = (int[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof byte[]) {
			byte[] b = (byte[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof char[]) {
			char[] b = (char[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof long[]) {
			long[] b = (long[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof float[]) {
			float[] b = (float[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof double[]) {
			double[] b = (double[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof boolean[]) {
			boolean[] b = (boolean[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof short[]) {
			short[] b = (short[]) objLista;
			colecao = Arrays.asList(b);
		} else if (objLista instanceof String) {
			String str = (String) objLista;
			if (str.length() == 0) {
				return false;
			}
			String separador = "" + ((String) objLista).charAt(0);
			str = str.substring(1);
			colecao = Arrays.asList(str.split(separador, -1));
			o = obj.toString();
		}

		return (colecao != null) && !colecao.isEmpty() && colecao.contains(o);
	}

	public static int length(Object x) {
		if (x == null) {
			return 0;
		}

		if (x instanceof String) {
			// Devolve o tamanho da String
			String str = (String) x;
			return str.length();
		}

		if (x instanceof Collection<?>) {
			// Devolve o n√∫mero de elementos de tipos como List, ArrayList,
			// Vector,
			// Set, etc.
			Collection<?> lista = (Collection<?>) x;
			return lista.size();
		}
		{
			// Para os objetos do tipo array, √© preciso testar cada um
			// dos tipos primitivos (int[], char[], etc.) e o caso Object[]
			// cobrir√° todos os outros casos.
			// if(x.getClass().isArray()) poderia ser usado antes de entrar
			// aqui, mas vi em algum lugar que isso pode ser custoso, al√©m de
			// usar reflex√£o diretamente, que dizem ser bom evitar

			if (x instanceof Object[]) {
				Object[] b = (Object[]) x;
				return b.length;
			}
			if (x instanceof int[]) {
				int[] b = (int[]) x;
				return b.length;
			}
			if (x instanceof byte[]) {
				byte[] b = (byte[]) x;
				return b.length;
			}
			if (x instanceof char[]) {
				char[] b = (char[]) x;
				return b.length;
			}
			if (x instanceof long[]) {
				long[] b = (long[]) x;
				return b.length;
			}
			if (x instanceof float[]) {
				float[] b = (float[]) x;
				return b.length;
			}
			if (x instanceof double[]) {
				double[] b = (double[]) x;
				return b.length;
			}
			if (x instanceof boolean[]) {
				boolean[] b = (boolean[]) x;
				return b.length;
			}
			if (x instanceof short[]) {
				short[] b = (short[]) x;
				return b.length;
			}
		}

		if (x instanceof Map<?, ?>) {
			// Devolve a quantidade de entradas contidas num map.
			Map<?, ?> m = (Map<?, ?>) x;
			return m.size();
		}

		// Caso padr√£o, nada a fazer
		return 0;
	}

	public static boolean between(double valor, double valorInicial, double valorFinal) {
		return (valor >= valorInicial && valor <= valorFinal);
	}

	public static String concat(Object s1, Object s2) {
		return concat(new Object[] { s1, s2 });
	}

	public static String concat(Object s1, Object s2, Object s3) {
		return concat(new Object[] { s1, s2, s3 });
	}

	public static String concat(Object s1, Object s2, Object s3, Object s4) {
		return concat(new Object[] { s1, s2, s3, s4 });
	}

	public static String concat(Object s1, Object s2, Object s3, Object s4, Object s5) {
		return concat(new Object[] { s1, s2, s3, s4, s5 });
	}

	public static String concat(Object... objs) {
		if (objs == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Object o : objs) {
			if (o != null) {
				sb.append(o.toString());
			}
		}

		return sb.toString();
	}

	public static String escaparJS(String s) {
		String j = StringEscapeUtils.escapeJava(s).replaceAll("\'", "\\\'");
		return j;
	}

	public static StreamedContent downloadFile(Object fileBytes, String contentType, String name) {
		if (fileBytes instanceof byte[]) {
			return new DefaultStreamedContent(new ByteArrayInputStream((byte[]) fileBytes), contentType, name);
		}
		if (fileBytes instanceof InputStream) {
			return new DefaultStreamedContent((java.io.InputStream) fileBytes, contentType, name);
		}
		return null;
	}

	public static StreamedContent downloadHtmlAsPDF(Object html, String name, String url) throws Exception {
		if (html instanceof String) {
			return downloadFile(PdfUtil.convertHtml2Pdf(((String) html).getBytes("ISO-8859-1"), url), "application/pdf",
					name);
		}
		if (html instanceof byte[]) {
			return downloadFile(PdfUtil.convertHtml2Pdf((byte[]) html, url), "application/pdf", name);
		}
		return null;
	}

	public static String piece(String string, String separador, int numPiece) {
		if (numPiece <= 0) {
			return "";
		}
		String[] pieces = string.split(separador, -1);
		if (numPiece > pieces.length) {
			return "";
		}
		return pieces[numPiece - 1];
	}

	public Date addDate(Date data, String campo, int valor) throws Exception {
		int tipoValor = 0;

		try {
			if (campo.equals("dia")) {
				tipoValor = Calendar.DATE;
			} else if (campo.equals("mes")) {
				tipoValor = Calendar.MONTH;
			} else if (campo.equals("ano")) {
				tipoValor = Calendar.YEAR;
			} else {
				throw new Exception("O tipo de campo s√≥ pode ser dia, mes ou ano.");
			}

			Calendar c = Calendar.getInstance();
			c.setTime(data);
			c.add(tipoValor, valor);
			return c.getTime();
		} catch (Exception e) {
		}

		return null;
	}

	public static String formatarData(Date data, String formato) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
			return simpleDateFormat.format(data);
		} catch (Exception e) {
			return "";
		}
	}

}
