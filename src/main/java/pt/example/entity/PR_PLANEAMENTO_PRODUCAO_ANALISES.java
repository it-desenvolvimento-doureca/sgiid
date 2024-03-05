package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_PLANEAMENTO_PRODUCAO_ANALISES")
public class PR_PLANEAMENTO_PRODUCAO_ANALISES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLANEAMENTO_PRODUCAO_ANALISES")
	private Integer ID_PLANEAMENTO_PRODUCAO_ANALISES;
	@Column(name = "ANO")
	private Integer ANO;
	@Column(name = "SEMANA")
	private Integer SEMANA;
	@Column(name = "N_SEMANAS")
	private Integer N_SEMANAS;
	@Column(name = "ID_PLANO_LINHA_1")
	private Integer ID_PLANO_LINHA_1;
	@Column(name = "ID_PLANO_LINHA_2")
	private Integer ID_PLANO_LINHA_2;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_ANULA")
	private Timestamp DATA_ANULA;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;
	@Column(name = "ATIVO")
	private Boolean ATIVO;
	@Column(name = "DATA_MRP")
	private Date DATA_MRP;
	@Column(name = "TIPO_ANALISE")
	private String TIPO_ANALISE;

	public Integer getID_PLANEAMENTO_PRODUCAO_ANALISES() {
		return ID_PLANEAMENTO_PRODUCAO_ANALISES;
	}

	public Integer getANO() {
		return ANO;
	}

	public Integer getSEMANA() {
		return SEMANA;
	}

	public Integer getN_SEMANAS() {
		return N_SEMANAS;
	}

	public Integer getID_PLANO_LINHA_1() {
		return ID_PLANO_LINHA_1;
	}

	public Integer getID_PLANO_LINHA_2() {
		return ID_PLANO_LINHA_2;
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

	public void setID_PLANEAMENTO_PRODUCAO_ANALISES(Integer iD_PLANEAMENTO_PRODUCAO_ANALISES) {
		ID_PLANEAMENTO_PRODUCAO_ANALISES = iD_PLANEAMENTO_PRODUCAO_ANALISES;
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

	public void setID_PLANO_LINHA_1(Integer iD_PLANO_LINHA_1) {
		ID_PLANO_LINHA_1 = iD_PLANO_LINHA_1;
	}

	public void setID_PLANO_LINHA_2(Integer iD_PLANO_LINHA_2) {
		ID_PLANO_LINHA_2 = iD_PLANO_LINHA_2;
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

	public Date getDATA_MRP() {
		return DATA_MRP;
	}

	public void setDATA_MRP(Date dATA_MRP) {
		DATA_MRP = dATA_MRP;
	}

	public String getTIPO_ANALISE() {
		return TIPO_ANALISE;
	}

	public void setTIPO_ANALISE(String tIPO_ANALISE) {
		TIPO_ANALISE = tIPO_ANALISE;
	}

}