package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_LIMITES_ENCOMENDA")
public class GER_DIC_LIMITES_ENCOMENDA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIMITE")
	private Integer ID_LIMITE;
	@Column(name = "PROREF")
	private String PROREF;
	@Column(name = "PRODES")
	private String PRODES;
	@Column(name = "CLICOD")
	private Integer CLICOD;
	@Column(name = "ETSNUM")
	private String ETSNUM;
	@Column(name = "ADRNOM")
	private String ADRNOM;
	@Column(name = "QUANT_MIN")
	private Float QUANT_MIN;
	@Column(name = "QUANT_MAX_SEMANA")
	private Float QUANT_MAX_SEMANA;
	@Column(name = "DATAINSERT")
	private Timestamp DATAINSERT;

	public Integer getID_LIMITE() {
		return ID_LIMITE;
	}

	public String getPROREF() {
		return PROREF;
	}

	public String getPRODES() {
		return PRODES;
	}

	public Integer getCLICOD() {
		return CLICOD;
	}

	public String getETSNUM() {
		return ETSNUM;
	}

	public String getADRNOM() {
		return ADRNOM;
	}

	public Float getQUANT_MIN() {
		return QUANT_MIN;
	}

	public Float getQUANT_MAX_SEMANA() {
		return QUANT_MAX_SEMANA;
	}

	public Timestamp getDATAINSERT() {
		return DATAINSERT;
	}

	public void setID_LIMITE(Integer iD_LIMITE) {
		ID_LIMITE = iD_LIMITE;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public void setPRODES(String pRODES) {
		PRODES = pRODES;
	}

	public void setCLICOD(Integer cLICOD) {
		CLICOD = cLICOD;
	}

	public void setETSNUM(String eTSNUM) {
		ETSNUM = eTSNUM;
	}

	public void setADRNOM(String aDRNOM) {
		ADRNOM = aDRNOM;
	}

	public void setQUANT_MIN(Float qUANT_MIN) {
		QUANT_MIN = qUANT_MIN;
	}

	public void setQUANT_MAX_SEMANA(Float qUANT_MAX_SEMANA) {
		QUANT_MAX_SEMANA = qUANT_MAX_SEMANA;
	}

	public void setDATAINSERT(Timestamp dATAINSERT) {
		DATAINSERT = dATAINSERT;
	}

}
