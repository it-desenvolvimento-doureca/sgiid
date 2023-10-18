package pt.example.bootstrap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.example.entity.conf;

public class ConnectProgress {

	private static final String QUERY = "select * from SOFA where utimod= 'recep1'";
	public static String querySofaGetAll = "select * from SOFA where utimod='%s'";
	Connection globalconnection = null;

	public static void main(String[] args) throws SQLException {
	}

	private Connection getConnection(String url) throws SQLException {
		try {
			// the openedge driver string
			// Class.forName("com.ddtek.jdbcx.openedge.OpenEdgeDataSource40");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// the openedge url
			// String url =
			// "jdbc:datadirect:openedge://192.168.40.112:20613;DatabaseName=silv-ver;User=SYSPROGRESS;Password=SYSPROGRESS;";
			// conf conf_url = new conf();
			// String url = conf_url.url;
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

	public List<HashMap<String, String>> getTipoFalta(String url) throws SQLException {

		String query = "select DISTINCT * from SPAARR order by arrlib";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("ARRCOD", rs.getString("ARRCOD"));
				x.put("arrlib", rs.getString("arrlib"));
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

	public List<HashMap<String, String>> getFornecedores(String url) throws SQLException {

		String query = "select FOUCOD,ADRNOM,ADRNUMINT from SDTFOE where FOUETSNUM= ''";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("FOUCOD", rs.getString("FOUCOD"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("ADRNUMINT", rs.getString("ADRNUMINT"));
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

	public List<HashMap<String, String>> verificaOF(String url, String of) throws SQLException {

		String query = "select OFNUM from SOFA where OFNUM = '" + of + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("OFNUM", rs.getString("OFNUM"));
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

	public List<HashMap<String, String>> verificaEtiqueta(String url, String etiqueta, String quantidade,
			String aramzem, String data, String hora, String proref) throws SQLException {

		String query = "select PROREF,DATCRE  from STOMVS where etqnum = '" + etiqueta
				+ "' and liecod ='DPCHI' and uticre = 'silver' " + "and mvsdat = '" + data + "' and mvsqte = "
				+ quantidade + " " + "and hmscre >= CONVERT( TIME, '" + hora
				+ "' )  and hmscre <=  DATEADD(MINUTE,1, CONVERT( TIME, '" + hora + "' ))" + "and proref = '" + proref
				+ "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
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

	public List<HashMap<String, String>> verificaREF(String url, String proref) throws SQLException {

		String query = "select PROREF from SDTPRA where PROREF = '" + proref + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
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

	public List<HashMap<String, String>> getArmazem(String url) throws SQLException {

		String query = "select LIECOD,ADRNOM from SDTLIE  ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
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

	public List<HashMap<String, String>> getOrigineComposant(String url, String PROREF, String OF) throws SQLException {

		String query = "select (select COUNT(a.PROREF) from SOFC a inner join SOFA b  on a.OFANUMENR = b.OFANUMENR where a.PROREF = '"
				+ PROREF + "' and b.OFNUM = '" + OF + "') as total, " + "a.INDNUMCSE,a.NCLRANG "
				+ "from SOFC a inner join SOFA b  on a.OFANUMENR = b.OFANUMENR where a.PROREF = '" + PROREF
				+ "' and b.OFNUM = '" + OF + "'";

		/*
		 * String query = "select " +
		 * "(select COUNT(b.INDNUMENR) from  SOFA a inner join SOFB b ON b.OFANUMENR = a.OFANUMENR left JOIN SOFC c ON c.ofanumenr = a.ofanumenr where b.PROREF  = '"
		 * +PROREF+"' and a.OFNUM = '"+OF+"') as total, " + "b.INDNUMENR,c.NCLRANG " +
		 * "from SOFA a  inner join SOFB b ON b.OFANUMENR = a.OFANUMENR " +
		 * "left JOIN SOFC c ON c.ofanumenr = a.ofanumenr where a.OFNUM = '"
		 * +OF+"' and b.PROREF = '"+PROREF+"'";
		 */

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("total", rs.getString("total"));
				x.put("INDNUMCSE", rs.getString("INDNUMCSE"));
				x.put("NCLRANG", rs.getString("NCLRANG"));
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

	public void EXEC_SINCRO(String etqnum, Float qtd, String url) throws SQLException {

		try {

			Connection connection = getConnection(url);

			/* String sql = "EXEC SP_Merge_" + tabela + " ?,?"; */

			String sql = "UPDATE SETQDE set ETQEMBQTE = " + qtd + "where etqnum = '" + etqnum + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			/*
			 * ps.setBoolean(1, true); ps.setBoolean(2, false);
			 */

			boolean results = ps.execute();
			int rsCount = 0;
			// Loop through the available result sets.
			while (results) {

				ResultSet rs = ps.getResultSet();

				while (rs.next()) {
					// System.out.println(rs.getString("message"));
				}
				rs.close();

				// Check for next result set
				results = ps.getMoreResults();
			}
			ps.close();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			globalconnection.close();
			// System.exit(2);
		} finally {

		}

	}

	public void EXEC_SINCRO_AO_APAGAR(String etqnum, Float qtd, String url) throws SQLException {

		try {

			Connection connection = getConnection(url);

			/* String sql = "EXEC SP_Merge_" + tabela + " ?,?"; */

			String sql = "UPDATE SETQDE set ETQEMBQTE = ETQEMBQTE + " + qtd + "where etqnum = '" + etqnum + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			/*
			 * ps.setBoolean(1, true); ps.setBoolean(2, false);
			 */

			boolean results = ps.execute();
			int rsCount = 0;
			// Loop through the available result sets.
			while (results) {

				ResultSet rs = ps.getResultSet();

				while (rs.next()) {
					// System.out.println(rs.getString("message"));
				}
				rs.close();

				// Check for next result set
				results = ps.getMoreResults();
			}
			ps.close();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			globalconnection.close();
			// System.exit(2);
		} finally {

		}

	}

	public void EXEC_SINCRO2(String etqnum, Float qtd, String url) throws SQLException {

		try {

			Connection connection = getConnection(url);

			/* String sql = "EXEC SP_Merge_" + tabela + " ?,?"; */

			String sql = "UPDATE SETQDE set ETQEMBQTE = ETQEMBQTE + " + qtd + "where etqnum = '" + etqnum + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			/*
			 * ps.setBoolean(1, true); ps.setBoolean(2, false);
			 */

			boolean results = ps.execute();
			int rsCount = 0;
			// Loop through the available result sets.
			while (results) {

				ResultSet rs = ps.getResultSet();

				while (rs.next()) {
					// System.out.println(rs.getString("message"));
				}
				rs.close();

				// Check for next result set
				results = ps.getMoreResults();
			}
			ps.close();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			globalconnection.close();
			// System.exit(2);
		} finally {

		}

	}

	public List<HashMap<String, String>> getOrigineComposant2(String url, String PROREF, String OF)
			throws SQLException {

		String query = "select a.INDNUMENR from SOFB a inner join SOFA b  on a.OFANUMENR = b.OFANUMENR where a.PROREF = '"
				+ PROREF + "' and b.OFNUM ='" + OF + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("INDNUMENR", rs.getString("INDNUMENR"));
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

	public List<HashMap<String, String>> getMaquinas(String url) throws SQLException {

		String query = "select b.ssecod,a.SSEDES from SPASSE a inner join SDTSEC b on a.ssecod = b.ssecod ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("ssecod", rs.getString("ssecod"));
				x.put("SSEDES", rs.getString("SSEDES"));
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
	
	public List<HashMap<String, String>> getMaquinasSilver(String url) throws SQLException {

		String query = "select a.ssecod,a.SSEDES from SPASSE a  ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("ssecod", rs.getString("ssecod"));
				x.put("SSEDES", rs.getString("SSEDES"));
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

	public List<HashMap<String, String>> getStock(List<HashMap<String, String>> data, String url) throws SQLException {

		HashMap<String, String> firstMap = data.get(0);
		String proref = firstMap.get("proref");
		String liecod = firstMap.get("liecod");
		if (proref == null)
			proref = "";
		String query = "select (select SUM(xa.STOQTE) from STOLIE  xa LEFT JOIN SDTPRA  xb ON xa.proref = xb.proref  "
				+ "where (((xa.proref) = a.proref)) ) as STOQTE,b.UNIUTI,a.LIECOD  from STOLIE  a LEFT JOIN SDTPRA  b ON a.proref = b.proref "
				+ "where a.LIECOD in (" + liecod + ") GROUP BY b.UNIUTI, a.proref,a.liecod HAVING (((a.proref)='"
				+ proref + "')) order by STOQTE desc";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("STOQTE", rs.getString("STOQTE"));
				x.put("UNIUTI", rs.getString("UNIUTI"));
				x.put("LIECOD", rs.getString("LIECOD"));
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
	
	public List<HashMap<String, String>> getStockPintura(List<HashMap<String, String>> data, String url) throws SQLException {

		HashMap<String, String> firstMap = data.get(0);
		String proref = firstMap.get("proref");
		if (proref == null)
			proref = "";
		String query = "select (select SUM(xa.STOQTE) from STOLIE  xa LEFT JOIN SDTPRA  xb ON xa.proref = xb.proref  "
				+ "where (((xa.proref) = a.proref)) ) as STOQTE,b.UNIUTI,a.LIECOD,ISNULL(STRING_AGG(nullif(EMPCOD,'') ,';')  WITHIN GROUP (ORDER BY EMPCOD),'s/ localização') EMPCOD  from STOLIE  a LEFT JOIN STODET c on a.LIECOD = c.liecod  and a.PROREF = c.proref LEFT JOIN SDTPRA  b ON a.proref = b.proref "
				+ "where a.LIECOD in (select COD_ARMAZEM from SGIID.dbo.PIN_DIC_ARMAZEM) GROUP BY b.UNIUTI, a.proref,a.liecod HAVING (((a.proref)='"
				+ proref + "')) order by STOQTE desc";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("STOQTE", rs.getString("STOQTE"));
				x.put("UNIUTI", rs.getString("UNIUTI"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
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

	public List<HashMap<String, String>> getArmazemStockPintura(List<HashMap<String, String>> data, String url) throws SQLException {

		HashMap<String, String> firstMap = data.get(0);
		String proref = firstMap.get("proref");
		if (proref == null)
			proref = "";
		String query = "select a.proref, (select SUM(xa.STOQTE) from STOLIE  xa where (((xa.proref) = a.proref)) ) as STOQTE,a.UNIUTI,a.LIECOD, "
				+ "ISNULL(STRING_AGG(nullif(EMPCOD,'') ,';')  WITHIN GROUP (ORDER BY EMPCOD),'s/ localização') EMPCOD "
				+ "FROM ( "
				+ "SELECT a.proref,EMPCOD,UNIUTI,a.LIECOD from STOLIE  a "
				+ "LEFT JOIN STODET c on a.LIECOD = c.liecod  and a.PROREF = c.proref "
				+ "LEFT JOIN SDTPRA  b ON a.proref = b.proref "
				+ "where a.LIECOD in (select COD_ARMAZEM from SGIID.dbo.PIN_DIC_ARMAZEM) "
				+ " and lotqte <> 0 and a.proref ='"+proref+"' "
				+ "GROUP BY b.UNIUTI, a.proref,a.liecod,EMPCOD "
				+ ")a "
				+ "GROUP BY a.UNIUTI, a.proref,a.liecod "
				+ "order by STOQTE desc";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("STOQTE", rs.getString("STOQTE"));
				x.put("UNIUTI", rs.getString("UNIUTI"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
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
	
	public List<HashMap<String, String>> getComponentes(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2,UNISTO from SDTPRA where ACHFAMCOD='C001' AND ACHFASCOD IN ('CM04','CM05','CM06')";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
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
	
	public List<HashMap<String, String>> getComponentesPintura3(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2,UNISTO from SDTPRA where  Protypcod='PSOP' and Gescod='OFPP' and Datann is null";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
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
	
	

	public List<HashMap<String, String>> getComponentesPintura(String url) throws SQLException {

		String query = "select distinct a.PROREF,PRODES1,PRODES2,UNISTO from SDTPRA  a where ACHFAMCOD='C001' AND ACHFASCOD IN ('CM20') ORDER BY PROREF";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
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
	
	public List<HashMap<String, String>> getTiposAcabamento(String url,String tipo) throws SQLException {

		String query = "select k.zpavallib TipoAcabamento,zpaval  "
				+ "	from SILVER_BI.dbo.SDTZPA t "
				+ "	LEFT JOIN SILVER_BI.dbo.SPAZPL k ON t.zpacod = k.zpacod AND t.zpaval = k.zpavalcod  "
				+ "	WHERE t.zpacod='ACAB' AND zpavallib IS NOT NULL and zpaval like '"+tipo+"%' "
				+ "	GROUP BY zpavallib,zpaval "
				+ "	ORDER BY zpavallib";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("TipoAcabamento", rs.getString("TipoAcabamento"));
				x.put("zpaval", rs.getString("zpaval"));
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

	public List<HashMap<String, String>> getComponentesPintura2(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2,UNISTO,LIECOD,STRING_AGG(CASE WHEN EMPCOD = '' THEN 's/ localização' ELSE EMPCOD END ,';')  WITHIN GROUP (ORDER BY EMPCOD) EMPCOD from ("
				+ "select distinct a.PROREF,PRODES1,PRODES2,UNISTO,LIECOD,EMPCOD  from SDTPRA  a "
				+ "LEFT JOIN STODET b on a.PROREF = b.PROREF "
				+ "where  a.gescod='OFPP' "
				+ ") tab "
				+ "GROUP BY PROREF,PRODES1,PRODES2,UNISTO,LIECOD "
				+ "ORDER BY PROREF";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
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
	
	public List<HashMap<String, String>> getFamilias(String url) throws SQLException {

		String query = "select DISTINCT LEFT(QUACOD,2) as fam,(SELECT q.OPEDES FROM sdtopp q WHERE q.opecod=(LEFT(quacod,2)) ) as QUALIB from SPAQUA";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("fam", rs.getString("fam"));
				x.put("QUALIB", rs.getString("QUALIB"));
				// x.put("SSEDES", rs.getString("SSEDES"));
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

	public List<HashMap<String, String>> getDefeitos(String url) throws SQLException {

		String query = "select DISTINCT QUACOD defeito,QUALIB descricao from SPAQUA";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("defeito", rs.getString("defeito"));
				x.put("descricao", rs.getString("descricao"));
				// x.put("SSEDES", rs.getString("SSEDES"));
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

	public List<HashMap<String, String>> getComponentesDOSGIID(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2,UNISTO from SDTPRA where ACHFAMCOD='C001' AND ACHFASCOD IN ('CM04','CM05','CM06') AND PROREF in (select COD_REF from SGIID.dbo.AB_DIC_COMPONENTE WHERE COD_REF is not null and COD_REF != '' )";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
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

	
	public List<HashMap<String, String>> getReferenciasBudget(String url) throws SQLException {

		String query = "select PROREF,PRODES1 from SDTPRA po where po.protypcod IN ('PF','PCF','PFI') ORDER BY PROREF";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1")); 
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

	
	public List<HashMap<String, String>> getReferencias(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRDFAMCOD from SDTPRA po where po.protypcod IN ('COM','PF','PCF','PSOF','PSO','COMS','EMBA','PFI') ORDER BY PROREF";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("FAMCOD", rs.getString("PRDFAMCOD"));
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

	public List<HashMap<String, String>> getReferencia(String PROREF, String url) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,a.PRODES2,a.UNISTO,p.prpprx from SDTPRA a LEFT JOIN SILVER_BI.dbo.SDTPRC x   on x.proref = a.PROREF "
				+ "LEFT JOIN SILVER_BI.dbo.SDTPRP p ON x.indnumenr = p.indnumenr and p.prptyp=7 "
				+ "	and p.prpdatvld=(SELECT top 1 p1.prpdatvld  FROM SILVER_BI.dbo.SDTPRP p1 WHERE p1.indnumenr = x.indnumenr AND p1.prpdatvld<= GETDATE() ORDER BY p1.prpdatvld desc) where a.PROREF = '" + PROREF + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
				x.put("PRECO_MATERIAL", rs.getString("prpprx"));
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

	public List<HashMap<String, String>> getReferenciasMANU(String url) throws SQLException {

		String query = "select po.PROREF,PRODES1,STOCK_TOTAL from SDTPRA po left join(SELECT PROREF, sum(lotqte) STOCK_TOTAL FROM stodet "
				+ " GROUP BY proref ) pa on po.proref = pa.PROREF where po.protypcod IN ('MANU') ORDER BY po.PROREF";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("STOCK_TOTAL", rs.getString("STOCK_TOTAL"));

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

	public List<HashMap<String, String>> getReferenciasSTOCK(String url, String proref) throws SQLException {

		String query = "SELECT proref Referencia, liecod Armazem, empcod Localizacao, sum(lotqte) stock FROM stodet where proref = '"
				+ proref + "' and lotqte <> 0  " + "GROUP BY proref, liecod, empcod ORDER BY proref, liecod, empcod";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("Referencia", rs.getString("Referencia"));
				x.put("Armazem", rs.getString("Armazem"));
				x.put("Localizacao", rs.getString("Localizacao"));
				x.put("stock", rs.getString("stock"));

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

	public List<HashMap<String, String>> getComponentesTodos(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2,UNISTO from SDTPRA where protypcod in ('PF','COM','PSO','PCF','PFI')";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("UNISTO", rs.getString("UNISTO"));
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

	public List<HashMap<String, String>> getComponentesProducao(String url) throws SQLException {

		String query = "select PROREF,PRODES1,PRODES2 from SDTPRA where gescod in ('OFCF','OFCF2')";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
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

	public List<HashMap<String, String>> getComponentesSaoBento(String url) throws SQLException {

		String query = "SELECT DISTINCT c.PROREF, r1.PRODES1, r1.PRODES2 FROM SILVER_BI.dbo.xcoupon c "
				+ "INNER JOIN SILVER.dbo.SDTPRA r ON c.prorefout = r.proref "
				+ "LEFT JOIN SILVER.dbo.SDTPRA r1 ON c.proref = r1.proref "
				+ "WHERE r.GESCOD IN ('OFCR','OFM','OFP','OFPI')";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
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

	public List<HashMap<String, String>> getComponentesProjeto(String url) throws SQLException {

		String query = "EXEC GET_COMPONENTES_PROJETO";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
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

	public List<HashMap<String, String>> getEtiquetas(String url, String PROREF) throws SQLException {

		String query = "SELECT a.etqnum, a.liecod,a.etqembqte,a.unicod,a.etqsitsto,a.etqetat,a.etqorilot1,a.datcre ,a.PROREF,b.PRODES1,a.EMPCOD "
				+ "FROM setqde a inner join SDTPRA b on a.PROREF = b.PROREF WHERE a.etqetat=1 AND  a.ETQSITSTO = 2 AND a.PROREF = '"
				+ PROREF + "' ORDER BY a.etqnum";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("etqnum", rs.getString("etqnum"));
				x.put("liecod", rs.getString("liecod"));
				x.put("etqembqte", rs.getString("etqembqte"));
				x.put("unicod", rs.getString("unicod"));
				x.put("etqsitsto", rs.getString("etqsitsto"));
				x.put("etqetat", rs.getString("etqetat"));
				x.put("etqorilot1", rs.getString("etqorilot1"));
				x.put("datcre", rs.getString("datcre"));
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
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

	public List<HashMap<String, String>> getDadosEtiqueta(String url, String etiqueta) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,b.ETQEMBQTE,a.UNISTO,b.VA1REF,b.VA2REF,b.UNICOD,b.EMPCOD,b.ETQORILOT1,b.LIECOD, b.INDREF,b.ETQNUMENR ,c.LOTNUMENR,b.INDNUMENR,b.DATCRE,b.ETQORIQTE1 "
				+ " ,(SELECT count(*) FROM stodet x WHERE x.proref= a.PROREF AND x.LIECOD='DPCHI' AND x.empcod='ZONQUA' AND x.EMPCOD != b.EMPCOD AND lotqte>0 ) TOTAL_ZONQUA "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.ETQNUM = '" + etiqueta + "' and b.ETQETAT = 1 and b.ETQSITSTO=2 ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("ETQEMBQTE", rs.getString("ETQEMBQTE"));
				x.put("UNISTO", rs.getString("UNISTO"));
				x.put("VA1REF", rs.getString("VA1REF"));
				x.put("VA2REF", rs.getString("VA2REF"));
				x.put("UNICOD", rs.getString("UNICOD"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
				x.put("ETQORILOT1", rs.getString("ETQORILOT1"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("LOTNUMENR", rs.getString("LOTNUMENR"));
				x.put("INDREF", rs.getString("INDREF"));
				x.put("ETQNUMENR", rs.getString("ETQNUMENR"));
				x.put("INDNUMENR", rs.getString("INDNUMENR"));
				x.put("DATCRE", rs.getString("DATCRE"));
				x.put("ETQORIQTE1", rs.getString("ETQORIQTE1"));
				x.put("TOTAL_ZONQUA", rs.getString("TOTAL_ZONQUA"));
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

	public List<HashMap<String, String>> getDadosEtiquetabyREF(String url, String PROREF) throws SQLException {

		String query = "select  b.ETQNUM,a.PROREF,a.PRODES1,b.ETQEMBQTE,a.UNISTO,b.VA1REF,b.VA2REF,b.UNICOD,b.EMPCOD,b.ETQORILOT1,b.LIECOD, b.INDREF,b.ETQNUMENR ,c.LOTNUMENR,b.INDNUMENR,b.DATCRE,b.ETQORIQTE1 "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.PROREF = '" + PROREF
				+ "' and b.ETQEMBQTE > 0 and b.ETQETAT = 1 and b.ETQSITSTO=2  order by b.DATCRE";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("ETQEMBQTE", rs.getString("ETQEMBQTE"));
				x.put("UNISTO", rs.getString("UNISTO"));
				x.put("VA1REF", rs.getString("VA1REF"));
				x.put("VA2REF", rs.getString("VA2REF"));
				x.put("UNICOD", rs.getString("UNICOD"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
				x.put("ETQORILOT1", rs.getString("ETQORILOT1"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("LOTNUMENR", rs.getString("LOTNUMENR"));
				x.put("INDREF", rs.getString("INDREF"));
				x.put("ETQNUMENR", rs.getString("ETQNUMENR"));
				x.put("INDNUMENR", rs.getString("INDNUMENR"));
				x.put("ETQNUM", rs.getString("ETQNUM"));
				x.put("DATCRE", rs.getString("DATCRE"));
				x.put("ETQORIQTE1", rs.getString("ETQORIQTE1"));

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

	public List<HashMap<String, String>> getDadosEtiquetabyREFcisterna(String url, String PROREF, String PROREF2)
			throws SQLException {

		String query = "select  b.ETQNUM,a.PROREF,a.PRODES1,b.ETQEMBQTE,a.UNISTO,b.VA1REF,b.VA2REF,b.UNICOD,b.EMPCOD,b.ETQORILOT1,b.LIECOD, b.INDREF,b.ETQNUMENR ,c.LOTNUMENR,b.INDNUMENR,b.DATCRE,b.ETQORIQTE1 "
				+ ", CASE WHEN b.PROREF = '" + PROREF + "' THEN 1 ELSE 2 END as valorordem " + "from  SDTPRA a "
				+ "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.PROREF in ('" + PROREF + "','" + PROREF2
				+ "') and b.ETQEMBQTE > 0 and b.ETQETAT = 1 and b.ETQSITSTO=2 order by valorordem, b.DATCRE";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("ETQEMBQTE", rs.getString("ETQEMBQTE"));
				x.put("UNISTO", rs.getString("UNISTO"));
				x.put("VA1REF", rs.getString("VA1REF"));
				x.put("VA2REF", rs.getString("VA2REF"));
				x.put("UNICOD", rs.getString("UNICOD"));
				x.put("EMPCOD", rs.getString("EMPCOD"));
				x.put("ETQORILOT1", rs.getString("ETQORILOT1"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("LOTNUMENR", rs.getString("LOTNUMENR"));
				x.put("INDREF", rs.getString("INDREF"));
				x.put("ETQNUMENR", rs.getString("ETQNUMENR"));
				x.put("INDNUMENR", rs.getString("INDNUMENR"));
				x.put("ETQNUM", rs.getString("ETQNUM"));
				x.put("DATCRE", rs.getString("DATCRE"));
				x.put("ETQORIQTE1", rs.getString("ETQORIQTE1"));

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

	public List<HashMap<String, String>> getUsers(String url) throws SQLException {

		String query = "select * from SDTRES  where RESTYPCOD = 'MO' ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
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

	public List<HashMap<String, String>> getClientes(String url) throws SQLException {

		String query = "select b.CLICOD,b.ADRNOM,b.ADRTVAIDE from SDTCLI a inner join SDTCLE b on a.CLICOD = b.CLICOD where b.ETSNUM = ''";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("ADRTVAIDE", rs.getString("ADRTVAIDE"));
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

	public List<HashMap<String, String>> getMoradas(String url, String ID) throws SQLException {

		String query = "Select CLICOD,ETSNUM,ADRLIB2,ADRLIB3,ADRLIB1,ADRBDI,ADRNOM,adrcdp+' - '+adrbdi as LOCATION from SDTCLE where CLICOD = '" + ID
				+ "' and ETSNUM like 'E%'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("ADRLIB3", rs.getString("ADRLIB3"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("ADRBDI", rs.getString("ADRBDI"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("LOCATION", rs.getString("LOCATION"));
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
	
	public List<HashMap<String, String>> getMoradasTodas(String url) throws SQLException {

		String query = "Select CLICOD,ETSNUM,ADRLIB2,ADRLIB3,ADRLIB1,ADRBDI,ADRNOM,adrcdp+' - '+adrbdi as LOCATION from SDTCLE where ETSNUM like 'E%'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("ADRLIB3", rs.getString("ADRLIB3"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("ADRBDI", rs.getString("ADRBDI"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("LOCATION", rs.getString("LOCATION"));
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

	public List<HashMap<String, String>> getMoradasReferencia(String url, String referencia) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,a.PRODES2,d.PRDFAMCOD,c.CLICOD,c.ETSNUM,ADRLIB2,ADRLIB3,ADRLIB1,ADRBDI,ADRNOM , CASE WHEN c.ETSNUM like 'E%' THEN 'MORADA' ELSE 'CLIENTE' END TIPO "
				+ "from SDTPRA a left join SDTCLP b on a.PROREF = b.PROREF "
				+ "left join SDTCLE c on b.CLICOD = c.CLICOD and (b.ETSNUM = c.ETSNUM or c.ETSNUM = '') left join SDTPRA d on a.PROREF = d.PROREF "
				+ "where a.PROREF = '" + referencia + "' and c.CLICOD is not null ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("ADRLIB3", rs.getString("ADRLIB3"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("ADRBDI", rs.getString("ADRBDI"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("TIPO", rs.getString("TIPO"));
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

	public List<HashMap<String, String>> verificaOFNUM(String url, String ofnum) throws SQLException {

		String query = "select b.PROREF,(b.OFBQTEINI - b.OFBQTEREA) as QUANTIDADE,a.OFDATFP,a.DATCRE "
				+ "from SOFA  a inner join SOFB b on a.ofanumenr = b.ofanumenr where b.ofetat != '4' and a.OFNUM ='"
				+ ofnum + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("QUANTIDADE", rs.getString("QUANTIDADE"));
				x.put("OFDATFP", rs.getString("OFDATFP"));
				x.put("DATCRE", rs.getString("DATCRE"));
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

	public List<HashMap<String, String>> getEncomendasCliente(String url, String PROREF, String data, String datafim)
			throws SQLException {

		String query = "select d.CDDCHRONO,b.PROREF,c.ADRNOM,c.ETSNUM,c.ADRLIB1,c.ADRLIB2,c.CLICOD, (d.CDDQTC-d.CDDQTL) as QUANTIDADE,d.CDDDATBES from SVCEBA a "
				+ "inner join SVCPBA b on a.CDENUMENR = b.CDENUMENR "
				+ "inner join SDTCLE c on a.CLICODLIV = c.CLICOD and a.ETSNUMLIV = c.ETSNUM "
				+ "inner join SVCPDL d on b.CDLNUMENR = d.CDLNUMENR " + "where b.PROREF = '" + PROREF
				+ "'  and  (d.CDDQTC-d.CDDQTL) > 0 and d.CDDLIVCOD in (3,4,5) and d.CDDETAT = 0 "
				// + "and d.CDDDATBES > '"+ data + "' "
				+ "and d.CDDDATBES <= '" + datafim + "' order by d.CDDDATBES";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("QUANTIDADE", rs.getString("QUANTIDADE"));
				x.put("CDDDATBES", rs.getString("CDDDATBES"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("CDDCHRONO", rs.getString("CDDCHRONO"));
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

	public List<HashMap<String, String>> getEnviado(String url, String PROREF, String data, String datafim)
			throws SQLException {

		String query = "Select b.PROREF,a.BLNUM,b.LIPQTL,a.LIVDATDEP,a.LIVDATREC,c.ADRNOM,c.ADRLIB1,c.ADRLIB2,c.ETSNUM,c.CLICOD from SVLEBA a "
				+ "inner join SVLPBA b on a.LIVNUMENR = b.LIVNUMENR "
				+ "inner join SDTCLE c on a.CLICODLIV = c.CLICOD and a.ETSNUMLIV = c.ETSNUM " + "where b.PROREF = '"
				+ PROREF + "'  and a.BLNUM like 'GR%' and LIVDATDEP > '" + data + "' and LIVDATDEP <= '" + datafim
				+ "' order by LIVDATDEP DESC";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("BLNUM", rs.getString("BLNUM"));
				x.put("LIPQTL", rs.getString("LIPQTL"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("LIVDATDEP", rs.getString("LIVDATDEP"));
				x.put("LIVDATREC", rs.getString("LIVDATREC"));
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

	public List<HashMap<String, String>> getEnviosGarantidos(String url, String PROREF, String data, String datafim,
			String CLICODLIV, String ETSNUM) throws SQLException {

		String query = "Select b.PROREF,a.BLNUM,b.LIPQTL,a.LIVDATDEP,a.LIVDATREC,c.ADRNOM,c.ADRLIB1,c.ADRLIB2,c.ETSNUM,c.CLICOD from SVLEBA a "
				+ "inner join SVLPBA b on a.LIVNUMENR = b.LIVNUMENR "
				+ "inner join SDTCLE c on a.CLICODLIV = c.CLICOD and a.ETSNUMLIV = c.ETSNUM " + "where ((not ('"
				+ PROREF + "' != '0' )) or (b.PROREF = '" + PROREF + "')) "
				+ "and a.BLNUM like 'GR%' and LIVDATDEP >= '" + data + "' and LIVDATDEP <= '" + datafim + "' "
				+ "and ((not ('" + CLICODLIV + "' != 'null' )) or (CLICODLIV = " + CLICODLIV + ")) and ((not ('"
				+ ETSNUM + "' != 'null' )) or (ETSNUM = '" + ETSNUM + "')) " + "order by LIVDATDEP DESC";

		System.out.println(query);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("BLNUM", rs.getString("BLNUM"));
				x.put("LIPQTL", rs.getString("LIPQTL"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("LIVDATDEP", rs.getString("LIVDATDEP"));
				x.put("LIVDATREC", rs.getString("LIVDATREC"));
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

	public List<HashMap<String, String>> getPlaneado(String url, String PROREF) throws SQLException {

		String query = "Select a.OFNUM,b.PROREF,a.OFDATFP,(b.OFBQTEINI - b.OFBQTEREA) as QUANTIDADE from SOFA a "
				+ "inner join SOFB b on a.OFANUMENR = b.OFANUMENR " + "where b.PROREF = '" + PROREF
				+ "' and (b.OFBQTEINI - b.OFBQTEREA) > 0 and b.OFETAT != 4 ORDER BY a.OFDATFP ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("OFNUM", rs.getString("OFNUM"));
				x.put("PROREF", rs.getString("PROREF"));
				x.put("OFDATFP", rs.getString("OFDATFP"));
				x.put("QUANTIDADE", rs.getString("QUANTIDADE"));
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

	public List<HashMap<String, String>> getStock2(String url, String PROREF) throws SQLException {

		String query = "Select a.PROREF,a.LIECOD,a.STOQTE from STOLIE a " + "where a.PROREF = '" + PROREF
				+ "' and a.STOQTE <> 0 order by a.LIECOD";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("STOQTE", rs.getString("STOQTE"));
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

	public List<HashMap<String, String>> validalote(String url, String LOTE) throws SQLException {

		String query = "select LOTREF from STOLOT where LOTREF = '" + LOTE + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("LOTREF", rs.getString("LOTREF"));
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

	public List<HashMap<String, String>> validaEtiqueta(String url, String ETIQUETA) throws SQLException {

		String query = "select ETQNUM from SETQDE where ETQNUM = '" + ETIQUETA + "' ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("ETQNUM", rs.getString("ETQNUM"));
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

	public List<HashMap<String, String>> validaGuia(String url, String GUIA) throws SQLException {

		String query = "select BLNUM from SVLEBA where BLNUM = '" + GUIA + "' ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("BLNUM", rs.getString("BLNUM"));
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

	public List<HashMap<String, String>> getDadosporEtiqueta(String url, String etiqueta) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,b.ETQEMBQTE,b.ETQORILOT1,b.LIECOD,b.ETQORIQTE1 ,b.ETQNUM  "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.ETQNUM = '" + etiqueta + "' ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("ETQEMBQTE", rs.getString("ETQEMBQTE"));
				x.put("ETQORILOT1", rs.getString("ETQORILOT1"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("ETQORIQTE1", rs.getString("ETQORIQTE1"));
				x.put("ETQNUM", rs.getString("ETQNUM"));
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

	public List<HashMap<String, String>> getDadosporGuia(String url, String guia) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,b.ETQEMBQTE,b.ETQORILOT1,b.LIECOD,b.ETQORIQTE1 ,b.ETQNUM  "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "inner join SVLEBA d on d.BLNUM = b.ETQCONDOC " + "where d.BLNUM = '" + guia + "' ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("ETQEMBQTE", rs.getString("ETQEMBQTE"));
				x.put("ETQORILOT1", rs.getString("ETQORILOT1"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("ETQORIQTE1", rs.getString("ETQORIQTE1"));
				x.put("ETQNUM", rs.getString("ETQNUM"));
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

	public List<HashMap<String, String>> getDadosporLote(String url, String lote) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,b.ETQEMBQTE,b.ETQORILOT1,b.LIECOD,b.ETQORIQTE1 ,b.ETQNUM  "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.ETQORILOT1 = '" + lote + "'";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("ETQEMBQTE", rs.getString("ETQEMBQTE"));
				x.put("ETQORILOT1", rs.getString("ETQORILOT1"));
				x.put("LIECOD", rs.getString("LIECOD"));
				x.put("ETQORIQTE1", rs.getString("ETQORIQTE1"));
				x.put("ETQNUM", rs.getString("ETQNUM"));
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

	public List<HashMap<String, String>> getComponentesdoCliente(String url, String CLICOD, String ETSNUM)
			throws SQLException {

		String query = "select a.PROREF,a.PRODES1,a.PRODES2,d.PRDFAMCOD from SDTPRA a "
				+ "inner join SDTCLP b on a.PROREF = b.PROREF "
				+ "inner join SDTCLE c on b.CLICOD = c.CLICOD and b.ETSNUM = c.ETSNUM "
				+ "left join SDTPRA d on a.PROREF = d.PROREF " + "where c.CLICOD = '" + CLICOD + "' and c.ETSNUM = '"
				+ ETSNUM + "' and d.protypcod in ('PF','COM','PSO','PCF','PSOF','PFI')";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("FAMCOD", rs.getString("PRDFAMCOD"));
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

	public List<HashMap<String, String>> getMoradaClientePorReferencia(String url, String PROREF) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,a.PRODES2,d.PRDFAMCOD,c.CLICOD,c.ETSNUM,ADRLIB2,ADRLIB3,ADRLIB1,ADRBDI,ADRNOM"
				+ ", x.NOME_CLIENTE " + "from SDTPRA a  "
				+ "inner join SDTCLP b on a.PROREF = b.PROREF inner join SDTCLE c on b.CLICOD = c.CLICOD and b.ETSNUM = c.ETSNUM "
				+ "left join SDTPRA d on a.PROREF = d.PROREF  "
				+ "left join (select xa.ADRNOM as NOME_CLIENTE,CLICOD  from SDTCLE xa where  xa.ETSNUM = '') x on x.CLICOD = c.CLICOD  "
				+ "where a.PROREF = '" + PROREF + "' and c.ETSNUM like 'E%' " + "order by x.NOME_CLIENTE ";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();
				x.put("PROREF", rs.getString("PROREF"));
				x.put("PRODES1", rs.getString("PRODES1"));
				x.put("PRODES2", rs.getString("PRODES2"));
				x.put("FAMCOD", rs.getString("PRDFAMCOD"));
				x.put("CLICOD", rs.getString("CLICOD"));
				x.put("ETSNUM", rs.getString("ETSNUM"));
				x.put("ADRLIB2", rs.getString("ADRLIB2"));
				x.put("ADRLIB3", rs.getString("ADRLIB3"));
				x.put("ADRLIB1", rs.getString("ADRLIB1"));
				x.put("ADRBDI", rs.getString("ADRBDI"));
				x.put("ADRNOM", rs.getString("ADRNOM"));
				x.put("NOME_CLIENTE", rs.getString("NOME_CLIENTE"));
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

	public List<HashMap<String, String>> getCartelas(String url, List<HashMap<String, String>> data)
			throws SQLException {
		HashMap<String, String> firstMap = data.get(0);

		String query = "select top 10000 *,c.DESCRICAO as DESCRICAO_HIST from CART_CAB a inner join CART_LINHA_ARTIGO b on a.ID_CART_CAB = b.ID_CART_CAB "
				+ "inner join CART_LINHA_HISTORICO c on a.ID_CART_CAB = c.ID_CART_CAB inner join CART_LINHA_RACK d on a.ID_CART_CAB = d.ID_CART_CAB "
				+ "inner join CART_LOAD_RAW e on b.ID_CART_LINHA_ARTIGO = e.ID_CART_LINHA_ARTIGO inner join CART_LOAD_USER f on a.ID_CART_CAB = f.ID_CART_CAB "
				+ "where  ((not ('" + firstMap.get("utilizador") + "' != 'null' and '" + firstMap.get("utilizador")
				+ "' != '')) or (f.LOAD_USER = '" + firstMap.get("utilizador") + "')) and " + "((not ('"
				+ firstMap.get("ref") + "' != 'null' and '" + firstMap.get("ref") + "' != '')) or (b.ID_ARTIGO like '%"
				+ firstMap.get("ref") + "%')) and " + "((not ('" + firstMap.get("lote") + "' != 'null' and '"
				+ firstMap.get("lote") + "' != '')) or (a.CARREGAR_ID = '" + firstMap.get("lote") + "')) and "
				+ "((not ('" + firstMap.get("tina") + "' != 'null' and '" + firstMap.get("tina")
				+ "' != '')) or (c.TINA = '" + firstMap.get("tina") + "'))";
		/*
		 * String query = "select * from " +
		 * "(select a.*,c.DURACAO,c.LIMITE_FIM,c.LIMITE_INCIO,c.SEQUENCIA,c.TEMPERATURA_BANHO,c.TEMPO_FIM,c.TINA,c.DESCRICAO as DESCRICAO_HIST  from CART_CAB a inner join CART_LINHA_HISTORICO c on a.ID_CART_CAB = c.ID_CART_CAB) t, "
		 * +
		 * "(select c.ID_ARTIGO, c.LOAD_PIECES,f.* from CART_CAB f inner join CART_LINHA_ARTIGO b on  f.ID_CART_CAB = b.ID_CART_CAB inner join CART_LINHA_ARTIGO c on b.ID_CART_LINHA_ARTIGO = c.ID_CART_LINHA_ARTIGO ) y, "
		 * +
		 * "(select c.* from CART_CAB g inner join CART_LINHA_RACK c on g.ID_CART_CAB = c.ID_CART_CAB ) u, "
		 * +
		 * "(select c.* from CART_CAB h inner join CART_LOAD_USER c on h.ID_CART_CAB = c.ID_CART_CAB ) i "
		 * +
		 * "where t.ID_CART_CAB = y.ID_CART_CAB and y.ID_CART_CAB = u.ID_CART_CAB and u.ID_CART_CAB = i.ID_CART_CAB and "
		 * + "((not ('"+firstMap.get("utilizador")+"' != 'null' and '"+firstMap.
		 * get("utilizador")+"' != '')) or (i.LOAD_USER = '"+firstMap.get(
		 * "utilizador")+"')) and " +
		 * "((not ('"+firstMap.get("ref")+"' != 'null' and '"+firstMap.get("ref"
		 * )+"' != '')) or (y.ID_ARTIGO like '%"+firstMap.get("ref")+"%')) and " +
		 * "((not ('"+firstMap.get("lote")+"' != 'null' and '"+firstMap.get(
		 * "lote")+"' != '')) or (t.CARREGAR_ID = '"+firstMap.get("lote") +"')) and " +
		 * "((not ('"+firstMap.get("tina")+"' != 'null' and '"+firstMap.get(
		 * "tina")+"' != '')) or (t.TINA = '"+firstMap.get("tina")+"'))";
		 */

		// System.out.println(query);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				// parser das opera��es
				HashMap<String, String> x = new HashMap<>();

				int count = metaData.getColumnCount();
				for (int i = 1; i <= count; i++) {
					String columnName = metaData.getColumnName(i);
					x.put(columnName, rs.getString(columnName));
				}
				// x.put("TITLE", rs.getString("TITLE"));
				// x.put("CARREGAR_ID", rs.getString("CARREGAR_ID"));
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