package br.com.ido.qpedido.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class UtilDownload {
	public static final String CONTENT_DISPOSITION_INLINE = "inline";
	public static final String CONTENT_DISPOSITION_ATTACHMENT = "attachment";
	public static final String MIMETYPE_PDF = "application/pdf";
	public static final String MIMETYPE_HTML = "text/html";
	public static final String MIMETYPE_JPG = "image/jpeg";
	public static final String MIMETYPE_OCTETSTREAM = "application/octet-stream";
	public static final int DEFAULT_BUFFER_SIZE = 10240; // 10 KB.

	public static void download(byte[] bytes, String nomeDoArquivo,
			String mimeType, String contentDisposition) throws IOException,
			ExcecaoNegocio {
		if (bytes == null) {
			throw new ExcecaoNegocio("O arquivo " + nomeDoArquivo
					+ " está vazio.");
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();
		BufferedInputStream input = null;

		try {
			input = new BufferedInputStream(new ByteArrayInputStream(bytes),
					DEFAULT_BUFFER_SIZE);

			response.reset();
			response.setHeader("Content-Type", mimeType);
			response.setHeader("Content-Length", String.valueOf(bytes.length));
			response.setHeader("Content-Disposition", contentDisposition
					+ ";filename=\"" + nomeDoArquivo + "\"");

			response.getOutputStream().write(bytes);

			response.getOutputStream().flush();
			response.getOutputStream().close();

			input.close();
			facesContext.responseComplete();
		} finally {
		}
	}
	
	public static byte[] concatenarRelatorios(List<Relatorio> relatorios) throws JRException{
		List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
		for (Relatorio relatorio : relatorios) {
			jasperPrints.add(relatorio.getJasperPrint_());
		}		
		JRPdfExporter exporter = new JRPdfExporter();		
		ByteArrayOutputStream out = new ByteArrayOutputStream();		
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));		
		exporter.exportReport();
		return out.toByteArray();		
	}

	public static String getContentDispositionInline() {
		return CONTENT_DISPOSITION_INLINE;
	}

	public static String getContentDispositionAttachment() {
		return CONTENT_DISPOSITION_ATTACHMENT;
	}

	public static String getMimetypePdf() {
		return MIMETYPE_PDF;
	}

	public static String getMimetypeHtml() {
		return MIMETYPE_HTML;
	}

	public static String getMimetypeJpg() {
		return MIMETYPE_JPG;
	}

	public static int getDefaultBufferSize() {
		return DEFAULT_BUFFER_SIZE;
	}

}
