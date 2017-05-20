package br.com.ido.qpedido.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {

	public static Date obterDateInicio(int dia, int mes, int ano) {
		Calendar fixacao = new GregorianCalendar();
		fixacao.set(Calendar.YEAR, ano);
		fixacao.set(Calendar.MONTH, mes - 1);
		fixacao.set(Calendar.DAY_OF_MONTH, dia);
		fixacao.set(Calendar.HOUR_OF_DAY, 0);
		fixacao.set(Calendar.MINUTE, 0);
		fixacao.set(Calendar.SECOND, 0);
		fixacao.set(Calendar.MILLISECOND, 0);
		return fixacao.getTime();
	}

	public static Date obterDateFinal(int dia, int mes, int ano) {
		Calendar fixacao = new GregorianCalendar();
		fixacao.set(Calendar.YEAR, ano);
		fixacao.set(Calendar.MONTH, mes - 1);
		fixacao.set(Calendar.DAY_OF_MONTH, dia);
		fixacao.set(Calendar.HOUR_OF_DAY, 23);
		fixacao.set(Calendar.MINUTE, 59);
		fixacao.set(Calendar.SECOND, 59);
		fixacao.set(Calendar.MILLISECOND, 999);
		return fixacao.getTime();
	}

	public static Date addDiaData(Date data, int nrDias) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, nrDias);
		return cal.getTime();
	}

	public static Date addMesData(Date data, int nrMes) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.MONTH, nrMes);
		return cal.getTime();
	}

	public static Date obterDtInicioGraca(Date dtInicioAtualizacao) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dtInicioAtualizacao);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		if (depoisDe1Junho(dtInicioAtualizacao)) {
			cal.add(Calendar.YEAR, 1);
		}
		return cal.getTime();
	}

	public static Date obterDtFinalGraca(Date dtInicioGraca) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dtInicioGraca);
		return obterDateInicio(31, 12, cal.get(Calendar.YEAR) + 1);
	}

	public static boolean depoisDe1Junho(Date data) {
		Calendar cal1 = new GregorianCalendar(3000, Calendar.JULY, 1, 23, 59,
				999);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(data);

		if (cal2.get(Calendar.MONTH) >= cal1.get(Calendar.MONTH))
			return cal2.get(Calendar.DAY_OF_MONTH) > 1;
		return false;
	}

	public static boolean mesmoMesEAno(Date dt1, Date dt2) {
		if (dt1 != null && dt2 != null) {
			Calendar cal1 = new GregorianCalendar();
			cal1.setTime(dt1);
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(dt2);
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
					&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				return true;
			}
		}
		return false;
	}

	public static boolean mesmoMesOuSuperior(Date dt1, Date dt2) {
		if (dt1 != null && dt2 != null) {
			Calendar cal1 = new GregorianCalendar();
			cal1.setTime(dt1);
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(dt2);
			if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR))
				return true;
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
					&& cal1.get(Calendar.MONTH) >= cal2.get(Calendar.MONTH)) {
				return true;
			}
		}
		return false;
	}

	public static int extrairDia(Date dt1) {
		if (dt1 != null) {
			Calendar cal1 = new GregorianCalendar();
			cal1.setTime(dt1);
			return cal1.get(Calendar.DAY_OF_MONTH);
		}
		return -1;
	}

	public static boolean mesmoMesOuInferior(Date dt1, Date dt2) {
		if (dt1 != null && dt2 != null) {
			Calendar cal1 = new GregorianCalendar();
			cal1.setTime(dt1);
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(dt2);
			if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR))
				return true;
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
					&& cal1.get(Calendar.MONTH) <= cal2.get(Calendar.MONTH)) {
				return true;
			}
		}
		return false;
	}

	public static Date setHoraInicioDia(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date setHoraFinalDia(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static int calcularNumeroDeMeses(Date dtInicio, Date dtFinal) {
		if (dtInicio == null || dtFinal == null)
			return 0;
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(dtInicio);
		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(dtFinal);
		int m1 = cal1.get(Calendar.YEAR) * 12 + cal1.get(Calendar.MONTH);
		int m2 = cal2.get(Calendar.YEAR) * 12 + cal2.get(Calendar.MONTH);
		return m2 - m1 + 1;
	}

	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static boolean datasIguais(Date data1, Date data2) {
		Date d1 = removeTime(data1);
		Date d2 = removeTime(data2);
		return d1.equals(d2);
	}

	public static int obterAnoData(Date data) {
		Calendar cData = Calendar.getInstance();
		cData.setTime(data);
		return cData.get(Calendar.YEAR);
	}
	
	public static Date obterDataInicioAnoCorrente() {
		Calendar c = Calendar.getInstance();
		int anoCorrente = obterAnoData(new Date());
		c.set(Calendar.YEAR, anoCorrente);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return setHoraInicioDia(c.getTime());
	}
	
	public static Date obterDataInicioPorAno(int ano) {
		Calendar c = Calendar.getInstance();		
		c.set(Calendar.YEAR, ano);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return setHoraInicioDia(c.getTime());
	}

	public static Date obterDataFimRPVExpedidos() {
		Calendar c = Calendar.getInstance();
		int anoCorrente = obterAnoData(new Date());
		c.set(Calendar.YEAR, anoCorrente);
		c.set(Calendar.MONTH, 6);
		c.set(Calendar.DAY_OF_MONTH, 31);
		return setHoraFinalDia(c.getTime());
	}
	
	public static Date obterDataFimRPVExpedidosporAno(int ano) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, ano);
		c.set(Calendar.MONTH, 6);
		c.set(Calendar.DAY_OF_MONTH, 31);
		return setHoraFinalDia(c.getTime());
	}
}
