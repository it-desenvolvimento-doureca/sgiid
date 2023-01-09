package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_DIC_ARTIGOS_TIPOLOGIA")
public class MAN_DIC_ARTIGOS_TIPOLOGIA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("PROREF")
	private String PROREF;
	@JsonProperty("PROTYPCOD")
	private String PROTYPCOD;
	@JsonProperty("PRODES1")
	private String PRODES1;
	@JsonProperty("PRODES2")
	private String PRODES2;
	@JsonProperty("LOTQTE")
	private BigDecimal LOTQTE;
	@JsonProperty("STOCK_MINIMO")
	private BigDecimal STOCK_MINIMO;
	@JsonProperty("DATA_UPDATE")
	private Timestamp DATA_UPDATE;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	public Integer getID() {
		return ID;
	}

	public String getPROREF() {
		return PROREF;
	}

	public String getPROTYPCOD() {
		return PROTYPCOD;
	}

	public String getPRODES1() {
		return PRODES1;
	}

	public String getPRODES2() {
		return PRODES2;
	}

	public BigDecimal getLOTQTE() {
		return LOTQTE;
	}

	public BigDecimal getSTOCK_MINIMO() {
		return STOCK_MINIMO;
	}

	public Timestamp getDATA_UPDATE() {
		return DATA_UPDATE;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public void setPROTYPCOD(String pROTYPCOD) {
		PROTYPCOD = pROTYPCOD;
	}

	public void setPRODES1(String pRODES1) {
		PRODES1 = pRODES1;
	}

	public void setPRODES2(String pRODES2) {
		PRODES2 = pRODES2;
	}

	public void setLOTQTE(BigDecimal lOTQTE) {
		LOTQTE = lOTQTE;
	}

	public void setSTOCK_MINIMO(BigDecimal sTOCK_MINIMO) {
		STOCK_MINIMO = sTOCK_MINIMO;
	}

	public void setDATA_UPDATE(Timestamp dATA_UPDATE) {
		DATA_UPDATE = dATA_UPDATE;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

}
