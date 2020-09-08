package pt.example.rest;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pt.example.dao.PR_DIC_CAPACIDADE_ACABAMENTODao;
import pt.example.dao.PR_DIC_CAPACIDADE_RACKSDao;
import pt.example.dao.PR_DIC_PRODUCAO_SEMANADao;
import pt.example.dao.PR_DIC_SECTORES_AGREGADORESDao;
import pt.example.dao.PR_DIC_SECTORES_AGREGADORES_LINHADao;
import pt.example.dao.PR_DIC_SEMANAS_ANALISEDao;
import pt.example.dao.PR_DIC_VALIDACAO_BASTIDORDao;
import pt.example.dao.PR_GESTAO_BARRASDao;
import pt.example.dao.PR_GESTAO_BARRAS_REFERENCIASDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_ANALISESDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_CABDao;
import pt.example.dao.PR_PLANEAMENTO_PRODUCAO_LINHASDao;
import pt.example.entity.PR_DIC_CAPACIDADE_ACABAMENTO;
import pt.example.entity.PR_DIC_CAPACIDADE_RACKS;
import pt.example.entity.PR_DIC_PRODUCAO_SEMANA;
import pt.example.entity.PR_DIC_SECTORES_AGREGADORES;
import pt.example.entity.PR_DIC_SECTORES_AGREGADORES_LINHA;
import pt.example.entity.PR_DIC_SEMANAS_ANALISE;
import pt.example.entity.PR_DIC_VALIDACAO_BASTIDOR;
import pt.example.entity.PR_GESTAO_BARRAS;
import pt.example.entity.PR_GESTAO_BARRAS_REFERENCIAS;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_CAB;
import pt.example.entity.PR_PLANEAMENTO_PRODUCAO_LINHAS;

@Stateless
@Path("/sirb")
public class SIRB_2 {

	@Inject
	private PR_DIC_PRODUCAO_SEMANADao dao1;
	@Inject
	private PR_DIC_CAPACIDADE_ACABAMENTODao dao2;
	@Inject
	private PR_DIC_CAPACIDADE_RACKSDao dao3;
	@Inject
	private PR_DIC_SEMANAS_ANALISEDao dao4;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_CABDao dao5;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_LINHASDao dao6;
	@Inject
	private PR_DIC_SECTORES_AGREGADORES_LINHADao dao7;
	@Inject
	private PR_DIC_SECTORES_AGREGADORESDao dao8;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_ANALISESDao dao9;
	@Inject
	private PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSDao dao10;
	@Inject
	private PR_GESTAO_BARRASDao dao11;
	@Inject
	private PR_GESTAO_BARRAS_REFERENCIASDao dao12;
	@Inject
	private PR_DIC_VALIDACAO_BASTIDORDao dao13;

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	/************************************* PR_DIC_PRODUCAO_SEMANA */

	@POST
	@Path("/createPR_DIC_PRODUCAO_SEMANA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_PRODUCAO_SEMANA insertPR_DIC_PRODUCAO_SEMANAA(final PR_DIC_PRODUCAO_SEMANA data) {
		return dao1.create(data);
	}

	@GET
	@Path("/getPR_DIC_PRODUCAO_SEMANA")
	@Produces("application/json")
	public List<PR_DIC_PRODUCAO_SEMANA> getPR_DIC_PRODUCAO_SEMANA() {
		return dao1.getall();
	}

