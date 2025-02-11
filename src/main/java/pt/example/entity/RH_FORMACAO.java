package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_FORMACAO")
public class RH_FORMACAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("OBJETIVOS")
	private String OBJETIVOS;
	@JsonProperty("ID_AREA_FORMACAO")
	private Integer ID_AREA_FORMACAO;
	@JsonProperty("MODALIDADE")
	private Integer MODALIDADE;
	@JsonProperty("ID_CRITERIOS_AVALIACAO")
	private Integer ID_CRITERIOS_AVALIACAO;
	@JsonProperty("INTERMO_EXTERNO")
	private Integer INTERMO_EXTERNO;
	@JsonProperty("ID_ENTIDADE_FORMADORA")
	private Integer ID_ENTIDADE_FORMADORA;
	@JsonProperty("FORMADOR")
	private String FORMADOR;
	@JsonProperty("DATA_INICIO")
	private Date DATA_INICIO;
	@JsonProperty("DATA_FIM")
	private Date DATA_FIM;
	@JsonProperty("DURACAO")
	private BigDecimal DURACAO;
	@JsonProperty("HORARIO")
	private String HORARIO;
	@JsonProperty("VALIDADE")
	private Date VALIDADE;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("AVALIACAO_EFICACIA")
	private Boolean AVALIACAO_EFICACIA;
	@JsonProperty("AVALIACAO_TEXTO")
	private String AVALIACAO_TEXTO;
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
	@JsonProperty("AVALIACAO_SATISFACAO")
	private Boolean AVALIACAO_SATISFACAO;

	public Integer getID() {
		return ID;
	}

	public String getNOME() {
		return NOME;
	}

	public String getOBJETIVOS() {
		return OBJETIVOS;
	}

	public Integer getID_AREA_FORMACAO() {
		return ID_AREA_FORMACAO;
	}

	public Integer getMODALIDADE() {
		return MODALIDADE;
	}

	public Integer getID_CRITERIOS_AVALIACAO() {
		return ID_CRITERIOS_AVALIACAO;
	}

	public Integer getINTERMO_EXTERNO() {
		return INTERMO_EXTERNO;
	}

	public Integer getID_ENTIDADE_FORMADORA() {
		return ID_ENTIDADE_FORMADORA;
	}

	public String getFORMADOR() {
		return FORMADOR;
	}

	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	public BigDecimal getDURACAO() {
		return DURACAO;
	}

	public String getHORARIO() {
		return HORARIO;
	}

	public Date getVALIDADE() {
		return VALIDADE;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public Boolean getAVALIACAO_EFICACIA() {
		return AVALIACAO_EFICACIA;
	}

	public String getAVALIACAO_TEXTO() {
		return AVALIACAO_TEXTO;
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

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setOBJETIVOS(String oBJETIVOS) {
		OBJETIVOS = oBJETIVOS;
	}

	public void setID_AREA_FORMACAO(Integer iD_AREA_FORMACAO) {
		ID_AREA_FORMACAO = iD_AREA_FORMACAO;
	}

	public void setMODALIDADE(Integer mODALIDADE) {
		MODALIDADE = mODALIDADE;
	}

	public void setID_CRITERIOS_AVALIACAO(Integer iD_CRITERIOS_AVALIACAO) {
		ID_CRITERIOS_AVALIACAO = iD_CRITERIOS_AVALIACAO;
	}

	public void setINTERMO_EXTERNO(Integer iNTERMO_EXTERNO) {
		INTERMO_EXTERNO = iNTERMO_EXTERNO;
	}

	public void setID_ENTIDADE_FORMADORA(Integer iD_ENTIDADE_FORMADORA) {
		ID_ENTIDADE_FORMADORA = iD_ENTIDADE_FORMADORA;
	}

	public void setFORMADOR(String fORMADOR) {
		FORMADOR = fORMADOR;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setDURACAO(BigDecimal dURACAO) {
		DURACAO = dURACAO;
	}

	public void setHORARIO(String hORARIO) {
		HORARIO = hORARIO;
	}

	public void setVALIDADE(Date vALIDADE) {
		VALIDADE = vALIDADE;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setAVALIACAO_EFICACIA(Boolean aVALIACAO_EFICACIA) {
		AVALIACAO_EFICACIA = aVALIACAO_EFICACIA;
	}

	public void setAVALIACAO_TEXTO(String aVALIACAO_TEXTO) {
		AVALIACAO_TEXTO = aVALIACAO_TEXTO;
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

	public Boolean getAVALIACAO_SATISFACAO() {
		return AVALIACAO_SATISFACAO;
	}

	public void setAVALIACAO_SATISFACAO(Boolean aVALIACAO_SATISFACAO) {
		AVALIACAO_SATISFACAO = aVALIACAO_SATISFACAO;
	}

	
}