package br.com.ido.qpedido.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


import br.com.ido.funcoes.Funcoes;
import br.com.ido.qpedido.enums.ParametroEnum;
import br.com.minhaLib.excecao.excecaobanco.ExcecaoBanco;

public class QRCodeUtil {

	public static byte[] gerarQRCode(Long codMesa) {
	
		//String caminho = "C:/Users/d1396/Desktop/seucurso.png";
		int tam = 525;
		String tipoArquivo = "jpg";
		//File myFile = new File(caminho);
		try {
			String textodoCodigo = Funcoes.getParam(ParametroEnum.IP_SERVIDOR.getCodigo()) + "/meSirva/ws/mesaEnderecoEmpresa/lerQRCode?codMesa=" + codMesa;
			Hashtable hintMap = new Hashtable();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(textodoCodigo, BarcodeFormat.QR_CODE, tam, tam, hintMap);
			int tamanho = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, tamanho, tamanho);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, tipoArquivo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
			
		} catch (WriterException | IOException | ExcecaoBanco e) {
			ExcecoesUtil.TratarExcecao(e);
			return null;
		}
		
	}

}
