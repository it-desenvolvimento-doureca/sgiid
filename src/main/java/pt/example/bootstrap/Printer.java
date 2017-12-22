package pt.example.bootstrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

public class Printer {

	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws IOException {
		enviarEmail();
	}

	public static void enviarEmail() throws IOException {
		

        String user = "alertas.it.doureca@gmail.com";
        String pass = "DourecA2@";
        String source = "Cloud%20Printing%20Test";
        
		String url = "https://www.google.com/cloudprint/search?output=json";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("POST");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		//BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		//while ((inputLine = in.readLine()) != null) {
		//	response.append(inputLine);
		//}
		//in.close();

		// print result
		System.out.println(response.toString());
	}

}