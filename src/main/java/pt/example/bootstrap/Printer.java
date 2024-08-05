package pt.example.bootstrap;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JEditorPane;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class Printer {

	public static void main(String[] args) throws IOException, PrinterException {

		// PDDocument document = PDDocument.load(new
		// File("C:/sgiid/relatorios/20240719_P240717115.txt"));

		// Xerox7225XPERTGO PCL6

		//printTxt("file:///C:/sgiid/relatorios/20240723_ssP240717115.txt", "PDF");
		// document.close();

	}

	public static Response imprimir(String documento, String impressora, String localizacao) {
		PDDocument document = null;

		File f = new File("C:/" + localizacao + "/relatorios/" + documento + ".pdf");
		if (!f.exists()) {
			return Response.status(404).entity("Ficheiro não existe").build();
		}

		try {
			document = PDDocument.load(new File("C:/" + localizacao + "/relatorios/" + documento + ".pdf"));
		} catch (InvalidPasswordException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			return Response.status(404).entity("Ficheiro não existe").build();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			return Response.status(404).entity("Ficheiro não existe").build();
		}

		try {
			// return
			print(document, impressora);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return Response.status(404).entity("Erro ao imprimir").build();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		try {
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return Response.status(404).entity("Erro ao imprimir").build();
		}
		return Response.ok().entity("Impresso").build();
	}

	private static Response print(PDDocument document, String impressora) throws IOException, PrinterException {
		/*
		 * PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		 * aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
		 * aset.add(new MediaPrintableArea(0, 0, 150, 100, MediaPrintableArea.MM));
		 */

		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, patts);
		PrintService myService = null;
		for (int i = 0; i < services.length; i++) {
			String svcName = services[i].toString();
			// System.out.println("service found: " + svcName);

			if (svcName.contains(impressora)) {
				myService = services[i];
				// System.out.println("my printer found: " + svcName);
				break;
			}
		}

		if (myService != null) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(myService);

			// override the page format
			Book book = new Book();
			// append all pages

			PageFormat pf0 = job.defaultPage();
			PageFormat pf1 = (PageFormat) pf0.clone();
			Paper p = pf0.getPaper();
			p.setImageableArea(0, 0, pf0.getWidth(), pf0.getHeight());
			pf1.setPaper(p);
			PageFormat pf2 = job.validatePage(pf1);
			// System.out.println(pf2.getHeight()+ "-"+pf2.getWidth());
			// book.append(new PDFPrintable(document));
			// job.setPrintable(new PDFPrintable(document,Scaling.SCALE_TO_FIT,false),);
			book.append(new PDFPrintable(document, Scaling.ACTUAL_SIZE, false), pf2, document.getNumberOfPages());
			job.setPageable(book);

			job.print();
		} else {
			// System.out.println("Não encontrou a impressora");
			return Response.status(404).entity("Impressora não existe ou não está disponível").build();
		}
		document.close();
		return Response.ok().entity("Impresso").build();

	}

	private static String convertFromUtf8ToIso(String s1) {
		if (s1 == null) {
			return null;
		}
		String s = new String(s1.getBytes(StandardCharsets.UTF_8));
		byte[] b = s.getBytes(StandardCharsets.ISO_8859_1);
		return new String(b, StandardCharsets.ISO_8859_1);
	}

	public static String printTxt(String document, String impressora) throws IOException, PrinterException {

		BufferedReader content = new BufferedReader(
				new InputStreamReader(new FileInputStream(document.replaceAll("file://", "")), "Cp1252"));

		String conteudo = IOUtils.toString(content);
		JEditorPane text = new JEditorPane("",conteudo);

		content.close();
		Font font = new Font("Consolas", Font.PLAIN, 6);

		text.setFont(font);
		text.getDocument().putProperty("IgnoreCharsetDirective", true);

		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, patts);
		PrintService myService = null;
		for (int i = 0; i < services.length; i++) {
			String svcName = services[i].toString();
			// System.out.println("service found: " + svcName);

			if (svcName.contains(impressora)) {
				myService = services[i];
				// System.out.println("my printer found: " + svcName);
				break;
			}
		}

		/*
		 * PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		 * MediaSize media = MediaSize.getMediaSizeForName(MediaSizeName.ISO_A4);
		 */

		int height = 290;
		int width = 200;
		MediaPrintableArea mpa = new MediaPrintableArea(5, 5, width, height, MediaPrintableArea.MM);
		HashPrintRequestAttributeSet hpras = new HashPrintRequestAttributeSet(mpa);
		// 595, 842
		if (myService != null) {
			Dimension pageSize = new Dimension(mmToPoints(width + 10), mmToPoints(height - 10));
			//int totalPages = calculateTotalPages(text, pageSize);
			//MessageFormat footer = new MessageFormat("Página {0,number,integer} de " + totalPages);
			MessageFormat footer = new MessageFormat("-{0,number,integer}-" );
			int start = document.indexOf("_") + 1;
			int end = document.lastIndexOf(".txt");
			String fileName = document.substring(start, end);
			MessageFormat header = new MessageFormat(fileName);
			text.print(header, footer, false, myService, hpras, false);
		} else {
			// System.out.println("Não encontrou a impressora");
			// return Response.status(404).entity("Impressora não existe ou não está
			// disponível").build();
		}

		// return Response.ok().entity("Impresso").build();
		return "Impresso";
	}

	public static int mmToPoints(double mm) {
		return (int) Math.ceil(mm * 2.83465);

	}

	private static int calculateTotalPages(JEditorPane editorPane, Dimension pageSize) {

		float pageHeight = (float) pageSize.getHeight();
		float totalHeight = editorPane.getPreferredSize().height;
		return (int) Math.ceil(totalHeight / pageHeight);

	}

	public ArrayList<String> getImpressoras() {
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		// System.out.println("Number of print services: " +
		// printServices.length);
		ArrayList<String> impressoras = new ArrayList<String>();
		for (PrintService printer : printServices) {
			// System.out.println("Printer: " + printer.getName());
			impressoras.add(printer.getName());
		}

		return impressoras;
	}

}