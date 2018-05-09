package pt.example.bootstrap;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public List<HashMap<String, String>> getFornecedores(String url) throws SQLException {

		String query = "select FOUCOD,ADRNOM from SDTFOE where FOUETSNUM= ''";

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// Usa sempre assim que fecha os resources automaticamente
		try (Connection connection = getConnection(url);
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
		 * +PROREF+"' and a.OFNUM = '"+OF+"') as total, " +
		 * "b.INDNUMENR,c.NCLRANG " +
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

	public List<HashMap<String, String>> getStock(List<HashMap<String, String>> data, String url) throws SQLException {

		HashMap<String, String> firstMap = data.get(0);
		String proref = firstMap.get("proref");
		String liecod = firstMap.get("liecod");
		if (proref == null)
			proref = "";
		String query = "select SUM(a.STOQTE) as STOQTE,b.UNIUTI,a.LIECOD  from STOLIE  a LEFT JOIN SDTPRA  b ON a.proref = b.proref "
				+ "where a.LIECOD in (" + liecod + ") GROUP BY b.UNIUTI, a.proref,a.liecod HAVING (((a.proref)='"
				+ proref + "'))";

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

	public List<HashMap<String, String>> getDadosEtiqueta(String url, String etiqueta) throws SQLException {

		String query = "select a.PROREF,a.PRODES1,b.ETQEMBQTE,a.UNISTO,b.VA1REF,b.VA2REF,b.UNICOD,b.EMPCOD,b.ETQORILOT1,b.LIECOD, b.INDREF,b.ETQNUMENR ,c.LOTNUMENR,b.INDNUMENR,b.DATCRE "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.ETQNUM = '" + etiqueta + "'";

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

		String query = "select  b.ETQNUM,a.PROREF,a.PRODES1,b.ETQEMBQTE,a.UNISTO,b.VA1REF,b.VA2REF,b.UNICOD,b.EMPCOD,b.ETQORILOT1,b.LIECOD, b.INDREF,b.ETQNUMENR ,c.LOTNUMENR,b.INDNUMENR,b.DATCRE "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.PROREF = '" + PROREF + "' and b.ETQEMBQTE > 0 order by b.DATCRE";

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

	public List<HashMap<String, String>> getDadosEtiquetabyREFcisterna(String url, String PROREF) throws SQLException {

		String query = "select  b.ETQNUM,a.PROREF,a.PRODES1,b.ETQEMBQTE,a.UNISTO,b.VA1REF,b.VA2REF,b.UNICOD,b.EMPCOD,b.ETQORILOT1,b.LIECOD, b.INDREF,b.ETQNUMENR ,c.LOTNUMENR,b.INDNUMENR,b.DATCRE "
				+ "from  SDTPRA a " + "inner join  SETQDE b on a.PROREF= b.PROREF "
				+ "inner join  STOLOT c on b.INDNUMENR = c.INDNUMENR and b.ETQORILOT1 = c.LOTREF "
				+ "where b.PROREF = '" + PROREF + "' and b.ETQEMBQTE > 0 and b.ETQETAT = 1 order by b.DATCRE";

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
		 * )+"' != '')) or (y.ID_ARTIGO like '%"+firstMap.get("ref")+"%')) and "
		 * + "((not ('"+firstMap.get("lote")+"' != 'null' and '"+firstMap.get(
		 * "lote")+"' != '')) or (t.CARREGAR_ID = '"+firstMap.get("lote")
		 * +"')) and " +
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
				// parser das operações
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