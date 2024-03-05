package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_REGISTO_BASTIDOR")
public class PIN_DIC_REGISTO_BASTIDOR {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("ID_CABINE")
	private Integer ID_CABINE;
	@JsonProperty("TEMPERATURA")
	private BigDecimal TEMPERATURA;
	@JsonProperty("HUMIDADE")
	private BigDecimal HUMIDADE;
	@JsonProperty("DATA")
	private Date DATA;
	@JsonProperty("HORA")
	private Time HORA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("ID_CABINE_2")
	private Integer ID_CABINE_2;
	@JsonProperty("TEMPERATURA_2")
	private BigDecimal TEMPERATURA_2;
	@JsonProperty("HUMIDADE_2")
	private BigDecimal HUMIDADE_2;
	@JsonProperty("ID_CABINE_3")
	private Integer ID_CABINE_3;
	@JsonProperty("TEMPERATURA_3")
	private BigDecimal TEMPERATURA_3;
	@JsonProperty("HUMIDADE_3")
	private BigDecimal HUMIDADE_3;
	@JsonProperty("VERSAO")
	private Integer VERSAO;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
	}

	public Integer getID_CABINE() {
		return ID_CABINE;
	}

	public void setID_CABINE(Integer iD_CABINE) {
		ID_CABINE = iD_CABINE;
	}

	public BigDecimal getTEMPERATURA() {
		return TEMPERATURA;
	}

	public void setTEMPERATURA(BigDecimal tEMPERATURA) {
		TEMPERATURA = tEMPERATURA;
	}

	public BigDecimal getHUMIDADE() {
		return HUMIDADE;
	}

	public void setHUMIDADE(BigDecimal hUMIDADE) {
		HUMIDADE = hUMIDADE;
	}

	public Date getDATA() {
		return DATA;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public Time getHORA() {
		return HORA;
	}

	public void setHORA(Time hORA) {
		HORA = hORA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public Integer getID_CABINE_2() {
		return ID_CABINE_2;
	}

	public void setID_CABINE_2(Integer iD_CABINE_2) {
		ID_CABINE_2 = iD_CABINE_2;
	}

	public BigDecimal getTEMPERATURA_2() {
		return TEMPERATURA_2;
	}

	public void setTEMPERATURA_2(BigDecimal tEMPERATURA_2) {
		TEMPERATURA_2 = tEMPERATURA_2;
	}

	public BigDecimal getHUMIDADE_2() {
		return HUMIDADE_2;
	}

	public void setHUMIDADE_2(BigDecimal hUMIDADE_2) {
		HUMIDADE_2 = hUMIDADE_2;
	}

	public Integer getID_CABINE_3() {
		return ID_CABINE_3;
	}

	public void setID_CABINE_3(Integer iD_CABINE_3) {
		ID_CABINE_3 = iD_CABINE_3;
	}

	public BigDecimal getTEMPERATURA_3() {
		return TEMPERATURA_3;
	}

	public void setTEMPERATURA_3(BigDecimal tEMPERATURA_3) {
		TEMPERATURA_3 = tEMPERATURA_3;
	}

	public BigDecimal getHUMIDADE_3() {
		return HUMIDADE_3;
	}

	public void setHUMIDADE_3(BigDecimal hUMIDADE_3) {
		HUMIDADE_3 = hUMIDADE_3;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}
}
