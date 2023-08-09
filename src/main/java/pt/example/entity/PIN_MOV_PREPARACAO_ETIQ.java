package pt.example.entity;

import java.math.BigDecimal;
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
@Table(name = "PIN_MOV_PREPARACAO_ETIQ")
public class PIN_MOV_PREPARACAO_ETIQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_MOV_PREP_ETIQUETA")
	private Integer ID_MOV_PREP_ETIQUETA;
	@JsonProperty("ID_PREPARACAO_LIN")
	private Integer ID_PREPARACAO_LIN;
	@JsonProperty("ETQNUM")
	private String ETQNUM;
	@JsonProperty("QUANT")
	private BigDecimal QUANT;
	@JsonProperty("CONSUMIR")
	private BigDecimal CONSUMIR;
	@JsonProperty("QUANT_FINAL")
	private BigDecimal QUANT_FINAL;
	@JsonProperty("INDREF")
	private String INDREF;
	@JsonProperty("VA1REF")
	private String VA1REF;
	@JsonProperty("VA2REF")
	private String VA2REF;
	@JsonProperty("PROREF")
	private String PROREF;
	@JsonProperty("UNICOD")
	private String UNICOD;
	@JsonProperty("LIECOD")
	private String LIECOD;
	@JsonProperty("EMPCOD")
	private String EMPCOD;
	@JsonProperty("ETQORILOT1")
	private String ETQORILOT1;
	@JsonProperty("ETQNUMENR")
	private Integer ETQNUMENR;
	@JsonProperty("LOTNUMENR")
	private Integer LOTNUMENR;
	@JsonProperty("UNISTO")
	private String UNISTO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("INDNUMENR")
	private Integer INDNUMENR;
	@JsonProperty("DATCRE")
	private Date DATCRE;
	@JsonProperty("PRODES")
	private String PRODES;
	@JsonProperty("ETQORIQTE1")
	private BigDecimal ETQORIQTE1;
	@JsonProperty("SINAL")
	private String SINAL;
	@JsonProperty("OBSERVACAO")
	private String OBSERVACAO;
	@JsonProperty("DATA_PREP_EXEC")
	private Timestamp DATA_PREP_EXEC;
	@JsonProperty("LINHA")
	private Integer LINHA;
	@JsonProperty("UNIDADE_CONSUMO")
	private String UNIDADE_CONSUMO;
	@JsonProperty("FACTOR_CONVERSAO")
	private BigDecimal FACTOR_CONVERSAO;

	public Integer getID_MOV_PREP_ETIQUETA() {
		return ID_MOV_PREP_ETIQUETA;
	}

	public void setID_MOV_PREP_ETIQUETA(Integer iD_MOV_PREP_ETIQUETA) {
		ID_MOV_PREP_ETIQUETA = iD_MOV_PREP_ETIQUETA;
	}

	public Integer getID_PREPARACAO_LIN() {
		return ID_PREPARACAO_LIN;
	}

	public void setID_PREPARACAO_LIN(Integer iD_PREPARACAO_LIN) {
		ID_PREPARACAO_LIN = iD_PREPARACAO_LIN;
	}

	public String getETQNUM() {
		return ETQNUM;
	}

	public void setETQNUM(String eTQNUM) {
		ETQNUM = eTQNUM;
	}

	public BigDecimal getQUANT() {
		return QUANT;
	}

	public void setQUANT(BigDecimal qUANT) {
		QUANT = qUANT;
	}

	public BigDecimal getCONSUMIR() {
		return CONSUMIR;
	}

	public void setCONSUMIR(BigDecimal cONSUMIR) {
		CONSUMIR = cONSUMIR;
	}

	public BigDecimal getQUANT_FINAL() {
		return QUANT_FINAL;
	}

	public void setQUANT_FINAL(BigDecimal qUANT_FINAL) {
		QUANT_FINAL = qUANT_FINAL;
	}

	public String getINDREF() {
		return INDREF;
	}

	public void setINDREF(String iNDREF) {
		INDREF = iNDREF;
	}

	public String getVA1REF() {
		return VA1REF;
	}

	public void setVA1REF(String vA1REF) {
		VA1REF = vA1REF;
	}

	public String getVA2REF() {
		return VA2REF;
	}

	public void setVA2REF(String vA2REF) {
		VA2REF = vA2REF;
	}

	public String getPROREF() {
		return PROREF;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public String getUNICOD() {
		return UNICOD;
	}

	public void setUNICOD(String uNICOD) {
		UNICOD = uNICOD;
	}

	public String getLIECOD() {
		return LIECOD;
	}

	public void setLIECOD(String lIECOD) {
		LIECOD = lIECOD;
	}

	public String getEMPCOD() {
		return EMPCOD;
	}

	public void setEMPCOD(String eMPCOD) {
		EMPCOD = eMPCOD;
	}

	public String getETQORILOT1() {
		return ETQORILOT1;
	}

	public void setETQORILOT1(String eTQORILOT1) {
		ETQORILOT1 = eTQORILOT1;
	}

	public Integer getETQNUMENR() {
		return ETQNUMENR;
	}

	public void setETQNUMENR(Integer eTQNUMENR) {
		ETQNUMENR = eTQNUMENR;
	}

	public Integer getLOTNUMENR() {
		return LOTNUMENR;
	}

	public void setLOTNUMENR(Integer lOTNUMENR) {
		LOTNUMENR = lOTNUMENR;
	}

	public String getUNISTO() {
		return UNISTO;
	}

	public void setUNISTO(String uNISTO) {
		UNISTO = uNISTO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Integer getINDNUMENR() {
		return INDNUMENR;
	}

	public void setINDNUMENR(Integer iNDNUMENR) {
		INDNUMENR = iNDNUMENR;
	}

	public Date getDATCRE() {
		return DATCRE;
	}

	public void setDATCRE(Date dATCRE) {
		DATCRE = dATCRE;
	}

	public String getPRODES() {
		return PRODES;
	}

	public void setPRODES(String pRODES) {
		PRODES = pRODES;
	}

	public BigDecimal getETQORIQTE1() {
		return ETQORIQTE1;
	}

	public void setETQORIQTE1(BigDecimal eTQORIQTE1) {
		ETQORIQTE1 = eTQORIQTE1;
	}

	public String getSINAL() {
		return SINAL;
	}

	public void setSINAL(String sINAL) {
		SINAL = sINAL;
	}

	public String getOBSERVACAO() {
		return OBSERVACAO;
	}

	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}

	public Timestamp getDATA_PREP_EXEC() {
		return DATA_PREP_EXEC;
	}

	public void setDATA_PREP_EXEC(Timestamp dATA_PREP_EXEC) {
		DATA_PREP_EXEC = dATA_PREP_EXEC;
	}

	public Integer getLINHA() {
		return LINHA;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

	public String getUNIDADE_CONSUMO() {
		return UNIDADE_CONSUMO;
	}

	public void setUNIDADE_CONSUMO(String uNIDADE_CONSUMO) {
		UNIDADE_CONSUMO = uNIDADE_CONSUMO;
	}

	public BigDecimal getFACTOR_CONVERSAO() {
		return FACTOR_CONVERSAO;
	}

	public void setFACTOR_CONVERSAO(BigDecimal fACTOR_CONVERSAO) {
		FACTOR_CONVERSAO = fACTOR_CONVERSAO;
	}

}
