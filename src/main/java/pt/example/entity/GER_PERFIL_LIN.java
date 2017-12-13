package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_PERFIL_LIN")
public class GER_PERFIL_LIN {

	private Integer ID_PERFIL_LIN;
	private Integer ID_PERFIL_CAB;
	private String ID_CAMPO;

	@Id
	@Column(name = "ID_PERFIL_LIN")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_PERFIL_LIN() {
		return ID_PERFIL_LIN;
	}

	@Column(name = "ID_CAMPO")
	public String getID_CAMPO() {
		return ID_CAMPO;
	}

	@Column(name = "ID_PERFIL_CAB")
	public Integer getID_PERFIL_CAB() {
		return ID_PERFIL_CAB;
	}

	public void setID_PERFIL_CAB(Integer iD_PERFIL_CAB) {
		ID_PERFIL_CAB = iD_PERFIL_CAB;
	}

	public void setID_PERFIL_LIN(Integer iD_PERFIL_LIN) {
		ID_PERFIL_LIN = iD_PERFIL_LIN;
	}

	public void setID_CAMPO(String iD_CAMPO) {
		ID_CAMPO = iD_CAMPO;
	}

}
