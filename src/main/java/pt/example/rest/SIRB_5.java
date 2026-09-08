package pt.example.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import pt.example.dao.QUA_CR_DIC_TIPO_ENSAIODao;
import pt.example.entity.QUA_CR_DIC_TIPO_ENSAIO;
import pt.example.dao.QUA_CR_DIC_TIPO_PECADao;
import pt.example.entity.QUA_CR_DIC_TIPO_PECA;
import pt.example.dao.QUA_CR_DIC_TIPO_SUPERFICIEDao;
import pt.example.entity.QUA_CR_DIC_TIPO_SUPERFICIE;
import pt.example.dao.QUA_CR_DIC_ASPECTODao;
import pt.example.entity.QUA_CR_DIC_ASPECTO;
import pt.example.dao.QUA_CR_DIC_RESULTADODao;
import pt.example.entity.QUA_CR_DIC_RESULTADO;
import pt.example.dao.QUA_CR_DIC_LOCAL_PRODUCAODao;
import pt.example.entity.QUA_CR_DIC_LOCAL_PRODUCAO;
import pt.example.dao.QUA_CR_DIC_PATAMARDao;
import pt.example.entity.QUA_CR_DIC_PATAMAR;
import pt.example.dao.QUA_CR_DIC_CONJ_EQUIPDao;
import pt.example.entity.QUA_CR_DIC_CONJ_EQUIP;
import pt.example.dao.QUA_CR_DIC_CONJ_EQUIP_LINDao;
import pt.example.entity.QUA_CR_DIC_CONJ_EQUIP_LIN;
import pt.example.dao.QUA_CR_DIC_REFERENCIADao;
import pt.example.entity.QUA_CR_DIC_REFERENCIA;
import pt.example.dao.QUA_CR_DIC_REF_TESTEDao;
import pt.example.entity.QUA_CR_DIC_REF_TESTE;
import pt.example.dao.QUA_CR_DIC_REFERENCIA_FICHEIROSDao;
import pt.example.entity.QUA_CR_DIC_REFERENCIA_FICHEIROS;
import pt.example.dao.QUA_CR_MOV_RELATORIODao;
import pt.example.entity.QUA_CR_MOV_RELATORIO;
import pt.example.dao.QUA_CR_MOV_CONDICOES_CABDao;
import pt.example.entity.QUA_CR_MOV_CONDICOES_CAB;
import pt.example.dao.QUA_CR_MOV_CONDICOESDao;
import pt.example.entity.QUA_CR_MOV_CONDICOES;
import pt.example.dao.QUA_CR_MOV_ESPESSURA_CABDao;
import pt.example.entity.QUA_CR_MOV_ESPESSURA_CAB;
import pt.example.dao.QUA_CR_MOV_ESPESSURADao;
import pt.example.entity.QUA_CR_MOV_ESPESSURA;
import pt.example.dao.QUA_CR_MOV_ENSAIO_CABDao;
import pt.example.entity.QUA_CR_MOV_ENSAIO_CAB;
import pt.example.dao.QUA_CR_MOV_ENSAIO_TESTEDao;
import pt.example.entity.QUA_CR_MOV_ENSAIO_TESTE;
import pt.example.dao.QUA_CR_MOV_ENSAIO_LEITURADao;
import pt.example.entity.QUA_CR_MOV_ENSAIO_LEITURA;
import pt.example.dao.QUA_CR_MOV_CORROSAO_CABDao;
import pt.example.entity.QUA_CR_MOV_CORROSAO_CAB;
import pt.example.dao.QUA_CR_MOV_CORROSAO_AMOSTRADao;
import pt.example.entity.QUA_CR_MOV_CORROSAO_AMOSTRA;
import pt.example.dao.QUA_CR_MOV_CORROSAO_LEITURADao;
import pt.example.entity.QUA_CR_MOV_CORROSAO_LEITURA;
import pt.example.entity.QUA_CR_RELATORIO_DTO;
import pt.example.entity.QUA_CR_ENSAIO_TESTE_DTO;
import pt.example.entity.QUA_CR_CORROSAO_AMOSTRA_DTO;

/**
 * Modulo Qualidade > Laboratorio Cromagem (QUA_CR).
 *
 * Classe propria e nao mais endpoints no SIRB_4, que ja tem 2210 linhas e 307
 * caminhos. O JAX-RS junta todas as classes @Path("/sirb") no mesmo caminho,
 * portanto os endpoints continuam a sair em /rest/sirb/... como os restantes.
 *
 * Modelo e decisoes: sql\QUA_CROMAGEM_MODULO_COMPLETO.sql
 */
@Stateless
@Path("/sirb")
public class SIRB_5 {

