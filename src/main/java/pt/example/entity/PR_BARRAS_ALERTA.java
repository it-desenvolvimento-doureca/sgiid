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
@Table(name = "PR_BARRAS_ALERTA")
public class PR_BARRAS_ALERTA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "PROREF")
	private String PROREF;
	@Column(name = "DESIGN")
	private String DESIGN;
	@Column(name = "TURNO_1")
	private Boolean TURNO_1;
	@Column(name = "TURNO_2")
	private Boolean TURNO_2;
	@Column(name = "TURNO_3")
	private Boolean TURNO_3;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "LINHA")
	private Integer LINHA;
	@Column(name = "DATA")
	private Date DATA;

	public Integer getID() {
		return ID;
	}

	public String getPROREF() {
		return PROREF;
	}

	public String getDESIGN() {
		return DESIGN;
	}

	public Boolean getTURNO_1() {
		return TURNO_1;
	}

	public Boolean getTURNO_2() {
		return TURNO_2;
	}

	public Boolean getTURNO_3() {
		return TURNO_3;
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

	public void setTURNO_1(Boolean tURNO_1) {
		TURNO_1 = tURNO_1;
	}

	public void setTURNO_2(Boolean tURNO_2) {
		TURNO_2 = tURNO_2;
	}

	public void setTURNO_3(Boolean tURNO_3) {
		TURNO_3 = tURNO_3;
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

	public Date getDATA() {
		return DATA;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

}
