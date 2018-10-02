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
@Table(name = "GER_GRUPO_UTZ")
public class GER_GRUPO_UTZ {

	private Integer ID;
	private Integer ID_GRUPO;
	private Integer ID_UTZ;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "ID_GRUPO")
	public Integer getID_GRUPO() {
		return ID_GRUPO;
	}

	@Column(name = "ID_UTZ")
	public Integer getID_UTZ() {
		return ID_UTZ;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_GRUPO(Integer iD_GRUPO) {
		ID_GRUPO = iD_GRUPO;
	}

	public void setID_UTZ(Integer iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}
}
