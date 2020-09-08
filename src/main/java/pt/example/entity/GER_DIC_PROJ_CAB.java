package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_PROJ_CAB")
public class GER_DIC_PROJ_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROJ_CAB")
	private Integer ID_PROJ_CAB;
	@Column(name = "ID_PROGRAMA")
	private Integer ID_PROGRAMA;

	public Integer getID_PROJ_CAB() {
		return ID_PROJ_CAB;
	}

	public Integer getID_PROGRAMA() {
		return ID_PROGRAMA;
	}

	public void setID_PROJ_CAB(Integer iD_PROJ_CAB) {
		ID_PROJ_CAB = iD_PROJ_CAB;
	}

	public void setID_PROGRAMA(Integer iD_PROGRAMA) {
		ID_PROGRAMA = iD_PROGRAMA;
	}

}
