package pt.example.entity;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_CANDIDATURAS")
public class RH_CANDIDATURAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
    private Integer ID;
	@JsonProperty("NOME")
    private String NOME;
	@JsonProperty("EMAIL")
    private String EMAIL;
	@JsonProperty("DATANASCIMENTO")
    private String DATANASCIMENTO;
	@JsonProperty("GENERO")
    private String GENERO;
	@JsonProperty("TELEMOVEL")
    private String TELEMOVEL;
	@JsonProperty("DOMICILIO")
    private String DOMICILIO;
	@JsonProperty("FREGUESIA")
    private String FREGUESIA;
	@JsonProperty("CONCELHO")
    private String CONCELHO;
	@JsonProperty("CODIGOPOSTAL")
    private String CODIGOPOSTAL;
	@JsonProperty("DISTRITO")
    private String DISTRITO;
	@JsonProperty("CONTRIBUINTE")
    private String CONTRIBUINTE;
	@JsonProperty("CARTACONDUCAO")
    private String CARTACONDUCAO;
	@JsonProperty("TRABALHOUDOURECA")
    private String TRABALHOUDOURECA;
	@JsonProperty("CONHECEUDOURECA")
    private String CONHECEUDOURECA;
	@JsonProperty("FUNCOESINTERESSE")
    private String FUNCOESINTERESSE;
	@JsonProperty("HABILITACOES")
    private String HABILITACOES;
	@JsonProperty("AREAFORMACAO")
    private String AREAFORMACAO;
	@JsonProperty("ESTABELECIMENTOENSINO")
    private String ESTABELECIMENTOENSINO;
	@JsonProperty("ANOCONCLUSAO")
    private String ANOCONCLUSAO;
	@JsonProperty("FORMACOESRELEVANTES")
    private String FORMACOESRELEVANTES;
	@JsonProperty("CERTIFICADOEMPILHADORES")
    private String CERTIFICADOEMPILHADORES;
	@JsonProperty("ULTIMAEMPRESA")
    private String ULTIMAEMPRESA;
	@JsonProperty("ULTIMAFUNCAO")
    private String ULTIMAFUNCAO;
	@JsonProperty("DATAINICIO")
    private String DATAINICIO;
	@JsonProperty("DATAFIM")
    private String DATAFIM;
	@JsonProperty("NOMECV")
    private String NOMECV;
	@JsonProperty("DISPONIBILIDADE")
    private String DISPONIBILIDADE;
	@JsonProperty("CONTEUDOCV_1")
    private String CONTEUDOCV_1;
	@JsonProperty("CONTEUDOCV_2")
    private String CONTEUDOCV_2;
	@JsonProperty("DATA_SUBMISSAO")
    private Timestamp DATA_SUBMISSAO;
	@JsonProperty("DATA_CRIA")
    private Timestamp DATA_CRIA;
	@JsonProperty("DATA_MODIF")
    private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_CRIA")
    private Integer UTZ_CRIA;
	@JsonProperty("UTZ_MODIF")
    private Integer UTZ_MODIF;
	public Integer getID() {
		return ID;
	}
	public String getNOME() {
		return NOME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public String getDATANASCIMENTO() {
		return DATANASCIMENTO;
	}
	public String getGENERO() {
		return GENERO;
	}
	public String getTELEMOVEL() {
		return TELEMOVEL;
	}
	public String getDOMICILIO() {
		return DOMICILIO;
	}
	public String getFREGUESIA() {
		return FREGUESIA;
	}
	public String getCONCELHO() {
		return CONCELHO;
	}
	public String getCODIGOPOSTAL() {
		return CODIGOPOSTAL;
	}
	public String getDISTRITO() {
		return DISTRITO;
	}
	public String getCONTRIBUINTE() {
		return CONTRIBUINTE;
	}
	public String getCARTACONDUCAO() {
		return CARTACONDUCAO;
	}
	public String getTRABALHOUDOURECA() {
		return TRABALHOUDOURECA;
	}
	public String getCONHECEUDOURECA() {
		return CONHECEUDOURECA;
	}
	public String getFUNCOESINTERESSE() {
		return FUNCOESINTERESSE;
	}
	public String getHABILITACOES() {
		return HABILITACOES;
	}
	public String getAREAFORMACAO() {
		return AREAFORMACAO;
	}
	public String getESTABELECIMENTOENSINO() {
		return ESTABELECIMENTOENSINO;
	}
	public String getANOCONCLUSAO() {
		return ANOCONCLUSAO;
	}
	public String getFORMACOESRELEVANTES() {
		return FORMACOESRELEVANTES;
	}
	public String getCERTIFICADOEMPILHADORES() {
		return CERTIFICADOEMPILHADORES;
	}
	public String getULTIMAEMPRESA() {
		return ULTIMAEMPRESA;
	}
	public String getULTIMAFUNCAO() {
		return ULTIMAFUNCAO;
	}
	public String getDATAINICIO() {
		return DATAINICIO;
	}
	public String getDATAFIM() {
		return DATAFIM;
	}
	public String getNOMECV() {
		return NOMECV;
	}
	public String getDISPONIBILIDADE() {
		return DISPONIBILIDADE;
	}
	public String getCONTEUDOCV_1() {
		return CONTEUDOCV_1;
	}
	public String getCONTEUDOCV_2() {
		return CONTEUDOCV_2;
	}
	public Timestamp getDATA_SUBMISSAO() {
		return DATA_SUBMISSAO;
	}
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public void setDATANASCIMENTO(String dATANASCIMENTO) {
		DATANASCIMENTO = dATANASCIMENTO;
	}
	public void setGENERO(String gENERO) {
		GENERO = gENERO;
	}
	public void setTELEMOVEL(String tELEMOVEL) {
		TELEMOVEL = tELEMOVEL;
	}
	public void setDOMICILIO(String dOMICILIO) {
		DOMICILIO = dOMICILIO;
	}
	public void setFREGUESIA(String fREGUESIA) {
		FREGUESIA = fREGUESIA;
	}
	public void setCONCELHO(String cONCELHO) {
		CONCELHO = cONCELHO;
	}
	public void setCODIGOPOSTAL(String cODIGOPOSTAL) {
		CODIGOPOSTAL = cODIGOPOSTAL;
	}
	public void setDISTRITO(String dISTRITO) {
		DISTRITO = dISTRITO;
	}
	public void setCONTRIBUINTE(String cONTRIBUINTE) {
		CONTRIBUINTE = cONTRIBUINTE;
	}
	public void setCARTACONDUCAO(String cARTACONDUCAO) {
		CARTACONDUCAO = cARTACONDUCAO;
	}
	public void setTRABALHOUDOURECA(String tRABALHOUDOURECA) {
		TRABALHOUDOURECA = tRABALHOUDOURECA;
	}
	public void setCONHECEUDOURECA(String cONHECEUDOURECA) {
		CONHECEUDOURECA = cONHECEUDOURECA;
	}
	public void setFUNCOESINTERESSE(String fUNCOESINTERESSE) {
		FUNCOESINTERESSE = fUNCOESINTERESSE;
	}
	public void setHABILITACOES(String hABILITACOES) {
		HABILITACOES = hABILITACOES;
	}
	public void setAREAFORMACAO(String aREAFORMACAO) {
		AREAFORMACAO = aREAFORMACAO;
	}
	public void setESTABELECIMENTOENSINO(String eSTABELECIMENTOENSINO) {
		ESTABELECIMENTOENSINO = eSTABELECIMENTOENSINO;
	}
	public void setANOCONCLUSAO(String aNOCONCLUSAO) {
		ANOCONCLUSAO = aNOCONCLUSAO;
	}
	public void setFORMACOESRELEVANTES(String fORMACOESRELEVANTES) {
		FORMACOESRELEVANTES = fORMACOESRELEVANTES;
	}
	public void setCERTIFICADOEMPILHADORES(String cERTIFICADOEMPILHADORES) {
		CERTIFICADOEMPILHADORES = cERTIFICADOEMPILHADORES;
	}
	public void setULTIMAEMPRESA(String uLTIMAEMPRESA) {
		ULTIMAEMPRESA = uLTIMAEMPRESA;
	}
	public void setULTIMAFUNCAO(String uLTIMAFUNCAO) {
		ULTIMAFUNCAO = uLTIMAFUNCAO;
	}
	public void setDATAINICIO(String dATAINICIO) {
		DATAINICIO = dATAINICIO;
	}
	public void setDATAFIM(String dATAFIM) {
		DATAFIM = dATAFIM;
	}
	public void setNOMECV(String nOMECV) {
		NOMECV = nOMECV;
	}
	public void setDISPONIBILIDADE(String dISPONIBILIDADE) {
		DISPONIBILIDADE = dISPONIBILIDADE;
	}
	public void setCONTEUDOCV_1(String cONTEUDOCV_1) {
		CONTEUDOCV_1 = cONTEUDOCV_1;
	}
	public void setCONTEUDOCV_2(String cONTEUDOCV_2) {
		CONTEUDOCV_2 = cONTEUDOCV_2;
	}
	public void setDATA_SUBMISSAO(Timestamp dATA_SUBMISSAO) {
		DATA_SUBMISSAO = dATA_SUBMISSAO;
	}
	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}
	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}
	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}
	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
	
	
	

}
