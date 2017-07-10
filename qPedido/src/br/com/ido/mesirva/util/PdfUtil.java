package br.com.ido.mesirva.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.SimpleBookmark;

public class PdfUtil {
	private static final float larguraA4 = PageSize.A4.getWidth();
	private static final float alturaA4 = PageSize.A4.getHeight();

	public static byte[] convertImage2PDF(byte[] bytesDaImagem)
			throws MalformedURLException, IOException, DocumentException {

		Image imagem = Image.getInstance(bytesDaImagem);
		imagem.scaleToFit(larguraA4 - 50f, alturaA4 - 50f);

		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, baos);

		writer.open();
		document.open();
		document.add(imagem);
		document.close();

		writer.close();
		byte[] bytes = baos.toByteArray();
		IOUtil.close(baos);

		return bytes;
	}

	public static void concatPDF(List<InputStream> streamOfPDFFiles,
			OutputStream outputStream, boolean paginate) throws Exception {

		int pageOffset = 0;
		Document document = new Document();

		try {
			List<SimpleBookmark> masterBookmark = new ArrayList<SimpleBookmark>();
			List<InputStream> listPdfs = streamOfPDFFiles;
			List<PdfReader> readers = new ArrayList<PdfReader>();

			// Create Readers for the pdfs.
			for (InputStream isPdf : listPdfs) {
				PdfReader pdfReader = new PdfReader(isPdf);
				readers.add(pdfReader);
				pageOffset += pdfReader.getNumberOfPages();

				@SuppressWarnings("unchecked")
				List<SimpleBookmark> bookmarks = SimpleBookmark
						.getBookmark(pdfReader);
				if (bookmarks != null) {
					if (pageOffset != 0) {
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset,
								null);
					}
					masterBookmark.addAll(bookmarks);
				}
			}

			// Create a writer for the outputstream
			PdfCopy writer = new PdfCopy(document, outputStream);

			document.open();
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data

			PdfImportedPage page;
			int currentPageNumber = 0;
			int pageOfCurrentReaderPDF = 0;

			for (PdfReader pdfReader : readers) {

				while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
					document.newPage();
					pageOfCurrentReaderPDF++;
					currentPageNumber++;
					page = writer.getImportedPage(pdfReader,
							pageOfCurrentReaderPDF);
					cb.addTemplate(page, 0, 0);

					// Code for pagination.
					if (paginate) {
						cb.beginText();
						cb.setFontAndSize(bf, 9);
						cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
								+ currentPageNumber + " of " + pageOffset, 520,
								5, 0);
						cb.endText();
					}

					writer.addPage(page);
				}

				PRAcroForm form = pdfReader.getAcroForm();
				if (form != null) {
					writer.copyAcroForm(pdfReader);
				}

				pageOfCurrentReaderPDF = 0;
			}

			if (masterBookmark.size() > 0) {
				writer.setOutlines(masterBookmark);
			}

			outputStream.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if ((document != null) && (document.isOpen())) {
				document.close();
			}
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public static void convertHtml2Pdf(String input, OutputStream out, String url)
			throws DocumentException, IOException {

		convert(new ByteArrayInputStream(input.getBytes()), out, url);
	}

	public static void convertHtml2Pdf(byte[] input, OutputStream out, String url)
			throws DocumentException, IOException {

		convert(new ByteArrayInputStream(input), out, url);
	}

	public static byte[] convertHtml2Pdf(byte[] input, String url) throws DocumentException, IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		convert(new ByteArrayInputStream(input), baos, url);

		return baos.toByteArray();
	}

	private static void convert(InputStream input, OutputStream out, String url)
			throws DocumentException, DocumentException, IOException {

		Tidy tidy = new Tidy();
		tidy.setShowWarnings(false);
		tidy.setShowErrors(0);
		tidy.setQuiet(true);
		tidy.setErrout(new PrintWriter(new ByteArrayOutputStream()));
		org.w3c.dom.Document doc = tidy.parseDOM(input, null);
		ITextRenderer renderer = new ITextRenderer();
		try
		{
		String systemRoot = System.getenv().get("SystemRoot");
		renderer.getFontResolver().addFont(systemRoot+"\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.getFontResolver().addFont(systemRoot+"\\fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.getFontResolver().addFont(systemRoot+"\\fonts\\arialbi.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.getFontResolver().addFont(systemRoot+"\\fonts\\ariali.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		//renderer.getFontResolver().addFont(systemRoot+"\\fonts\\arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		}
		catch (Exception ex)
		{
			
		}
		renderer.setDocument(doc, url);
		renderer.layout();
		renderer.createPDF(out);
		
		
	}

}
