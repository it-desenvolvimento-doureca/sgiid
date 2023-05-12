package pt.example.bootstrap;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.DatatypeConverter;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class SendEmail {
	public static void main(String[] args) {

	}

	private String username;
	private String password;

	public void enviarEmail(String de, String para, String assunto, String mensagem, String nome_ficheiro,
			String[] files, String nomepasta, String ficheiro) {

		String host = null, port = null;

		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c:\\sgiid\\conf_email.ini"));
			host = p.getProperty("host");
			port = p.getProperty("port");
			username = p.getProperty("username");
			password = p.getProperty("password");
			// p.list(System.out);

		} catch (Exception e) {
		}

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// 587
		// ssl off
		// tls on

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			if (de == null) {
				message.setFrom(new InternetAddress("alertas.it.doureca@gmail.com"));
			} else {
				message.setFrom(new InternetAddress(de));
			}
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(assunto);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mensagem, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (nome_ficheiro != null) {
				MimeBodyPart attachPart = new MimeBodyPart();
				String filename = "/" + nomepasta + "/relatorios/" + nome_ficheiro + ".pdf";
				String nameREPORT = "Report.pdf";
				if (nome_ficheiro.contains(".xlsx")) {
					filename = "/" + nomepasta + "/relatorios/" + nome_ficheiro;
					nameREPORT = "Report.xlsx";
				}

				DataSource source = new FileDataSource(filename);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(nameREPORT);

				multipart.addBodyPart(attachPart);
			}

			if (ficheiro != null && !ficheiro.isEmpty()) {
				
				MimeBodyPart attachPart2 = new MimeBodyPart();
				Path p = Paths.get(ficheiro);
				String file = p.getFileName().toString();
				DataSource source2 = new FileDataSource(ficheiro);
				attachPart2.setDataHandler(new DataHandler(source2));
				attachPart2.setFileName(file);

				multipart.addBodyPart(attachPart2);
			}

			if (files != null && files.length > 0) {
				

				for (String pair : files) {
					// System.out.println(pair);

					String[] pairs = pair.split("<//>");
					String temp = pairs[1].split(",")[1];
					byte[] tile = DatatypeConverter.parseBase64Binary(temp);
					MimeBodyPart att = new MimeBodyPart();
					DataSource ds = new ByteArrayDataSource(tile, "image/*");
					att.setDataHandler(new DataHandler(ds));
					att.setFileName(pairs[0]);
					multipart.addBodyPart(att);

				}
			}
			message.setContent(multipart);

			Transport.send(message);

			// System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void enviarEmail2(String de, String para, String assunto, String mensagem, List<String> ficheiros,
			String filepath) {

		String host = null, port = null;

		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c:\\sgiid\\conf_email.ini"));
			host = p.getProperty("host");
			port = p.getProperty("port");
			username = p.getProperty("username");
			password = p.getProperty("password");
			// p.list(System.out);

		} catch (Exception e) {
		}

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// 587
		// ssl off
		// tls on

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(de));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(assunto);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mensagem, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// c�gigo para ficheiro aqui!
			if (ficheiros.size() > 0) {

				for (String pair : ficheiros) {
					MimeBodyPart attachPart = new MimeBodyPart();

					String filename = filepath + pair;
					DataSource source = new FileDataSource(filename);
					attachPart.setDataHandler(new DataHandler(source));
					attachPart.setFileName(pair);

					multipart.addBodyPart(attachPart);

				}
			}

			message.setContent(multipart);

			Transport.send(message);
			// LogFile.log("EMAIL ENVIADO! PARA: " + para,false);
			// System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}