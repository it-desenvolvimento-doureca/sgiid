package pt.example.bootstrap;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import pt.example.entity.conf;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class ReportGenerator {
	public static void main(String[] args) {
		/*
		try {
			 report.relatorio("docx");
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@SuppressWarnings("deprecation")
	public String relatorio(String format,String Name, Integer ID, String relatorio, String url2) throws JRException, SQLException {
		HashMap hm = null;
		String fileName = null;

		// System.out.println("Start ....");
		fileName = Name+"." + format;
		conf pasta = new conf();
		String jrxmlFileName = ""+pasta.filePath+"jasperfiles/"+relatorio+".jrxml";
		String jasperFileName = ""+pasta.filePath+"jasperfiles/"+relatorio+".jasper";
		String exportFileName = ""+pasta.filePath+"" + fileName;

		JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String url = "jdbc:jtds:sqlserver://192.168.40.101/"+pasta.database+";instance=DOURECA;User=sa;Password=DourecA2@;";
		String url = url2;
		Connection conn = DriverManager.getConnection(url);

		// Create parametros
		hm = new HashMap();
		hm.put("id", ID);

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
		deleteoldfiles();
		return fileName;

	}

	public void deleteoldfiles() {
		conf pasta = new conf();
		File directory = new File(pasta.filePath);
		File files[] = directory.listFiles();
		for (int index = 0; index < files.length; index++) {
					
			if (!files[index].isDirectory()){
				long diff = new Date().getTime() - files[index].lastModified();
				int x = 15; //se o ficheiro não for modificado à 15 dias é apagado
				if(diff > x * 24 * 60 * 60 * 1000){
					boolean wasDeleted = files[index].delete();
				}
			}
		}
	}
	

}