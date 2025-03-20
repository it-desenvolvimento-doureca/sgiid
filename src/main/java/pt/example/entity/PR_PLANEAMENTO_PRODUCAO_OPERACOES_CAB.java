package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB")
public class PR_PLANEAMENTO_PRODUCAO_OPERACOES_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PLANEAMENTO_PRODUCAO_CAB")
	private Integer ID_PLANEAMENTO_PRODUCAO_CAB;
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("DATA_MRP")
	private Timestamp DATA_MRP;
	@JsonProperty("N_MRP")
	private String N_MRP;
	@JsonProperty("USER_MRP")
	private String USER_MRP;
	@JsonProperty("HORA_MRP")
	private String HORA_MRP;
	@JsonProperty("SECCAO")
	private String SECCAO;
	@JsonProperty("ID_SEMANAS_ANALISE")
	private Integer ID_SEMANAS_ANALISE;
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
	@JsonProperty("SEMANAS")
	private String SEMANAS;
	@JsonProperty("NUMERO_SEMANAS")
	private Integer NUMERO_SEMANAS;
	@JsonProperty("OPERACAO")
	private String OPERACAO;

	public Integer getID_PLANEAMENTO_PRODUCAO_CAB() {
		return ID_PLANEAMENTO_PRODUCAO_CAB;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Timestamp getDATA_MRP() {
		return DATA_MRP;
	}

	public String getN_MRP() {
		return N_MRP;
	}

	public String getUSER_MRP() {
		return USER_MRP;
	}

	public String getHORA_MRP() {
		return HORA_MRP;
	}

	public String getSECCAO() {
		return SECCAO;
	}

	public Integer getID_SEMANAS_ANALISE() {
		return ID_SEMANAS_ANALISE;
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

	public String getSEMANAS() {
		return SEMANAS;
	}

	public Integer getNUMERO_SEMANAS() {
		return NUMERO_SEMANAS;
	}

	public void setID_PLANEAMENTO_PRODUCAO_CAB(Integer iD_PLANEAMENTO_PRODUCAO_CAB) {
		ID_PLANEAMENTO_PRODUCAO_CAB = iD_PLANEAMENTO_PRODUCAO_CAB;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_MRP(Timestamp dATA_MRP) {
		DATA_MRP = dATA_MRP;
	}

	public void setN_MRP(String n_MRP) {
		N_MRP = n_MRP;
	}

	public void setUSER_MRP(String uSER_MRP) {
		USER_MRP = uSER_MRP;
	}

	public void setHORA_MRP(String hORA_MRP) {
		HORA_MRP = hORA_MRP;
	}

	public void setSECCAO(String sECCAO) {
		SECCAO = sECCAO;
	}

	public void setID_SEMANAS_ANALISE(Integer iD_SEMANAS_ANALISE) {
		ID_SEMANAS_ANALISE = iD_SEMANAS_ANALISE;
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

	public void setSEMANAS(String sEMANAS) {
		SEMANAS = sEMANAS;
	}

	public void setNUMERO_SEMANAS(Integer nUMERO_SEMANAS) {
		NUMERO_SEMANAS = nUMERO_SEMANAS;
	}

	public String getOPERACAO() {
		return OPERACAO;
	}

	public void setOPERACAO(String oPERACAO) {
		OPERACAO = oPERACAO;
	}

}
