package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_MC_MOV_CALIB_EQUIP_DET")
public class QUA_MC_MOV_CALIB_EQUIP_DET {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CALIB_EQUIP_DET")
	private Integer ID_CALIB_EQUIP_DET;
	@JsonProperty("ID_CALIB_EQUIP")
	private Integer ID_CALIB_EQUIP;
	@JsonProperty("NUM")
	private Integer NUM;
	@JsonProperty("ID_TIPO_CALIBRACAO")
	private Integer ID_TIPO_CALIBRACAO;
	@JsonProperty("ID_TIPO_ACEITACAO")
	private Integer ID_TIPO_ACEITACAO;
	@JsonProperty("NUM_CERTIF_EXTERNO")
	private String NUM_CERTIF_EXTERNO;
	@JsonProperty("ANEXO_CERTIF_EXTERNO")
	private String ANEXO_CERTIF_EXTERNO;
	@JsonProperty("ID_ENTIDADE_CALIBRACAO")
	private Integer ID_ENTIDADE_CALIBRACAO;
	@JsonProperty("DATA_CALIBRACAO")
	private Date DATA_CALIBRACAO;
	@JsonProperty("VALIDADE_CALIBRACAO")
	private String VALIDADE_CALIBRACAO;
	@JsonProperty("NUM_VALIDADE_CALIBRACAO")
	private String NUM_VALIDADE_CALIBRACAO;
	@JsonProperty("ID_RESP_VALIDACAO")
	private Integer ID_RESP_VALIDACAO;
	@JsonProperty("ID_RESULTADO_VALIDACAO")
	private Integer ID_RESULTADO_VALIDACAO;
	@JsonProperty("PERIODO_INTERCALIB_ANTERIOR")
	private String PERIODO_INTERCALIB_ANTERIOR;
	@JsonProperty("ERRO_MAXIMO")
	private java.math.BigDecimal ERRO_MAXIMO;
	@JsonProperty("ID_ESTADO_METROLOGICO")
	private Integer ID_ESTADO_METROLOGICO;
	@JsonProperty("ID_MSA")
	private Integer ID_MSA;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Date DATA_CRIA;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF")
	private Date DATA_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Date DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID_CALIB_EQUIP_DET() { return ID_CALIB_EQUIP_DET; }
	public void setID_CALIB_EQUIP_DET(Integer v) { ID_CALIB_EQUIP_DET = v; }
	public Integer getID_CALIB_EQUIP() { return ID_CALIB_EQUIP; }
	public void setID_CALIB_EQUIP(Integer v) { ID_CALIB_EQUIP = v; }
	public Integer getNUM() { return NUM; }
	public void setNUM(Integer v) { NUM = v; }
	public Integer getID_TIPO_CALIBRACAO() { return ID_TIPO_CALIBRACAO; }
	public void setID_TIPO_CALIBRACAO(Integer v) { ID_TIPO_CALIBRACAO = v; }
	public Integer getID_TIPO_ACEITACAO() { return ID_TIPO_ACEITACAO; }
	public void setID_TIPO_ACEITACAO(Integer v) { ID_TIPO_ACEITACAO = v; }
	public String getNUM_CERTIF_EXTERNO() { return NUM_CERTIF_EXTERNO; }
	public void setNUM_CERTIF_EXTERNO(String v) { NUM_CERTIF_EXTERNO = v; }
	public String getANEXO_CERTIF_EXTERNO() { return ANEXO_CERTIF_EXTERNO; }
	public void setANEXO_CERTIF_EXTERNO(String v) { ANEXO_CERTIF_EXTERNO = v; }
	public Integer getID_ENTIDADE_CALIBRACAO() { return ID_ENTIDADE_CALIBRACAO; }
	public void setID_ENTIDADE_CALIBRACAO(Integer v) { ID_ENTIDADE_CALIBRACAO = v; }
	public Date getDATA_CALIBRACAO() { return DATA_CALIBRACAO; }
	public void setDATA_CALIBRACAO(Date v) { DATA_CALIBRACAO = v; }
	public String getVALIDADE_CALIBRACAO() { return VALIDADE_CALIBRACAO; }
	public void setVALIDADE_CALIBRACAO(String v) { VALIDADE_CALIBRACAO = v; }
	public String getNUM_VALIDADE_CALIBRACAO() { return NUM_VALIDADE_CALIBRACAO; }
	public void setNUM_VALIDADE_CALIBRACAO(String v) { NUM_VALIDADE_CALIBRACAO = v; }
	public Integer getID_RESP_VALIDACAO() { return ID_RESP_VALIDACAO; }
	public void setID_RESP_VALIDACAO(Integer v) { ID_RESP_VALIDACAO = v; }
	public Integer getID_RESULTADO_VALIDACAO() { return ID_RESULTADO_VALIDACAO; }
	public void setID_RESULTADO_VALIDACAO(Integer v) { ID_RESULTADO_VALIDACAO = v; }
	public String getPERIODO_INTERCALIB_ANTERIOR() { return PERIODO_INTERCALIB_ANTERIOR; }
	public void setPERIODO_INTERCALIB_ANTERIOR(String v) { PERIODO_INTERCALIB_ANTERIOR = v; }
	public java.math.BigDecimal getERRO_MAXIMO() { return ERRO_MAXIMO; }
	public void setERRO_MAXIMO(java.math.BigDecimal v) { ERRO_MAXIMO = v; }
	public Integer getID_ESTADO_METROLOGICO() { return ID_ESTADO_METROLOGICO; }
	public void setID_ESTADO_METROLOGICO(Integer v) { ID_ESTADO_METROLOGICO = v; }
	public Integer getID_MSA() { return ID_MSA; }
	public void setID_MSA(Integer v) { ID_MSA = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Date getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Date v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Date getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Date v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Date getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Date v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}
