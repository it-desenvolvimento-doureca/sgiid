package pt.example.bootstrap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.core.Response;

public class SomeQuarterlyJob{
	public static HashMap<String, String> cab;
	public static HashMap<String, String> artig;
	public static HashMap<String, String> rack;
	public static ArrayList<HashMap<String, String>> rack_geral;
	public static HashMap<String, String> hist;
	public static ArrayList<HashMap<String, String>> hist_geral;
	public static HashMap<String, String> road;
	public static ArrayList<HashMap<String, String>> road_geral;
	public static HashMap<String, String> user;
	public static ArrayList<HashMap<String, String>> user_geral;
	public static ArrayList<HashMap<String, String>> artig_geral;
	public static Integer id_artigo;



	public static Response importar(InputStream file) {
		return ler_ficheiro(file);
	}



	private static Response ler_ficheiro(InputStream FILE) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(FILE))) {
			cab = new HashMap<>();
			artig = new HashMap<>();
			rack = new HashMap<>();
			rack_geral = new ArrayList<>();
			hist = new HashMap<>();
			road = new HashMap<>();
			road_geral = new ArrayList<>();
			user = new HashMap<>();
			artig_geral = new ArrayList<>();
			user_geral = new ArrayList<>();
			hist_geral = new ArrayList<>();

			String sCurrentLine;
			Integer count = 1;
			id_artigo = 0;
			
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("****************************************")) {
					//count++;
				} else if(sCurrentLine.length() > 34){
					// System.out.println(count);
					switch (count) {
					case 1:
						// cabecalho
						cabecalho(sCurrentLine);
						/// utilizadores
						loaduser(sCurrentLine);
						//break;
					//case 2:
						// linhas artigos
						artigos(sCurrentLine);
						// load road
						loadroad(sCurrentLine);
					//	break;
					//case 3:
						// linhas racks
						racks(sCurrentLine);
						// cabecalho
						//cabecalho(sCurrentLine);
						//break;
					//case 4:
						// linhas historico
						historico(sCurrentLine);
						break;

					default:
						break;
					}
				}
			}

			Integer ID = (Integer) ConnectionSQL.inserir_CAB(cab, "CART_CAB", null, null);
			if (ID != null) {

				for (HashMap<String, String> objects : user_geral) {
					ConnectionSQL.inserir_CAB(objects, "CART_LOAD_USER", "ID_CART_CAB", ID);
				}

				for (HashMap<String, String> object2 : rack_geral) {
					ConnectionSQL.inserir_CAB(object2, "CART_LINHA_RACK", "ID_CART_CAB", ID);
				}

				for (HashMap<String, String> object3 : hist_geral) {
					ConnectionSQL.inserir_CAB(object3, "CART_LINHA_HISTORICO", "ID_CART_CAB", ID);
				}

				for (HashMap<String, String> object : artig_geral) {
					String id = object.get("ID");
					object.remove("ID");
					Integer id_linha = (Integer) ConnectionSQL.inserir_CAB(object, "CART_LINHA_ARTIGO", "ID_CART_CAB",
							ID);
					for (HashMap<String, String> result : road_geral) {
						if (result.get("ID") != null) {
							if (result.get("ID").equals(id)) {
								result.remove("ID");
								ConnectionSQL.inserir_CAB(result, "CART_LOAD_RAW", "ID_CART_LINHA_ARTIGO", id_linha);
							}
						}
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(200).entity("Erro Ao Importar").build();
		}
		 return Response.ok().entity("Ficheiro Importado").build();
	}

	public static void artigos(String linha) {
		String campo = (linha.trim().length() > 0) ? linha.substring(5, 34).trim() : "";

		String valor = (linha.trim().length() > 0) ? linha.substring(36, linha.length()).trim() : "";
		switch (campo) {
		case "ID Artigo":
			road = new HashMap<>();
			artig.put("ID_ARTIGO", valor);
			break;
		case "Load pieces":
			artig.put("LOAD_PIECES", valor);
			id_artigo++;
			artig.put("ID", id_artigo.toString());
			artig_geral.add(artig);
			artig = new HashMap<>();
			break;
		default:
			break;
		}
	}

	public static void loadroad(String linha) {
		String campo = (linha.trim().length() > 0) ? linha.substring(5, 13).trim() : "";

		String valor = (linha.trim().length() > 0) ? linha.substring(36, linha.length()).trim() : "";
		switch (campo) {
		case "Load raw":
			road.put("LOAD_RAW", valor);
			road.put("ID", id_artigo.toString());
			road_geral.add(road);
			road = new HashMap<>();
			break;
		default:
			break;
		}

	}

	public static void loaduser(String linha) {
		String campo = (linha.trim().length() > 0) ? linha.substring(5, 14).trim() : "";

		String valor = (linha.trim().length() > 0) ? linha.substring(36, linha.length()).trim() : "";
		switch (campo) {
		case "Load user":
			user.put("LOAD_USER", valor);
			user_geral.add(user);
			user = new HashMap<>();
			break;
		default:
			break;
		}
	}

	public static void historico(String linha) {
		if (linha.trim().length() > 0 && linha.substring(34, 35).equals("S")) {
			hist.put("DESCRICAO", linha.substring(5, 34).trim());
			hist.put("SEQUENCIA", linha.substring(36, 38));
			hist.put("TINA", linha.substring(49, 52));
			hist.put("TEMPO_INICIO", linha.substring(56, 64));
			
			Boolean vazio = false;
			if(linha.substring(68, 76).equals("--:--:--")){
				hist.put("TEMPO_FIM", linha.substring(56, 64));
				vazio = true;
			}else{
				hist.put("TEMPO_FIM", linha.substring(68, 76));
			}
			
			if(linha.substring(80, 81).equals("D")){
				if(vazio){
					hist.put("DURACAO", linha.substring(85, 93));
				}else{
					hist.put("DURACAO","00:00:00");
				}
				hist.put("LIMITE_INCIO", linha.substring(97, 105));
				hist.put("LIMITE_FIM", linha.substring(106, 114));
				
				if (linha.length() > 116) {
					if (linha.substring(115, 116).equals("T")) {
						hist.put("TEMPERATURA_BANHO", linha.substring(117, 118));
					}
				}			
			}else{
				if(vazio){
					hist.put("DURACAO", linha.substring(80, 88));
				}else{
					hist.put("DURACAO","00:00:00");
				}
				hist.put("LIMITE_INCIO", linha.substring(92, 100));
				hist.put("LIMITE_FIM", linha.substring(101, 109));
				
				if (linha.length() > 111) {
					if (linha.substring(110, 111).equals("T")) {
						hist.put("TEMPERATURA_BANHO", linha.substring(112, 118));
					}
				}
			}
			

			
			hist_geral.add(hist);
			hist = new HashMap<>();
		}
	}

	public static void racks(String linha) {
		String campo = (linha.trim().length() > 0) ? linha.substring(5, 34).trim() : "";

		String valor = (linha.trim().length() > 0) ? linha.substring(36, linha.length()).trim() : "";

		switch (campo) {
		case "Rack code":
			rack.put("RACK_CODE", valor);
			break;
		case "Rack type":
			rack.put("RACK_TYPE", valor);
			rack_geral.add(rack);
			rack = new HashMap<>();
			break;
		default:
			break;
		}
	}

	public static void cabecalho(String linha) {
		String campo = (linha.trim().length() > 0) ? linha.substring(5, 25).trim() : "";

		String valor = (linha.trim().length() > 0) ? linha.substring(36, linha.length()).trim() : "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(linha.substring(5, 9).equals("DH/I")){
			Date date = null;
			try {
				date = formatter.parse(linha.substring(22, 32));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cab.put("DATA_INICIO",  new java.sql.Date(date.getTime()).toString());
			cab.put("HORA_INICIO", linha.substring(10, 18));
		}
		if(linha.substring(33, 37).equals("DH/F")){
			Date date2 = null;
			try {
				date2 = formatter.parse(linha.substring(50, 60));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cab.put("DATA_FIM",  new java.sql.Date(date2.getTime()).toString());
			cab.put("HORA_FIM", linha.substring(38, 46));
		}
		
		switch (campo) {
		case "Title":
			cab.put("TITLE", valor);
			break;
		case "Carregar ID":
			cab.put("CARREGAR_ID", valor);
			break;
		case "Support program":
			cab.put("SUPPORT_PROGRAM", valor);
			break;
		case "Receita":
			cab.put("RECEITA", valor);
			break;
		case "Descrição":
			cab.put("DESCRICAO", valor);
			break;
		case "Suporte":
			cab.put("SUPORTE", valor);
			break;
		case "N. Manuten.":
			cab.put("N_MANUTEN", valor);
			break;
		case "PRETRATTAMENTO":
			cab.put("PRETRATTAMENTO", valor);
			break;
		case "COBRE":
			cab.put("COBRE", valor);
			break;
		case "NICHEL":
			cab.put("NICHEL", valor);
			break;
		case "NIQUEL FINITURA":
			cab.put("NIQUEL_FINITURA", valor);
			break;
		case "CROMAGEM":
			cab.put("CROMAGEM", valor);
			break;
		case "DESMETALLIZACAO":
			cab.put("DESMETALLIZACAO", valor);
			break;
		case "PECAS":
			cab.put("PECAS", valor);
			break;
		case "SUPERFICIE":
			cab.put("SUPERFICIE", valor);
			break;
		default:
			break;
		}
		
	}
}