package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_REVISOES_PRIORITARIAS")
public class PR_REVISOES_PRIORITARIAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "PROREF")
	private String PROREF;
	@Column(name = "DESIGN")
	private String DESIGN;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "LINHA")
	private Integer LINHA;

	public Integer getID() {
		return ID;
	}

	public String getPROREF() {
		return PROREF;
	}

	public String getDESIGN() {
		return DESIGN;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

	public void setDESIGN(String dESIGN) {
		DESIGN = dESIGN;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public Integer getLINHA() {
		return LINHA;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

}
