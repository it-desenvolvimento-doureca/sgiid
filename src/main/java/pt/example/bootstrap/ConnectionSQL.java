package pt.example.bootstrap;



import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class ConnectionSQL {

	public static String getURL() {
		String url = null;
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c:\\cartelas\\conf.ini"));
			url = p.getProperty("DBlocation")+";User="+p.getProperty("DBuser")+";Password="+p.getProperty("DBpassword");
			// p.list(System.out);
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}



	// Devolve sempre uma conexao nova. Quem chama e responsavel por a fechar,
	// obrigatoriamente com try-with-resources.
	private static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("Driver JDBC do SQL Server nao encontrado", e);
		}
		return DriverManager.getConnection(getURL());
	}

	public static List<String> GetData() {
		String query = "Select * from CART_ULTIMO_FICHEIRO";

		List<String> x = new ArrayList<>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				String coffeeName = rs.getString("DATA_ULTIMO_FICHEIRO");
				//System.out.println(coffeeName);
				x.add(coffeeName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	public static boolean atualizadata(Timestamp data_ficheiro, String nomeficheiro) throws ParseException {
		String query = "Select top 1 * from CART_ULTIMO_FICHEIRO ";

		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement()) {

			int size = 0;
			String date_bd = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			try (ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					size++;
					date_bd = rs.getString("DATA_ULTIMO_FICHEIRO");
				}
			}

			if (size == 0) {

				stmt.executeUpdate("INSERT INTO CART_ULTIMO_FICHEIRO (DATA_ULTIMO_FICHEIRO,NOME_FICHEIRO) VALUES ('"
						+ data_ficheiro + "','" + nomeficheiro + "')");

			} else {
				if (date_bd != null) {
					Timestamp date2 = new java.sql.Timestamp(format.parse(date_bd).getTime());
					if (data_ficheiro.getTime() > date2.getTime()) {
						stmt.executeUpdate("UPDATE CART_ULTIMO_FICHEIRO SET DATA_ULTIMO_FICHEIRO = '" + data_ficheiro
								+ "',NOME_FICHEIRO='" + nomeficheiro + "'");
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] words = nomeficheiro.split("_");
		String CARREGAR_ID = words[0];
		return VerificaDados(CARREGAR_ID);
	}

	public static boolean VerificaDados(String CARREGAR_ID) {
		String query = "Select  * from CART_CAB where CARREGAR_ID = '" + CARREGAR_ID + "'";

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			int size = 0;
			while (rs.next()) {
				size++;
			}
			return size == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Object inserir_CAB(HashMap<String, String> dictMap, String tabela, String campo, Integer ID) {

		StringBuilder sql = new StringBuilder("INSERT INTO ").append(tabela).append(" (");
		StringBuilder placeholders = new StringBuilder();
		List<String> valores = new ArrayList<>(dictMap.size());
		Integer id = null;

		// Uma unica passagem para garantir que colunas e valores ficam pela mesma ordem
		for (Iterator<Map.Entry<String, String>> iter = dictMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> entry = iter.next();
			sql.append(entry.getKey());
			placeholders.append("?");
			valores.add(entry.getValue());

			if (iter.hasNext()) {
				sql.append(",");
				placeholders.append(",");
			}
		}

		if (ID != null)
			sql.append("," + campo);
		sql.append(") VALUES (");
		sql.append(placeholders);
		if (ID != null)
			sql.append("," + ID);
		sql.append(")");
		// System.out.println(sql);

		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS)) {

			int i = 1;
			for (String value : valores) {
				stmt.setString(i++, value);
			}

			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
				return null;
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {

					id = generatedKeys.getInt(1);
					// System.out.println(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}


}
