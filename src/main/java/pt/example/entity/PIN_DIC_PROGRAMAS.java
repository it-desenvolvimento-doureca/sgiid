package pt.example.entity;

import java.math.BigDecimal;
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
@Table(name = "PIN_DIC_PROGRAMAS")
public class PIN_DIC_PROGRAMAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("CODIGO")
	private String CODIGO;
	@JsonProperty("NOME_PROGRAMA")
	private String NOME_PROGRAMA;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
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
	@JsonProperty("RACK")
	private String RACK;
	@JsonProperty("QTD_RACK")
	private Integer QTD_RACK;

	public Integer getID() {
		return ID;
	}

	public String getNOME_PROGRAMA() {
		return NOME_PROGRAMA;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
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

	public void setNOME_PROGRAMA(String nOME_PROGRAMA) {
		NOME_PROGRAMA = nOME_PROGRAMA;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
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

	public String getRACK() {
		return RACK;
	}

	public Integer getQTD_RACK() {
		return QTD_RACK;
	}

	public void setRACK(String rACK) {
		RACK = rACK;
	}

	public void setQTD_RACK(Integer qTD_RACK) {
		QTD_RACK = qTD_RACK;
	}

	public String getCODIGO() {
		return CODIGO;
	}

	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}

}