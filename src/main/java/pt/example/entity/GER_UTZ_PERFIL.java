package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_UTZ_PERFIL")
public class GER_UTZ_PERFIL {

	private Integer ID_PERFIL_UTZ;
	private Integer ID_UTZ;
	private Integer ID_PERFIL;

	@Id
	@Column(name = "ID_PERFIL_UTZ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_PERFIL_UTZ() {
		return ID_PERFIL_UTZ;
	}

	@Column(name = "ID_UTZ")
	public Integer getID_UTZ() {
		return ID_UTZ;
	}

	@Column(name = "ID_PERFIL")
	public Integer getID_PERFIL() {
		return ID_PERFIL;
	}

	public void setID_PERFIL_UTZ(Integer iD_PERFIL_UTZ) {
		ID_PERFIL_UTZ = iD_PERFIL_UTZ;
	}

	public void setID_UTZ(Integer iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}

	public void setID_PERFIL(Integer iD_PERFIL) {
		ID_PERFIL = iD_PERFIL;
	}
}