	@Inject private QUA_CR_DIC_TIPO_ENSAIODao daoTipoEnsaio;
	@Inject private QUA_CR_DIC_TIPO_PECADao daoTipoPeca;
	@Inject private QUA_CR_DIC_TIPO_SUPERFICIEDao daoTipoSuperficie;
	@Inject private QUA_CR_DIC_ASPECTODao daoAspecto;
	@Inject private QUA_CR_DIC_RESULTADODao daoResultado;
	@Inject private QUA_CR_DIC_LOCAL_PRODUCAODao daoLocalProducao;
	@Inject private QUA_CR_DIC_PATAMARDao daoPatamar;
	@Inject private QUA_CR_DIC_CONJ_EQUIPDao daoConjEquip;
	@Inject private QUA_CR_DIC_CONJ_EQUIP_LINDao daoConjEquipLin;
	@Inject private QUA_CR_DIC_REFERENCIADao daoReferencia;
	@Inject private QUA_CR_DIC_REF_TESTEDao daoRefTeste;
	@Inject private QUA_CR_DIC_REFERENCIA_FICHEIROSDao daoRefFicheiros;
	@Inject private QUA_CR_MOV_RELATORIODao daoRelatorio;
	@Inject private QUA_CR_MOV_CONDICOES_CABDao daoCondicoesCab;
	@Inject private QUA_CR_MOV_CONDICOESDao daoCondicoes;
	@Inject private QUA_CR_MOV_ESPESSURA_CABDao daoEspessuraCab;
	@Inject private QUA_CR_MOV_ESPESSURADao daoEspessura;
	@Inject private QUA_CR_MOV_ENSAIO_CABDao daoEnsaioCab;
	@Inject private QUA_CR_MOV_ENSAIO_TESTEDao daoEnsaioTeste;
	@Inject private QUA_CR_MOV_ENSAIO_LEITURADao daoEnsaioLeitura;
	@Inject private QUA_CR_MOV_CORROSAO_CABDao daoCorrosaoCab;
	@Inject private QUA_CR_MOV_CORROSAO_AMOSTRADao daoCorrosaoAmostra;
	@Inject private QUA_CR_MOV_CORROSAO_LEITURADao daoCorrosaoLeitura;

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	/************************************* QUA_CR_DIC_TIPO_ENSAIO */
	@POST
	@Path("/createQUA_CR_DIC_TIPO_ENSAIO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_TIPO_ENSAIO insertQUA_CR_DIC_TIPO_ENSAIO(final QUA_CR_DIC_TIPO_ENSAIO data) {
		return daoTipoEnsaio.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_TIPO_ENSAIO")
	@Produces("application/json")
	public List<QUA_CR_DIC_TIPO_ENSAIO> getQUA_CR_DIC_TIPO_ENSAIO() {
		return daoTipoEnsaio.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_TIPO_ENSAIObyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_TIPO_ENSAIO> getQUA_CR_DIC_TIPO_ENSAIObyid(@PathParam("id") Integer id) {
		return daoTipoEnsaio.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_TIPO_ENSAIO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_TIPO_ENSAIO updateQUA_CR_DIC_TIPO_ENSAIO(final QUA_CR_DIC_TIPO_ENSAIO data) {
		return daoTipoEnsaio.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_TIPO_ENSAIO/{id}")
	public void deleteQUA_CR_DIC_TIPO_ENSAIO(@PathParam("id") Integer id) {
		QUA_CR_DIC_TIPO_ENSAIO e = new QUA_CR_DIC_TIPO_ENSAIO();
		e.setID_TIPO_ENSAIO(id);
		daoTipoEnsaio.delete(e);
	}

	/************************************* QUA_CR_DIC_TIPO_PECA */
	@POST
	@Path("/createQUA_CR_DIC_TIPO_PECA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_TIPO_PECA insertQUA_CR_DIC_TIPO_PECA(final QUA_CR_DIC_TIPO_PECA data) {
		return daoTipoPeca.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_TIPO_PECA")
	@Produces("application/json")
	public List<QUA_CR_DIC_TIPO_PECA> getQUA_CR_DIC_TIPO_PECA() {
		return daoTipoPeca.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_TIPO_PECAbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_TIPO_PECA> getQUA_CR_DIC_TIPO_PECAbyid(@PathParam("id") Integer id) {
		return daoTipoPeca.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_TIPO_PECA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_TIPO_PECA updateQUA_CR_DIC_TIPO_PECA(final QUA_CR_DIC_TIPO_PECA data) {
		return daoTipoPeca.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_TIPO_PECA/{id}")
	public void deleteQUA_CR_DIC_TIPO_PECA(@PathParam("id") Integer id) {
		QUA_CR_DIC_TIPO_PECA e = new QUA_CR_DIC_TIPO_PECA();
		e.setID_TIPO_PECA(id);
		daoTipoPeca.delete(e);
	}

	/************************************* QUA_CR_DIC_TIPO_SUPERFICIE */
	@POST
	@Path("/createQUA_CR_DIC_TIPO_SUPERFICIE")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_TIPO_SUPERFICIE insertQUA_CR_DIC_TIPO_SUPERFICIE(final QUA_CR_DIC_TIPO_SUPERFICIE data) {
		return daoTipoSuperficie.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_TIPO_SUPERFICIE")
	@Produces("application/json")
	public List<QUA_CR_DIC_TIPO_SUPERFICIE> getQUA_CR_DIC_TIPO_SUPERFICIE() {
		return daoTipoSuperficie.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_TIPO_SUPERFICIEbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_TIPO_SUPERFICIE> getQUA_CR_DIC_TIPO_SUPERFICIEbyid(@PathParam("id") Integer id) {
		return daoTipoSuperficie.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_TIPO_SUPERFICIE")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_TIPO_SUPERFICIE updateQUA_CR_DIC_TIPO_SUPERFICIE(final QUA_CR_DIC_TIPO_SUPERFICIE data) {
		return daoTipoSuperficie.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_TIPO_SUPERFICIE/{id}")
	public void deleteQUA_CR_DIC_TIPO_SUPERFICIE(@PathParam("id") Integer id) {
		QUA_CR_DIC_TIPO_SUPERFICIE e = new QUA_CR_DIC_TIPO_SUPERFICIE();
		e.setID_TIPO_SUPERFICIE(id);
		daoTipoSuperficie.delete(e);
	}

	/************************************* QUA_CR_DIC_ASPECTO */
	@POST
	@Path("/createQUA_CR_DIC_ASPECTO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_ASPECTO insertQUA_CR_DIC_ASPECTO(final QUA_CR_DIC_ASPECTO data) {
		return daoAspecto.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_ASPECTO")
	@Produces("application/json")
	public List<QUA_CR_DIC_ASPECTO> getQUA_CR_DIC_ASPECTO() {
		return daoAspecto.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_ASPECTObyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_ASPECTO> getQUA_CR_DIC_ASPECTObyid(@PathParam("id") Integer id) {
		return daoAspecto.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_ASPECTO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_ASPECTO updateQUA_CR_DIC_ASPECTO(final QUA_CR_DIC_ASPECTO data) {
		return daoAspecto.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_ASPECTO/{id}")
	public void deleteQUA_CR_DIC_ASPECTO(@PathParam("id") Integer id) {
		QUA_CR_DIC_ASPECTO e = new QUA_CR_DIC_ASPECTO();
		e.setID_ASPECTO(id);
		daoAspecto.delete(e);
	}

	/************************************* QUA_CR_DIC_RESULTADO */
	@POST
	@Path("/createQUA_CR_DIC_RESULTADO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_RESULTADO insertQUA_CR_DIC_RESULTADO(final QUA_CR_DIC_RESULTADO data) {
		return daoResultado.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_RESULTADO")
	@Produces("application/json")
	public List<QUA_CR_DIC_RESULTADO> getQUA_CR_DIC_RESULTADO() {
		return daoResultado.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_RESULTADObyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_RESULTADO> getQUA_CR_DIC_RESULTADObyid(@PathParam("id") Integer id) {
		return daoResultado.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_RESULTADO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_RESULTADO updateQUA_CR_DIC_RESULTADO(final QUA_CR_DIC_RESULTADO data) {
		return daoResultado.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_RESULTADO/{id}")
	public void deleteQUA_CR_DIC_RESULTADO(@PathParam("id") Integer id) {
		QUA_CR_DIC_RESULTADO e = new QUA_CR_DIC_RESULTADO();
		e.setID_RESULTADO(id);
		daoResultado.delete(e);
	}

	/************************************* QUA_CR_DIC_LOCAL_PRODUCAO */
	@POST
	@Path("/createQUA_CR_DIC_LOCAL_PRODUCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_LOCAL_PRODUCAO insertQUA_CR_DIC_LOCAL_PRODUCAO(final QUA_CR_DIC_LOCAL_PRODUCAO data) {
		return daoLocalProducao.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_LOCAL_PRODUCAO")
	@Produces("application/json")
	public List<QUA_CR_DIC_LOCAL_PRODUCAO> getQUA_CR_DIC_LOCAL_PRODUCAO() {
		return daoLocalProducao.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_LOCAL_PRODUCAObyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_LOCAL_PRODUCAO> getQUA_CR_DIC_LOCAL_PRODUCAObyid(@PathParam("id") Integer id) {
		return daoLocalProducao.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_LOCAL_PRODUCAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_LOCAL_PRODUCAO updateQUA_CR_DIC_LOCAL_PRODUCAO(final QUA_CR_DIC_LOCAL_PRODUCAO data) {
		return daoLocalProducao.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_LOCAL_PRODUCAO/{id}")
	public void deleteQUA_CR_DIC_LOCAL_PRODUCAO(@PathParam("id") Integer id) {
		QUA_CR_DIC_LOCAL_PRODUCAO e = new QUA_CR_DIC_LOCAL_PRODUCAO();
		e.setID_LOCAL_PRODUCAO(id);
		daoLocalProducao.delete(e);
	}

	/************************************* QUA_CR_DIC_PATAMAR */
	@POST
	@Path("/createQUA_CR_DIC_PATAMAR")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_PATAMAR insertQUA_CR_DIC_PATAMAR(final QUA_CR_DIC_PATAMAR data) {
		return daoPatamar.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_PATAMAR")
	@Produces("application/json")
	public List<QUA_CR_DIC_PATAMAR> getQUA_CR_DIC_PATAMAR() {
		return daoPatamar.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_PATAMARbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_PATAMAR> getQUA_CR_DIC_PATAMARbyid(@PathParam("id") Integer id) {
		return daoPatamar.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_PATAMAR")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_PATAMAR updateQUA_CR_DIC_PATAMAR(final QUA_CR_DIC_PATAMAR data) {
		return daoPatamar.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_PATAMAR/{id}")
	public void deleteQUA_CR_DIC_PATAMAR(@PathParam("id") Integer id) {
		QUA_CR_DIC_PATAMAR e = new QUA_CR_DIC_PATAMAR();
		e.setID_PATAMAR(id);
		daoPatamar.delete(e);
	}

	/************************************* QUA_CR_DIC_CONJ_EQUIP */
	@POST
	@Path("/createQUA_CR_DIC_CONJ_EQUIP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_CONJ_EQUIP insertQUA_CR_DIC_CONJ_EQUIP(final QUA_CR_DIC_CONJ_EQUIP data) {
		return daoConjEquip.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_CONJ_EQUIP")
	@Produces("application/json")
	public List<QUA_CR_DIC_CONJ_EQUIP> getQUA_CR_DIC_CONJ_EQUIP() {
		return daoConjEquip.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_CONJ_EQUIPbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_CONJ_EQUIP> getQUA_CR_DIC_CONJ_EQUIPbyid(@PathParam("id") Integer id) {
		return daoConjEquip.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_CONJ_EQUIP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_CONJ_EQUIP updateQUA_CR_DIC_CONJ_EQUIP(final QUA_CR_DIC_CONJ_EQUIP data) {
		return daoConjEquip.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_CONJ_EQUIP/{id}")
	public void deleteQUA_CR_DIC_CONJ_EQUIP(@PathParam("id") Integer id) {
		QUA_CR_DIC_CONJ_EQUIP e = new QUA_CR_DIC_CONJ_EQUIP();
		e.setID_CONJ_EQUIP(id);
		daoConjEquip.delete(e);
	}

	/************************************* QUA_CR_DIC_CONJ_EQUIP_LIN */
	@POST
	@Path("/createQUA_CR_DIC_CONJ_EQUIP_LIN")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_CONJ_EQUIP_LIN insertQUA_CR_DIC_CONJ_EQUIP_LIN(final QUA_CR_DIC_CONJ_EQUIP_LIN data) {
		return daoConjEquipLin.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_CONJ_EQUIP_LIN")
	@Produces("application/json")
	public List<QUA_CR_DIC_CONJ_EQUIP_LIN> getQUA_CR_DIC_CONJ_EQUIP_LIN() {
		return daoConjEquipLin.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_CONJ_EQUIP_LINbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_CONJ_EQUIP_LIN> getQUA_CR_DIC_CONJ_EQUIP_LINbyid(@PathParam("id") Integer id) {
		return daoConjEquipLin.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_CONJ_EQUIP_LIN")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_CONJ_EQUIP_LIN updateQUA_CR_DIC_CONJ_EQUIP_LIN(final QUA_CR_DIC_CONJ_EQUIP_LIN data) {
		return daoConjEquipLin.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_CONJ_EQUIP_LIN/{id}")
	public void deleteQUA_CR_DIC_CONJ_EQUIP_LIN(@PathParam("id") Integer id) {
		QUA_CR_DIC_CONJ_EQUIP_LIN e = new QUA_CR_DIC_CONJ_EQUIP_LIN();
		e.setID_CONJ_EQUIP_LIN(id);
		daoConjEquipLin.delete(e);
	}
	@GET
	@Path("/getQUA_CR_DIC_CONJ_EQUIP_LINbyConjEquip/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_CONJ_EQUIP_LIN> getQUA_CR_DIC_CONJ_EQUIP_LINbyConjEquip(@PathParam("id") Integer id) {
		return daoConjEquipLin.getbyConjEquip(id);
	}

	/************************************* QUA_CR_DIC_REFERENCIA */
	@POST
	@Path("/createQUA_CR_DIC_REFERENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_REFERENCIA insertQUA_CR_DIC_REFERENCIA(final QUA_CR_DIC_REFERENCIA data) {
		return daoReferencia.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA")
	@Produces("application/json")
	public List<QUA_CR_DIC_REFERENCIA> getQUA_CR_DIC_REFERENCIA() {
		return daoReferencia.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_REFERENCIAbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_REFERENCIA> getQUA_CR_DIC_REFERENCIAbyid(@PathParam("id") Integer id) {
		return daoReferencia.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_REFERENCIA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_REFERENCIA updateQUA_CR_DIC_REFERENCIA(final QUA_CR_DIC_REFERENCIA data) {
		return daoReferencia.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_REFERENCIA/{id}")
	public void deleteQUA_CR_DIC_REFERENCIA(@PathParam("id") Integer id) {
		QUA_CR_DIC_REFERENCIA e = new QUA_CR_DIC_REFERENCIA();
		e.setID_REFERENCIA(id);
		daoReferencia.delete(e);
	}

	/************************************* QUA_CR_DIC_REF_TESTE */
	@POST
	@Path("/createQUA_CR_DIC_REF_TESTE")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_REF_TESTE insertQUA_CR_DIC_REF_TESTE(final QUA_CR_DIC_REF_TESTE data) {
		return daoRefTeste.create(data);
	}

	@GET
	@Path("/getQUA_CR_DIC_REF_TESTE")
	@Produces("application/json")
	public List<QUA_CR_DIC_REF_TESTE> getQUA_CR_DIC_REF_TESTE() {
		return daoRefTeste.getall();
	}

	@GET
	@Path("/getQUA_CR_DIC_REF_TESTEbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_REF_TESTE> getQUA_CR_DIC_REF_TESTEbyid(@PathParam("id") Integer id) {
		return daoRefTeste.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_REF_TESTE")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_REF_TESTE updateQUA_CR_DIC_REF_TESTE(final QUA_CR_DIC_REF_TESTE data) {
		return daoRefTeste.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_REF_TESTE/{id}")
	public void deleteQUA_CR_DIC_REF_TESTE(@PathParam("id") Integer id) {
		QUA_CR_DIC_REF_TESTE e = new QUA_CR_DIC_REF_TESTE();
		e.setID_REF_TESTE(id);
		daoRefTeste.delete(e);
	}
	@GET
	@Path("/getQUA_CR_DIC_REF_TESTEbyReferencia/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_REF_TESTE> getQUA_CR_DIC_REF_TESTEbyReferencia(@PathParam("id") Integer id) {
		return daoRefTeste.getbyReferencia(id);
	}

	/************************************* QUA_CR_DIC_REFERENCIA_FICHEIROS */
	@POST
	@Path("/createQUA_CR_DIC_REFERENCIA_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_REFERENCIA_FICHEIROS insertQUA_CR_DIC_REFERENCIA_FICHEIROS(final QUA_CR_DIC_REFERENCIA_FICHEIROS data) {
		return daoRefFicheiros.create(data);
	}

	// Sem endpoint "getall" de proposito: seriam ~250 MB de fotografias numa
	// resposta. Para listar, getQUA_CR_DIC_REFERENCIA_FICHEIROSbyReferencia
	// (metadados) e depois ...FICHEIROScontent/{id} por ficheiro.

	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA_FICHEIROSbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_DIC_REFERENCIA_FICHEIROS> getQUA_CR_DIC_REFERENCIA_FICHEIROSbyid(@PathParam("id") Integer id) {
		return daoRefFicheiros.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_DIC_REFERENCIA_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_DIC_REFERENCIA_FICHEIROS updateQUA_CR_DIC_REFERENCIA_FICHEIROS(final QUA_CR_DIC_REFERENCIA_FICHEIROS data) {
		return daoRefFicheiros.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_DIC_REFERENCIA_FICHEIROS/{id}")
	public void deleteQUA_CR_DIC_REFERENCIA_FICHEIROS(@PathParam("id") Integer id) {
		QUA_CR_DIC_REFERENCIA_FICHEIROS e = new QUA_CR_DIC_REFERENCIA_FICHEIROS();
		e.setID(id);
		daoRefFicheiros.delete(e);
	}

	/************************************* QUA_CR_MOV_RELATORIO */
	@POST
	@Path("/createQUA_CR_MOV_RELATORIO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_RELATORIO insertQUA_CR_MOV_RELATORIO(final QUA_CR_MOV_RELATORIO data) {
		return daoRelatorio.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_RELATORIO")
	@Produces("application/json")
	public List<QUA_CR_MOV_RELATORIO> getQUA_CR_MOV_RELATORIO() {
		return daoRelatorio.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_RELATORIObyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_RELATORIO> getQUA_CR_MOV_RELATORIObyid(@PathParam("id") Integer id) {
		return daoRelatorio.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_RELATORIO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_RELATORIO updateQUA_CR_MOV_RELATORIO(final QUA_CR_MOV_RELATORIO data) {
		return daoRelatorio.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_RELATORIO/{id}")
	public void deleteQUA_CR_MOV_RELATORIO(@PathParam("id") Integer id) {
		QUA_CR_MOV_RELATORIO e = new QUA_CR_MOV_RELATORIO();
		e.setID_RELATORIO(id);
		daoRelatorio.delete(e);
	}

	/************************************* QUA_CR_MOV_CONDICOES_CAB */
	@POST
	@Path("/createQUA_CR_MOV_CONDICOES_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CONDICOES_CAB insertQUA_CR_MOV_CONDICOES_CAB(final QUA_CR_MOV_CONDICOES_CAB data) {
		return daoCondicoesCab.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_CONDICOES_CAB")
	@Produces("application/json")
	public List<QUA_CR_MOV_CONDICOES_CAB> getQUA_CR_MOV_CONDICOES_CAB() {
		return daoCondicoesCab.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_CONDICOES_CABbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CONDICOES_CAB> getQUA_CR_MOV_CONDICOES_CABbyid(@PathParam("id") Integer id) {
		return daoCondicoesCab.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_CONDICOES_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CONDICOES_CAB updateQUA_CR_MOV_CONDICOES_CAB(final QUA_CR_MOV_CONDICOES_CAB data) {
		return daoCondicoesCab.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_CONDICOES_CAB/{id}")
	public void deleteQUA_CR_MOV_CONDICOES_CAB(@PathParam("id") Integer id) {
		QUA_CR_MOV_CONDICOES_CAB e = new QUA_CR_MOV_CONDICOES_CAB();
		e.setID_CONDICOES_CAB(id);
		daoCondicoesCab.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_CONDICOES_CABbyRelatorio/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CONDICOES_CAB> getQUA_CR_MOV_CONDICOES_CABbyRelatorio(@PathParam("id") Integer id) {
		return daoCondicoesCab.getbyRelatorio(id);
	}

	/************************************* QUA_CR_MOV_CONDICOES */
	@POST
	@Path("/createQUA_CR_MOV_CONDICOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CONDICOES insertQUA_CR_MOV_CONDICOES(final QUA_CR_MOV_CONDICOES data) {
		return daoCondicoes.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_CONDICOES")
	@Produces("application/json")
	public List<QUA_CR_MOV_CONDICOES> getQUA_CR_MOV_CONDICOES() {
		return daoCondicoes.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_CONDICOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CONDICOES> getQUA_CR_MOV_CONDICOESbyid(@PathParam("id") Integer id) {
		return daoCondicoes.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_CONDICOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CONDICOES updateQUA_CR_MOV_CONDICOES(final QUA_CR_MOV_CONDICOES data) {
		return daoCondicoes.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_CONDICOES/{id}")
	public void deleteQUA_CR_MOV_CONDICOES(@PathParam("id") Integer id) {
		QUA_CR_MOV_CONDICOES e = new QUA_CR_MOV_CONDICOES();
		e.setID_CONDICAO(id);
		daoCondicoes.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_CONDICOESbyCondicoesCab/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CONDICOES> getQUA_CR_MOV_CONDICOESbyCondicoesCab(@PathParam("id") Integer id) {
		return daoCondicoes.getbyCondicoesCab(id);
	}

	/************************************* QUA_CR_MOV_ESPESSURA_CAB */
	@POST
	@Path("/createQUA_CR_MOV_ESPESSURA_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ESPESSURA_CAB insertQUA_CR_MOV_ESPESSURA_CAB(final QUA_CR_MOV_ESPESSURA_CAB data) {
		return daoEspessuraCab.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_ESPESSURA_CAB")
	@Produces("application/json")
	public List<QUA_CR_MOV_ESPESSURA_CAB> getQUA_CR_MOV_ESPESSURA_CAB() {
		return daoEspessuraCab.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_ESPESSURA_CABbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ESPESSURA_CAB> getQUA_CR_MOV_ESPESSURA_CABbyid(@PathParam("id") Integer id) {
		return daoEspessuraCab.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_ESPESSURA_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ESPESSURA_CAB updateQUA_CR_MOV_ESPESSURA_CAB(final QUA_CR_MOV_ESPESSURA_CAB data) {
		return daoEspessuraCab.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_ESPESSURA_CAB/{id}")
	public void deleteQUA_CR_MOV_ESPESSURA_CAB(@PathParam("id") Integer id) {
		QUA_CR_MOV_ESPESSURA_CAB e = new QUA_CR_MOV_ESPESSURA_CAB();
		e.setID_ESPESSURA_CAB(id);
		daoEspessuraCab.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_ESPESSURA_CABbyRelatorio/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ESPESSURA_CAB> getQUA_CR_MOV_ESPESSURA_CABbyRelatorio(@PathParam("id") Integer id) {
		return daoEspessuraCab.getbyRelatorio(id);
	}

	/************************************* QUA_CR_MOV_ESPESSURA */
	@POST
	@Path("/createQUA_CR_MOV_ESPESSURA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ESPESSURA insertQUA_CR_MOV_ESPESSURA(final QUA_CR_MOV_ESPESSURA data) {
		return daoEspessura.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_ESPESSURA")
	@Produces("application/json")
	public List<QUA_CR_MOV_ESPESSURA> getQUA_CR_MOV_ESPESSURA() {
		return daoEspessura.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_ESPESSURAbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ESPESSURA> getQUA_CR_MOV_ESPESSURAbyid(@PathParam("id") Integer id) {
		return daoEspessura.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_ESPESSURA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ESPESSURA updateQUA_CR_MOV_ESPESSURA(final QUA_CR_MOV_ESPESSURA data) {
		return daoEspessura.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_ESPESSURA/{id}")
	public void deleteQUA_CR_MOV_ESPESSURA(@PathParam("id") Integer id) {
		QUA_CR_MOV_ESPESSURA e = new QUA_CR_MOV_ESPESSURA();
		e.setID_ESPESSURA(id);
		daoEspessura.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_ESPESSURAbyEspessuraCab/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ESPESSURA> getQUA_CR_MOV_ESPESSURAbyEspessuraCab(@PathParam("id") Integer id) {
		return daoEspessura.getbyEspessuraCab(id);
	}

	/************************************* QUA_CR_MOV_ENSAIO_CAB */
	@POST
	@Path("/createQUA_CR_MOV_ENSAIO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ENSAIO_CAB insertQUA_CR_MOV_ENSAIO_CAB(final QUA_CR_MOV_ENSAIO_CAB data) {
		return daoEnsaioCab.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_CAB")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_CAB> getQUA_CR_MOV_ENSAIO_CAB() {
		return daoEnsaioCab.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_CABbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_CAB> getQUA_CR_MOV_ENSAIO_CABbyid(@PathParam("id") Integer id) {
		return daoEnsaioCab.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_ENSAIO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ENSAIO_CAB updateQUA_CR_MOV_ENSAIO_CAB(final QUA_CR_MOV_ENSAIO_CAB data) {
		return daoEnsaioCab.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_ENSAIO_CAB/{id}")
	public void deleteQUA_CR_MOV_ENSAIO_CAB(@PathParam("id") Integer id) {
		QUA_CR_MOV_ENSAIO_CAB e = new QUA_CR_MOV_ENSAIO_CAB();
		e.setID_ENSAIO_CAB(id);
		daoEnsaioCab.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_CABbyRelatorio/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_CAB> getQUA_CR_MOV_ENSAIO_CABbyRelatorio(@PathParam("id") Integer id) {
		return daoEnsaioCab.getbyRelatorio(id);
	}

	/************************************* QUA_CR_MOV_ENSAIO_TESTE */
	@POST
	@Path("/createQUA_CR_MOV_ENSAIO_TESTE")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ENSAIO_TESTE insertQUA_CR_MOV_ENSAIO_TESTE(final QUA_CR_MOV_ENSAIO_TESTE data) {
		return daoEnsaioTeste.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_TESTE")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_TESTE> getQUA_CR_MOV_ENSAIO_TESTE() {
		return daoEnsaioTeste.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_TESTEbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_TESTE> getQUA_CR_MOV_ENSAIO_TESTEbyid(@PathParam("id") Integer id) {
		return daoEnsaioTeste.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_ENSAIO_TESTE")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ENSAIO_TESTE updateQUA_CR_MOV_ENSAIO_TESTE(final QUA_CR_MOV_ENSAIO_TESTE data) {
		return daoEnsaioTeste.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_ENSAIO_TESTE/{id}")
	public void deleteQUA_CR_MOV_ENSAIO_TESTE(@PathParam("id") Integer id) {
		QUA_CR_MOV_ENSAIO_TESTE e = new QUA_CR_MOV_ENSAIO_TESTE();
		e.setID_ENSAIO_TESTE(id);
		daoEnsaioTeste.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_TESTEbyEnsaioCab/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_TESTE> getQUA_CR_MOV_ENSAIO_TESTEbyEnsaioCab(@PathParam("id") Integer id) {
		return daoEnsaioTeste.getbyEnsaioCab(id);
	}

	/************************************* QUA_CR_MOV_ENSAIO_LEITURA */
	@POST
	@Path("/createQUA_CR_MOV_ENSAIO_LEITURA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ENSAIO_LEITURA insertQUA_CR_MOV_ENSAIO_LEITURA(final QUA_CR_MOV_ENSAIO_LEITURA data) {
		return daoEnsaioLeitura.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_LEITURA")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_LEITURA> getQUA_CR_MOV_ENSAIO_LEITURA() {
		return daoEnsaioLeitura.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_LEITURAbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_LEITURA> getQUA_CR_MOV_ENSAIO_LEITURAbyid(@PathParam("id") Integer id) {
		return daoEnsaioLeitura.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_ENSAIO_LEITURA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_ENSAIO_LEITURA updateQUA_CR_MOV_ENSAIO_LEITURA(final QUA_CR_MOV_ENSAIO_LEITURA data) {
		return daoEnsaioLeitura.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_ENSAIO_LEITURA/{id}")
	public void deleteQUA_CR_MOV_ENSAIO_LEITURA(@PathParam("id") Integer id) {
		QUA_CR_MOV_ENSAIO_LEITURA e = new QUA_CR_MOV_ENSAIO_LEITURA();
		e.setID_ENSAIO_LEITURA(id);
		daoEnsaioLeitura.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_ENSAIO_LEITURAbyEnsaioTeste/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_ENSAIO_LEITURA> getQUA_CR_MOV_ENSAIO_LEITURAbyEnsaioTeste(@PathParam("id") Integer id) {
		return daoEnsaioLeitura.getbyEnsaioTeste(id);
	}

	/************************************* QUA_CR_MOV_CORROSAO_CAB */
	@POST
	@Path("/createQUA_CR_MOV_CORROSAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CORROSAO_CAB insertQUA_CR_MOV_CORROSAO_CAB(final QUA_CR_MOV_CORROSAO_CAB data) {
		return daoCorrosaoCab.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_CAB")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_CAB> getQUA_CR_MOV_CORROSAO_CAB() {
		return daoCorrosaoCab.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_CABbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_CAB> getQUA_CR_MOV_CORROSAO_CABbyid(@PathParam("id") Integer id) {
		return daoCorrosaoCab.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_CORROSAO_CAB")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CORROSAO_CAB updateQUA_CR_MOV_CORROSAO_CAB(final QUA_CR_MOV_CORROSAO_CAB data) {
		return daoCorrosaoCab.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_CORROSAO_CAB/{id}")
	public void deleteQUA_CR_MOV_CORROSAO_CAB(@PathParam("id") Integer id) {
		QUA_CR_MOV_CORROSAO_CAB e = new QUA_CR_MOV_CORROSAO_CAB();
		e.setID_CORROSAO_CAB(id);
		daoCorrosaoCab.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_CABbyRelatorio/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_CAB> getQUA_CR_MOV_CORROSAO_CABbyRelatorio(@PathParam("id") Integer id) {
		return daoCorrosaoCab.getbyRelatorio(id);
	}

	/************************************* QUA_CR_MOV_CORROSAO_AMOSTRA */
	@POST
	@Path("/createQUA_CR_MOV_CORROSAO_AMOSTRA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CORROSAO_AMOSTRA insertQUA_CR_MOV_CORROSAO_AMOSTRA(final QUA_CR_MOV_CORROSAO_AMOSTRA data) {
		return daoCorrosaoAmostra.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_AMOSTRA")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_AMOSTRA> getQUA_CR_MOV_CORROSAO_AMOSTRA() {
		return daoCorrosaoAmostra.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_AMOSTRAbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_AMOSTRA> getQUA_CR_MOV_CORROSAO_AMOSTRAbyid(@PathParam("id") Integer id) {
		return daoCorrosaoAmostra.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_CORROSAO_AMOSTRA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CORROSAO_AMOSTRA updateQUA_CR_MOV_CORROSAO_AMOSTRA(final QUA_CR_MOV_CORROSAO_AMOSTRA data) {
		return daoCorrosaoAmostra.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_CORROSAO_AMOSTRA/{id}")
	public void deleteQUA_CR_MOV_CORROSAO_AMOSTRA(@PathParam("id") Integer id) {
		QUA_CR_MOV_CORROSAO_AMOSTRA e = new QUA_CR_MOV_CORROSAO_AMOSTRA();
		e.setID_CORROSAO_AMOSTRA(id);
		daoCorrosaoAmostra.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_AMOSTRAbyCorrosaoCab/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_AMOSTRA> getQUA_CR_MOV_CORROSAO_AMOSTRAbyCorrosaoCab(@PathParam("id") Integer id) {
		return daoCorrosaoAmostra.getbyCorrosaoCab(id);
	}

	/************************************* QUA_CR_MOV_CORROSAO_LEITURA */
	@POST
	@Path("/createQUA_CR_MOV_CORROSAO_LEITURA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CORROSAO_LEITURA insertQUA_CR_MOV_CORROSAO_LEITURA(final QUA_CR_MOV_CORROSAO_LEITURA data) {
		return daoCorrosaoLeitura.create(data);
	}

	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_LEITURA")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_LEITURA> getQUA_CR_MOV_CORROSAO_LEITURA() {
		return daoCorrosaoLeitura.getall();
	}

	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_LEITURAbyid/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_LEITURA> getQUA_CR_MOV_CORROSAO_LEITURAbyid(@PathParam("id") Integer id) {
		return daoCorrosaoLeitura.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_CR_MOV_CORROSAO_LEITURA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_CORROSAO_LEITURA updateQUA_CR_MOV_CORROSAO_LEITURA(final QUA_CR_MOV_CORROSAO_LEITURA data) {
		return daoCorrosaoLeitura.update(data);
	}

	@DELETE
	@Path("/deleteQUA_CR_MOV_CORROSAO_LEITURA/{id}")
	public void deleteQUA_CR_MOV_CORROSAO_LEITURA(@PathParam("id") Integer id) {
		QUA_CR_MOV_CORROSAO_LEITURA e = new QUA_CR_MOV_CORROSAO_LEITURA();
		e.setID_CORROSAO_LEITURA(id);
		daoCorrosaoLeitura.delete(e);
	}
	@GET
	@Path("/getQUA_CR_MOV_CORROSAO_LEITURAbyCorrosaoAmostra/{id}")
	@Produces("application/json")
	public List<QUA_CR_MOV_CORROSAO_LEITURA> getQUA_CR_MOV_CORROSAO_LEITURAbyCorrosaoAmostra(@PathParam("id") Integer id) {
		return daoCorrosaoLeitura.getbyCorrosaoAmostra(id);
	}

	/************************************* LISTAGENS */

	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_DIC_REFERENCIA_LISTA() {
		return daoReferencia.getlista();
	}

	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA_DROPDOWN")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_DIC_REFERENCIA_DROPDOWN() {
		return daoReferencia.getdropdown();
	}

	// ano = 0 devolve todos os anos.
	@GET
	@Path("/getQUA_CR_MOV_RELATORIO_LISTA/{ano}")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_MOV_RELATORIO_LISTA(@PathParam("ano") Integer ano) {
		return daoRelatorio.getlista(ano);
	}

	// Substitui os 4 relatorios "SEG PCS CROMADAS" do Access.
	@GET
	@Path("/getQUA_CR_MOV_RELATORIO_SEGUIMENTO/{idReferencia}")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_MOV_RELATORIO_SEGUIMENTO(@PathParam("idReferencia") Integer idReferencia) {
		return daoRelatorio.getlistabyReferencia(idReferencia);
	}

	/************************************* ANALISES */

	// Substitui os relatorios Access "EVOLUCAO RESULTADOS" e "EVOLUCAO DE
	// ENSAIOS - DOURECA" (que era o mesmo relatorio, filtrado pelo local).
	// idLocal = 0 nao filtra. Datas em yyyy-MM-dd.
	@GET
	@Path("/getQUA_CR_ANALISE_EVOLUCAO/{dataIni}/{dataFim}/{idLocal}")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_ANALISE_EVOLUCAO(@PathParam("dataIni") String dataIni,
			@PathParam("dataFim") String dataFim, @PathParam("idLocal") Integer idLocal) {
		return daoRelatorio.getanaliseevolucao(dataIni, dataFim, idLocal);
	}

	// A mesma contagem, agrupada por referencia em vez de por mes.
	@GET
	@Path("/getQUA_CR_ANALISE_POR_REFERENCIA/{dataIni}/{dataFim}/{idLocal}")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_ANALISE_POR_REFERENCIA(@PathParam("dataIni") String dataIni,
			@PathParam("dataFim") String dataFim, @PathParam("idLocal") Integer idLocal) {
		return daoRelatorio.getanaliseporreferencia(dataIni, dataFim, idLocal);
	}

	/************************************* ANEXOS DA REFERENCIA */

	// So metadados. As fotografias sao ~250 MB no total, portanto o conteudo
	// nunca vem numa listagem - pede-se um a um pelo endpoint content.
	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA_FICHEIROSbyReferencia/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_DIC_REFERENCIA_FICHEIROSbyReferencia(@PathParam("id") Integer id) {
		return daoRefFicheiros.getbyReferencia(id);
	}

	// A fotografia da referencia aparece no cabecalho do ecra de ensaio.
	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA_FICHEIROSbyCategoria/{id}/{categoria}")
	@Produces("application/json")
	public List<Object[]> getQUA_CR_DIC_REFERENCIA_FICHEIROSbyCategoria(
			@PathParam("id") Integer id, @PathParam("categoria") String categoria) {
		return daoRefFicheiros.getbyReferenciaCategoria(id, categoria);
	}

	@GET
	@Path("/getQUA_CR_DIC_REFERENCIA_FICHEIROScontent/{id}")
	@Produces("application/json")
	public String getQUA_CR_DIC_REFERENCIA_FICHEIROScontent(@PathParam("id") Integer id) {
		return daoRefFicheiros.getConteudo(id);
	}

	/************************************* NUMERACAO DO RELATORIO */

	// Sequencia por ano. Conta tambem sobre os anulados, para nunca reutilizar
	// um numero que ja saiu impresso num boletim.
	@GET
	@Path("/getQUA_CR_PROXIMO_NUMERO/{ano}")
	@Produces("application/json")
	public Map<String, Object> getQUA_CR_PROXIMO_NUMERO(@PathParam("ano") Integer ano) {
		Map<String, Object> r = new HashMap<String, Object>();
		Integer num = daoRelatorio.proximoNumero(ano);
		r.put("ANO", ano);
		r.put("NUM_SEQ", num);
		r.put("N_RELAT_CR", num + "/" + ano);
		return r;
	}

	/************************************* BOLETIM COMPLETO */

	/**
	 * Grava o boletim todo numa chamada: cabecalho + as quatro tabs.
	 *
	 * Estrategia: grava/atualiza o cabecalho, apaga as linhas antigas de cada
	 * bloco e recria-as. E o padrao de createQUA_EPI_ENTREGA_COMPLETA, e evita
	 * ter de reconciliar linha a linha o que o utilizador acrescentou, alterou
	 * ou removeu na grelha.
	 *
	 * Um bloco a null significa "esta tab nao foi preenchida" e nao mexe no que
	 * la esta; para limpar um bloco, envia-se o cabecalho com a lista vazia.
	 *
	 * Tudo corre na transaccao JTA do @Stateless: ou grava o boletim inteiro,
	 * ou nao grava nada.
	 */
	@POST
	@Path("/createQUA_CR_RELATORIO_COMPLETO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_CR_MOV_RELATORIO createQUA_CR_RELATORIO_COMPLETO(final QUA_CR_RELATORIO_DTO dto) {
		QUA_CR_MOV_RELATORIO rel = dto.getRELATORIO();
		rel.setATIVO(true);

		if (rel.getID_RELATORIO() == null) {
			// Numero atribuido aqui e nao no ecra, para dois utilizadores a
			// gravar ao mesmo tempo nao ficarem com o mesmo numero.
			if (rel.getANO() == null) {
				rel.setANO(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
			}
			if (rel.getNUM_SEQ() == null) {
				rel.setNUM_SEQ(daoRelatorio.proximoNumero(rel.getANO()));
			}
			if (rel.getN_RELAT_CR() == null || rel.getN_RELAT_CR().trim().isEmpty()) {
				rel.setN_RELAT_CR(rel.getNUM_SEQ() + "/" + rel.getANO());
			}
			if (rel.getDATA_REGISTO() == null) {
				rel.setDATA_REGISTO(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			rel = daoRelatorio.create(rel);
		} else {
			rel = daoRelatorio.update(rel);
		}
		Integer idRel = rel.getID_RELATORIO();

		// ---- Tab Condicoes ----
		if (dto.getCONDICOES_CAB() != null) {
			QUA_CR_MOV_CONDICOES_CAB cab = dto.getCONDICOES_CAB();
			cab.setID_RELATORIO(idRel);
			cab.setATIVO(true);
			cab = (cab.getID_CONDICOES_CAB() == null)
				? daoCondicoesCab.create(cab) : daoCondicoesCab.update(cab);

			apagarFilhos("QUA_CR_MOV_CONDICOES", "ID_CONDICOES_CAB", cab.getID_CONDICOES_CAB());
			if (dto.getCONDICOES() != null) {
				for (QUA_CR_MOV_CONDICOES lin : dto.getCONDICOES()) {
					lin.setID_CONDICAO(null);
					lin.setID_CONDICOES_CAB(cab.getID_CONDICOES_CAB());
					lin.setATIVO(true);
					daoCondicoes.create(lin);
				}
			}
		}

		// ---- Tab Espessuras / Poros / Fissuras ----
		if (dto.getESPESSURA_CAB() != null) {
			QUA_CR_MOV_ESPESSURA_CAB cab = dto.getESPESSURA_CAB();
			cab.setID_RELATORIO(idRel);
			cab.setATIVO(true);
			cab = (cab.getID_ESPESSURA_CAB() == null)
				? daoEspessuraCab.create(cab) : daoEspessuraCab.update(cab);

			apagarFilhos("QUA_CR_MOV_ESPESSURA", "ID_ESPESSURA_CAB", cab.getID_ESPESSURA_CAB());
			if (dto.getESPESSURAS() != null) {
				for (QUA_CR_MOV_ESPESSURA lin : dto.getESPESSURAS()) {
					lin.setID_ESPESSURA(null);
					lin.setID_ESPESSURA_CAB(cab.getID_ESPESSURA_CAB());
					lin.setATIVO(true);
					daoEspessura.create(lin);
				}
			}
		}

		// ---- Tab Pautas de Ensaio (tres niveis) ----
		if (dto.getENSAIO_CAB() != null) {
			QUA_CR_MOV_ENSAIO_CAB cab = dto.getENSAIO_CAB();
			cab.setID_RELATORIO(idRel);
			cab.setATIVO(true);
			cab = (cab.getID_ENSAIO_CAB() == null)
				? daoEnsaioCab.create(cab) : daoEnsaioCab.update(cab);

			// As leituras primeiro, senao ficam orfas.
			apagarNetos("QUA_CR_MOV_ENSAIO_LEITURA", "ID_ENSAIO_TESTE",
			            "QUA_CR_MOV_ENSAIO_TESTE", "ID_ENSAIO_CAB", cab.getID_ENSAIO_CAB());
			apagarFilhos("QUA_CR_MOV_ENSAIO_TESTE", "ID_ENSAIO_CAB", cab.getID_ENSAIO_CAB());

			if (dto.getTESTES() != null) {
				for (QUA_CR_ENSAIO_TESTE_DTO bloco : dto.getTESTES()) {
					QUA_CR_MOV_ENSAIO_TESTE teste = bloco.getTESTE();
					if (teste == null) { continue; }
					teste.setID_ENSAIO_TESTE(null);
					teste.setID_ENSAIO_CAB(cab.getID_ENSAIO_CAB());
					teste.setATIVO(true);
					teste = daoEnsaioTeste.create(teste);

					if (bloco.getLEITURAS() != null) {
						for (QUA_CR_MOV_ENSAIO_LEITURA leit : bloco.getLEITURAS()) {
							leit.setID_ENSAIO_LEITURA(null);
							leit.setID_ENSAIO_TESTE(teste.getID_ENSAIO_TESTE());
							leit.setATIVO(true);
							daoEnsaioLeitura.create(leit);
						}
					}
				}
			}
		}

		// ---- Tab NSS / CaCl2 (tres niveis) ----
		if (dto.getCORROSAO_CAB() != null) {
			QUA_CR_MOV_CORROSAO_CAB cab = dto.getCORROSAO_CAB();
			cab.setID_RELATORIO(idRel);
			cab.setATIVO(true);
			cab = (cab.getID_CORROSAO_CAB() == null)
				? daoCorrosaoCab.create(cab) : daoCorrosaoCab.update(cab);

			apagarNetos("QUA_CR_MOV_CORROSAO_LEITURA", "ID_CORROSAO_AMOSTRA",
			            "QUA_CR_MOV_CORROSAO_AMOSTRA", "ID_CORROSAO_CAB", cab.getID_CORROSAO_CAB());
			apagarFilhos("QUA_CR_MOV_CORROSAO_AMOSTRA", "ID_CORROSAO_CAB", cab.getID_CORROSAO_CAB());

			if (dto.getAMOSTRAS_CORROSAO() != null) {
				for (QUA_CR_CORROSAO_AMOSTRA_DTO bloco : dto.getAMOSTRAS_CORROSAO()) {
					QUA_CR_MOV_CORROSAO_AMOSTRA am = bloco.getAMOSTRA();
					if (am == null) { continue; }
					am.setID_CORROSAO_AMOSTRA(null);
					am.setID_CORROSAO_CAB(cab.getID_CORROSAO_CAB());
					am.setATIVO(true);
					am = daoCorrosaoAmostra.create(am);

					if (bloco.getLEITURAS() != null) {
						for (QUA_CR_MOV_CORROSAO_LEITURA leit : bloco.getLEITURAS()) {
							leit.setID_CORROSAO_LEITURA(null);
							leit.setID_CORROSAO_AMOSTRA(am.getID_CORROSAO_AMOSTRA());
							leit.setATIVO(true);
							daoCorrosaoLeitura.create(leit);
						}
					}
				}
			}
		}

		return rel;
	}

	/**
	 * Carrega o boletim todo para a ficha, numa chamada.
	 *
	 * Sem isto o ecra faria 9 pedidos encadeados (cabecalho, depois cada
	 * cabecalho de bloco, depois as linhas de cada um, depois as leituras de
	 * cada teste e de cada amostra).
	 *
	 * Devolve o cabecalho de cada bloco mais recente, que e o que a ficha
	 * mostra - um relatorio pode ter medicoes repetidas.
	 */
	@GET
	@Path("/getQUA_CR_RELATORIO_COMPLETO/{id}")
	@Produces("application/json")
	public Map<String, Object> getQUA_CR_RELATORIO_COMPLETO(@PathParam("id") Integer id) {
		Map<String, Object> r = new HashMap<String, Object>();

		List<QUA_CR_MOV_RELATORIO> rels = daoRelatorio.getbyid(id);
		if (rels.isEmpty()) { return r; }
		r.put("RELATORIO", rels.get(0));

		List<QUA_CR_MOV_CONDICOES_CAB> cond = daoCondicoesCab.getbyRelatorio(id);
		if (!cond.isEmpty()) {
			QUA_CR_MOV_CONDICOES_CAB cab = cond.get(cond.size() - 1);
			r.put("CONDICOES_CAB", cab);
			r.put("CONDICOES", daoCondicoes.getbyCondicoesCab(cab.getID_CONDICOES_CAB()));
		}

		List<QUA_CR_MOV_ESPESSURA_CAB> esp = daoEspessuraCab.getbyRelatorio(id);
		if (!esp.isEmpty()) {
			QUA_CR_MOV_ESPESSURA_CAB cab = esp.get(esp.size() - 1);
			r.put("ESPESSURA_CAB", cab);
			r.put("ESPESSURAS", daoEspessura.getbyEspessuraCab(cab.getID_ESPESSURA_CAB()));
		}

		List<QUA_CR_MOV_ENSAIO_CAB> ens = daoEnsaioCab.getbyRelatorio(id);
		if (!ens.isEmpty()) {
			QUA_CR_MOV_ENSAIO_CAB cab = ens.get(ens.size() - 1);
			r.put("ENSAIO_CAB", cab);
			List<Map<String, Object>> testes = new ArrayList<Map<String, Object>>();
			for (QUA_CR_MOV_ENSAIO_TESTE t : daoEnsaioTeste.getbyEnsaioCab(cab.getID_ENSAIO_CAB())) {
				Map<String, Object> bloco = new HashMap<String, Object>();
				bloco.put("TESTE", t);
				bloco.put("LEITURAS", daoEnsaioLeitura.getbyEnsaioTeste(t.getID_ENSAIO_TESTE()));
				testes.add(bloco);
			}
			r.put("TESTES", testes);
		}

		List<QUA_CR_MOV_CORROSAO_CAB> cor = daoCorrosaoCab.getbyRelatorio(id);
		if (!cor.isEmpty()) {
			QUA_CR_MOV_CORROSAO_CAB cab = cor.get(cor.size() - 1);
			r.put("CORROSAO_CAB", cab);
			List<Map<String, Object>> amostras = new ArrayList<Map<String, Object>>();
			for (QUA_CR_MOV_CORROSAO_AMOSTRA a : daoCorrosaoAmostra.getbyCorrosaoCab(cab.getID_CORROSAO_CAB())) {
				Map<String, Object> bloco = new HashMap<String, Object>();
				bloco.put("AMOSTRA", a);
				bloco.put("LEITURAS", daoCorrosaoLeitura.getbyCorrosaoAmostra(a.getID_CORROSAO_AMOSTRA()));
				amostras.add(bloco);
			}
			r.put("AMOSTRAS_CORROSAO", amostras);
		}

		return r;
	}

	// Apaga a serio (nao e soft delete): estas linhas vao ser recriadas a
	// seguir a partir do que o ecra enviou, e manter as antigas com ATIVO=0
	// encheria a tabela a cada gravacao do mesmo boletim.
	private void apagarFilhos(String tabela, String campoPai, Integer idPai) {
		if (idPai == null) { return; }
		Query q = entityManager.createNativeQuery(
			"DELETE FROM " + tabela + " WHERE " + campoPai + " = :id");
		q.setParameter("id", idPai);
		q.executeUpdate();
	}

	private void apagarNetos(String tabela, String campoPai,
	                         String tabelaPai, String campoAvo, Integer idAvo) {
		if (idAvo == null) { return; }
		Query q = entityManager.createNativeQuery(
			"DELETE FROM " + tabela + " WHERE " + campoPai + " IN "
			+ "( SELECT " + campoPai + " FROM " + tabelaPai + " WHERE " + campoAvo + " = :id )");
		q.setParameter("id", idAvo);
		q.executeUpdate();
	}
}
