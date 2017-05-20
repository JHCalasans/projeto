package br.com.ido.qpedido.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

public class IOUtil {

	private static final float larguraA4 = PageSize.A4.getWidth();
	private static final float alturaA4 = PageSize.A4.getHeight();

	public static byte[] getBytesFromInputStream(InputStream stream)
			throws IOException {

		byte[] buffer = new byte[8192];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int bytesRead;
		while ((bytesRead = stream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}

		return baos.toByteArray();
	}

	public static byte[] imageToPDF(byte[] bytesDaImagem)
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
		close(baos);

		return bytes;
	}

	public static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static BufferedImage getScaledImage(BufferedImage image, int width,
			int height) throws IOException {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		double scaleX = (double) width / imageWidth;
		double scaleY = (double) height / imageHeight;
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(
				scaleX, scaleY);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(
				scaleTransform, AffineTransformOp.TYPE_BILINEAR);

		return bilinearScaleOp.filter(image, new BufferedImage(width, height,
				image.getType()));
	}

	public static InputStream getInputStreamFromImageURL(URL urlDaImagem,
			String formatoDaImagem) throws IOException {

		BufferedImage imagem = ImageIO.read(urlDaImagem);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(imagem, formatoDaImagem, os);
		return new ByteArrayInputStream(os.toByteArray());
	}

	public static boolean criarDiretorio(String nome) throws Exception {
		File diretorio = new File(nome);
		if (!diretorio.exists()) {
			if (diretorio.mkdir()) {
				return true;
			} else {
				throw new Exception("Erro ao criar diretorio");
			}

		} else {
			return false;
		}
	}

	public static void removerArquivos(File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File file : files) {
				removerArquivos(file);
			}
		}
		f.delete();
	}

}
