package br.com.ido.qpedido.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.ido.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.ido.qpedido.enums.TipoRelatorioEnum;
import br.com.ido.qpedido.exceptions.ExcecaoGerarRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioUtil {

	public static void gerarRelatorio(Map<String, Object> parametros,
			TipoRelatorioEnum tipoRel,
			Collection<?> dados)
			throws ExcecaoGerarRelatorio {

		try {

			String path = FacesUtil.getApplicationUri() + tipoRel.getCodigo();
			URL urlReport = new URL(path);
			Relatorio relatorio;			
			if (dados != null) {
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
				relatorio = new Relatorio(parametros, urlReport, dataSource);
			} else
				relatorio = new Relatorio(parametros, urlReport);

			byte[] bytesDoRelatorioEmPDF = relatorio.exportaParaPDF();

			UtilDownload.download(bytesDoRelatorioEmPDF, tipoRel.getDescricao()
					+ ".pdf", UtilDownload.MIMETYPE_OCTETSTREAM,
					UtilDownload.CONTENT_DISPOSITION_INLINE);

		} catch (MalformedURLException e) {
			throw new ExcecaoGerarRelatorio("A URL '" + tipoRel.getCodigo()
					+ "' do Relatório não é válida", e);
		} catch (JRException e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar criar o Relatório "
							+ tipoRel.getDescricao(), e);
		} catch (IOException | ExcecaoNegocio e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar fazer download do Relatório "
							+ tipoRel.getDescricao(), e);
		}
	}
	
	public static void gerarRelatorio(List<DadosRelatorio> relatorios)
			throws ExcecaoGerarRelatorio {

		String nomeRel = relatorios.get(0).getTiposRel().getDescricao();
		try {
			List<Relatorio> relatoriosList = new ArrayList<Relatorio>();

			for (DadosRelatorio dadosRel : relatorios) {
				String path = FacesUtil.getApplicationUri()
						+ dadosRel.getTiposRel().getCodigo();
				URL urlReport = new URL(path);
				Relatorio relatorio;
				if (dadosRel.getDados() != null) {
					JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
							dadosRel.getDados());
					relatorio = new Relatorio(dadosRel.getParametros(),
							urlReport, dataSource);
				} else
					relatorio = new Relatorio(dadosRel.getParametros(),
							urlReport);
				relatoriosList.add(relatorio);
			}

			byte[] bytesDoRelatorioEmPDF = UtilDownload
					.concatenarRelatorios(relatoriosList);

			UtilDownload.download(bytesDoRelatorioEmPDF, nomeRel + ".pdf",
					UtilDownload.MIMETYPE_OCTETSTREAM,
					UtilDownload.CONTENT_DISPOSITION_INLINE);

		} catch (MalformedURLException e) {
			throw new ExcecaoGerarRelatorio("A URL do Relatório '" + nomeRel
					+ "' não é válida", e);
		} catch (JRException e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar criar o Relatório " + nomeRel, e);
		} catch (IOException | ExcecaoNegocio e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar fazer download do Relatório " + nomeRel, e);
		}
	}

	public static void gerarRelatorio(Map<String, Object> parametros,
			TipoRelatorioEnum tipoRel) throws ExcecaoGerarRelatorio {

		gerarRelatorio(parametros, tipoRel, null);
	}

	public static void gerarRelatorio(TipoRelatorioEnum tipoRel,
			Collection<?> dados)
			throws ExcecaoGerarRelatorio {

		gerarRelatorio(new HashMap<String, Object>(), tipoRel,
				dados);
	}
	
	public static void gerarRelatorioPlanilha(Map<String, Object> parametros,
			TipoRelatorioEnum tipoRel,
			Collection<?> dados, byte[] relEspelho)
			throws ExcecaoGerarRelatorio {

		try {

			String path = FacesUtil.getApplicationUri() + tipoRel.getCodigo();
			URL urlReport = new URL(path);
			Relatorio relatorio;			
			if (dados != null) {
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
				relatorio = new Relatorio(parametros, urlReport, dataSource);
			} else
				relatorio = new Relatorio(parametros, urlReport);
			
			boolean detailFound = false;
			for (Iterator<JRPrintPage> i = relatorio.getJasperPrint_().getPages().iterator(); i.hasNext();) {

				detailFound = false;

				JRPrintPage page = i.next();

				for (Object o : page.getElements()) {

					JRPrintElement element = (JRPrintElement) o;

					if (element.getKey() != null && element.getKey().contains("detailExists"))
						detailFound = true;
				}
				if (!detailFound) {

					Iterator<JRPrintElement> itt = page.getElements().iterator();

					while (itt.hasNext()) {

						JRPrintElement element = (JRPrintElement) itt.next();

						if (element.getKey() != null && element.getKey().contains("header")) {
							itt.remove();
						}
					}
				}
			}

			
			byte[] bytesDoRelatorioEmPDF = new byte[relatorio.exportaParaPDF().length + relEspelho.length] ;

			System.arraycopy(relatorio.exportaParaPDF(), 0, bytesDoRelatorioEmPDF, 0, relatorio.exportaParaPDF().length);
			System.arraycopy(relEspelho, 0, bytesDoRelatorioEmPDF, relatorio.exportaParaPDF().length, relEspelho.length);
			
			UtilDownload.download(bytesDoRelatorioEmPDF, tipoRel.getDescricao()
					+ ".pdf", UtilDownload.MIMETYPE_OCTETSTREAM,
					UtilDownload.CONTENT_DISPOSITION_INLINE);

		} catch (MalformedURLException e) {
			throw new ExcecaoGerarRelatorio("A URL '" + tipoRel.getCodigo()
					+ "' do Relatório não é válida", e);
		} catch (JRException e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar criar o Relatório "
							+ tipoRel.getDescricao(), e);
		} catch (IOException | ExcecaoNegocio e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar fazer download do Relatório "
							+ tipoRel.getDescricao(), e);
		}
	}
	
	public static byte[] gerarRelatorioBytes(Map<String, Object> parametros,
			TipoRelatorioEnum tipoRel,
			Collection<?> dados)
			throws ExcecaoGerarRelatorio {

		try {

			String path = FacesUtil.getApplicationUri() + tipoRel.getCodigo();
			URL urlReport = new URL(path);
			Relatorio relatorio;			
			if (dados != null) {
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
				relatorio = new Relatorio(parametros, urlReport, dataSource);
			} else
				relatorio = new Relatorio(parametros, urlReport);

			byte[] bytesDoRelatorioEmPDF = relatorio.exportaParaPDF();
			return bytesDoRelatorioEmPDF;

		} catch (MalformedURLException e) {
			throw new ExcecaoGerarRelatorio("A URL '" + tipoRel.getCodigo()
					+ "' do Relatório não é válida", e);
		} catch (JRException e) {
			throw new ExcecaoGerarRelatorio(
					"Falha ao tentar criar o Relatório "
							+ tipoRel.getDescricao(), e);
		}
	}
}
