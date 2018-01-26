package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_VISTAS")
public class GER_VISTAS {

	private Integer ID;
	private Integer ID_UTZ;
	private String COLSTATE;
	private String FILTERSTATE;
	private String GROUPSTATE;
	private String SORTSTATE;
	private String DESCRICAO;
	private String FAMILIAS;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@Column(name = "ID_UTZ")
	public Integer getID_UTZ() {
		return ID_UTZ;
	}

	@Column(name = "COLSTATE")
	public String getCOLSTATE() {
		return COLSTATE;
	}

	@Column(name = "FILTERSTATE")
	public String getFILTERSTATE() {
		return FILTERSTATE;
	}

	@Column(name = "GROUPSTATE")
	public String getGROUPSTATE() {
		return GROUPSTATE;
	}

	@Column(name = "SORTSTATE")
	public String getSORTSTATE() {
		return SORTSTATE;
	}

	public void setID_UTZ(Integer iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}

	public void setCOLSTATE(String cOLSTATE) {
		COLSTATE = cOLSTATE;
	}

	public void setFILTERSTATE(String fILTERSTATE) {
		FILTERSTATE = fILTERSTATE;
	}

	public void setGROUPSTATE(String gROUPSTATE) {
		GROUPSTATE = gROUPSTATE;
	}

	public void setSORTSTATE(String sORTSTATE) {
		SORTSTATE = sORTSTATE;
	}
	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}
	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	@Column(name = "FAMILIAS")
	public String getFAMILIAS() {
		return FAMILIAS;
	}

	public void setFAMILIAS(String fAMILIAS) {
		FAMILIAS = fAMILIAS;
	}

}
