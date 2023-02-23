package pt.example.bootstrap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;
import com.auxilii.msgparser.attachment.Attachment;
import com.auxilii.msgparser.attachment.FileAttachment;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class Testes {
	public static void main(String[] args) {
		try {
			teste();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void teste() throws IOException {

		String filepath = "C:/sgiid/temp_files/2023021017544220.msg";

		String texto = "<div class=\"MsoNormal\"> ";
		try {

			String fileName = "2023021017544220.msg";

			MsgParser msgp = new MsgParser();
			Message msg = msgp.parseMsg(filepath);
			String from_email = msg.getFromEmail();
			String from_name = msg.getFromName();
			String subject = msg.getSubject();
			String body = msg.getConvertedBodyHTML();
			String to_list = msg.getDisplayTo();
			String cc_list = msg.getDisplayCc();
			String bcc_list = msg.getDisplayBcc();
			List list = msg.getAttachments();
			texto += "<b>Anexos</b> - " + list.size() + "<br>";
			Iterator it_list = list.iterator();
			Attachment attachemetn = null;
			while (it_list.hasNext()) {
				attachemetn = (Attachment) it_list.next();
				texto += "<i>" + attachemetn + "</i><br>";
			}

			body = body.replaceAll("<!\\[endif]-->", "<![endif]");
			body = body.replaceAll("<!\\[endif]", "<![endif]-->");
			body = body.replaceAll("(?s)<!--.*?-->", "");

			List<Attachment> atts = msg.getAttachments();
			for (Attachment att : atts) {
				if (att instanceof FileAttachment) {
					FileAttachment file = (FileAttachment) att;

					// you get the actual attachment with
					if (file.getFilename() != null && !file.getFilename().isEmpty()) {
						String encodedString = new String(Base64.getEncoder().encodeToString((file.getData())));

						body = body.replaceAll("\\\"cid:" + file.getFilename() + ".*?\\\"",
								"\"data:image/" + getFileExtension(file) + ";base64," + encodedString + "\"");
					}
				}
			}
			// System.out.println(body);
			texto += "------------------------------ <br>";
			texto += "<b>De</b>:  " + from_name + " <" + from_email + "><br>";
			texto += "<b>Para</b>: " + to_list + "<br>";
			texto += "<b>Cc</b>: " + cc_list + "<br>";
			texto += "<b>Bcc</b>: " + bcc_list + "<br>";
			texto += "<b>Assunto</b>: " + subject + "<br><br><b>Mensagem</b>:<br></div>";
			texto += body + "<br>";

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(texto);

	}

	private static String getFileExtension(FileAttachment file) {
		System.out.println(file.getFilename());
		String fileName = file.getFilename();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

}