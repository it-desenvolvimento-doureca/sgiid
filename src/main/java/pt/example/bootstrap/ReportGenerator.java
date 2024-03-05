package pt.example.bootstrap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.enterprise.inject.spi.Bean;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class ReportGenerator {
	public static void main(String[] args) {
		/*
		 * try { report.relatorio("docx");
		 * 
		 * } catch (JRException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@SuppressWarnings("deprecation")
	public String relatorio(String format, String Name, Integer ID, String relatorio, String url2, String filepath,String subpasta,String CLIENTE,String DOCUMENTOS,String IDIOMA,List<HashMap<String, String>> dados)
			throws JRException, SQLException {
		HashMap hm = null;
		String fileName = null;

		// System.out.println("Start ....");
		fileName = Name + "." + format;

		String jrxmlFileName = "c:/" + filepath + "/relatorios/jasperfiles/"+subpasta+  relatorio + ".jrxml";
		String jasperFileName = "c:/" + filepath + "/relatorios/jasperfiles/"+subpasta + relatorio + ".jasper";
		String exportFileName = "c:/" + filepath + "/relatorios/" + fileName;

		List<Bean> beans = new ArrayList<Bean>();
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(beans);

		JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String url =
		// "jdbc:jtds:sqlserver://192.168.40.101/"+pasta.database+";instance=DOURECA;User=sa;Password=DourecA2@;";
		// String url =
		// "jdbc:jtds:sqlserver://192.168.40.126/SGIID;instance=DEVDOURECA;User=sa;Password=DourecA2@;";
		String url = url2;
		Connection conn = DriverManager.getConnection(url);

		// Create parametros
		hm = new HashMap();
		if(subpasta.equals("financeira/")){
			hm.put("DOCUMENTOS", DOCUMENTOS);
			hm.put("ID_CLIENTE", CLIENTE);
			hm.put("IDIOMA", IDIOMA);
		}else if(subpasta.equals("producao/") && dados != null){
		
			HashMap<String, String> firstMap = dados.get(0);			
			String LINHA = firstMap.get("LINHA");
			String DATA_INI = firstMap.get("DATA_INI");
			String DATA_FIM = firstMap.get("DATA_FIM");
			String PROREF = firstMap.get("PROREF");
			String TIPO_AREA = firstMap.get("TIPO_AREA");
			String HORA_INI = firstMap.get("HORA_INI");
			String HORA_FIM = firstMap.get("HORA_FIM");
			String CHECK1 = firstMap.get("CHECK1");
			
			hm.put("LINHA", LINHA);
			hm.put("DATA_INI", DATA_INI);
			hm.put("DATA_FIM", DATA_FIM);
			hm.put("PROREF", PROREF);
			hm.put("TIPO_AREA", TIPO_AREA);
			hm.put("HORA_INI", HORA_INI);
			hm.put("HORA_FIM", HORA_FIM);
			hm.put("CHECK1", CHECK1);
		}else if( dados != null){
			HashMap<String, String> firstMap = dados.get(0);	
			if(firstMap != null && firstMap.size() > 0) {
				for (Entry<String, String> entry : firstMap.entrySet())
				    	 hm.put(entry.getKey(), entry.getValue()); 				
				
			}
		}else{		
			hm.put("id", ID);
		}
		

		// Generate jasper print
		JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);

		// Export pdf file
		if (format.equals("pdf")) {
			JasperExportManager.exportReportToPdfFile(jprint, exportFileName);
		} else if (format.equals("xlsx")) {

			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jprint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, exportFileName);
			exporter.exportReport();
		} else if (format.equals("docx")) {

			Exporter exporter = new JRDocxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jprint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportFileName));
			exporter.exportReport();
		}
		conn.close();
		// System.out.println("Done exporting reports to pdf");
		deleteoldfiles(filepath);
		return fileName;

	}

	@SuppressWarnings("deprecation")
	public String relatorio2(String format, String Name, String relatorio, String url2, String filepath, String date1,
			String date2, String operario, String ativo, String sector, String dados,String subPASTA)
			throws JRException, SQLException {
		HashMap hm = null;
		String fileName = null;

		// System.out.println("Start ....");
		fileName = Name + "." + format;

		String jrxmlFileName = "c:/" + filepath + "/relatorios/jasperfiles"+subPASTA+"/" + relatorio + ".jrxml";
		String jasperFileName = "c:/" + filepath + "/relatorios/jasperfiles"+subPASTA+"/" + relatorio + ".jasper";
		String exportFileName = "c:/" + filepath + "/relatorios/" + fileName;

		// List<Bean> beans = new ArrayList<Bean>();
		// JRDataSource jrDataSource = new JRBeanCollectionDataSource(beans);

		JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

		/*try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// String url =
		// "jdbc:jtds:sqlserver://192.168.40.101/"+pasta.database+";instance=DOURECA;User=sa;Password=DourecA2@;";
		// String url =
		// "jdbc:jtds:sqlserver://192.168.40.126/SGIID;instance=DEVDOURECA;User=sa;Password=DourecA2@;";
		String url = url2;
		
		//File file = new File("C:\\Users\\it2\\Downloads\\teste.json");
		InputStream iStream = null;
		try {
			iStream = new ByteArrayInputStream(dados.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonDataSource jsonDataSource = null;
		jsonDataSource = new JsonDataSource(iStream);

		// Create parametros
		hm = new HashMap();
		hm.put("date", date1);
		hm.put("date2", date2);
		hm.put("operario", operario);
		hm.put("ativo", ativo);
		hm.put("sector", sector);
		/* hm.put("sector_acesso", SECTOR_ACESSO); */
		//hm.put("dados", dados);

		// Generate jasper print
		JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, jsonDataSource);

		// Export pdf file
		if (format.equals("pdf")) {
			JasperExportManager.exportReportToPdfFile(jprint, exportFileName);
		} else if (format.equals("xlsx")) {

			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jprint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, exportFileName);
			exporter.exportReport();
		} else if (format.equals("docx")) {

			Exporter exporter = new JRDocxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jprint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportFileName));
			exporter.exportReport();
		}
		// conn.close();
		// System.out.println("Done exporting reports to pdf");
		deleteoldfiles(filepath);
		return fileName;

	}

	@SuppressWarnings("deprecation")
	public String relatorio2(String format, String Name, Integer ID, String relatorio, String pasta, String filepath,
			String url,String email) throws JRException, SQLException {
		HashMap hm = null;
		String fileName = null;

		// System.out.println("Start ....");
		fileName = Name + "." + format;

		String jrxmlFileName = "c:/" + filepath + "/relatorios/jasperfiles/" + relatorio + ".jrxml";
		String jasperFileName = "c:/" + filepath + "/relatorios/jasperfiles/" + relatorio + ".jasper";
		String exportFileName = pasta + fileName;

		List<Bean> beans = new ArrayList<Bean>();
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(beans);

		JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String url =
		// "jdbc:jtds:sqlserver://192.168.40.101/"+pasta.database+";instance=DOURECA;User=sa;Password=DourecA2@;";
		// String url =
		// "jdbc:jtds:sqlserver://192.168.40.126/SGIID;instance=DEVDOURECA;User=sa;Password=DourecA2@;";
		Connection conn = DriverManager.getConnection(url);

		// Create parametros
		hm = new HashMap();
		if(email != null){
			hm.put("email", email);
		}else{
			hm.put("id", ID);
		}

		// Generate jasper print
		JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);

		// Export pdf file
		if (format.equals("pdf")) {
			JasperExportManager.exportReportToPdfFile(jprint, exportFileName);
		} else if (format.equals("xlsx")) {

			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jprint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, exportFileName);
			exporter.exportReport();
		} else if (format.equals("docx")) {

			Exporter exporter = new JRDocxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jprint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportFileName));
			exporter.exportReport();
		}
		conn.close();
		
		if(email != null) deleteoldfiles(filepath);
		return fileName;

	}

	public void deleteoldfiles(String filepath) {
		File directory = new File("c:/" + filepath + "/relatorios/");
		File files[] = directory.listFiles();
		for (int index = 0; index < files.length; index++) {

			if (!files[index].isDirectory()) {
				long diff = new Date().getTime() - files[index].lastModified();
				int x = 7; // se o ficheiro n�o for modificado � 7 dias �
							// apagado
				if (diff > x * 24 * 60 * 60 * 1000) {
					boolean wasDeleted = files[index].delete();
				}
			}
		}
	}

}