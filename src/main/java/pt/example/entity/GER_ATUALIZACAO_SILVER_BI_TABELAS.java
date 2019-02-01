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
@Table(name = "GER_ATUALIZACAO_SILVER_BI_TABELAS")
public class GER_ATUALIZACAO_SILVER_BI_TABELAS {
	private Integer ID;

	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private String TABELA;
	private String DIAS;
	private Boolean TOTAL;
	private Boolean ATIVO;
	private String BASE_DE_DADOS;
	private Integer DIA_DA_SEMANA;
	private String HORAS;
	private Integer TIPO_OCORRENCIA;
	private Date DATA_INICIAL;
	private Timestamp DATA_ATUALIZACAO;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "TABELA")
	public String getTABELA() {
		return TABELA;
	}

	@Column(name = "DIAS")
	public String getDIAS() {
		return DIAS;
	}

	@Column(name = "TOTAL")
	public Boolean getTOTAL() {
		return TOTAL;
	}

	@Column(name = "DIA_DA_SEMANA")
	public Integer getDIA_DA_SEMANA() {
		return DIA_DA_SEMANA;
	}

	@Column(name = "HORAS")
	public String getHORAS() {
		return HORAS;
	}

	@Column(name = "TIPO_OCORRENCIA")
	public Integer getTIPO_OCORRENCIA() {
		return TIPO_OCORRENCIA;
	}

	@Column(name = "DATA_INICIAL")
	public Date getDATA_INICIAL() {
		return DATA_INICIAL;
	}

	@Column(name = "ATIVO")
	public Boolean getATIVO() {
		return ATIVO;
	}

	@Column(name = "BASE_DE_DADOS")
	public String getBASE_DE_DADOS() {
		return BASE_DE_DADOS;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public void setBASE_DE_DADOS(String bASE_DE_DADOS) {
		BASE_DE_DADOS = bASE_DE_DADOS;
	}

	public void setDIA_DA_SEMANA(Integer dIA_DA_SEMANA) {
		DIA_DA_SEMANA = dIA_DA_SEMANA;
	}

	public void setHORAS(String hORAS) {
		HORAS = hORAS;
	}

	public void setTIPO_OCORRENCIA(Integer tIPO_OCORRENCIA) {
		TIPO_OCORRENCIA = tIPO_OCORRENCIA;
	}

	public void setDATA_INICIAL(Date dATA_INICIAL) {
		DATA_INICIAL = dATA_INICIAL;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setTABELA(String tABELA) {
		TABELA = tABELA;
	}

	public void setDIAS(String dIAS) {
		DIAS = dIAS;
	}

	public void setTOTAL(Boolean tOTAL) {
		TOTAL = tOTAL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@Column(name = "DATA_ATUALIZACAO")
	public Timestamp getDATA_ATUALIZACAO() {
		return DATA_ATUALIZACAO;
	}

	public void setDATA_ATUALIZACAO(Timestamp dATA_ATUALIZACAO) {
		DATA_ATUALIZACAO = dATA_ATUALIZACAO;
	}

}
