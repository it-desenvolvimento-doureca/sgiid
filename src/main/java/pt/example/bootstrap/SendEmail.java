package pt.example.bootstrap;

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

import pt.example.entity.conf;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class SendEmail {
	public static void main(String[] args) {

	}
	
	public void enviarEmail(String de,String para,String assunto,String mensagem,String nome_ficheiro){

		final String username = "alertas.it.doureca@gmail.com";
		final String password = "DourecA2@";

		Properties props = new Properties();	
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//587
		//ssl off
		//tls on
		
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
			
			MimeBodyPart attachPart = new MimeBodyPart();
			conf pasta = new conf();
			String filename = "/"+pasta.nomepasta+"/relatorios/"+nome_ficheiro+".pdf";
			DataSource source = new FileDataSource(filename);			
			attachPart.setDataHandler(new DataHandler(source));			
			attachPart.setFileName("Report.pdf");
			
			multipart.addBodyPart(attachPart);
			
			message.setContent(multipart);
			
			Transport.send(message);

			//System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}