package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_PROJ_REF")
public class GER_DIC_PROJ_REF {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROJ_REF")
	private Integer ID_PROJ_REF;
	@Column(name = "ID_PROJ_CAB")
	private Integer ID_PROJ_CAB;
	@Column(name = "ID_REF")
	private String ID_REF;
	@Column(name = "PV")
	private Boolean PV;
	@Column(name = "DESC_REF")
	private String DESC_REF;

	public Integer getID_PROJ_REF() {
		return ID_PROJ_REF;
	}

	public Integer getID_PROJ_CAB() {
		return ID_PROJ_CAB;
	}

	public String getID_REF() {
		return ID_REF;
	}

	public Boolean getPV() {
		return PV;
	}

	public void setID_PROJ_REF(Integer iD_PROJ_REF) {
		ID_PROJ_REF = iD_PROJ_REF;
	}

	public void setID_PROJ_CAB(Integer iD_PROJ_CAB) {
		ID_PROJ_CAB = iD_PROJ_CAB;
	}

	public void setID_REF(String iD_REF) {
		ID_REF = iD_REF;
	}

	public void setPV(Boolean pV) {
		PV = pV;
	}

	public String getDESC_REF() {
		return DESC_REF;
	}

	public void setDESC_REF(String dESC_REF) {
		DESC_REF = dESC_REF;
	}

}
