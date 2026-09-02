package pt.example.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.example.bootstrap.ConnectProgress;
import pt.example.bootstrap.ReportGenerator;
import pt.example.bootstrap.SendEmail;
import pt.example.entity.EMAIL;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pt.example.dao.QUA_EPI_DIC_FAMILIADao;
import pt.example.dao.QUA_EPI_DIC_EPIDao;
import pt.example.dao.QUA_EPI_LOCALDao;
import pt.example.dao.QUA_EPI_LOCAL_RESPDao;
import pt.example.entity.QUA_EPI_LOCAL;
import pt.example.dao.RH_SECTORES_EPI_FAMILIADao;
import pt.example.dao.QUA_EPI_FUNCDao;
import pt.example.dao.QUA_EPI_MOV_PEDIDODao;
import pt.example.dao.QUA_EPI_MOV_PEDIDO_LINDao;
import pt.example.dao.QUA_EPI_MOV_PEDIDO_HISTDao;
import pt.example.dao.GER_EVENTOS_CONFDao;
import pt.example.entity.GER_EVENTOS_CONF;
import pt.example.dao.QUA_EPI_MOV_ENTREGADao;
import pt.example.dao.QUA_EPI_MOV_ENTREGA_ETIQDao;
import pt.example.entity.QUA_EPI_MOV_ENTREGA;
import pt.example.entity.QUA_EPI_MOV_ENTREGA_ETIQ;
import pt.example.entity.QUA_EPI_ENTREGA_DTO;
import pt.example.entity.QUA_EPI_MOV_PEDIDO;
import pt.example.entity.QUA_EPI_MOV_PEDIDO_LIN;
import pt.example.entity.QUA_EPI_MOV_PEDIDO_HIST;
import pt.example.entity.QUA_EPI_DIC_FAMILIA;
import pt.example.entity.QUA_EPI_DIC_EPI;
import pt.example.entity.QUA_EPI_LOCAL_RESP;
import pt.example.entity.RH_SECTORES_EPI_FAMILIA;
import pt.example.entity.QUA_EPI_FUNC;
import pt.example.dao.QUA_MC_DIC_SECCOESDao;
import pt.example.dao.QUA_MC_DIC_RESP_VALIDODao;
import pt.example.dao.QUA_MC_DIC_TIPO_CALIBRDao;
import pt.example.dao.QUA_MC_DIC_TIPO_ACEITDao;
import pt.example.dao.QUA_MC_DIC_TIPO_VERIF_GABARITDao;
import pt.example.dao.QUA_MC_DIC_RESULT_VALIDDao;
import pt.example.dao.QUA_MC_DIC_PECAS_CROMDao;
import pt.example.dao.QUA_MC_ENTIDADES_CALIBRDao;
import pt.example.dao.QUA_MC_ENTIDADES_CERTIFDao;
import pt.example.dao.QUA_MC_EQUIPAMENTOSDao;
import pt.example.dao.QUA_MC_MOV_CALIB_EQUIPDao;
import pt.example.dao.QUA_MC_MOV_CALIB_EQUIP_DETDao;
import pt.example.dao.QUA_MC_GABARITOSDao;
import pt.example.dao.QUA_MC_MOV_VERIF_GABARITODao;
import pt.example.dao.QUA_MC_MOV_VERIF_INT_GABARITODao;
import pt.example.dao.QUA_MC_MAQUINASDao;
import pt.example.dao.QUA_MC_MOV_VERIF_MAQUINADao;
import pt.example.dao.QUA_MC_SALASDao;
import pt.example.dao.QUA_MC_MOV_VERIF_SALADao;
import pt.example.dao.QUA_MC_DIC_ESTADO_METROLOGICODao;
import pt.example.dao.QUA_MC_DIC_MSADao;
import pt.example.dao.QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSDao;
import pt.example.dao.QUA_MC_EQUIPAMENTOS_FICHEIROSDao;
import pt.example.dao.QUA_MC_GABARITOS_FICHEIROSDao;
import pt.example.dao.QUA_MC_MOV_VERIF_GABARITO_FICHEIROSDao;
import pt.example.dao.QUA_MC_MOV_CALIB_EQUIP_FICHEIROSDao;
import pt.example.dao.QUA_MC_DERROGACOESDao;
import pt.example.dao.QUA_MC_DERROGACOES_ACOESDao;
import pt.example.dao.QUA_MC_DERROGACOES_FICHEIROSDao_MC;
import pt.example.dao.QUA_MC_DECLARACOES_NCDao;
import pt.example.dao.QUA_MC_SECCOES_CHEFESDao;
import pt.example.dao.GER_UTILIZADORESDao;
import pt.example.entity.QUA_MC_SECCOES_CHEFES;
import pt.example.entity.GER_UTILIZADORES;
import java.util.Map;

import pt.example.entity.QUA_MC_DIC_SECCOES;
import pt.example.entity.QUA_MC_DIC_RESP_VALIDACAO;
import pt.example.entity.QUA_MC_DIC_TIPO_CALIBRACAO;
import pt.example.entity.QUA_MC_DIC_TIPO_ACEITACAO;
import pt.example.entity.QUA_MC_DIC_TIPO_VERIF_GABARIT;
import pt.example.entity.QUA_MC_DIC_RESULTADO_VALIDACAO;
import pt.example.entity.QUA_MC_DIC_PECAS_CROMADAS;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO_CERTIF;
import pt.example.entity.QUA_MC_EQUIPAMENTOS;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP_DET;
import pt.example.entity.QUA_MC_GABARITOS;
import pt.example.entity.QUA_MC_MOV_VERIF_GABARITO;
import pt.example.entity.QUA_MC_MOV_VERIF_INT_GABARITO;
import pt.example.entity.QUA_MC_MAQUINAS;
import pt.example.entity.QUA_MC_MOV_VERIF_MAQUINA;
import pt.example.entity.QUA_MC_SALAS;
import pt.example.entity.QUA_MC_MOV_VERIF_SALA;
import pt.example.entity.QUA_MC_DIC_ESTADO_METROLOGICO;
import pt.example.entity.QUA_MC_DIC_MSA;
import pt.example.entity.QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS;
import pt.example.entity.QUA_MC_EQUIPAMENTOS_FICHEIROS;
import pt.example.entity.QUA_MC_GABARITOS_FICHEIROS;
import pt.example.entity.QUA_MC_MOV_VERIF_GABARITO_FICHEIROS;
import pt.example.entity.QUA_MC_MOV_CALIB_EQUIP_FICHEIROS;
import pt.example.entity.QUA_MC_DERROGACOES;
import pt.example.entity.QUA_MC_DERROGACOES_ACOES;
import pt.example.entity.QUA_MC_DERROGACOES_FICHEIROS;
import pt.example.entity.QUA_MC_DECLARACOES_NC;

@Stateless
@Path("/sirb")
public class SIRB_4 {

	@Inject private QUA_MC_DIC_SECCOESDao dao1;
	@Inject private QUA_MC_DIC_RESP_VALIDODao dao2;
	@Inject private QUA_MC_DIC_TIPO_CALIBRDao dao3;
	@Inject private QUA_MC_DIC_TIPO_ACEITDao dao4;
	@Inject private QUA_MC_DIC_TIPO_VERIF_GABARITDao dao5;
	@Inject private QUA_MC_DIC_RESULT_VALIDDao dao6;
	@Inject private QUA_MC_DIC_PECAS_CROMDao dao7;
	@Inject private QUA_MC_ENTIDADES_CALIBRDao dao8;
	@Inject private QUA_MC_ENTIDADES_CERTIFDao dao9;
	@Inject private QUA_MC_EQUIPAMENTOSDao dao10;
	@Inject private QUA_MC_MOV_CALIB_EQUIPDao dao11;
	@Inject private QUA_MC_MOV_CALIB_EQUIP_DETDao dao12;
	@Inject private QUA_MC_GABARITOSDao dao13;
	@Inject private QUA_MC_MOV_VERIF_GABARITODao dao14;
	@Inject private QUA_MC_MOV_VERIF_INT_GABARITODao dao15;
	@Inject private QUA_MC_MAQUINASDao dao16;
	@Inject private QUA_MC_MOV_VERIF_MAQUINADao dao17;
	@Inject private QUA_MC_SALASDao dao18;
	@Inject private QUA_MC_MOV_VERIF_SALADao dao19;
	@Inject private QUA_MC_DIC_ESTADO_METROLOGICODao dao20;
	@Inject private QUA_MC_DIC_MSADao dao21;
	@Inject private QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSDao dao22;
	@Inject private QUA_MC_EQUIPAMENTOS_FICHEIROSDao dao23;
	@Inject private QUA_MC_MOV_CALIB_EQUIP_FICHEIROSDao dao24;
	@Inject private QUA_MC_DERROGACOESDao dao25;
	@Inject private QUA_MC_DERROGACOES_ACOESDao dao26;
	@Inject private QUA_MC_DERROGACOES_FICHEIROSDao_MC dao27;
	@Inject private QUA_MC_DECLARACOES_NCDao dao28;
	@Inject private QUA_MC_GABARITOS_FICHEIROSDao dao29;
	@Inject private QUA_MC_MOV_VERIF_GABARITO_FICHEIROSDao dao30;
	// Módulo EPI's
	@Inject private QUA_EPI_DIC_FAMILIADao daoEpi1;
	@Inject private QUA_EPI_DIC_EPIDao daoEpi2;
	@Inject private QUA_EPI_LOCALDao daoEpi3;
	@Inject private QUA_EPI_LOCAL_RESPDao daoEpi4;
	@Inject private RH_SECTORES_EPI_FAMILIADao daoEpi5;
	@Inject private QUA_EPI_FUNCDao daoEpi6;
	@Inject private QUA_EPI_MOV_PEDIDODao daoEpi7;
	@Inject private QUA_EPI_MOV_PEDIDO_LINDao daoEpi8;
	@Inject private QUA_EPI_MOV_PEDIDO_HISTDao daoEpi9;
	@Inject private GER_EVENTOS_CONFDao daoEventos;
	@Inject private pt.example.dao.GER_CONF_CONSUMOS_EPIS_SILVERDao daoConfEpi;
	@Inject private QUA_EPI_MOV_ENTREGADao daoEpi10;
	@Inject private QUA_EPI_MOV_ENTREGA_ETIQDao daoEpi11;
	@Inject private QUA_MC_SECCOES_CHEFESDao daoSeccoesChefes;
	@Inject private GER_UTILIZADORESDao daoUtilizadores;

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	/**
	 * URL de ligação ao SILVER, lida de GER_PARAMETROS.
	 * Equivalente ao SIRB.getURLSILVER(), mas a selecionar a coluna pelo nome
	 * em vez de por posição (o original usa content[2]).
	 */
	private String getURLSILVER() {
		Query q = entityManager.createNativeQuery("select top 1 URL_SILVER from GER_PARAMETROS");
		List<?> dados = q.getResultList();
		if (dados == null || dados.isEmpty() || dados.get(0) == null) {
			return "";
		}
		return dados.get(0).toString();
	}