	@GET
	@Path("/getPR_DIC_PRODUCAO_SEMANAbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_PRODUCAO_SEMANA> getPR_DIC_PRODUCAO_SEMANAbyip(@PathParam("id") Integer id) {
		return dao1.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_PRODUCAO_SEMANA/{id}")
	public void deletePR_DIC_PRODUCAO_SEMANA(@PathParam("id") Integer id) {
		PR_DIC_PRODUCAO_SEMANA PR_DIC_PRODUCAO_SEMANA = new PR_DIC_PRODUCAO_SEMANA();
		PR_DIC_PRODUCAO_SEMANA.setID_PRODUCAO_SEMANA(id);
		dao1.delete(PR_DIC_PRODUCAO_SEMANA);
	}

	@PUT
	@Path("/updatePR_DIC_PRODUCAO_SEMANA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_PRODUCAO_SEMANA updatePR_DIC_PRODUCAO_SEMANA(final PR_DIC_PRODUCAO_SEMANA PR_DIC_PRODUCAO_SEMANA) {
		PR_DIC_PRODUCAO_SEMANA.setID_PRODUCAO_SEMANA(PR_DIC_PRODUCAO_SEMANA.getID_PRODUCAO_SEMANA());
		return dao1.update(PR_DIC_PRODUCAO_SEMANA);
	}

	/************************************* PR_DIC_CAPACIDADE_ACABAMENTO */

	@POST
	@Path("/createPR_DIC_CAPACIDADE_ACABAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_ACABAMENTO insertPR_DIC_CAPACIDADE_ACABAMENTOA(final PR_DIC_CAPACIDADE_ACABAMENTO data) {
		return dao2.create(data);
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_ACABAMENTO")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_ACABAMENTO> getPR_DIC_CAPACIDADE_ACABAMENTO() {
		return dao2.getall();
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_ACABAMENTObyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_ACABAMENTO> getPR_DIC_CAPACIDADE_ACABAMENTObyip(@PathParam("id") Integer id) {
		return dao2.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_CAPACIDADE_ACABAMENTO/{id}")
	public void deletePR_DIC_CAPACIDADE_ACABAMENTO(@PathParam("id") Integer id) {
		PR_DIC_CAPACIDADE_ACABAMENTO PR_DIC_CAPACIDADE_ACABAMENTO = new PR_DIC_CAPACIDADE_ACABAMENTO();
		PR_DIC_CAPACIDADE_ACABAMENTO.setID_CAPACIDADE_ACABAMENTO(id);
		dao2.delete(PR_DIC_CAPACIDADE_ACABAMENTO);
	}

	@PUT
	@Path("/updatePR_DIC_CAPACIDADE_ACABAMENTO")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_ACABAMENTO updatePR_DIC_CAPACIDADE_ACABAMENTO(
			final PR_DIC_CAPACIDADE_ACABAMENTO PR_DIC_CAPACIDADE_ACABAMENTO) {
		PR_DIC_CAPACIDADE_ACABAMENTO
				.setID_CAPACIDADE_ACABAMENTO(PR_DIC_CAPACIDADE_ACABAMENTO.getID_CAPACIDADE_ACABAMENTO());
		return dao2.update(PR_DIC_CAPACIDADE_ACABAMENTO);
	}

	/************************************* PR_DIC_CAPACIDADE_RACKS */

	@POST
	@Path("/createPR_DIC_CAPACIDADE_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_RACKS insertPR_DIC_CAPACIDADE_RACKSA(final PR_DIC_CAPACIDADE_RACKS data) {
		return dao3.create(data);
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_RACKS")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_RACKS> getPR_DIC_CAPACIDADE_RACKS() {
		return dao3.getall();
	}

	@GET
	@Path("/getPR_DIC_CAPACIDADE_RACKSbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_CAPACIDADE_RACKS> getPR_DIC_CAPACIDADE_RACKSbyip(@PathParam("id") Integer id) {
		return dao3.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_CAPACIDADE_RACKS/{id}")
	public void deletePR_DIC_CAPACIDADE_RACKS(@PathParam("id") Integer id) {
		PR_DIC_CAPACIDADE_RACKS PR_DIC_CAPACIDADE_RACKS = new PR_DIC_CAPACIDADE_RACKS();
		PR_DIC_CAPACIDADE_RACKS.setID_CAPACIDADE_RACKS(id);
		dao3.delete(PR_DIC_CAPACIDADE_RACKS);
	}

	@PUT
	@Path("/updatePR_DIC_CAPACIDADE_RACKS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_CAPACIDADE_RACKS updatePR_DIC_CAPACIDADE_RACKS(
			final PR_DIC_CAPACIDADE_RACKS PR_DIC_CAPACIDADE_RACKS) {
		PR_DIC_CAPACIDADE_RACKS.setID_CAPACIDADE_RACKS(PR_DIC_CAPACIDADE_RACKS.getID_CAPACIDADE_RACKS());
		return dao3.update(PR_DIC_CAPACIDADE_RACKS);
	}

	/************************************* PR_DIC_SEMANAS_ANALISE */

	@POST
	@Path("/createPR_DIC_SEMANAS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SEMANAS_ANALISE insertPR_DIC_SEMANAS_ANALISEA(final PR_DIC_SEMANAS_ANALISE data) {
		return dao4.create(data);
	}

	@GET
	@Path("/getPR_DIC_SEMANAS_ANALISE")
	@Produces("application/json")
	public List<PR_DIC_SEMANAS_ANALISE> getPR_DIC_SEMANAS_ANALISE() {
		return dao4.getall();
	}

	@GET
	@Path("/getPR_DIC_SEMANAS_ANALISEbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SEMANAS_ANALISE> getPR_DIC_SEMANAS_ANALISEbyip(@PathParam("id") Integer id) {
		return dao4.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_SEMANAS_ANALISE/{id}")
	public void deletePR_DIC_SEMANAS_ANALISE(@PathParam("id") Integer id) {
		PR_DIC_SEMANAS_ANALISE PR_DIC_SEMANAS_ANALISE = new PR_DIC_SEMANAS_ANALISE();
		PR_DIC_SEMANAS_ANALISE.setID_SEMANAS_ANALISE(id);
		dao4.delete(PR_DIC_SEMANAS_ANALISE);
	}

	@PUT
	@Path("/updatePR_DIC_SEMANAS_ANALISE")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SEMANAS_ANALISE updatePR_DIC_SEMANAS_ANALISE(final PR_DIC_SEMANAS_ANALISE PR_DIC_SEMANAS_ANALISE) {
		PR_DIC_SEMANAS_ANALISE.setID_SEMANAS_ANALISE(PR_DIC_SEMANAS_ANALISE.getID_SEMANAS_ANALISE());
		return dao4.update(PR_DIC_SEMANAS_ANALISE);
	}

	@GET
	@Path("/GET_QUANT_RACKS_BARRAS")
	@Produces("application/json")
	public List<Object[]> GET_QUANT_RACKS_BARRAS() {

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_QUANT_RACKS_BARRAS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@GET
	@Path("/GET_TIPO_ACABAMENTO")
	@Produces("application/json")
	public List<Object[]> GET_TIPO_ACABAMENTO() {

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_TIPO_ACABAMENTO]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_LISTA_MRPS")
	@Produces("application/json")
	public List<Object[]> GET_LISTA_MRPS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_LISTA_MRPS] '" + DATA + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_LISTA_MRPS_PARA_PRODUCAO")
	@Produces("application/json")
	public List<Object[]> GET_LISTA_MRPS_PARA_PRODUCAO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");
		String PRIMEIRA_SEMANA = firstMap.get("PRIMEIRA_SEMANA");
		String N_MRP = firstMap.get("N_MRP");
		String LINHA = firstMap.get("LINHA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_LISTA_MRPS_PARA_PRODUCAO] '" + DATA + "',"
				+ PRIMEIRA_SEMANA + "," + N_MRP + "," + LINHA + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_CAB */

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_CAB insertPR_PLANEAMENTO_PRODUCAO_CABA(final PR_PLANEAMENTO_PRODUCAO_CAB data) {
		return dao5.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_CAB")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getPR_PLANEAMENTO_PRODUCAO_CAB() {
		return dao5.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_CAB2")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getPR_PLANEAMENTO_PRODUCAO_CAB2() {
		return dao5.getall2();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_CABbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_CAB> getPR_PLANEAMENTO_PRODUCAO_CABbyip(@PathParam("id") Integer id) {
		return dao5.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_CAB/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_CAB(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_CAB PR_PLANEAMENTO_PRODUCAO_CAB = new PR_PLANEAMENTO_PRODUCAO_CAB();
		PR_PLANEAMENTO_PRODUCAO_CAB.setID_PLANEAMENTO_PRODUCAO_CAB(id);
		dao5.delete(PR_PLANEAMENTO_PRODUCAO_CAB);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_CAB updatePR_PLANEAMENTO_PRODUCAO_CAB(
			final PR_PLANEAMENTO_PRODUCAO_CAB PR_PLANEAMENTO_PRODUCAO_CAB) {
		PR_PLANEAMENTO_PRODUCAO_CAB
				.setID_PLANEAMENTO_PRODUCAO_CAB(PR_PLANEAMENTO_PRODUCAO_CAB.getID_PLANEAMENTO_PRODUCAO_CAB());
		return dao5.update(PR_PLANEAMENTO_PRODUCAO_CAB);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_LINHAS */

	@POST
	@Path("/getPR_PLANEAMENTO_PRODUCAO_LINHAS_FILTRO")
	@Consumes("*/*")
	@Produces("application/json")
	public List<Object[]> getPR_PLANEAMENTO_PRODUCAO_LINHAS_FILTRO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID_PLANEAMENTO_PRODUCAO_CAB = firstMap.get("ID_PLANEAMENTO_PRODUCAO_CAB");
		String FASE = firstMap.get("FASE");
		String COD_REF = firstMap.get("COD_REF");
		String ACABAMENTO = firstMap.get("ACABAMENTO");
		String RACK = firstMap.get("RACK");
		String ANTECEDENCIA = firstMap.get("ANTECEDENCIA");
		String BARRAS_CAPACIDADE = firstMap.get("BARRAS_CAPACIDADE");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_PR_PLANEAMENTO_PRODUCAO_LINHAS_FILTRO] "
				+ ID_PLANEAMENTO_PRODUCAO_CAB + ",'" + COD_REF + "','" + FASE + "','" + ACABAMENTO + "','" + RACK + "',"
				+ ANTECEDENCIA + "," + BARRAS_CAPACIDADE + "");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_LINHAS insertPR_PLANEAMENTO_PRODUCAO_LINHASA(
			final PR_PLANEAMENTO_PRODUCAO_LINHAS data) {
		return dao6.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_LINHAS> getPR_PLANEAMENTO_PRODUCAO_LINHAS() {
		return dao6.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_LINHASbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_LINHAS> getPR_PLANEAMENTO_PRODUCAO_LINHASbyip(@PathParam("id") Integer id) {
		return dao6.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_LINHAS/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_LINHAS(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_LINHAS PR_PLANEAMENTO_PRODUCAO_LINHAS = new PR_PLANEAMENTO_PRODUCAO_LINHAS();
		PR_PLANEAMENTO_PRODUCAO_LINHAS.setID_PLANEAMENTO_PRODUCAO_LINHA(id);
		dao6.delete(PR_PLANEAMENTO_PRODUCAO_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_LINHAS updatePR_PLANEAMENTO_PRODUCAO_LINHAS(
			final PR_PLANEAMENTO_PRODUCAO_LINHAS PR_PLANEAMENTO_PRODUCAO_LINHAS) {
		PR_PLANEAMENTO_PRODUCAO_LINHAS
				.setID_PLANEAMENTO_PRODUCAO_LINHA(PR_PLANEAMENTO_PRODUCAO_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA());
		return dao6.update(PR_PLANEAMENTO_PRODUCAO_LINHAS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_LINHAS_CAMPOS")
	@Consumes("*/*")
	@Produces("application/json")
	public boolean updatePR_PLANEAMENTO_PRODUCAO_LINHAS_CAMPOS(
			final PR_PLANEAMENTO_PRODUCAO_LINHAS PR_PLANEAMENTO_PRODUCAO_LINHAS) {

		entityManager.createNativeQuery("UPDATE PR_PLANEAMENTO_PRODUCAO_LINHAS SET UTZ_MODIF= "
				+ PR_PLANEAMENTO_PRODUCAO_LINHAS.getUTZ_MODIF() + ", DATA_MODIF = GETDATE(),NUM_BARRAS_PLANO = "
				+ PR_PLANEAMENTO_PRODUCAO_LINHAS.getNUM_BARRAS_PLANO() + " WHERE ID_PLANEAMENTO_PRODUCAO_LINHA = "
				+ PR_PLANEAMENTO_PRODUCAO_LINHAS.getID_PLANEAMENTO_PRODUCAO_LINHA() + "").executeUpdate();
		return true;
	}

	/************************************* PR_DIC_SECTORES_AGREGADORES */

	@POST
	@Path("/createPR_DIC_SECTORES_AGREGADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES insertPR_DIC_SECTORES_AGREGADORESA(final PR_DIC_SECTORES_AGREGADORES data) {
		return dao8.create(data);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES> getPR_DIC_SECTORES_AGREGADORES() {
		return dao8.getall();
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORESbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES> getPR_DIC_SECTORES_AGREGADORESbyip(@PathParam("id") Integer id) {
		return dao8.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_SECTORES_AGREGADORES/{id}")
	public void deletePR_DIC_SECTORES_AGREGADORES(@PathParam("id") Integer id) {
		PR_DIC_SECTORES_AGREGADORES PR_DIC_SECTORES_AGREGADORES = new PR_DIC_SECTORES_AGREGADORES();
		PR_DIC_SECTORES_AGREGADORES.setID_SECTOR_AGREGADOR(id);
		dao8.delete(PR_DIC_SECTORES_AGREGADORES);
	}

	@PUT
	@Path("/updatePR_DIC_SECTORES_AGREGADORES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES updatePR_DIC_SECTORES_AGREGADORES(
			final PR_DIC_SECTORES_AGREGADORES PR_DIC_SECTORES_AGREGADORES) {
		PR_DIC_SECTORES_AGREGADORES.setID_SECTOR_AGREGADOR(PR_DIC_SECTORES_AGREGADORES.getID_SECTOR_AGREGADOR());
		return dao8.update(PR_DIC_SECTORES_AGREGADORES);
	}

	/************************************* PR_DIC_SECTORES_AGREGADORES_LINHA */

	@POST
	@Path("/createPR_DIC_SECTORES_AGREGADORES_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES_LINHA insertPR_DIC_SECTORES_AGREGADORES_LINHAA(
			final PR_DIC_SECTORES_AGREGADORES_LINHA data) {
		return dao7.create(data);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHA")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getPR_DIC_SECTORES_AGREGADORES_LINHA() {
		return dao7.getall();
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHAbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getPR_DIC_SECTORES_AGREGADORES_LINHAbyip(
			@PathParam("id") Integer id) {
		return dao7.getbyid(id);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHAgetSectoresAll/{id}/{linha}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getSectoresAll(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao7.getSectoresAll(id, linha);
	}

	@GET
	@Path("/getPR_DIC_SECTORES_AGREGADORES_LINHAgetSectoresAgregadores/{id}/{linha}")
	@Produces("application/json")
	public List<PR_DIC_SECTORES_AGREGADORES_LINHA> getSectoresAgregadores(@PathParam("id") Integer id,
			@PathParam("linha") Integer linha) {
		return dao7.getSectoresAgregadores(id, linha);
	}

	@DELETE
	@Path("/deletePR_DIC_SECTORES_AGREGADORES_LINHA/{id}")
	public void deletePR_DIC_SECTORES_AGREGADORES_LINHA(@PathParam("id") Integer id) {
		PR_DIC_SECTORES_AGREGADORES_LINHA PR_DIC_SECTORES_AGREGADORES_LINHA = new PR_DIC_SECTORES_AGREGADORES_LINHA();
		PR_DIC_SECTORES_AGREGADORES_LINHA.setID_SECTOR_AGREGADOR_LINHA(id);
		dao7.delete(PR_DIC_SECTORES_AGREGADORES_LINHA);
	}

	@PUT
	@Path("/updatePR_DIC_SECTORES_AGREGADORES_LINHA")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_SECTORES_AGREGADORES_LINHA updatePR_DIC_SECTORES_AGREGADORES_LINHA(
			final PR_DIC_SECTORES_AGREGADORES_LINHA PR_DIC_SECTORES_AGREGADORES_LINHA) {
		PR_DIC_SECTORES_AGREGADORES_LINHA
				.setID_SECTOR_AGREGADOR_LINHA(PR_DIC_SECTORES_AGREGADORES_LINHA.getID_SECTOR_AGREGADOR_LINHA());
		return dao7.update(PR_DIC_SECTORES_AGREGADORES_LINHA);
	}

	/************************************* PR_GESTAO_BARRAS */

	@POST
	@Path("/createPR_GESTAO_BARRAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS insertPR_GESTAO_BARRASA(final PR_GESTAO_BARRAS data) {
		return dao11.create(data);
	}

	@GET
	@Path("/getPR_GESTAO_BARRAS")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS> getPR_GESTAO_BARRAS() {
		return dao11.getall();
	}

	@GET
	@Path("/getPR_GESTAO_BARRASbyid/{id}")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS> getPR_GESTAO_BARRASbyip(@PathParam("id") Integer id) {
		return dao11.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_GESTAO_BARRAS/{id}")
	public void deletePR_GESTAO_BARRAS(@PathParam("id") Integer id) {
		PR_GESTAO_BARRAS PR_GESTAO_BARRAS = new PR_GESTAO_BARRAS();
		PR_GESTAO_BARRAS.setID_GESTAO_BARRAS(id);
		dao11.delete(PR_GESTAO_BARRAS);
	}

	@PUT
	@Path("/updatePR_GESTAO_BARRAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS updatePR_GESTAO_BARRAS(final PR_GESTAO_BARRAS PR_GESTAO_BARRAS) {
		PR_GESTAO_BARRAS.setID_GESTAO_BARRAS(PR_GESTAO_BARRAS.getID_GESTAO_BARRAS());
		return dao11.update(PR_GESTAO_BARRAS);
	}

	/************************************* PR_GESTAO_BARRAS_REFERENCIAS */

	@POST
	@Path("/createPR_GESTAO_BARRAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS_REFERENCIAS insertPR_GESTAO_BARRAS_REFERENCIASA(final PR_GESTAO_BARRAS_REFERENCIAS data) {
		return dao12.create(data);
	}

	@GET
	@Path("/getPR_GESTAO_BARRAS_REFERENCIAS")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS_REFERENCIAS> getPR_GESTAO_BARRAS_REFERENCIAS() {
		return dao12.getall();
	}

	@GET
	@Path("/getPR_GESTAO_BARRAS_REFERENCIASbyid/{id}")
	@Produces("application/json")
	public List<PR_GESTAO_BARRAS_REFERENCIAS> getPR_GESTAO_BARRAS_REFERENCIASbyip(@PathParam("id") Integer id) {
		return dao12.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_GESTAO_BARRAS_REFERENCIAS/{id}")
	public void deletePR_GESTAO_BARRAS_REFERENCIAS(@PathParam("id") Integer id) {
		PR_GESTAO_BARRAS_REFERENCIAS PR_GESTAO_BARRAS_REFERENCIAS = new PR_GESTAO_BARRAS_REFERENCIAS();
		PR_GESTAO_BARRAS_REFERENCIAS.setID_GESTAO_BARRAS_REFERENCIAS(id);
		dao12.delete(PR_GESTAO_BARRAS_REFERENCIAS);
	}

	@PUT
	@Path("/updatePR_GESTAO_BARRAS_REFERENCIAS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_GESTAO_BARRAS_REFERENCIAS updatePR_GESTAO_BARRAS_REFERENCIAS(
			final PR_GESTAO_BARRAS_REFERENCIAS PR_GESTAO_BARRAS_REFERENCIAS) {
		PR_GESTAO_BARRAS_REFERENCIAS
				.setID_GESTAO_BARRAS_REFERENCIAS(PR_GESTAO_BARRAS_REFERENCIAS.getID_GESTAO_BARRAS_REFERENCIAS());
		return dao12.update(PR_GESTAO_BARRAS_REFERENCIAS);
	}

	/************************************* PR_DIC_VALIDACAO_BASTIDOR */

	@POST
	@Path("/createPR_DIC_VALIDACAO_BASTIDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_VALIDACAO_BASTIDOR insertPR_DIC_VALIDACAO_BASTIDORA(final PR_DIC_VALIDACAO_BASTIDOR data) {
		return dao13.create(data);
	}

	@GET
	@Path("/getPR_DIC_VALIDACAO_BASTIDOR")
	@Produces("application/json")
	public List<PR_DIC_VALIDACAO_BASTIDOR> getPR_DIC_VALIDACAO_BASTIDOR() {
		return dao13.getall();
	}

	@GET
	@Path("/getPR_DIC_VALIDACAO_BASTIDORbyid/{id}")
	@Produces("application/json")
	public List<PR_DIC_VALIDACAO_BASTIDOR> getPR_DIC_VALIDACAO_BASTIDORbyip(@PathParam("id") Integer id) {
		return dao13.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_DIC_VALIDACAO_BASTIDOR/{id}")
	public void deletePR_DIC_VALIDACAO_BASTIDOR(@PathParam("id") Integer id) {
		PR_DIC_VALIDACAO_BASTIDOR PR_DIC_VALIDACAO_BASTIDOR = new PR_DIC_VALIDACAO_BASTIDOR();
		PR_DIC_VALIDACAO_BASTIDOR.setID_VALIDACAO_BASTIDOR(id);
		dao13.delete(PR_DIC_VALIDACAO_BASTIDOR);
	}

	@PUT
	@Path("/updatePR_DIC_VALIDACAO_BASTIDOR")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_DIC_VALIDACAO_BASTIDOR updatePR_DIC_VALIDACAO_BASTIDOR(
			final PR_DIC_VALIDACAO_BASTIDOR PR_DIC_VALIDACAO_BASTIDOR) {
		PR_DIC_VALIDACAO_BASTIDOR.setID_VALIDACAO_BASTIDOR(PR_DIC_VALIDACAO_BASTIDOR.getID_VALIDACAO_BASTIDOR());
		return dao13.update(PR_DIC_VALIDACAO_BASTIDOR);
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_ANALISES */

	@POST
	@Path("/GET_PLANOS")
	@Produces("application/json")
	public List<Object[]> GET_PLANOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ANO = firstMap.get("ANO");
		String SEMANA = firstMap.get("SEMANA");

		Query query_folder = entityManager
				.createNativeQuery("DECLARE @SEMANA int = " + SEMANA + "; " + " DECLARE @ANO int = " + ANO + "; "
						+ "select a.ID_PLANEAMENTO_PRODUCAO_CAB,CAST(a.DATA_CRIA as date) DATA_CRIA ,a.DATA_MRP,a.N_MRP,a.ID_LINHA,a.ESTADO,a.NUMERO_SEMANAS "
						+ "from PR_PLANEAMENTO_PRODUCAO_CAB a "
						+ "where DATEPART(iso_week,a.DATA_CRIA) = @SEMANA and YEAR(a.DATA_CRIA) = @ANO and ativo = 1");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_BARRAS_PRODUZIR")
	@Produces("application/json")
	public List<Object[]> GET_BARRAS_PRODUZIR(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_BARRAS_PRODUZIR] '" + IDS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_TIPO_ACABAMENTO_ANALISE")
	@Produces("application/json")
	public List<Object[]> GET_TIPO_ACABAMENTO_ANALISE(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_TIPO_ACABAMENTO_ANALISE] '" + IDS + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS_ANALISES")
	@Produces("application/json")
	public List<Object[]> GET_RACKS_ANALISES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String FILTRO = firstMap.get("FILTRO");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_RACKS_ANALISES] '" + IDS + "'," + FILTRO);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS_REFERENCIAS_ANALISES")
	@Produces("application/json")
	public List<Object[]> GET_RACKS_REFERENCIAS_ANALISES(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String RACK = firstMap.get("RACK");

		Query query_folder = entityManager
				.createNativeQuery("EXEC [GET_RACKS_REFERENCIAS_ANALISES] '" + IDS + "','" + RACK + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/ATUALIZAR_RECURSOS_HUMANOS")
	@Produces("application/json")
	public List<Object[]> ATUALIZAR_RECURSOS_HUMANOS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String IDS = firstMap.get("IDS");
		String ID_ANALISE = firstMap.get("ID_ANALISE");
		String N_SEMANAS = firstMap.get("N_SEMANAS");
		String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC [ATUALIZAR_RECURSOS_HUMANOS] '" + IDS + "'," + ID_ANALISE + "," + N_SEMANAS + ",'" + DATA + "'");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_GESTAO_BARRAS")
	@Produces("application/json")
	public List<Object[]> GET_GESTAO_BARRAS(final List<HashMap<String, String>> dados) {
		/*
		 * HashMap<String, String> firstMap = dados.get(0); String IDS =
		 * firstMap.get("IDS"); String ID_ANALISE = firstMap.get("ID_ANALISE");
		 * String N_SEMANAS = firstMap.get("N_SEMANAS"); String DATA =
		 * firstMap.get("DATA");
		 */

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_GESTAO_BARRAS] ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/DUPLICAR_GESTAO_BARRAS")
	@Produces("application/json")
	public List<Object[]> DUPLICAR_GESTAO_BARRAS(final List<HashMap<String, String>> dados) {

		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");
		String ID_USER = firstMap.get("ID_USER");
		/*
		 * String ID_ANALISE = firstMap.get("ID");
		 * 
		 * String N_SEMANAS = firstMap.get("N_SEMANAS"); String DATA =
		 * firstMap.get("DATA");
		 */

		Query query_folder = entityManager.createNativeQuery("EXEC [DUPLICAR_GESTAO_BARRAS] " + ID + "," + ID_USER);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES insertPR_PLANEAMENTO_PRODUCAO_ANALISESA(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES data) {
		return dao9.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getPR_PLANEAMENTO_PRODUCAO_ANALISES() {
		return dao9.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES2")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getPR_PLANEAMENTO_PRODUCAO_ANALISES2() {
		return dao9.getall2();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISESbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES> getPR_PLANEAMENTO_PRODUCAO_ANALISESbyip(@PathParam("id") Integer id) {
		return dao9.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_ANALISES/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_ANALISES(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES PR_PLANEAMENTO_PRODUCAO_ANALISES = new PR_PLANEAMENTO_PRODUCAO_ANALISES();
		PR_PLANEAMENTO_PRODUCAO_ANALISES.setID_PLANEAMENTO_PRODUCAO_ANALISES(id);
		dao9.delete(PR_PLANEAMENTO_PRODUCAO_ANALISES);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_ANALISES")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES updatePR_PLANEAMENTO_PRODUCAO_ANALISES(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES PR_PLANEAMENTO_PRODUCAO_ANALISES) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES.setID_PLANEAMENTO_PRODUCAO_ANALISES(
				PR_PLANEAMENTO_PRODUCAO_ANALISES.getID_PLANEAMENTO_PRODUCAO_ANALISES());
		return dao9.update(PR_PLANEAMENTO_PRODUCAO_ANALISES);
	}

	@POST
	@Path("/GET_DIVIDAS_LISTA_RESUMO_KAM")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA_RESUMO_KAM(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA_RESUMO_KAM]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_DIVIDAS_LISTA_TOP_15")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA_TOP_15(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA_TOP_15]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_DIVIDAS_LISTA_TOP_15_DIVIDAS")
	@Produces("application/json")
	public List<Object[]> GET_DIVIDAS_LISTA_TOP_15_DIVIDAS(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_DIVIDAS_LISTA_TOP_15_DIVIDAS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/INSERT_PR_PLANEAMENTO_PRODUCAO_LINHAS")
	@Produces("application/json")
	public List<Object[]> INSERT_PR_PLANEAMENTO_PRODUCAO_LINHAS(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String ID = firstMap.get("ID");

		Query query_folder = entityManager.createNativeQuery("EXEC [INSERT_PR_PLANEAMENTO_PRODUCAO_LINHAS] " + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	/************************************* PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS */

	@POST
	@Path("/createPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS insertPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSA(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS data) {
		return dao10.create(data);
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS> getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS() {
		return dao10.getall();
	}

	@GET
	@Path("/getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSbyid/{id}")
	@Produces("application/json")
	public List<PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS> getPR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOSbyip(
			@PathParam("id") Integer id) {
		return dao10.getbyid(id);
	}

	@DELETE
	@Path("/deletePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS/{id}")
	public void deletePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS(@PathParam("id") Integer id) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS = new PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS();
		PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS.setID(id);
		dao10.delete(PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS);
	}

	@PUT
	@Path("/updatePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
	@Consumes("*/*")
	@Produces("application/json")
	public PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS updatePR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS(
			final PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS) {
		PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS
				.setID(PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS.getID());
		return dao10.update(PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS);
	}

	@POST
	@Path("/GET_SEMANAS_PLANEAMENTO")
	@Produces("application/json")
	public List<Object[]> GET_SEMANAS_PLANEAMENTO(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String DATA = firstMap.get("DATA");
		String NUMERO_SEMANAS = firstMap.get("NUMERO_SEMANAS");
		String NOVO = firstMap.get("NOVO");
		String ID = firstMap.get("ID");

		Query query_folder = entityManager.createNativeQuery(
				"EXEC [GET_SEMANAS_PLANEAMENTO] '" + DATA + "'," + NUMERO_SEMANAS + "," + NOVO + "," + ID);

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_FASES")
	@Produces("application/json")
	public List<Object[]> GET_FASES(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_FASES]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/GET_RACKS")
	@Produces("application/json")
	public List<Object[]> GET_RACKS(final List<HashMap<String, String>> dados) {
		// HashMap<String, String> firstMap = dados.get(0);
		// String DATA = firstMap.get("DATA");

		Query query_folder = entityManager.createNativeQuery("EXEC [GET_RACKS]");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

	@POST
	@Path("/getEvolucaoDefeitoRef")
	@Produces("application/json")
	public List<Object[]> getEvolucaoDefeitoRef(final List<HashMap<String, String>> dados) {
		HashMap<String, String> firstMap = dados.get(0);
		String REF = firstMap.get("REF");
		String DATA_INI = firstMap.get("DATA_INI");
		String DATA_FIM = firstMap.get("DATA_FIM");
		String DEFEITO = firstMap.get("DEFEITO");

		/*
		 * String HORA_INI = firstMap.get("HORA_INI"); String HORA_FIM =
		 * firstMap.get("HORA_FIM");
		 */

		Query query_folder = entityManager.createNativeQuery("EXEC SILVER_BI.dbo.QUERY_EVOLUCAO_DEFEITO_REF '" + REF
				+ "','" + DATA_INI + "','" + DATA_FIM + "','" + DEFEITO + "' ");

		List<Object[]> dados_folder = query_folder.getResultList();

		return dados_folder;
	}

}
