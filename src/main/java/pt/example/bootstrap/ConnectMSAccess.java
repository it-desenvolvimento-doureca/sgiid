package pt.example.bootstrap;

import java.sql.*;

public class ConnectMSAccess {

	public static void main(String[] args) throws SQLException {
		getConnection("P:\\IT\\Desenvolvimento\\SGIID\\Módulo Recursos Humanos\\DMPTime.mdb");
	}

	private static void getConnection(String url) throws SQLException {
		// variables
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// Step 1: Loading or
		// registering Oracle JDBC driver class
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}

		// Step 2: Opening database connection
		try {

			String msAccDB = url;
			String dbURL = "jdbc:ucanaccess://" + msAccDB
					+ ";memory=false;showschema=true;keepMirror=C:/sgiid/db/DMPTimeDB";

			// Step 2.A: Create and
			// get connection using DriverManager class
			connection = DriverManager.getConnection(dbURL, "", "NaoPodemosErrar");

			// Step 2.B: Creating JDBC Statement
			statement = connection.createStatement();

			// Step 2.C: Executing SQL and
			// retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT top 100 * FROM Coleta a inner join Cartoes b on a.NFuncionario = b.CODIGO where DATA_HORA > '2019-03-01' order by data_hora");

			System.out.println("NumCartao\tDATA_HORA\t\tNome");
			System.out.println("==\t================\t===\t=======");

			// processing returned data and printing into console
		
			while (resultSet.next()) {
				System.out.println(resultSet.getString("NumCartao") + "\t" + resultSet.getString("DATA_HORA") + "\t"+ resultSet.getString("Nome") + "\t");
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} finally {
			// Step 3: Closing database connection
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}

	}

}