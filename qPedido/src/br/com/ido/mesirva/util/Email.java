package br.com.ido.mesirva.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	public boolean enviarEmail(String mensagem, String para, String de,
			String assunto, String caminhoArquivo, boolean isHTML) throws MessagingException {

		try {
			Properties properties = obterProperties();
			
			SimpleAuth auth = null;
			auth = new SimpleAuth(properties.getProperty("email.user"),
					properties.getProperty("email.pwd"));

			Session mailSession = Session.getDefaultInstance(properties, auth);

			// Configuração da mensagem
			MimeMessage msg = new MimeMessage(mailSession);
			InternetAddress addressFrom = new InternetAddress(de);
			Address to[] = new InternetAddress[1];
			msg.setFrom(addressFrom);
			// Configuração do Cabeçalho do email
			msg.setSubject(assunto, "UTF-8");
			String[] enderecos = para.split(";");

			MimeBodyPart textPart = new MimeBodyPart();

			// Verifica se é um email com corpo escrito em HTML
			if (isHTML == true) {
				// Corpo da mensagem em código HTML
				textPart.setContent(mensagem, "text/html; charset=UTF-8");
			} else {
				textPart.setContent(mensagem, "text/plain; charset=UTF-8");
			}

			for (int i = 0; i < enderecos.length; i++) {

				to[0] = new InternetAddress(enderecos[i]);
				msg.setRecipients(Message.RecipientType.TO, to);

				// Abre e anexa o arquivo
				MimeBodyPart attachFilePart = new MimeBodyPart();
				if(caminhoArquivo != null) {
					FileDataSource fds = new FileDataSource(caminhoArquivo);
					attachFilePart.setDataHandler(new DataHandler(fds));
					attachFilePart.setFileName(fds.getName());
				}

				// Monta a mensagem SMTP
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(textPart);
				if(caminhoArquivo != null)
					mp.addBodyPart(attachFilePart);
				msg.setContent(mp);

				// Envia mensagem
				Transport.send(msg);
				Thread.sleep(1);
				System.out.println(" E-mial enviado com sucesso! ");
			}
		} catch (Exception e) {
			// Falha no envio
			e.printStackTrace();
			System.out.println(" Falha ao enviar e-mail ");
			return false;
		}
		return true;
	}
	
	public static Properties obterProperties() throws IOException {
		ClassLoader cl = Email.class.getClassLoader();
		InputStream is = cl.getResourceAsStream("./br/com/ido/qpedido/util/mail.properties");
		Properties prop = new Properties();
		prop.load(is);
		return prop;
	}

	class SimpleAuth extends Authenticator {
		public String username = null;
		public String password = null;

		public SimpleAuth(String user, String pwd) {
			username = user;
			password = pwd;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

}