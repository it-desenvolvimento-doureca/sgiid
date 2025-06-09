package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_SUGESTOES")
public class RH_SUGESTOES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ASSUNTO")
	private String ASSUNTO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("CODIGO")
	private String CODIGO;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("MOTIVO_REJEICAO")
	private String MOTIVO_REJEICAO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("IP")
	private String IP;
	@JsonProperty("DATA_IMPLEMENTADA")
	private Timestamp DATA_IMPLEMENTADA;
	@JsonProperty("DATA_PLANEADA")
	private Timestamp DATA_PLANEADA;
	@JsonProperty("DATA_REJEITADA")
	private Timestamp DATA_REJEITADA;
	@JsonProperty("UTZ_IMPLEMENTADA")
	private Integer UTZ_IMPLEMENTADA;
	@JsonProperty("UTZ_PLANEADA")
	private Integer UTZ_PLANEADA;
	@JsonProperty("UTZ_REJEITADA")
	private Integer UTZ_REJEITADA;

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Integer getID() {
		return ID;
	}

	public String getASSUNTO() {
		return ASSUNTO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public String getCODIGO() {
		return CODIGO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public String getMOTIVO_REJEICAO() {
		return MOTIVO_REJEICAO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setASSUNTO(String aSSUNTO) {
		ASSUNTO = aSSUNTO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setMOTIVO_REJEICAO(String mOTIVO_REJEICAO) {
		MOTIVO_REJEICAO = mOTIVO_REJEICAO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public Timestamp getDATA_IMPLEMENTADA() {
		return DATA_IMPLEMENTADA;
	}

	public Timestamp getDATA_PLANEADA() {
		return DATA_PLANEADA;
	}

	public Timestamp getDATA_REJEITADA() {
		return DATA_REJEITADA;
	}

	public Integer getUTZ_IMPLEMENTADA() {
		return UTZ_IMPLEMENTADA;
	}

	public Integer getUTZ_PLANEADA() {
		return UTZ_PLANEADA;
	}

	public Integer getUTZ_REJEITADA() {
		return UTZ_REJEITADA;
	}

	public void setDATA_IMPLEMENTADA(Timestamp dATA_IMPLEMENTADA) {
		DATA_IMPLEMENTADA = dATA_IMPLEMENTADA;
	}

	public void setDATA_PLANEADA(Timestamp dATA_PLANEADA) {
		DATA_PLANEADA = dATA_PLANEADA;
	}

	public void setDATA_REJEITADA(Timestamp dATA_REJEITADA) {
		DATA_REJEITADA = dATA_REJEITADA;
	}

	public void setUTZ_IMPLEMENTADA(Integer uTZ_IMPLEMENTADA) {
		UTZ_IMPLEMENTADA = uTZ_IMPLEMENTADA;
	}

	public void setUTZ_PLANEADA(Integer uTZ_PLANEADA) {
		UTZ_PLANEADA = uTZ_PLANEADA;
	}

	public void setUTZ_REJEITADA(Integer uTZ_REJEITADA) {
		UTZ_REJEITADA = uTZ_REJEITADA;
	}

}
