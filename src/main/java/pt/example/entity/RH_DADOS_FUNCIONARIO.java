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
@Table(name = "RH_DADOS_FUNCIONARIO")
public class RH_DADOS_FUNCIONARIO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("ID_FUNCIONARIO")
	private String ID_FUNCIONARIO;

	@JsonProperty("NOME")
	private String NOME;

	@JsonProperty("ESTADO_CIVIL")
	private String ESTADO_CIVIL;

	@JsonProperty("NATURALIDADE")
	private String NATURALIDADE;

	@JsonProperty("NACIONALIDADE")
	private String NACIONALIDADE;

	@JsonProperty("SEXO")
	private Integer SEXO;

	@JsonProperty("TELEMOVEL")
	private String TELEMOVEL;

	@JsonProperty("EMAIL")
	private String EMAIL;

	@JsonProperty("DT_ADMISSAO")
	private Date DT_ADMISSAO;

	@JsonProperty("NIF")
	private String NIF;

	@JsonProperty("DT_NASCIMENTO")
	private Date DT_NASCIMENTO;

	@JsonProperty("MORADA")
	private String MORADA;

	@JsonProperty("CODIGO_POSTAL")
	private String CODIGO_POSTAL;

	@JsonProperty("LOCALIDADE")
	private String LOCALIDADE;

	@JsonProperty("BI_NUM")
	private String BI_NUM;

	@JsonProperty("BI_EMISSAO")
	private Date BI_EMISSAO;

	@JsonProperty("BI_VALIDADE")
	private Date BI_VALIDADE;

	@JsonProperty("NUM_SS")
	private String NUM_SS;

	@JsonProperty("ID_SITUACAO")
	private Integer ID_SITUACAO;

	@JsonProperty("ID_CENTRO_CP")
	private String ID_CENTRO_CP;

	@JsonProperty("ID_PROFISSAO")
	private Integer ID_PROFISSAO;

	@JsonProperty("ID_TP_CONTRATO")
	private Integer ID_TP_CONTRATO;

	@JsonProperty("ID_HABILITACAO")
	private Integer ID_HABILITACAO;

	@JsonProperty("ID_CAT_PROFISSIONAL")
	private String ID_CAT_PROFISSIONAL;

	@JsonProperty("ID_CODIGO_POSTAL")
	private Integer ID_CODIGO_POSTAL;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;

	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID_CODIGO_POSTAL() {
		return ID_CODIGO_POSTAL;
	}

	public void setID_CODIGO_POSTAL(Integer iD_CODIGO_POSTAL) {
		ID_CODIGO_POSTAL = iD_CODIGO_POSTAL;
	}

	public Integer getID() {
		return ID;
	}

	public String getID_FUNCIONARIO() {
		return ID_FUNCIONARIO;
	}

	public String getNOME() {
		return NOME;
	}

	public String getESTADO_CIVIL() {
		return ESTADO_CIVIL;
	}

	public String getNATURALIDADE() {
		return NATURALIDADE;
	}

	public String getNACIONALIDADE() {
		return NACIONALIDADE;
	}

	public Integer getSEXO() {
		return SEXO;
	}

	public String getTELEMOVEL() {
		return TELEMOVEL;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public Date getDT_ADMISSAO() {
		return DT_ADMISSAO;
	}

	public String getNIF() {
		return NIF;
	}

	public Date getDT_NASCIMENTO() {
		return DT_NASCIMENTO;
	}

	public String getMORADA() {
		return MORADA;
	}

	public String getCODIGO_POSTAL() {
		return CODIGO_POSTAL;
	}

	public String getLOCALIDADE() {
		return LOCALIDADE;
	}

	public String getBI_NUM() {
		return BI_NUM;
	}

	public Date getBI_EMISSAO() {
		return BI_EMISSAO;
	}

	public Date getBI_VALIDADE() {
		return BI_VALIDADE;
	}

	public String getNUM_SS() {
		return NUM_SS;
	}

	public Integer getID_SITUACAO() {
		return ID_SITUACAO;
	}

	public String getID_CENTRO_CP() {
		return ID_CENTRO_CP;
	}

	public Integer getID_PROFISSAO() {
		return ID_PROFISSAO;
	}

	public Integer getID_TP_CONTRATO() {
		return ID_TP_CONTRATO;
	}

	public Integer getID_HABILITACAO() {
		return ID_HABILITACAO;
	}

	public String getID_CAT_PROFISSIONAL() {
		return ID_CAT_PROFISSIONAL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_FUNCIONARIO(String iD_FUNCIONARIO) {
		ID_FUNCIONARIO = iD_FUNCIONARIO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setESTADO_CIVIL(String eSTADO_CIVIL) {
		ESTADO_CIVIL = eSTADO_CIVIL;
	}

	public void setNATURALIDADE(String nATURALIDADE) {
		NATURALIDADE = nATURALIDADE;
	}

	public void setNACIONALIDADE(String nACIONALIDADE) {
		NACIONALIDADE = nACIONALIDADE;
	}

	public void setSEXO(Integer sEXO) {
		SEXO = sEXO;
	}

	public void setTELEMOVEL(String tELEMOVEL) {
		TELEMOVEL = tELEMOVEL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public void setDT_ADMISSAO(Date dT_ADMISSAO) {
		DT_ADMISSAO = dT_ADMISSAO;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public void setDT_NASCIMENTO(Date dT_NASCIMENTO) {
		DT_NASCIMENTO = dT_NASCIMENTO;
	}

	public void setMORADA(String mORADA) {
		MORADA = mORADA;
	}

	public void setCODIGO_POSTAL(String cODIGO_POSTAL) {
		CODIGO_POSTAL = cODIGO_POSTAL;
	}

	public void setLOCALIDADE(String lOCALIDADE) {
		LOCALIDADE = lOCALIDADE;
	}

	public void setBI_NUM(String bI_NUM) {
		BI_NUM = bI_NUM;
	}

	public void setBI_EMISSAO(Date bI_EMISSAO) {
		BI_EMISSAO = bI_EMISSAO;
	}

	public void setBI_VALIDADE(Date bI_VALIDADE) {
		BI_VALIDADE = bI_VALIDADE;
	}

	public void setNUM_SS(String nUM_SS) {
		NUM_SS = nUM_SS;
	}

	public void setID_SITUACAO(Integer iD_SITUACAO) {
		ID_SITUACAO = iD_SITUACAO;
	}

	public void setID_CENTRO_CP(String iD_CENTRO_CP) {
		ID_CENTRO_CP = iD_CENTRO_CP;
	}

	public void setID_PROFISSAO(Integer iD_PROFISSAO) {
		ID_PROFISSAO = iD_PROFISSAO;
	}

	public void setID_TP_CONTRATO(Integer iD_TP_CONTRATO) {
		ID_TP_CONTRATO = iD_TP_CONTRATO;
	}

	public void setID_HABILITACAO(Integer iD_HABILITACAO) {
		ID_HABILITACAO = iD_HABILITACAO;
	}

	public void setID_CAT_PROFISSIONAL(String iD_CAT_PROFISSIONAL) {
		ID_CAT_PROFISSIONAL = iD_CAT_PROFISSIONAL;
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

}
