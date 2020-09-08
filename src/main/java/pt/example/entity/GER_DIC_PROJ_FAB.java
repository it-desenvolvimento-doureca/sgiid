package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_PROJ_FAB")
public class GER_DIC_PROJ_FAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROJ_FAB")
	private Integer ID_PROJ_FAB;
	@Column(name = "ID_PROJ_CAB")
	private Integer ID_PROJ_CAB;
	@Column(name = "ID_FABRICA")
	private Integer ID_FABRICA;
	@Column(name = "PERCENTAGEM")
	private Float PERCENTAGEM;

	public Integer getID_PROJ_FAB() {
		return ID_PROJ_FAB;
	}

	public Integer getID_PROJ_CAB() {
		return ID_PROJ_CAB;
	}

	public Integer getID_FABRICA() {
		return ID_FABRICA;
	}

	public Float getPERCENTAGEM() {
		return PERCENTAGEM;
	}

	public void setID_PROJ_FAB(Integer iD_PROJ_FAB) {
		ID_PROJ_FAB = iD_PROJ_FAB;
	}

	public void setID_PROJ_CAB(Integer iD_PROJ_CAB) {
		ID_PROJ_CAB = iD_PROJ_CAB;
	}

	public void setID_FABRICA(Integer iD_FABRICA) {
		ID_FABRICA = iD_FABRICA;
	}

	public void setPERCENTAGEM(Float pERCENTAGEM) {
		PERCENTAGEM = pERCENTAGEM;
	}

}
