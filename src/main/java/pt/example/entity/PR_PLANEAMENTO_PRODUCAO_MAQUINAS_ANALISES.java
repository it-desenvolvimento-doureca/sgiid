package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES")
public class PR_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES")
	private Integer ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES;
	@JsonProperty("ANO")
	private Integer ANO;
	@JsonProperty("SEMANA")
	private Integer SEMANA;
	@JsonProperty("N_SEMANAS")
	private Integer N_SEMANAS;
	@JsonProperty("ID_PLANOS")
	private String ID_PLANOS;
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
	@JsonProperty("DATA_MRP")
	private Date DATA_MRP;
	@JsonProperty("TIPO_ANALISE")
	private String TIPO_ANALISE;

	public Integer getANO() {
		return ANO;
	}

	public Integer getSEMANA() {
		return SEMANA;
	}

	public Integer getN_SEMANAS() {
		return N_SEMANAS;
	}

	public String getID_PLANOS() {
		return ID_PLANOS;
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

	public Date getDATA_MRP() {
		return DATA_MRP;
	}

	public String getTIPO_ANALISE() {
		return TIPO_ANALISE;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setSEMANA(Integer sEMANA) {
		SEMANA = sEMANA;
	}

	public void setN_SEMANAS(Integer n_SEMANAS) {
		N_SEMANAS = n_SEMANAS;
	}

	public void setID_PLANOS(String iD_PLANOS) {
		ID_PLANOS = iD_PLANOS;
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

	public void setDATA_MRP(Date dATA_MRP) {
		DATA_MRP = dATA_MRP;
	}

	public void setTIPO_ANALISE(String tIPO_ANALISE) {
		TIPO_ANALISE = tIPO_ANALISE;
	}

	public Integer getID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES() {
		return ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES;
	}

	public void setID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES(Integer iD_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES) {
		ID_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES = iD_PLANEAMENTO_PRODUCAO_MAQUINAS_ANALISES;
	}

}