	/************************************* QUA_MC_DIC_ESTADO_METROLOGICO */
	@POST
	@Path("/createQUA_MC_DIC_ESTADO_METROLOGICO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_ESTADO_METROLOGICO insertQUA_MC_DIC_ESTADO_METROLOGICO(final QUA_MC_DIC_ESTADO_METROLOGICO data) {
		return dao20.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_ESTADO_METROLOGICO")
	@Produces("application/json")
	public List<QUA_MC_DIC_ESTADO_METROLOGICO> getQUA_MC_DIC_ESTADO_METROLOGICO() {
		return dao20.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_ESTADO_METROLOGICO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_ESTADO_METROLOGICO updateQUA_MC_DIC_ESTADO_METROLOGICO(final QUA_MC_DIC_ESTADO_METROLOGICO data) {
		return dao20.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_ESTADO_METROLOGICO/{id}")
	public void deleteQUA_MC_DIC_ESTADO_METROLOGICO(@PathParam("id") Integer id) {
		QUA_MC_DIC_ESTADO_METROLOGICO e = new QUA_MC_DIC_ESTADO_METROLOGICO();
		e.setID_ESTADO_METROLOGICO(id);
		dao20.delete(e);
	}

	/************************************* QUA_MC_DIC_MSA */
	@POST
	@Path("/createQUA_MC_DIC_MSA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_MSA insertQUA_MC_DIC_MSA(final QUA_MC_DIC_MSA data) {
		return dao21.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_MSA")
	@Produces("application/json")
	public List<QUA_MC_DIC_MSA> getQUA_MC_DIC_MSA() {
		return dao21.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_MSA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_MSA updateQUA_MC_DIC_MSA(final QUA_MC_DIC_MSA data) {
		return dao21.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_MSA/{id}")
	public void deleteQUA_MC_DIC_MSA(@PathParam("id") Integer id) {
		QUA_MC_DIC_MSA e = new QUA_MC_DIC_MSA();
		e.setID_MSA(id);
		dao21.delete(e);
	}

	/************************************* QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS */
	@POST
	@Path("/createQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS insertQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS(final QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS data) {
		return dao22.create(data);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSbyentidade/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROSbyentidade(@PathParam("id") Integer id) {
		return dao22.getbyEntidade(id);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao22.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS updateQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS(final QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS data) {
		return dao22.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS/{id}")
	public void deleteQUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS e = new QUA_MC_ENTIDADES_CALIBRACAO_FICHEIROS();
		e.setID(id);
		dao22.delete(e);
	}

	/************************************* QUA_MC_EQUIPAMENTOS_FICHEIROS */
	@POST
	@Path("/createQUA_MC_EQUIPAMENTOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS_FICHEIROS insertQUA_MC_EQUIPAMENTOS_FICHEIROS(final QUA_MC_EQUIPAMENTOS_FICHEIROS data) {
		return dao23.create(data);
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS_FICHEIROSbyequipamento/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_EQUIPAMENTOS_FICHEIROSbyequipamento(@PathParam("id") Integer id) {
		return dao23.getbyEquipamento(id);
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_EQUIPAMENTOS_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao23.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_EQUIPAMENTOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS_FICHEIROS updateQUA_MC_EQUIPAMENTOS_FICHEIROS(final QUA_MC_EQUIPAMENTOS_FICHEIROS data) {
		return dao23.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_EQUIPAMENTOS_FICHEIROS/{id}")
	public void deleteQUA_MC_EQUIPAMENTOS_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_EQUIPAMENTOS_FICHEIROS e = new QUA_MC_EQUIPAMENTOS_FICHEIROS();
		e.setID(id);
		dao23.delete(e);
	}

	/************************************* QUA_MC_GABARITOS_FICHEIROS */
	@POST
	@Path("/createQUA_MC_GABARITOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS_FICHEIROS insertQUA_MC_GABARITOS_FICHEIROS(final QUA_MC_GABARITOS_FICHEIROS data) {
		return dao29.create(data);
	}

	@GET
	@Path("/getQUA_MC_GABARITOS_FICHEIROSbygabarito/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_GABARITOS_FICHEIROSbygabarito(@PathParam("id") Integer id) {
		return dao29.getbyGabarito(id);
	}

	@GET
	@Path("/getQUA_MC_GABARITOS_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_GABARITOS_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao29.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_GABARITOS_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS_FICHEIROS updateQUA_MC_GABARITOS_FICHEIROS(final QUA_MC_GABARITOS_FICHEIROS data) {
		return dao29.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_GABARITOS_FICHEIROS/{id}")
	public void deleteQUA_MC_GABARITOS_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_GABARITOS_FICHEIROS e = new QUA_MC_GABARITOS_FICHEIROS();
		e.setID(id);
		dao29.delete(e);
	}

	/************************************* QUA_MC_MOV_CALIB_EQUIP_FICHEIROS */
	@POST
	@Path("/createQUA_MC_MOV_CALIB_EQUIP_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_FICHEIROS insertQUA_MC_MOV_CALIB_EQUIP_FICHEIROS(final QUA_MC_MOV_CALIB_EQUIP_FICHEIROS data) {
		return dao24.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIP_FICHEIROSbycalibdet/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP_FICHEIROS> getQUA_MC_MOV_CALIB_EQUIP_FICHEIROSbycalibdet(@PathParam("id") Integer id) {
		return dao24.getbyCalibDet(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_CALIB_EQUIP_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_FICHEIROS updateQUA_MC_MOV_CALIB_EQUIP_FICHEIROS(final QUA_MC_MOV_CALIB_EQUIP_FICHEIROS data) {
		return dao24.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_CALIB_EQUIP_FICHEIROS/{id}")
	public void deleteQUA_MC_MOV_CALIB_EQUIP_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_MOV_CALIB_EQUIP_FICHEIROS e = new QUA_MC_MOV_CALIB_EQUIP_FICHEIROS();
		e.setID(id);
		dao24.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_GABARITO_FICHEIROS */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_GABARITO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_GABARITO_FICHEIROS insertQUA_MC_MOV_VERIF_GABARITO_FICHEIROS(final QUA_MC_MOV_VERIF_GABARITO_FICHEIROS data) {
		return dao30.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_GABARITO_FICHEIROSbyverif/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_GABARITO_FICHEIROS> getQUA_MC_MOV_VERIF_GABARITO_FICHEIROSbyverif(@PathParam("id") Integer id) {
		return dao30.getbyVerifGabarito(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_GABARITO_FICHEIROS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_GABARITO_FICHEIROS updateQUA_MC_MOV_VERIF_GABARITO_FICHEIROS(final QUA_MC_MOV_VERIF_GABARITO_FICHEIROS data) {
		return dao30.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_GABARITO_FICHEIROS/{id}")
	public void deleteQUA_MC_MOV_VERIF_GABARITO_FICHEIROS(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_GABARITO_FICHEIROS e = new QUA_MC_MOV_VERIF_GABARITO_FICHEIROS();
		e.setID(id);
		dao30.delete(e);
	}

	/************************************* QUA_MC_DERROGACOES */
	@POST
	@Path("/createQUA_MC_DERROGACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES insertQUA_MC_DERROGACOES(final QUA_MC_DERROGACOES data) {
		return dao25.create(data);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES")
	@Produces("application/json")
	public List<QUA_MC_DERROGACOES> getQUA_MC_DERROGACOES() {
		return dao25.getall();
	}

	@GET
	@Path("/getQUA_MC_DERROGACOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DERROGACOES> getQUA_MC_DERROGACOESbyid(@PathParam("id") Integer id) {
		return dao25.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DERROGACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES updateQUA_MC_DERROGACOES(final QUA_MC_DERROGACOES data) {
		return dao25.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DERROGACOES/{id}")
	public void deleteQUA_MC_DERROGACOES(@PathParam("id") Integer id) {
		QUA_MC_DERROGACOES e = new QUA_MC_DERROGACOES();
		e.setID_DERROGACAO(id);
		dao25.delete(e);
	}

	/************************************* QUA_MC_DERROGACOES_ACOES */
	@POST
	@Path("/createQUA_MC_DERROGACOES_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_ACOES insertQUA_MC_DERROGACOES_ACOES(final QUA_MC_DERROGACOES_ACOES data) {
		return dao26.create(data);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_ACOESbyderrogacao/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_DERROGACOES_ACOESbyderrogacao(@PathParam("id") Integer id) {
		return dao26.getbyDerrogacao(id);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_ACOESbyid/{id}")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_ACOES getQUA_MC_DERROGACOES_ACOESbyid(@PathParam("id") Integer id) {
		return dao26.read(id);
	}

	@PUT
	@Path("/updateQUA_MC_DERROGACOES_ACOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_ACOES updateQUA_MC_DERROGACOES_ACOES(final QUA_MC_DERROGACOES_ACOES data) {
		return dao26.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DERROGACOES_ACOES/{id}")
	public void deleteQUA_MC_DERROGACOES_ACOES(@PathParam("id") Integer id) {
		QUA_MC_DERROGACOES_ACOES e = new QUA_MC_DERROGACOES_ACOES();
		e.setID(id);
		dao26.delete(e);
	}

	/************************************* QUA_MC_DERROGACOES_FICHEIROS */
	@POST
	@Path("/createQUA_MC_DERROGACOES_FICHEIROS_MC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_FICHEIROS insertQUA_MC_DERROGACOES_FICHEIROS_MC(final QUA_MC_DERROGACOES_FICHEIROS data) {
		return dao27.create(data);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_FICHEIROSbyderrogacao/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_DERROGACOES_FICHEIROSbyderrogacao(@PathParam("id") Integer id) {
		return dao27.getbyDerrogacao(id);
	}

	@GET
	@Path("/getQUA_MC_DERROGACOES_FICHEIROScontent/{id}")
	@Produces("text/plain")
	public String getQUA_MC_DERROGACOES_FICHEIROScontent(@PathParam("id") Integer id) {
		return dao27.getConteudo(id);
	}

	@PUT
	@Path("/updateQUA_MC_DERROGACOES_FICHEIROS_MC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DERROGACOES_FICHEIROS updateQUA_MC_DERROGACOES_FICHEIROS_MC(final QUA_MC_DERROGACOES_FICHEIROS data) {
		return dao27.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DERROGACOES_FICHEIROS_MC/{id}")
	public void deleteQUA_MC_DERROGACOES_FICHEIROS_MC(@PathParam("id") Integer id) {
		QUA_MC_DERROGACOES_FICHEIROS e = new QUA_MC_DERROGACOES_FICHEIROS();
		e.setID(id);
		dao27.delete(e);
	}

	/************************************* QUA_MC_DECLARACOES_NC */
	@POST
	@Path("/createQUA_MC_DECLARACOES_NC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DECLARACOES_NC insertQUA_MC_DECLARACOES_NC(final QUA_MC_DECLARACOES_NC data) {
		return dao28.create(data);
	}

	@GET
	@Path("/getQUA_MC_DECLARACOES_NC")
	@Produces("application/json")
	public List<QUA_MC_DECLARACOES_NC> getQUA_MC_DECLARACOES_NC() {
		return dao28.getall();
	}

	@GET
	@Path("/getQUA_MC_DECLARACOES_NCbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DECLARACOES_NC> getQUA_MC_DECLARACOES_NCbyid(@PathParam("id") Integer id) {
		return dao28.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DECLARACOES_NC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DECLARACOES_NC updateQUA_MC_DECLARACOES_NC(final QUA_MC_DECLARACOES_NC data) {
		return dao28.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DECLARACOES_NC/{id}")
	public void deleteQUA_MC_DECLARACOES_NC(@PathParam("id") Integer id) {
		QUA_MC_DECLARACOES_NC e = new QUA_MC_DECLARACOES_NC();
		e.setID_DECLARACAO(id);
		dao28.delete(e);
	}

	/************************************* QUA_MC_DIC_SECCOES */
	@POST
	@Path("/createQUA_MC_DIC_SECCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_SECCOES insertQUA_MC_DIC_SECCOES(final QUA_MC_DIC_SECCOES data) {
		return dao1.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_SECCOES")
	@Produces("application/json")
	public List<QUA_MC_DIC_SECCOES> getQUA_MC_DIC_SECCOES() {
		return dao1.getall();
	}

	@GET
	@Path("/getQUA_MC_DIC_SECCOESbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DIC_SECCOES> getQUA_MC_DIC_SECCOESbyid(@PathParam("id") Integer id) {
		return dao1.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DIC_SECCOES")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_SECCOES updateQUA_MC_DIC_SECCOES(final QUA_MC_DIC_SECCOES data) {
		return dao1.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_SECCOES/{id}")
	public void deleteQUA_MC_DIC_SECCOES(@PathParam("id") Integer id) {
		QUA_MC_DIC_SECCOES e = new QUA_MC_DIC_SECCOES();
		e.setID_SECCAO(id);
		dao1.delete(e);
	}

	/************************************* QUA_MC_DIC_RESP_VALIDACAO */
	@POST
	@Path("/createQUA_MC_DIC_RESP_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESP_VALIDACAO insertQUA_MC_DIC_RESP_VALIDACAO(final QUA_MC_DIC_RESP_VALIDACAO data) {
		return dao2.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_RESP_VALIDACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESP_VALIDACAO> getQUA_MC_DIC_RESP_VALIDACAO() {
		return dao2.getall();
	}

	@GET
	@Path("/getQUA_MC_DIC_RESP_VALIDACAO_ATIVOS")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESP_VALIDACAO> getQUA_MC_DIC_RESP_VALIDACAO_ATIVOS() {
		return dao2.getativos();
	}

	@GET
	@Path("/getQUA_MC_DIC_RESP_VALIDObyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESP_VALIDACAO> getQUA_MC_DIC_RESP_VALIDObyid(@PathParam("id") Integer id) {
		return dao2.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_DIC_RESP_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESP_VALIDACAO updateQUA_MC_DIC_RESP_VALIDACAO(final QUA_MC_DIC_RESP_VALIDACAO data) {
		return dao2.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_RESP_VALIDACAO/{id}")
	public void deleteQUA_MC_DIC_RESP_VALIDACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_RESP_VALIDACAO e = new QUA_MC_DIC_RESP_VALIDACAO();
		e.setID_RESP_VALIDACAO(id);
		dao2.delete(e);
	}

	/************************************* QUA_MC_DIC_TIPO_CALIBRACAO */
	@POST
	@Path("/createQUA_MC_DIC_TIPO_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_CALIBRACAO insertQUA_MC_DIC_TIPO_CALIBRACAO(final QUA_MC_DIC_TIPO_CALIBRACAO data) {
		return dao3.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_TIPO_CALIBRACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_TIPO_CALIBRACAO> getQUA_MC_DIC_TIPO_CALIBRACAO() {
		return dao3.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_TIPO_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_CALIBRACAO updateQUA_MC_DIC_TIPO_CALIBRACAO(final QUA_MC_DIC_TIPO_CALIBRACAO data) {
		return dao3.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_TIPO_CALIBRACAO/{id}")
	public void deleteQUA_MC_DIC_TIPO_CALIBRACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_TIPO_CALIBRACAO e = new QUA_MC_DIC_TIPO_CALIBRACAO();
		e.setID_TIPO_CALIBRACAO(id);
		dao3.delete(e);
	}

	/************************************* QUA_MC_DIC_TIPO_ACEITACAO */
	@POST
	@Path("/createQUA_MC_DIC_TIPO_ACEITACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_ACEITACAO insertQUA_MC_DIC_TIPO_ACEITACAO(final QUA_MC_DIC_TIPO_ACEITACAO data) {
		return dao4.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_TIPO_ACEITACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_TIPO_ACEITACAO> getQUA_MC_DIC_TIPO_ACEITACAO() {
		return dao4.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_TIPO_ACEITACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_ACEITACAO updateQUA_MC_DIC_TIPO_ACEITACAO(final QUA_MC_DIC_TIPO_ACEITACAO data) {
		return dao4.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_TIPO_ACEITACAO/{id}")
	public void deleteQUA_MC_DIC_TIPO_ACEITACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_TIPO_ACEITACAO e = new QUA_MC_DIC_TIPO_ACEITACAO();
		e.setID_TIPO_ACEITACAO(id);
		dao4.delete(e);
	}

	/************************************* QUA_MC_DIC_TIPO_VERIF_GABARIT */
	@POST
	@Path("/createQUA_MC_DIC_TIPO_VERIF_GABARIT")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_VERIF_GABARIT insertQUA_MC_DIC_TIPO_VERIF_GABARIT(final QUA_MC_DIC_TIPO_VERIF_GABARIT data) {
		return dao5.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_TIPO_VERIF_GABARIT")
	@Produces("application/json")
	public List<QUA_MC_DIC_TIPO_VERIF_GABARIT> getQUA_MC_DIC_TIPO_VERIF_GABARIT() {
		return dao5.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_TIPO_VERIF_GABARIT")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_TIPO_VERIF_GABARIT updateQUA_MC_DIC_TIPO_VERIF_GABARIT(final QUA_MC_DIC_TIPO_VERIF_GABARIT data) {
		return dao5.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_TIPO_VERIF_GABARIT/{id}")
	public void deleteQUA_MC_DIC_TIPO_VERIF_GABARIT(@PathParam("id") Integer id) {
		QUA_MC_DIC_TIPO_VERIF_GABARIT e = new QUA_MC_DIC_TIPO_VERIF_GABARIT();
		e.setID_TIPO_VERIF_GABARIT(id);
		dao5.delete(e);
	}

	/************************************* QUA_MC_DIC_RESULTADO_VALIDACAO */
	@POST
	@Path("/createQUA_MC_DIC_RESULTADO_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESULTADO_VALIDACAO insertQUA_MC_DIC_RESULTADO_VALIDACAO(final QUA_MC_DIC_RESULTADO_VALIDACAO data) {
		return dao6.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_RESULTADO_VALIDACAO")
	@Produces("application/json")
	public List<QUA_MC_DIC_RESULTADO_VALIDACAO> getQUA_MC_DIC_RESULTADO_VALIDACAO() {
		return dao6.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_RESULTADO_VALIDACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_RESULTADO_VALIDACAO updateQUA_MC_DIC_RESULTADO_VALIDACAO(final QUA_MC_DIC_RESULTADO_VALIDACAO data) {
		return dao6.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_RESULTADO_VALIDACAO/{id}")
	public void deleteQUA_MC_DIC_RESULTADO_VALIDACAO(@PathParam("id") Integer id) {
		QUA_MC_DIC_RESULTADO_VALIDACAO e = new QUA_MC_DIC_RESULTADO_VALIDACAO();
		e.setID_RESULTADO_VALIDACAO(id);
		dao6.delete(e);
	}

	/************************************* QUA_MC_DIC_PECAS_CROMADAS */
	@POST
	@Path("/createQUA_MC_DIC_PECAS_CROMADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_PECAS_CROMADAS insertQUA_MC_DIC_PECAS_CROMADAS(final QUA_MC_DIC_PECAS_CROMADAS data) {
		return dao7.create(data);
	}

	@GET
	@Path("/getQUA_MC_DIC_PECAS_CROMADAS")
	@Produces("application/json")
	public List<QUA_MC_DIC_PECAS_CROMADAS> getQUA_MC_DIC_PECAS_CROMADAS() {
		return dao7.getall();
	}

	@PUT
	@Path("/updateQUA_MC_DIC_PECAS_CROMADAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_DIC_PECAS_CROMADAS updateQUA_MC_DIC_PECAS_CROMADAS(final QUA_MC_DIC_PECAS_CROMADAS data) {
		return dao7.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_DIC_PECAS_CROMADAS/{id}")
	public void deleteQUA_MC_DIC_PECAS_CROMADAS(@PathParam("id") Integer id) {
		QUA_MC_DIC_PECAS_CROMADAS e = new QUA_MC_DIC_PECAS_CROMADAS();
		e.setID_PECA_CROMADA(id);
		dao7.delete(e);
	}

	/************************************* QUA_MC_ENTIDADES_CALIBRACAO */
	@POST
	@Path("/createQUA_MC_ENTIDADES_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO insertQUA_MC_ENTIDADES_CALIBRACAO(final QUA_MC_ENTIDADES_CALIBRACAO data) {
		return dao8.create(data);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO")
	@Produces("application/json")
	public List<QUA_MC_ENTIDADES_CALIBRACAO> getQUA_MC_ENTIDADES_CALIBRACAO() {
		return dao8.getall();
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_ENTIDADES_CALIBRACAO> getQUA_MC_ENTIDADES_CALIBRbyid(@PathParam("id") Integer id) {
		return dao8.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_ENTIDADES_CALIBRACAO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO updateQUA_MC_ENTIDADES_CALIBRACAO(final QUA_MC_ENTIDADES_CALIBRACAO data) {
		return dao8.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_ENTIDADES_CALIBRACAO/{id}")
	public void deleteQUA_MC_ENTIDADES_CALIBRACAO(@PathParam("id") Integer id) {
		QUA_MC_ENTIDADES_CALIBRACAO e = new QUA_MC_ENTIDADES_CALIBRACAO();
		e.setID_ENTIDADE_CALIBRACAO(id);
		dao8.delete(e);
	}

	/************************************* QUA_MC_ENTIDADES_CALIBRACAO_CERTIF */
	@POST
	@Path("/createQUA_MC_ENTIDADES_CALIBRACAO_CERTIF")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_CERTIF insertQUA_MC_ENTIDADES_CALIBRACAO_CERTIF(final QUA_MC_ENTIDADES_CALIBRACAO_CERTIF data) {
		return dao9.create(data);
	}

	@GET
	@Path("/getQUA_MC_ENTIDADES_CALIBRACAO_CERTIFbyEntidade/{id}")
	@Produces("application/json")
	public List<QUA_MC_ENTIDADES_CALIBRACAO_CERTIF> getQUA_MC_ENTIDADES_CALIBRACAO_CERTIFbyEntidade(@PathParam("id") Integer id) {
		return dao9.getbyEntidade(id);
	}

	@PUT
	@Path("/updateQUA_MC_ENTIDADES_CALIBRACAO_CERTIF")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_ENTIDADES_CALIBRACAO_CERTIF updateQUA_MC_ENTIDADES_CALIBRACAO_CERTIF(final QUA_MC_ENTIDADES_CALIBRACAO_CERTIF data) {
		return dao9.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_ENTIDADES_CALIBRACAO_CERTIF/{id}")
	public void deleteQUA_MC_ENTIDADES_CALIBRACAO_CERTIF(@PathParam("id") Integer id) {
		QUA_MC_ENTIDADES_CALIBRACAO_CERTIF e = new QUA_MC_ENTIDADES_CALIBRACAO_CERTIF();
		e.setID_CERTIF(id);
		dao9.delete(e);
	}

	/************************************* QUA_MC_EQUIPAMENTOS */
	@POST
	@Path("/createQUA_MC_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS insertQUA_MC_EQUIPAMENTOS(final QUA_MC_EQUIPAMENTOS data) {
		return dao10.create(data);
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS")
	@Produces("application/json")
	public List<QUA_MC_EQUIPAMENTOS> getQUA_MC_EQUIPAMENTOS() {
		return dao10.getall();
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOS_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_EQUIPAMENTOS_LISTA() {
		return dao10.getlista();
	}

	@GET
	@Path("/getQUA_MC_EQUIPAMENTOSbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_EQUIPAMENTOS> getQUA_MC_EQUIPAMENTOSbyid(@PathParam("id") Integer id) {
		return dao10.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_EQUIPAMENTOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_EQUIPAMENTOS updateQUA_MC_EQUIPAMENTOS(final QUA_MC_EQUIPAMENTOS data) {
		return dao10.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_EQUIPAMENTOS/{id}")
	public void deleteQUA_MC_EQUIPAMENTOS(@PathParam("id") Integer id) {
		QUA_MC_EQUIPAMENTOS e = new QUA_MC_EQUIPAMENTOS();
		e.setID_EQUIPAMENTO(id);
		dao10.delete(e);
	}

	/************************************* QUA_MC_MOV_CALIB_EQUIP */
	@POST
	@Path("/createQUA_MC_MOV_CALIB_EQUIP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP insertQUA_MC_MOV_CALIB_EQUIP(final QUA_MC_MOV_CALIB_EQUIP data) {
		return dao11.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIPbyEquipamento/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP> getQUA_MC_MOV_CALIB_EQUIPbyEquipamento(@PathParam("id") Integer id) {
		return dao11.getbyEquipamento(id);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIPbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP> getQUA_MC_MOV_CALIB_EQUIPbyid(@PathParam("id") Integer id) {
		return dao11.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_CALIB_EQUIP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP updateQUA_MC_MOV_CALIB_EQUIP(final QUA_MC_MOV_CALIB_EQUIP data) {
		return dao11.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_CALIB_EQUIP/{id}")
	public void deleteQUA_MC_MOV_CALIB_EQUIP(@PathParam("id") Integer id) {
		QUA_MC_MOV_CALIB_EQUIP e = new QUA_MC_MOV_CALIB_EQUIP();
		e.setID_CALIB_EQUIP(id);
		dao11.delete(e);
	}

	/************************************* QUA_MC_MOV_CALIB_EQUIP_DET */
	@POST
	@Path("/createQUA_MC_MOV_CALIB_EQUIP_DET")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_DET insertQUA_MC_MOV_CALIB_EQUIP_DET(final QUA_MC_MOV_CALIB_EQUIP_DET data) {
		return dao12.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_CALIB_EQUIP_DETbyCalibEquip/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_CALIB_EQUIP_DET> getQUA_MC_MOV_CALIB_EQUIP_DETbyCalibEquip(@PathParam("id") Integer id) {
		return dao12.getbyCalibEquip(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_CALIB_EQUIP_DET")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_CALIB_EQUIP_DET updateQUA_MC_MOV_CALIB_EQUIP_DET(final QUA_MC_MOV_CALIB_EQUIP_DET data) {
		return dao12.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_CALIB_EQUIP_DET/{id}")
	public void deleteQUA_MC_MOV_CALIB_EQUIP_DET(@PathParam("id") Integer id) {
		QUA_MC_MOV_CALIB_EQUIP_DET e = new QUA_MC_MOV_CALIB_EQUIP_DET();
		e.setID_CALIB_EQUIP_DET(id);
		dao12.delete(e);
	}

	/************************************* QUA_MC_GABARITOS */
	@POST
	@Path("/createQUA_MC_GABARITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS insertQUA_MC_GABARITOS(final QUA_MC_GABARITOS data) {
		return dao13.create(data);
	}

	@GET
	@Path("/getQUA_MC_GABARITOS")
	@Produces("application/json")
	public List<QUA_MC_GABARITOS> getQUA_MC_GABARITOS() {
		return dao13.getall();
	}

	@GET
	@Path("/getQUA_MC_GABARITOS_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_MC_GABARITOS_LISTA() {
		return dao13.getlista();
	}

	@GET
	@Path("/getQUA_MC_GABARITOSbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_GABARITOS> getQUA_MC_GABARITOSbyid(@PathParam("id") Integer id) {
		return dao13.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_GABARITOS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_GABARITOS updateQUA_MC_GABARITOS(final QUA_MC_GABARITOS data) {
		return dao13.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_GABARITOS/{id}")
	public void deleteQUA_MC_GABARITOS(@PathParam("id") Integer id) {
		QUA_MC_GABARITOS e = new QUA_MC_GABARITOS();
		e.setID_GABARITO(id);
		dao13.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_GABARITO */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_GABARITO insertQUA_MC_MOV_VERIF_GABARITO(final QUA_MC_MOV_VERIF_GABARITO data) {
		return dao14.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_GABARITObyGabarito/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_GABARITO> getQUA_MC_MOV_VERIF_GABARITObyGabarito(@PathParam("id") Integer id) {
		return dao14.getbyGabarito(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_GABARITO updateQUA_MC_MOV_VERIF_GABARITO(final QUA_MC_MOV_VERIF_GABARITO data) {
		return dao14.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_GABARITO/{id}")
	public void deleteQUA_MC_MOV_VERIF_GABARITO(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_GABARITO e = new QUA_MC_MOV_VERIF_GABARITO();
		e.setID_VERIF_GABARITO(id);
		dao14.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_INT_GABARITO */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_INT_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_INT_GABARITO insertQUA_MC_MOV_VERIF_INT_GABARITO(final QUA_MC_MOV_VERIF_INT_GABARITO data) {
		return dao15.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_INT_GABARITObyVerif/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_INT_GABARITO> getQUA_MC_MOV_VERIF_INT_GABARITObyVerif(@PathParam("id") Integer id) {
		return dao15.getbyVerifGabarito(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_INT_GABARITO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_INT_GABARITO updateQUA_MC_MOV_VERIF_INT_GABARITO(final QUA_MC_MOV_VERIF_INT_GABARITO data) {
		return dao15.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_INT_GABARITO/{id}")
	public void deleteQUA_MC_MOV_VERIF_INT_GABARITO(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_INT_GABARITO e = new QUA_MC_MOV_VERIF_INT_GABARITO();
		e.setID_VERIF_INT_GABARITO(id);
		dao15.delete(e);
	}

	/************************************* QUA_MC_MAQUINAS */
	@POST
	@Path("/createQUA_MC_MAQUINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MAQUINAS insertQUA_MC_MAQUINAS(final QUA_MC_MAQUINAS data) {
		return dao16.create(data);
	}

	@GET
	@Path("/getQUA_MC_MAQUINAS")
	@Produces("application/json")
	public List<QUA_MC_MAQUINAS> getQUA_MC_MAQUINAS() {
		return dao16.getall();
	}

	@GET
	@Path("/getQUA_MC_MAQUINASbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_MAQUINAS> getQUA_MC_MAQUINASbyid(@PathParam("id") Integer id) {
		return dao16.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_MAQUINAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MAQUINAS updateQUA_MC_MAQUINAS(final QUA_MC_MAQUINAS data) {
		return dao16.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MAQUINAS/{id}")
	public void deleteQUA_MC_MAQUINAS(@PathParam("id") Integer id) {
		QUA_MC_MAQUINAS e = new QUA_MC_MAQUINAS();
		e.setID_MAQUINA(id);
		dao16.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_MAQUINA */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_MAQUINA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_MAQUINA insertQUA_MC_MOV_VERIF_MAQUINA(final QUA_MC_MOV_VERIF_MAQUINA data) {
		return dao17.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_MAQUINAbyMaquina/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_MAQUINA> getQUA_MC_MOV_VERIF_MAQUINAbyMaquina(@PathParam("id") Integer id) {
		return dao17.getbyMaquina(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_MAQUINA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_MAQUINA updateQUA_MC_MOV_VERIF_MAQUINA(final QUA_MC_MOV_VERIF_MAQUINA data) {
		return dao17.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_MAQUINA/{id}")
	public void deleteQUA_MC_MOV_VERIF_MAQUINA(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_MAQUINA e = new QUA_MC_MOV_VERIF_MAQUINA();
		e.setID_VERIF_MAQUINA(id);
		dao17.delete(e);
	}

	/************************************* QUA_MC_SALAS */
	@POST
	@Path("/createQUA_MC_SALAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_SALAS insertQUA_MC_SALAS(final QUA_MC_SALAS data) {
		return dao18.create(data);
	}

	@GET
	@Path("/getQUA_MC_SALAS")
	@Produces("application/json")
	public List<QUA_MC_SALAS> getQUA_MC_SALAS() {
		return dao18.getall();
	}

	@GET
	@Path("/getQUA_MC_SALASbyid/{id}")
	@Produces("application/json")
	public List<QUA_MC_SALAS> getQUA_MC_SALASbyid(@PathParam("id") Integer id) {
		return dao18.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_MC_SALAS")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_SALAS updateQUA_MC_SALAS(final QUA_MC_SALAS data) {
		return dao18.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_SALAS/{id}")
	public void deleteQUA_MC_SALAS(@PathParam("id") Integer id) {
		QUA_MC_SALAS e = new QUA_MC_SALAS();
		e.setID_SALA(id);
		dao18.delete(e);
	}

	/************************************* QUA_MC_MOV_VERIF_SALA */
	@POST
	@Path("/createQUA_MC_MOV_VERIF_SALA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_SALA insertQUA_MC_MOV_VERIF_SALA(final QUA_MC_MOV_VERIF_SALA data) {
		return dao19.create(data);
	}

	@GET
	@Path("/getQUA_MC_MOV_VERIF_SALAbySala/{id}")
	@Produces("application/json")
	public List<QUA_MC_MOV_VERIF_SALA> getQUA_MC_MOV_VERIF_SALAbySala(@PathParam("id") Integer id) {
		return dao19.getBySala(id);
	}

	@PUT
	@Path("/updateQUA_MC_MOV_VERIF_SALA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_MC_MOV_VERIF_SALA updateQUA_MC_MOV_VERIF_SALA(final QUA_MC_MOV_VERIF_SALA data) {
		return dao19.update(data);
	}

	@DELETE
	@Path("/deleteQUA_MC_MOV_VERIF_SALA/{id}")
	public void deleteQUA_MC_MOV_VERIF_SALA(@PathParam("id") Integer id) {
		QUA_MC_MOV_VERIF_SALA e = new QUA_MC_MOV_VERIF_SALA();
		e.setID_VERIF_SALA(id);
		dao19.delete(e);
	}

	// ============================================================
	// MÓDULO EPI's - Parametrização
	// ============================================================
	// Nota: os ecrãs fazem soft delete (update com ATIVO = 0). Os endpoints
	// DELETE abaixo apagam fisicamente e existem só por consistência do padrão.

	// ---------- Famílias de EPI ----------

	@POST
	@Path("/createQUA_EPI_DIC_FAMILIA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_DIC_FAMILIA insertQUA_EPI_DIC_FAMILIA(final QUA_EPI_DIC_FAMILIA data) {
		return daoEpi1.create(data);
	}

	@GET
	@Path("/getQUA_EPI_DIC_FAMILIA")
	@Produces("application/json")
	public List<QUA_EPI_DIC_FAMILIA> getQUA_EPI_DIC_FAMILIA() {
		return daoEpi1.getall();
	}

	@GET
	@Path("/getQUA_EPI_DIC_FAMILIAbyid/{id}")
	@Produces("application/json")
	public List<QUA_EPI_DIC_FAMILIA> getQUA_EPI_DIC_FAMILIAbyid(@PathParam("id") Integer id) {
		return daoEpi1.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_EPI_DIC_FAMILIA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_DIC_FAMILIA updateQUA_EPI_DIC_FAMILIA(final QUA_EPI_DIC_FAMILIA data) {
		return daoEpi1.update(data);
	}

	@DELETE
	@Path("/deleteQUA_EPI_DIC_FAMILIA/{id}")
	public void deleteQUA_EPI_DIC_FAMILIA(@PathParam("id") Integer id) {
		QUA_EPI_DIC_FAMILIA e = new QUA_EPI_DIC_FAMILIA();
		e.setID_FAMILIA(id);
		daoEpi1.delete(e);
	}

	// ---------- EPIs ----------

	@POST
	@Path("/createQUA_EPI_DIC_EPI")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_DIC_EPI insertQUA_EPI_DIC_EPI(final QUA_EPI_DIC_EPI data) {
		return daoEpi2.create(data);
	}

	@GET
	@Path("/getQUA_EPI_DIC_EPI")
	@Produces("application/json")
	public List<QUA_EPI_DIC_EPI> getQUA_EPI_DIC_EPI() {
		return daoEpi2.getall();
	}

	// Lista para a grelha, com a família resolvida
	@GET
	@Path("/getQUA_EPI_DIC_EPI_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_DIC_EPI_LISTA() {
		return daoEpi2.getlista();
	}

	// EPIs de uma família - usado no separador da ficha do funcionário
	@GET
	@Path("/getQUA_EPI_DIC_EPIbyfamilia/{id}")
	@Produces("application/json")
	public List<QUA_EPI_DIC_EPI> getQUA_EPI_DIC_EPIbyfamilia(@PathParam("id") Integer id) {
		return daoEpi2.getbyfamilia(id);
	}

	@GET
	@Path("/getQUA_EPI_DIC_EPIbyid/{id}")
	@Produces("application/json")
	public List<QUA_EPI_DIC_EPI> getQUA_EPI_DIC_EPIbyid(@PathParam("id") Integer id) {
		return daoEpi2.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_EPI_DIC_EPI")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_DIC_EPI updateQUA_EPI_DIC_EPI(final QUA_EPI_DIC_EPI data) {
		return daoEpi2.update(data);
	}

	@DELETE
	@Path("/deleteQUA_EPI_DIC_EPI/{id}")
	public void deleteQUA_EPI_DIC_EPI(@PathParam("id") Integer id) {
		QUA_EPI_DIC_EPI e = new QUA_EPI_DIC_EPI();
		e.setID_EPI(id);
		daoEpi2.delete(e);
	}

	// ---------- Stock ----------
	// O stock vem do SILVER dentro das próprias queries (SILVER.dbo.STODET),
	// como já se faz no PIN_MOV_PREPARACAO_LINHADao. Não há chamada JDBC à
	// parte: o total já vem na lista de EPIs.

	/**
	 * Detalhe do stock de um EPI: uma linha por armazém/lote, com validade.
	 * LIECOD = armazém; EMPCOD = localização dentro do armazém.
	 */
	@GET
	@Path("/getQUA_EPI_STOCK_DETALHE/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_STOCK_DETALHE(@PathParam("id") Integer id) {
		return daoEpi2.getstockdetalhe(id);
	}

	// ---------- Responsáveis por local ----------
	// Os locais são os GER_LOCAIS existentes (os mesmos de RH_SECTORES.local).
	// Não há dicionário de locais próprio do módulo EPI.

	@POST
	@Path("/createQUA_EPI_LOCAL_RESP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_LOCAL_RESP insertQUA_EPI_LOCAL_RESP(final QUA_EPI_LOCAL_RESP data) {
		return daoEpi4.create(data);
	}

	@GET
	@Path("/getQUA_EPI_LOCAL_RESP")
	@Produces("application/json")
	public List<QUA_EPI_LOCAL_RESP> getQUA_EPI_LOCAL_RESP() {
		return daoEpi4.getall();
	}

	// Pesquisa de artigos no SILVER, para o autocomplete do PROREF
	@GET
	@Path("/getQUA_EPI_ARTIGOS_SILVER/{termo}")
	@Produces("application/json")
	public List<HashMap<String, String>> getQUA_EPI_ARTIGOS_SILVER(@PathParam("termo") String termo)
			throws SQLException {
		ConnectProgress connectionProgress = new ConnectProgress();
		return connectionProgress.getArtigosEpiPorTermo(getURLSILVER(), termo);
	}

	// ---------- Locais EPI (subconjunto escolhido dos GER_LOCAIS) ----------

	@POST
	@Path("/createQUA_EPI_LOCAL")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_LOCAL insertQUA_EPI_LOCAL(final QUA_EPI_LOCAL data) {
		return daoEpi3.create(data);
	}

	// Locais EPI escolhidos, com descrição e contagem de responsáveis
	@GET
	@Path("/getQUA_EPI_LOCAL_LISTA")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_LOCAL_LISTA() {
		return daoEpi3.getlista();
	}

	// GER_LOCAIS ainda não escolhidos - dropdown de "Adicionar"
	@GET
	@Path("/getQUA_EPI_LOCAL_DISPONIVEIS")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_LOCAL_DISPONIVEIS() {
		return daoEpi3.getdisponiveis();
	}

	@PUT
	@Path("/updateQUA_EPI_LOCAL")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_LOCAL updateQUA_EPI_LOCAL(final QUA_EPI_LOCAL data) {
		return daoEpi3.update(data);
	}

	// Responsáveis de um local EPI, com nome e email resolvidos
	@GET
	@Path("/getQUA_EPI_LOCAL_RESPbylocal/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_LOCAL_RESPbylocal(@PathParam("id") Integer id) {
		return daoEpi4.getbylocal(id);
	}

	// Locais onde um utilizador é responsável - base da regra de visibilidade
	@GET
	@Path("/getQUA_EPI_LOCAISbyutz/{id}")
	@Produces("application/json")
	public List<Integer> getQUA_EPI_LOCAISbyutz(@PathParam("id") Integer id) {
		return daoEpi4.getlocaisbyutz(id);
	}

	@PUT
	@Path("/updateQUA_EPI_LOCAL_RESP")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_LOCAL_RESP updateQUA_EPI_LOCAL_RESP(final QUA_EPI_LOCAL_RESP data) {
		return daoEpi4.update(data);
	}

	@DELETE
	@Path("/deleteQUA_EPI_LOCAL_RESP/{id}")
	public void deleteQUA_EPI_LOCAL_RESP(@PathParam("id") Integer id) {
		QUA_EPI_LOCAL_RESP e = new QUA_EPI_LOCAL_RESP();
		e.setID(id);
		daoEpi4.delete(e);
	}

	// ---------- Famílias de EPI por sector ----------

	@POST
	@Path("/createRH_SECTORES_EPI_FAMILIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SECTORES_EPI_FAMILIA insertRH_SECTORES_EPI_FAMILIA(final RH_SECTORES_EPI_FAMILIA data) {
		return daoEpi5.create(data);
	}

	@GET
	@Path("/getRH_SECTORES_EPI_FAMILIA")
	@Produces("application/json")
	public List<RH_SECTORES_EPI_FAMILIA> getRH_SECTORES_EPI_FAMILIA() {
		return daoEpi5.getall();
	}

	// Famílias configuradas num sector
	@GET
	@Path("/getRH_SECTORES_EPI_FAMILIAbysector/{id}")
	@Produces("application/json")
	public List<Object[]> getRH_SECTORES_EPI_FAMILIAbysector(@PathParam("id") Integer id) {
		return daoEpi5.getbysector(id);
	}

	@PUT
	@Path("/updateRH_SECTORES_EPI_FAMILIA")
	@Consumes("*/*")
	@Produces("application/json")
	public RH_SECTORES_EPI_FAMILIA updateRH_SECTORES_EPI_FAMILIA(final RH_SECTORES_EPI_FAMILIA data) {
		return daoEpi5.update(data);
	}

	@DELETE
	@Path("/deleteRH_SECTORES_EPI_FAMILIA/{id}")
	public void deleteRH_SECTORES_EPI_FAMILIA(@PathParam("id") Integer id) {
		RH_SECTORES_EPI_FAMILIA e = new RH_SECTORES_EPI_FAMILIA();
		e.setID(id);
		daoEpi5.delete(e);
	}

	// ---------- EPIs por funcionário ----------

	@POST
	@Path("/createQUA_EPI_FUNC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_FUNC insertQUA_EPI_FUNC(final QUA_EPI_FUNC data) {
		return daoEpi6.create(data);
	}

	@GET
	@Path("/getQUA_EPI_FUNC")
	@Produces("application/json")
	public List<QUA_EPI_FUNC> getQUA_EPI_FUNC() {
		return daoEpi6.getall();
	}

	// Linhas do separador EPI's da ficha do funcionário (derivadas do sector)
	@GET
	@Path("/getQUA_EPI_FUNCbyfuncionario/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_FUNCbyfuncionario(@PathParam("id") Integer id) {
		return daoEpi6.getbyfuncionario(id);
	}

	@GET
	@Path("/getQUA_EPI_FUNCbyid/{id}")
	@Produces("application/json")
	public List<QUA_EPI_FUNC> getQUA_EPI_FUNCbyid(@PathParam("id") Integer id) {
		return daoEpi6.getbyid(id);
	}

	@PUT
	@Path("/updateQUA_EPI_FUNC")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_FUNC updateQUA_EPI_FUNC(final QUA_EPI_FUNC data) {
		return daoEpi6.update(data);
	}

	@DELETE
	@Path("/deleteQUA_EPI_FUNC/{id}")
	public void deleteQUA_EPI_FUNC(@PathParam("id") Integer id) {
		QUA_EPI_FUNC e = new QUA_EPI_FUNC();
		e.setID(id);
		daoEpi6.delete(e);
	}

	// ============================================================
	// MÓDULO EPI's - Pedidos
	// ============================================================

	@POST
	@Path("/createQUA_EPI_MOV_PEDIDO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_MOV_PEDIDO insertQUA_EPI_MOV_PEDIDO(final QUA_EPI_MOV_PEDIDO data) {
		return daoEpi7.create(data);
	}

	@GET
	@Path("/getQUA_EPI_MOV_PEDIDO")
	@Produces("application/json")
	public List<QUA_EPI_MOV_PEDIDO> getQUA_EPI_MOV_PEDIDO() {
		return daoEpi7.getall();
	}

	/**
	 * Lista de pedidos visíveis para o utilizador.
	 * A regra de visibilidade é aplicada na query, nunca no frontend.
	 */
	@GET
	@Path("/getQUA_EPI_MOV_PEDIDO_LISTA/{utz}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_MOV_PEDIDO_LISTA(@PathParam("utz") Integer utz) {
		return daoEpi7.getlista(utz);
	}

	@GET
	@Path("/getQUA_EPI_MOV_PEDIDObyid/{id}")
	@Produces("application/json")
	public List<QUA_EPI_MOV_PEDIDO> getQUA_EPI_MOV_PEDIDObyid(@PathParam("id") Integer id) {
		return daoEpi7.getbyid(id);
	}

	// Sector/turno do utilizador autenticado, para pré-preencher o pedido
	@GET
	@Path("/getQUA_EPI_SECTOR_UTZ/{utz}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_SECTOR_UTZ(@PathParam("utz") Integer utz) {
		return daoEpi7.getsectordoutilizador(utz);
	}

	// Sector/turno do destinatário, para o pedido feito na ficha do funcionário
	// Nome distinto de getQUA_EPI_SECTOR_FUNC, que devolve os funcionarios de um sector.
	@GET
	@Path("/getQUA_EPI_SECTOR_DO_FUNC/{func}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_SECTOR_DO_FUNC(@PathParam("func") Integer func) {
		return daoEpi7.getsectordofuncionario(func);
	}

	// Dados para o email de notificação (campos do template do evento)
	@GET
	@Path("/getQUA_EPI_DADOS_NOTIFICACAO/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_DADOS_NOTIFICACAO(@PathParam("id") Integer id) {
		return daoEpi7.getdadosnotificacao(id);
	}

	// Local EPI provável de um sector, para encaminhar o pedido logo à criação
	@GET
	@Path("/getQUA_EPI_LOCAL_DO_SECTOR/{sector}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_LOCAL_DO_SECTOR(@PathParam("sector") Integer sector) {
		return daoEpi7.getlocalepidosector(sector);
	}

	// Funcionários ativos de um sector - dropdown de destinatário
	@GET
	@Path("/getQUA_EPI_SECTOR_FUNC/{sector}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_SECTOR_FUNC(@PathParam("sector") Integer sector) {
		return daoEpi7.getfuncionariosdosector(sector);
	}

	@PUT
	@Path("/updateQUA_EPI_MOV_PEDIDO")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_MOV_PEDIDO updateQUA_EPI_MOV_PEDIDO(final QUA_EPI_MOV_PEDIDO data) {
		return daoEpi7.update(data);
	}

	@DELETE
	@Path("/deleteQUA_EPI_MOV_PEDIDO/{id}")
	public void deleteQUA_EPI_MOV_PEDIDO(@PathParam("id") Integer id) {
		QUA_EPI_MOV_PEDIDO e = new QUA_EPI_MOV_PEDIDO();
		e.setID_PEDIDO(id);
		daoEpi7.delete(e);
	}

	// ---------- Linhas do pedido ----------

	@POST
	@Path("/createQUA_EPI_MOV_PEDIDO_LIN")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_MOV_PEDIDO_LIN insertQUA_EPI_MOV_PEDIDO_LIN(final QUA_EPI_MOV_PEDIDO_LIN data) {
		return daoEpi8.create(data);
	}

	@GET
	@Path("/getQUA_EPI_MOV_PEDIDO_LINbypedido/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_MOV_PEDIDO_LINbypedido(@PathParam("id") Integer id) {
		return daoEpi8.getbypedido(id);
	}

	/**
	 * Valida uma linha antes de a gravar: devolução pendente do EPI anterior
	 * e duração de uso ultrapassada.
	 */
	@GET
	@Path("/getQUA_EPI_VALIDA_LINHA/{func}/{epi}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_VALIDA_LINHA(@PathParam("func") Integer func, @PathParam("epi") Integer epi) {
		return daoEpi8.validalinha(func, epi);
	}

	@PUT
	@Path("/updateQUA_EPI_MOV_PEDIDO_LIN")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_MOV_PEDIDO_LIN updateQUA_EPI_MOV_PEDIDO_LIN(final QUA_EPI_MOV_PEDIDO_LIN data) {
		return daoEpi8.update(data);
	}

	@DELETE
	@Path("/deleteQUA_EPI_MOV_PEDIDO_LIN/{id}")
	public void deleteQUA_EPI_MOV_PEDIDO_LIN(@PathParam("id") Integer id) {
		QUA_EPI_MOV_PEDIDO_LIN e = new QUA_EPI_MOV_PEDIDO_LIN();
		e.setID_LINHA(id);
		daoEpi8.delete(e);
	}

	// ---------- Histórico do pedido ----------

	@POST
	@Path("/createQUA_EPI_MOV_PEDIDO_HIST")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_MOV_PEDIDO_HIST insertQUA_EPI_MOV_PEDIDO_HIST(final QUA_EPI_MOV_PEDIDO_HIST data) {
		return daoEpi9.create(data);
	}

	@GET
	@Path("/getQUA_EPI_MOV_PEDIDO_HISTbypedido/{id}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_MOV_PEDIDO_HISTbypedido(@PathParam("id") Integer id) {
		return daoEpi9.getbypedido(id);
	}

	// ============================================================
	// MÓDULO EPI's - Levantamento / Entrega
	// ============================================================

	/**
	 * Pedidos prontos para levantamento num local (ACEITE, agendados até hoje,
	 * ainda sem entrega).
	 */
	@GET
	@Path("/getQUA_EPI_PEDIDOS_LEVANTAMENTO/{local}/{utz}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_PEDIDOS_LEVANTAMENTO(@PathParam("local") Integer local,
			@PathParam("utz") Integer utz) {
		return daoEpi10.getparalevantamento(local, utz);
	}

	/**
	 * Resolve uma etiqueta lida no levantamento.
	 * Devolve os dados do artigo no SILVER e, em EXISTE_ETIQUETAS/ETIQUETAS,
	 * se havia outra que devia sair primeiro (FEFO).
	 */
	@GET
	@Path("/getDadosEtiquetaEPI/{etiqueta}")
	@Produces("application/json")
	public List<HashMap<String, String>> getDadosEtiquetaEPI(@PathParam("etiqueta") String etiqueta)
			throws SQLException {
		ConnectProgress connectionProgress = new ConnectProgress();
		return connectionProgress.getDadosEtiquetaEPI(getURLSILVER(), etiqueta);
	}

	/**
	 * Fecho da entrega, numa só operação.
	 *
	 * Grava a entrega e as etiquetas, debita cada etiqueta no SILVER
	 * (UPDATE SETQDE.ETQEMBQTE via EXEC_SINCRO), passa o pedido a ENTREGUE e
	 * carimba DATA_SINCRO_SILVER.
	 *
	 * O débito no SILVER é feito por JDBC, fora da transação JTA: se falhar a
	 * meio, a entrega fica gravada mas sem DATA_SINCRO_SILVER, que é o sinal
	 * de que é preciso reconciliar. Preferiu-se isto a perder o registo da
	 * entrega já assinada.
	 */
	@POST
	@Path("/createQUA_EPI_ENTREGA_COMPLETA")
	@Consumes("*/*")
	@Produces("application/json")
	public QUA_EPI_MOV_ENTREGA createQUA_EPI_ENTREGA_COMPLETA(final QUA_EPI_ENTREGA_DTO dto) throws SQLException {
		boolean concluir = Boolean.TRUE.equals(dto.getCONCLUIR());
		QUA_EPI_MOV_ENTREGA entrega = dto.getENTREGA();
		entrega.setATIVO(true);
		entrega.setESTADO(concluir ? "CONCLUIDA" : "RASCUNHO");
		if (entrega.getDATA_HORA_ENTREGA() == null) {
			entrega.setDATA_HORA_ENTREGA(new java.sql.Timestamp(System.currentTimeMillis()));
		}

		// Regravar um rascunho reaproveita a entrega e substitui as etiquetas,
		// para não acumular linhas de gravações sucessivas.
		List<QUA_EPI_MOV_ENTREGA> rascunhos = daoEpi10.getrascunho(entrega.getID_PEDIDO());
		if (!rascunhos.isEmpty()) {
			entrega.setID_ENTREGA(rascunhos.get(0).getID_ENTREGA());
			daoEpi10.apagaretiquetas(entrega.getID_ENTREGA());
			entrega = daoEpi10.update(entrega);
		} else {
			entrega = daoEpi10.create(entrega);
		}

		if (dto.getETIQUETAS() != null) {
			for (QUA_EPI_MOV_ENTREGA_ETIQ etiq : dto.getETIQUETAS()) {
				etiq.setID_ENTREGA(entrega.getID_ENTREGA());
				etiq.setATIVO(true);
				daoEpi11.create(etiq);
			}
		}

		// Só a conclusão mexe no SILVER e fecha o pedido. Gravar é apenas
		// guardar o trabalho feito até ali.
		if (!concluir) {
			return entrega;
		}

		String url = getURLSILVER();
		ConnectProgress connectionProgress = new ConnectProgress();
		boolean sincronizouTudo = true;

		if (dto.getETIQUETAS() != null) {
			for (QUA_EPI_MOV_ENTREGA_ETIQ etiq : dto.getETIQUETAS()) {
				// Débito no SILVER: a etiqueta fica com a quantidade final
				if (etiq.getETQNUM() != null && etiq.getQUANT_FINAL() != null) {
					try {
						connectionProgress.EXEC_SINCRO(etiq.getETQNUM(),
								Float.valueOf(etiq.getQUANT_FINAL().floatValue()), url);
					} catch (Exception e) {
						e.printStackTrace();
						sincronizouTudo = false;
					}
				}
			}
		}

		if (sincronizouTudo) {
			entrega.setDATA_SINCRO_SILVER(new java.sql.Timestamp(System.currentTimeMillis()));
			entrega = daoEpi10.update(entrega);
		}

		// Pedido passa a ENTREGUE
		List<QUA_EPI_MOV_PEDIDO> pedidos = daoEpi7.getbyid(entrega.getID_PEDIDO());
		if (!pedidos.isEmpty()) {
			QUA_EPI_MOV_PEDIDO p = pedidos.get(0);
			String estadoAnterior = p.getESTADO();
			p.setESTADO("ENTREGUE");
			p.setUTZ_MODIF(entrega.getUTZ_CRIA());
			daoEpi7.update(p);

			// Fecho do pedido no historico, com a data efetiva da entrega
			QUA_EPI_MOV_PEDIDO_HIST h = new QUA_EPI_MOV_PEDIDO_HIST();
			h.setID_PEDIDO(entrega.getID_PEDIDO());
			h.setDATA_HORA(entrega.getDATA_HORA_ENTREGA());
			h.setID_UTILIZADOR(entrega.getUTZ_CRIA());
			h.setESTADO_ANTERIOR(estadoAnterior);
			h.setESTADO_NOVO("ENTREGUE");
			int nEtiquetas = dto.getETIQUETAS() == null ? 0 : dto.getETIQUETAS().size();
			h.setOBSERVACOES("Levantamento efetuado com " + nEtiquetas + " etiqueta(s).");
			h.setUTZ_CRIA(entrega.getUTZ_CRIA());
			h.setDATA_CRIA(new java.sql.Timestamp(System.currentTimeMillis()));
			h.setATIVO(true);
			daoEpi9.create(h);
		}

		// Por ultimo: o ficheiro de consumo nao pode por em causa a entrega,
		// que a esta altura ja esta gravada e o stock debitado.
		if (dto.getETIQUETAS() != null && !dto.getETIQUETAS().isEmpty()) {
			gerarFicheiroConsumoEPI(entrega, dto.getETIQUETAS());
		}

		return entrega;
	}

	/** Rascunho de entrega de um pedido, para repor o ecrã do levantamento. */
	@GET
	@Path("/getQUA_EPI_ENTREGA_RASCUNHO/{pedido}")
	@Produces("application/json")
	public List<QUA_EPI_MOV_ENTREGA> getQUA_EPI_ENTREGA_RASCUNHO(@PathParam("pedido") Integer pedido) {
		return daoEpi10.getrascunho(pedido);
	}

	/** Entrega já concluída de um pedido, para consulta do que foi entregue. */
	@GET
	@Path("/getQUA_EPI_ENTREGA_CONCLUIDA/{pedido}")
	@Produces("application/json")
	public List<QUA_EPI_MOV_ENTREGA> getQUA_EPI_ENTREGA_CONCLUIDA(@PathParam("pedido") Integer pedido) {
		return daoEpi10.getconcluida(pedido);
	}

	/** Etiquetas já gravadas numa entrega. */
	@GET
	@Path("/getQUA_EPI_ENTREGA_ETIQUETAS/{entrega}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_ENTREGA_ETIQUETAS(@PathParam("entrega") Integer entrega) {
		return daoEpi10.getetiquetasdaentrega(entrega);
	}

	/**
	 * Gera o ficheiro de consumo para o SILVER, no formato de largura fixa do
	 * consumo da pintura.
	 *
	 * Diferença face à pintura: ali a secção, subsecção e referência composta
	 * saem da OF; uma entrega de EPI não tem OF, por isso vêm todas de
	 * GER_CONF_CONSUMOS_EPIS_SILVER - a mesma solução usada na manutenção.
	 *
	 * Se a configuração não estiver preenchida, não gera nada e não falha: a
	 * entrega já está gravada e o stock debitado.
	 */
	private void gerarFicheiroConsumoEPI(QUA_EPI_MOV_ENTREGA entrega,
			List<QUA_EPI_MOV_ENTREGA_ETIQ> etiquetas) {
		try {
			pt.example.entity.GER_CONF_CONSUMOS_EPIS_SILVER conf = daoConfEpi.getconf();
			if (conf == null || vazio(conf.getSECCAO_EPI()) || vazio(conf.getSUBSECCAO_EPI())
					|| vazio(conf.getREF_COMPOSTO_EPI())) {
				// Sem configuração não há ficheiro - fica registado no log
				System.out.println("[EPI] Consumo nao gerado: GER_CONF_CONSUMOS_EPIS_SILVER por preencher.");
				return;
			}

			String seccao = conf.getSECCAO_EPI();
			String subseccao = conf.getSUBSECCAO_EPI();
			String refComposto = conf.getREF_COMPOSTO_EPI();
			String of = conf.getOF_EPI() == null ? "" : conf.getOF_EPI();

			java.text.SimpleDateFormat fData = new java.text.SimpleDateFormat("yyyyMMdd");
			java.text.SimpleDateFormat fHora = new java.text.SimpleDateFormat("HHmmss");
			java.util.Date agora = new java.util.Date();
			String datatual = fData.format(agora);
			String horatual = fHora.format(agora);
			String sequencia = sequenciaFicheiro();

			StringBuilder data = new StringBuilder();
			for (QUA_EPI_MOV_ENTREGA_ETIQ e : etiquetas) {
				data.append("01        ");                              // Sociedade
				data.append(datatual);                                  // Data suivi
				data.append(sequencia);                                 // Nº sequência
				data.append("    ");                                    // Linha de produção
				data.append("1");                                       // Tipo Nº OF
				data.append(preencher(of, 10));                         // Nº OF
				data.append("1");                                       // Tipo operação
				data.append("0010");                                    // Nº operação
				data.append("1");                                       // Posição
				data.append(preencher(seccao, 10));                     // Secção
				data.append(preencher(subseccao, 10));                  // Subsecção
				data.append("  ");                                      // Nº equipa
				data.append("    ");                                    // Tipo recurso
				data.append("          ");                              // Código recurso
				data.append("   C");                                    // Estabelecimento + tipo
				data.append(datatual).append(horatual);                 // Início
				data.append(datatual).append(horatual);                 // Fim
				data.append("1");                                       // Origem componente
				data.append(preencher(refComposto, 17));                // Referência composto
				data.append("          ");                              // Variante composto 1
				data.append("          ");                              // Variante composto 2
				data.append("          ");                              // Índice do composto
				data.append("000000000");                               // Nº registo Csé
				data.append("     ");                                   // Nº de rang
				data.append(preencher(e.getPROREF(), 17));              // Referência componente
				data.append("          ");                              // Variante componente 1
				data.append("          ");                              // Variante componente 2
				data.append("          ");                              // Índice do componente
				data.append("000000000");                               // Nº registo Cst
				data.append("1");                                       // Tipo quantidade
				data.append(quantidade(e.getCONSUMIR()));               // Quantidade
				data.append("-");                                       // Sinal: consumo
				data.append(preencher(e.getUNISTO(), 4));               // Unidade
				data.append("               ");                         // Quantidade US2
				data.append(preencher(e.getLIECOD(), 10));              // Lugar origem
				data.append(preencher(e.getEMPCOD(), 10));              // Localização origem
				data.append(preencher(e.getETQORILOT1(), 35));          // Referência do lote
				data.append(numerico(e.getLOTNUMENR(), 9));             // Nº lote interno
				data.append(preencher(e.getETQNUM(), 10));              // Nº etiqueta
				data.append("000000000");                               // Nº registo etiqueta
				data.append(preencher("EPI " + entrega.getID_PEDIDO(), 40)); // Texto livre
				data.append("\r\n");
			}

			if (data.length() == 0) {
				return;
			}

			String pasta = conf.getPASTA_FICHEIRO();
			if (vazio(pasta)) {
				pasta = pastaFicheiroPadrao();
			}
			if (vazio(pasta)) {
				System.out.println("[EPI] Consumo nao gerado: pasta de destino por definir.");
				return;
			}

			String nome = "CONSUMO_EPI_" + entrega.getID_ENTREGA() + "_"
					+ new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(agora) + ".txt";
			java.io.File dir = new java.io.File(pasta);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			java.io.File ficheiro = new java.io.File(dir, nome);
			java.io.FileWriter fw = new java.io.FileWriter(ficheiro);
			fw.write(data.toString());
			fw.close();

		} catch (Exception ex) {
			// Não pode derrubar a entrega, que já está gravada e assinada
			ex.printStackTrace();
		}
	}

	private boolean vazio(String s) {
		return s == null || s.trim().isEmpty();
	}

	/** Preenche à direita com espaços até ao tamanho fixo do campo. */
	private String preencher(String valor, int tamanho) {
		String v = valor == null ? "" : valor;
		StringBuilder sb = new StringBuilder(v);
		while (sb.length() < tamanho) {
			sb.append(' ');
		}
		return sb.substring(0, tamanho);
	}

	/** Preenche à esquerda com zeros. */
	private String numerico(String valor, int tamanho) {
		String v = valor == null ? "" : valor.trim();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tamanho; i++) {
			sb.append('0');
		}
		String s = sb.toString() + v;
		return s.substring(s.length() - tamanho);
	}

	/** Quantidade no formato do SILVER: 11 inteiros + 4 decimais + 2 espaços. */
	private String quantidade(java.math.BigDecimal q) {
		if (q == null) {
			return "000000000000000  ";
		}
		String r = String.format(java.util.Locale.US, "%.3f", q.doubleValue());
		String[] p = r.split("\\.");
		String inteiro = numerico(p.length > 0 ? p[0] : "0", 11);
		String decimal = (p.length > 1 ? p[1] : "") + "0000";
		decimal = decimal.substring(0, 4);
		return (inteiro + decimal + "  ").substring(0, 17);
	}

	/** Sequência diária de ficheiros, partilhada com os restantes módulos. */
	private String sequenciaFicheiro() {
		Query q = entityManager.createNativeQuery(
			"select top 1 NUMERO_SEQUENCIA from GER_SEQUENCIA_FICHEIRO "
			+ "where DATA_SEQUENCIA = CONVERT(date, GETDATE())");
		List<?> dados = q.getResultList();
		if (dados.isEmpty()) {
			entityManager.createNativeQuery(
				"INSERT INTO GER_SEQUENCIA_FICHEIRO (NUMERO_SEQUENCIA, DATA_SEQUENCIA) "
				+ "VALUES (1, CONVERT(date, GETDATE()))").executeUpdate();
			return "000000001";
		}
		int val = Integer.parseInt(dados.get(0).toString()) + 1;
		entityManager.createNativeQuery(
			"UPDATE GER_SEQUENCIA_FICHEIRO SET NUMERO_SEQUENCIA = " + val
			+ " where DATA_SEQUENCIA = CONVERT(date, GETDATE())").executeUpdate();
		return numerico(String.valueOf(val), 9);
	}

	/** Pasta de ficheiros configurada em GER_PARAMETROS. */
	private String pastaFicheiroPadrao() {
		Query q = entityManager.createNativeQuery("select top 1 PASTA_FICHEIRO from GER_PARAMETROS");
		List<?> dados = q.getResultList();
		if (dados.isEmpty() || dados.get(0) == null) {
			return null;
		}
		return dados.get(0).toString();
	}

	/**
	 * Template de email configurado para uma página/momento.
	 * Usado pelos eventos do módulo EPI (epis_pedidos + ACEITAR/REJEITAR),
	 * evitando fixar o ID_EVENTO no código.
	 */
	@GET
	@Path("/getGER_EVENTOS_CONFbypagina/{pagina}/{momento}")
	@Produces("application/json")
	public List<GER_EVENTOS_CONF> getGER_EVENTOS_CONFbypagina(@PathParam("pagina") String pagina,
			@PathParam("momento") String momento) {
		return daoEventos.getbypaginamomento(pagina, momento);
	}

	// ============================================================
	// MÓDULO EPI's - Análises (Consumos / Stocks)
	// ============================================================

	/**
	 * Consumos entre datas. Colaborador e EPI são opcionais: 0 = todos.
	 * Datas em yyyy-MM-dd.
	 */
	@GET
	@Path("/getQUA_EPI_CONSUMOS/{dataIni}/{dataFim}/{func}/{epi}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_CONSUMOS(@PathParam("dataIni") String dataIni,
			@PathParam("dataFim") String dataFim, @PathParam("func") Integer func,
			@PathParam("epi") Integer epi) {
		return daoEpi10.getconsumos(dataIni, dataFim, func, epi);
	}

	// Histórico de EPIs de uma pessoa, com indicação dos que tem atualmente
	@GET
	@Path("/getQUA_EPI_HISTORICO/{func}")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_HISTORICO(@PathParam("func") Integer func) {
		return daoEpi10.gethistoricopessoa(func);
	}

	// Stocks de todos os EPIs, com validade mais próxima e lotes expirados
	@GET
	@Path("/getQUA_EPI_STOCKS")
	@Produces("application/json")
	public List<Object[]> getQUA_EPI_STOCKS() {
		return daoEpi10.getstocks();
	}

	// QUA_MC_SECCOES_CHEFES endpoints
	@GET
	@Path("/getChefesSecao/{idSeccao}")
	@Produces("application/json")
	public List<Map<String, Object>> getChefesSecao(@PathParam("idSeccao") Integer idSeccao) {
		List<Object[]> resultados = daoSeccoesChefes.getChefesSecao(idSeccao);
		List<Map<String, Object>> chefes = new ArrayList<>();
		for (Object[] row : resultados) {
			Map<String, Object> chefe = new HashMap<>();
			chefe.put("ID_SECCAO", row[0]);
			chefe.put("ID_UTILIZADOR", row[1]);
			chefe.put("EMAIL", row[2]);
			chefe.put("NOME_UTILIZADOR", row[3]);
			chefes.add(chefe);
		}
		return chefes;
	}

	@POST
	@Path("/adicionarChefe")
	@Consumes("application/json")
	@Produces("application/json")
	public void adicionarChefe(Map<String, Object> data) {
		Integer idSeccao = ((Number) data.get("idSeccao")).intValue();
		Integer idUtilizador = ((Number) data.get("idUtilizador")).intValue();
		daoSeccoesChefes.adicionarChefe(idSeccao, idUtilizador);
	}

	@DELETE
	@Path("/removerChefe/{idSeccao}/{idUtilizador}")
	@Produces("application/json")
	public void removerChefe(@PathParam("idSeccao") Integer idSeccao, @PathParam("idUtilizador") Integer idUtilizador) {
		daoSeccoesChefes.removerChefe(idSeccao, idUtilizador);
	}

	@GET
	@Path("/getAllUtilizadores")
	@Produces("application/json")
	public List<GER_UTILIZADORES> getAllUtilizadores() {
		return daoUtilizadores.getAll();
	}

	// Enviar email com relatório de declaração NC
	@POST
	@Path("/enviarEmailComDeclaracao")
	@Consumes("application/json")
	@Produces("application/json")
	public Map<String, Object> enviarEmailComDeclaracao(Map<String, Object> data) {
		Map<String, Object> resultado = new HashMap<>();
		try {
			Integer idDeclaracao = ((Number) data.get("idDeclaracao")).intValue();
			String para = (String) data.get("para");
			String assunto = (String) data.get("assunto");
			String mensagem = (String) data.get("mensagem");

			String filename = System.currentTimeMillis() + "";
			String filepath = "sgiid";

			// Gerar PDF via ReportGenerator
			pt.example.bootstrap.ReportGenerator reportGen = new pt.example.bootstrap.ReportGenerator();
			reportGen.relatorio("pdf", filename, idDeclaracao, "declaracao_nc_mc", getURL(), filepath, "", null, null, null, null);

			// Criar e enviar email com anexo
			EMAIL email = new EMAIL();
			email.setDE(data.get("de") != null ? data.get("de").toString() : "");
			email.setPARA(para);
			email.setBCC("");
			email.setASSUNTO(assunto);
			email.setMENSAGEM(mensagem);
			email.setNOME_FICHEIRO(filename);

			// Enviar email (SendEmail adiciona automaticamente .pdf ao nome_ficheiro)
			SendEmail send = new SendEmail();
			send.enviarEmail(email.getDE(), email.getPARA(), email.getASSUNTO(), email.getMENSAGEM(),
				email.getNOME_FICHEIRO(), null, "sgiid", null, email.getBCC());

			resultado.put("sucesso", true);
			resultado.put("mensagem", "Email enviado com sucesso");
		} catch (Exception e) {
			resultado.put("sucesso", false);
			resultado.put("mensagem", "Erro ao enviar email: " + e.getMessage());
			e.printStackTrace();
		}
		return resultado;
	}

	public String getURL() {
		String url = "";
		Query query_folder = entityManager.createNativeQuery("select top 1 * from GER_PARAMETROS a");
		List<Object[]> dados_folder = query_folder.getResultList();
		for (Object[] content : dados_folder) {
			url = content[3].toString();
		}
		return url;
	}
}
