package pt.example.bootstrap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.example.entity.conf;

public class ConnectProgress {

	private static final String QUERY = "select * from PUB.\"SOFA\" where utimod= 'recep1'";
	public static String querySofaGetAll = "select * from PUB.\"SOFA\" where utimod='%s'";
	Connection globalconnection = null;

	public static void main(String[] args) throws SQLException {
	}

	private Connection getConnection() throws SQLException {
		try {
			// the openedge driver string
			Class.forName("com.ddtek.jdbcx.openedge.OpenEdgeDataSource40");
			// the openedge url
			// String url =
			// "jdbc:datadirect:openedge://192.168.40.112:20613;DatabaseName=silv-ver;User=SYSPROGRESS;Password=SYSPROGRESS;";
			conf conf_url = new conf();
			String url = conf_url.url;
			// get the openedge database connection
			globalconnection = DriverManager.getConnection(url);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			globalconnection.close();
			// System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			globalconnection.close();
			// System.exit(2);
		} finally {
			// connection.close();
		}
		return globalconnection;
	}

	public List<HashMap<String, String>> getFornecedores() throws SQLException {

		String query = "select FOUCOD,ADRNOM from PUB.\"SDTFOE\" where FOUETSNUM= ''";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("FOUCOD", rs.getString("FOUCOD"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				list.add(x);
			}
			stmt.close();
			rs.close();
			connection.close();
			globalconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			globalconnection.close();
		}
		return list;
	}

	public List<HashMap<String, String>> getArmazem() throws SQLException {

		String query = "select LIECOD,ADRNOM from PUB.\"SDTLIE \" ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				list.add(x);
			}
			stmt.close();
			rs.close();
			connection.close();
			globalconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			globalconnection.close();
		}
		return list;
	}

	public List<HashMap<String, String>> getStock(String proref, String liecod) throws SQLException {
		if (proref == null)
			proref = "";
		String query = "select SUM(a.STOQTE) as STOQTE,b.UNIUTI  from PUB.\"STOLIE \" a LEFT JOIN PUB.\"SDTPRA \" b ON a.proref = b.proref "
				+ "where a.LIECOD in (" + liecod + ") GROUP BY b.UNIUTI, a.proref HAVING (((a.proref)='" + proref
				+ "'))";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("STOQTE", rs.getString("STOQTE"));
				x.put("UNIUTI", rs.getString("UNIUTI"));
				list.add(x);
			}
			stmt.close();
			rs.close();
			connection.close();
			globalconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			globalconnection.close();
		}
		return list;
	}

	public List<HashMap<String, String>> getComponentes() throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2 from PUB.\"SDTPRA\" where ACHFAMCOD='C001' AND ACHFASCOD IN ('CM04','CM05','CM06')";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				list.add(x);
			}
			stmt.close();
			rs.close();
			connection.close();
			globalconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			globalconnection.close();
		}
		return list;
	}

	public List<HashMap<String, String>> getUsers() throws SQLException {

		String query = "select * from PUB.\"SDTRES \" where RESTYPCOD = 'MO' ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das operações
				HashMap<String, String> x = new HashMap<>();
				x.put("RESCOD", rs.getString("RESCOD"));
				x.put("RESDES", rs.getString("RESDES"));
				list.add(x);
			}

			stmt.close();
			rs.close();
			connection.close();
			globalconnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			globalconnection.close();
		}
		return list;
	}

}