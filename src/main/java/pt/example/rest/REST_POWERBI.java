package pt.example.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

@Stateless
@Path("/sirb")
public class REST_POWERBI {

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	@GET
	@Path("/getembedUrl")
	@Produces("application/json")
	public Map<String, Object> getembedUrl() {
		try {
			return getembedUrlPOWERBI();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*public void main(String argv[]) {
		try {
			getembedUrlPOWERBI();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	public Map<String, Object> getembedUrlPOWERBI() throws JSONException {
		Query query_folder = entityManager.createNativeQuery(
				"select tenantId,clientId,clientSecret,username,password,resource,endpoint,report_id, group_id,dataset from BusinessAnalyticsReporting a");

		List<Object[]> dados_folder = query_folder.getResultList();

		String tenantId = "fbd0b0d4-bb8b-4937-8529-b52340c4d82d";
		String clientId = "f4627a15-0f72-4c00-862c-59ab8a5d89b7";
		String clientSecret = "8qFZD0+/FkqZqq4Cb6zrmp0FYtBN2uhta4RJPDnXSME=";
		String username = "bi@doureca.pt";
		String password = "*@43TyU7#";
		String resource = "https://analysis.windows.net/powerbi/api";
		String endpoint = "https://login.microsoftonline.com/common/oauth2/token";
		String report_id = "7b627422-2451-4ee5-9ecd-0c0aa4b39f12";
		String group_id = "94d876e1-2886-4c2b-b658-cb89c54af7dc";
		String dataset = "dd752bc0-38b7-42c9-ad26-0d3246ca9e6f";

		for (Object[] content : dados_folder) {

			tenantId = content[0].toString();
			clientId = content[1].toString();
			clientSecret = content[2].toString();
			username = content[3].toString();
			password = content[4].toString();
			resource = content[5].toString();
			endpoint = content[6].toString();
			report_id = content[7].toString();
			group_id = content[8].toString();
			dataset = content[9].toString();
		}

		Map<String, Object> token = null;
		try {
			String accessToken = getAccessToken(clientId, username, password, resource, endpoint);
			token = getResponse_embed(accessToken, group_id, report_id, username, dataset);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return token;
	}

	public String getAccessToken(String clientId, String username, String password, String resource,
			String endpoint) throws MalformedURLException, IOException {

		URL url = new URL(endpoint);
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("grant_type", "password");
		params.put("scope", "openid");
		params.put("client_id", clientId);
		params.put("username", username);
		params.put("password", password);
		params.put("resource", resource);

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		/*
		 * for (int c; (c = in.read()) >= 0;) System.out.print((char) c);
		 */

		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(conn.getInputStream());
		String accessToken = null;

		while (parser.nextToken() != JsonToken.END_OBJECT) {
			String name = parser.getCurrentName();
			if ("access_token".equals(name)) {
				parser.nextToken();
				accessToken = parser.getText();
			}
		}

		// System.out.print(accessToken);
		return accessToken;
	}

	public static Map<String, Object> getResponse_embed(String accessToken, String group_id, String report_id,
			String username, String dataset) throws MalformedURLException, IOException, JSONException {

		URL url = new URL("https://api.powerbi.com/v1.0/myorg/groups/" + group_id + "/reports");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		conn.setDoOutput(true);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		// for (int c; (c = in.read()) >= 0;) System.out.print((char) c);
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject jsonResponse = new JSONObject(response.toString());
		JSONArray valueResponse = jsonResponse.getJSONArray("value");

		JSONObject jsonValue = new JSONObject(valueResponse.get(0).toString());
		String value = jsonValue.getString("embedUrl");
		/*
		 * JsonFactory factory = new JsonFactory(); JsonParser parser =
		 * factory.createParser(conn.getInputStream());
		 */

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("embedUrl", value);
		params.put("report_id", report_id);
		params.put("accessToken", accessToken);

		return params;
	}

}
