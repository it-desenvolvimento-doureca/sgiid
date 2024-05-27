package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_WINROBOT_ETIQUETAS")
public class PR_WINROBOT_ETIQUETAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_ARTIGO")
	private Integer ID_ARTIGO;
	@JsonProperty("ETIQUETA")
	private String ETIQUETA;
	@JsonProperty("QUANT")
	private BigDecimal QUANT;
	@JsonProperty("CONSUMIR")
	private BigDecimal CONSUMIR;
	@JsonProperty("QUANT_FINAL")
	private BigDecimal QUANT_FINAL;
	@JsonProperty("PROREF")
	private String PROREF;
	@JsonProperty("PRODES")
	private String PRODES;
	@JsonProperty("UNICOD")
	private String UNICOD;
	@JsonProperty("LIECOD")
	private String LIECOD;
	@JsonProperty("EMPCOD")
	private String EMPCOD;
	@JsonProperty("INDREF")
	private String INDREF;
	@JsonProperty("VA1REF")
	private String VA1REF;
	@JsonProperty("VA2REF")
	private String VA2REF;
	@JsonProperty("ETQORILOT1")
	private String ETQORILOT1;
	@JsonProperty("ETQORIQTE1")
	private BigDecimal ETQORIQTE1;
	@JsonProperty("ETQNUMENR")
	private String ETQNUMENR;
	@JsonProperty("UNISTO")
	private String UNISTO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("INDEX_ORIGEM")
	private Integer INDEX_ORIGEM;
	@JsonProperty("LOTNUMENR")
	private String LOTNUMENR;
	@JsonProperty("INDNUMENR")
	private String INDNUMENR;
	@JsonProperty("DATCRE")
	private String DATCRE;

	public Integer getID() {
		return ID;
	}

	public Integer getID_ARTIGO() {
		return ID_ARTIGO;
	}

	public String getETIQUETA() {
		return ETIQUETA;
	}

	public BigDecimal getQUANT() {
		return QUANT;
	}

	public BigDecimal getCONSUMIR() {
		return CONSUMIR;
	}

	public BigDecimal getQUANT_FINAL() {
		return QUANT_FINAL;
	}

	public String getPROREF() {
		return PROREF;
	}

	public String getPRODES() {
		return PRODES;
	}

	public String getUNICOD() {
		return UNICOD;
	}

	public String getLIECOD() {
		return LIECOD;
	}

	public String getEMPCOD() {
		return EMPCOD;
	}

	public String getINDREF() {
		return INDREF;
	}

	public String getVA1REF() {
		return VA1REF;
	}

	public String getVA2REF() {
		return VA2REF;
	}

	public String getETQORILOT1() {
		return ETQORILOT1;
	}

	public BigDecimal getETQORIQTE1() {
		return ETQORIQTE1;
	}

	public String getETQNUMENR() {
		return ETQNUMENR;
	}

	public String getUNISTO() {
		return UNISTO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_ARTIGO(Integer iD_ARTIGO) {
		ID_ARTIGO = iD_ARTIGO;
	}

	public void setETIQUETA(String eTIQUETA) {
		ETIQUETA = eTIQUETA;
	}

	public void setQUANT(BigDecimal qUANT) {
		QUANT = qUANT;
	}

	public void setCONSUMIR(BigDecimal cONSUMIR) {
		CONSUMIR = cONSUMIR;
	}

	public void setQUANT_FINAL(BigDecimal qUANT_FINAL) {
		QUANT_FINAL = qUANT_FINAL;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public void setPRODES(String pRODES) {
		PRODES = pRODES;
	}

	public void setUNICOD(String uNICOD) {
		UNICOD = uNICOD;
	}

	public void setLIECOD(String lIECOD) {
		LIECOD = lIECOD;
	}

	public void setEMPCOD(String eMPCOD) {
		EMPCOD = eMPCOD;
	}

	public void setINDREF(String iNDREF) {
		INDREF = iNDREF;
	}

	public void setVA1REF(String vA1REF) {
		VA1REF = vA1REF;
	}

	public void setVA2REF(String vA2REF) {
		VA2REF = vA2REF;
	}

	public void setETQORILOT1(String eTQORILOT1) {
		ETQORILOT1 = eTQORILOT1;
	}

	public void setETQORIQTE1(BigDecimal eTQORIQTE1) {
		ETQORIQTE1 = eTQORIQTE1;
	}

	public void setETQNUMENR(String eTQNUMENR) {
		ETQNUMENR = eTQNUMENR;
	}

	public void setUNISTO(String uNISTO) {
		UNISTO = uNISTO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Integer getINDEX_ORIGEM() {
		return INDEX_ORIGEM;
	}

	public void setINDEX_ORIGEM(Integer iNDEX_ORIGEM) {
		INDEX_ORIGEM = iNDEX_ORIGEM;
	}

	public String getLOTNUMENR() {
		return LOTNUMENR;
	}

	public String getINDNUMENR() {
		return INDNUMENR;
	}

	public void setLOTNUMENR(String lOTNUMENR) {
		LOTNUMENR = lOTNUMENR;
	}

	public void setINDNUMENR(String iNDNUMENR) {
		INDNUMENR = iNDNUMENR;
	}

	public String getDATCRE() {
		return DATCRE;
	}

	public void setDATCRE(String dATCRE) {
		DATCRE = dATCRE;
	}

}