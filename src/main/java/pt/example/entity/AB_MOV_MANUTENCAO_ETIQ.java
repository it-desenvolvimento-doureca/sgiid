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
@Table(name = "AB_MOV_MANUTENCAO_ETIQ")
public class AB_MOV_MANUTENCAO_ETIQ {

	private Integer ID_MOV_MANU_ETIQUETA;
	private Integer ID_MANUTENCAO_LIN;
	private String ETQNUM;
	private Float QUANT;
	private Float CONSUMIR;
	private Float QUANT_FINAL;
	private String UNISTO;
	private String INDREF;
	private String VA1REF;
	private String VA2REF;
	private String PROREF;
	private String UNICOD;
	private String LIECOD;
	private String EMPCOD;
	private String ETQORILOT1;
	private Integer ETQNUMENR;
	private Integer LOTNUMENR;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Integer INDNUMENR;
	private Date DATCRE;
	private String PRODES;
	private Integer ETQORIQTE1;

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	@Id
	@Column(name = "ID_MOV_MANU_ETIQUETA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_MOV_MANU_ETIQUETA() {
		return ID_MOV_MANU_ETIQUETA;
	}

	@Column(name = "ETQNUM")
	public String getETQNUM() {
		return ETQNUM;
	}

	@Column(name = "QUANT")
	public Float getQUANT() {
		return QUANT;
	}

	@Column(name = "CONSUMIR")
	public Float getCONSUMIR() {
		return CONSUMIR;
	}

	@Column(name = "QUANT_FINAL")
	public Float getQUANT_FINAL() {
		return QUANT_FINAL;
	}

	@Column(name = "UNISTO")
	public String getUNISTO() {
		return UNISTO;
	}

	@Column(name = "INDREF")
	public String getINDREF() {
		return INDREF;
	}

	@Column(name = "VA1REF")
	public String getVA1REF() {
		return VA1REF;
	}

	@Column(name = "VA2REF")
	public String getVA2REF() {
		return VA2REF;
	}

	@Column(name = "PROREF")
	public String getPROREF() {
		return PROREF;
	}

	@Column(name = "UNICOD")
	public String getUNICOD() {
		return UNICOD;
	}

	@Column(name = "LIECOD")
	public String getLIECOD() {
		return LIECOD;
	}

	@Column(name = "EMPCOD")
	public String getEMPCOD() {
		return EMPCOD;
	}

	@Column(name = "ETQORILOT1")
	public String getETQORILOT1() {
		return ETQORILOT1;
	}

	@Column(name = "ETQNUMENR")
	public Integer getETQNUMENR() {
		return ETQNUMENR;
	}

	@Column(name = "LOTNUMENR")
	public Integer getLOTNUMENR() {
		return LOTNUMENR;
	}

	@Column(name = "DATCRE")
	public Date getDATCRE() {
		return DATCRE;
	}

	@Column(name = "PRODES")
	public String getPRODES() {
		return PRODES;
	}

	public void setDATCRE(Date dATCRE) {
		DATCRE = dATCRE;
	}

	public void setPRODES(String pRODES) {
		PRODES = pRODES;
	}

	public void setID_MOV_MANU_ETIQUETA(Integer iD_MOV_MANU_ETIQUETA) {
		ID_MOV_MANU_ETIQUETA = iD_MOV_MANU_ETIQUETA;
	}

	public void setETQNUM(String eTQNUM) {
		ETQNUM = eTQNUM;
	}

	public void setQUANT(Float qUANT) {
		QUANT = qUANT;
	}

	public void setCONSUMIR(Float cONSUMIR) {
		CONSUMIR = cONSUMIR;
	}

	public void setQUANT_FINAL(Float qUANT_FINAL) {
		QUANT_FINAL = qUANT_FINAL;
	}

	public void setUNISTO(String uNISTO) {
		UNISTO = uNISTO;
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

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
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

	public void setETQORILOT1(String eTQORILOT1) {
		ETQORILOT1 = eTQORILOT1;
	}

	public void setETQNUMENR(Integer eTQNUMENR) {
		ETQNUMENR = eTQNUMENR;
	}

	public void setLOTNUMENR(Integer lOTNUMENR) {
		LOTNUMENR = lOTNUMENR;
	}

	@Column(name = "ID_MANUTENCAO_LIN")
	public Integer getID_MANUTENCAO_LIN() {
		return ID_MANUTENCAO_LIN;
	}

	public void setID_MANUTENCAO_LIN(Integer iD_MANUTENCAO_LIN) {
		ID_MANUTENCAO_LIN = iD_MANUTENCAO_LIN;
	}

	@Column(name = "INDNUMENR")
	public Integer getINDNUMENR() {
		return INDNUMENR;
	}

	public void setINDNUMENR(Integer iNDNUMENR) {
		INDNUMENR = iNDNUMENR;
	}

	@Column(name = "ETQORIQTE1")
	public Integer getETQORIQTE1() {
		return ETQORIQTE1;
	}

	public void setETQORIQTE1(Integer eTQORIQTE1) {
		ETQORIQTE1 = eTQORIQTE1;
	}

}
