package pt.example.bootstrap;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrinterResolution;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.Pageable;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class Printer {
	private Vector m_PDFList;
	private PrinterJob m_PrinterJob;

	public static void main(String[] args) throws IOException, PrinterException {


		PDDocument document = PDDocument.load(new File("C:/sgiid_dev/relatorios/22122017140747.pdf"));

		print(document);
		document.close();
	}

	private static void print(PDDocument document) throws IOException, PrinterException {
		
		/*PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
		aset.add(new MediaPrintableArea(0, 0, 150, 100, MediaPrintableArea.MM));*/
		
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, patts);
		PrintService myService = null;
		for (int i = 0; i < services.length; i++) {
			String svcName = services[i].toString();
			System.out.println("service found: " + svcName);

			if (svcName.contains("Microsoft Print to PDF")) {
				myService = services[i];
				System.out.println("my printer found: " + svcName);
				break;
			}
		}
		
		if(myService != null){
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(myService); 
			job.setPageable(new PDFPageable(document));
			job.print();
		}else{
			System.out.println("Não encontrou a impressora");
		}
		
	}


	public ArrayList<String> getImpressoras() {
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		// System.out.println("Number of print services: " +
		// printServices.length);
		ArrayList<String> impressoras = null;
		for (PrintService printer : printServices) {
			// System.out.println("Printer: " + printer.getName());
			impressoras.add(printer.getName());
		}

		return impressoras;
	}

}