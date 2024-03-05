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
@Table(name = "PIN_DIC_REGISTO_SALAS_MISTURA")
public class PIN_DIC_REGISTO_SALAS_MISTURA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("ID_CABINE")
	private Integer ID_CABINE;
	@JsonProperty("DATA")
	private Date DATA;
	@JsonProperty("HORA")
	private Time HORA;
	@JsonProperty("TEMPERATURA")
	private BigDecimal TEMPERATURA;
	@JsonProperty("VISCOSIDADE")
	private BigDecimal VISCOSIDADE;
	@JsonProperty("ID_REFERENCIA_COR")
	private Integer ID_REFERENCIA_COR;
	@JsonProperty("REFERENCIA_COR")
	private String REFERENCIA_COR;
	@JsonProperty("VALOR_COR")
	private BigDecimal VALOR_COR;
	@JsonProperty("REFERENCIA_COR_DESC")
	private String REFERENCIA_COR_DESC;
	@JsonProperty("LOTE_COR")
	private String LOTE_COR;
	@JsonProperty("ID_REFERENCIA_DILUENTE")
	private Integer ID_REFERENCIA_DILUENTE;
	@JsonProperty("REFERENCIA_DILUENTE")
	private String REFERENCIA_DILUENTE;
	@JsonProperty("VALOR_DILUENTE")
	private BigDecimal VALOR_DILUENTE;
	@JsonProperty("REFERENCIA_DILUENTE_DESC")
	private String REFERENCIA_DILUENTE_DESC;
	@JsonProperty("LOTE_DILUENTE")
	private String LOTE_DILUENTE;
	@JsonProperty("ID_REFERENCIA_CATALIZADOR")
	private Integer ID_REFERENCIA_CATALIZADOR;
	@JsonProperty("REFERENCIA_CATALIZADOR")
	private String REFERENCIA_CATALIZADOR;
	@JsonProperty("VALOR_CATALIZADOR")
	private BigDecimal VALOR_CATALIZADOR;
	@JsonProperty("REFERENCIA_CATALIZADOR_DESC")
	private String REFERENCIA_CATALIZADOR_DESC;
	@JsonProperty("LOTE_CATALIZADOR")
	private String LOTE_CATALIZADOR;
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

	public BigDecimal getTEMPERATURA() {
		return TEMPERATURA;
	}

	public void setTEMPERATURA(BigDecimal tEMPERATURA) {
		TEMPERATURA = tEMPERATURA;
	}

	public BigDecimal getVISCOSIDADE() {
		return VISCOSIDADE;
	}

	public void setVISCOSIDADE(BigDecimal vISCOSIDADE) {
		VISCOSIDADE = vISCOSIDADE;
	}

	public Integer getID_REFERENCIA_COR() {
		return ID_REFERENCIA_COR;
	}

	public void setID_REFERENCIA_COR(Integer iD_REFERENCIA_COR) {
		ID_REFERENCIA_COR = iD_REFERENCIA_COR;
	}

	public String getREFERENCIA_COR() {
		return REFERENCIA_COR;
	}

	public void setREFERENCIA_COR(String rEFERENCIA_COR) {
		REFERENCIA_COR = rEFERENCIA_COR;
	}

	public BigDecimal getVALOR_COR() {
		return VALOR_COR;
	}

	public void setVALOR_COR(BigDecimal vALOR_COR) {
		VALOR_COR = vALOR_COR;
	}

	public String getREFERENCIA_COR_DESC() {
		return REFERENCIA_COR_DESC;
	}

	public void setREFERENCIA_COR_DESC(String rEFERENCIA_COR_DESC) {
		REFERENCIA_COR_DESC = rEFERENCIA_COR_DESC;
	}

	public String getLOTE_COR() {
		return LOTE_COR;
	}

	public void setLOTE_COR(String lOTE_COR) {
		LOTE_COR = lOTE_COR;
	}

	public Integer getID_REFERENCIA_DILUENTE() {
		return ID_REFERENCIA_DILUENTE;
	}

	public void setID_REFERENCIA_DILUENTE(Integer iD_REFERENCIA_DILUENTE) {
		ID_REFERENCIA_DILUENTE = iD_REFERENCIA_DILUENTE;
	}

	public String getREFERENCIA_DILUENTE() {
		return REFERENCIA_DILUENTE;
	}

	public void setREFERENCIA_DILUENTE(String rEFERENCIA_DILUENTE) {
		REFERENCIA_DILUENTE = rEFERENCIA_DILUENTE;
	}

	public BigDecimal getVALOR_DILUENTE() {
		return VALOR_DILUENTE;
	}

	public void setVALOR_DILUENTE(BigDecimal vALOR_DILUENTE) {
		VALOR_DILUENTE = vALOR_DILUENTE;
	}

	public String getREFERENCIA_DILUENTE_DESC() {
		return REFERENCIA_DILUENTE_DESC;
	}

	public void setREFERENCIA_DILUENTE_DESC(String rEFERENCIA_DILUENTE_DESC) {
		REFERENCIA_DILUENTE_DESC = rEFERENCIA_DILUENTE_DESC;
	}

	public String getLOTE_DILUENTE() {
		return LOTE_DILUENTE;
	}

	public void setLOTE_DILUENTE(String lOTE_DILUENTE) {
		LOTE_DILUENTE = lOTE_DILUENTE;
	}

	public Integer getID_REFERENCIA_CATALIZADOR() {
		return ID_REFERENCIA_CATALIZADOR;
	}

	public void setID_REFERENCIA_CATALIZADOR(Integer iD_REFERENCIA_CATALIZADOR) {
		ID_REFERENCIA_CATALIZADOR = iD_REFERENCIA_CATALIZADOR;
	}

	public String getREFERENCIA_CATALIZADOR() {
		return REFERENCIA_CATALIZADOR;
	}

	public void setREFERENCIA_CATALIZADOR(String rEFERENCIA_CATALIZADOR) {
		REFERENCIA_CATALIZADOR = rEFERENCIA_CATALIZADOR;
	}

	public BigDecimal getVALOR_CATALIZADOR() {
		return VALOR_CATALIZADOR;
	}

	public void setVALOR_CATALIZADOR(BigDecimal vALOR_CATALIZADOR) {
		VALOR_CATALIZADOR = vALOR_CATALIZADOR;
	}

	public String getREFERENCIA_CATALIZADOR_DESC() {
		return REFERENCIA_CATALIZADOR_DESC;
	}

	public void setREFERENCIA_CATALIZADOR_DESC(String rEFERENCIA_CATALIZADOR_DESC) {
		REFERENCIA_CATALIZADOR_DESC = rEFERENCIA_CATALIZADOR_DESC;
	}

	public String getLOTE_CATALIZADOR() {
		return LOTE_CATALIZADOR;
	}

	public void setLOTE_CATALIZADOR(String lOTE_CATALIZADOR) {
		LOTE_CATALIZADOR = lOTE_CATALIZADOR;
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

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

}
