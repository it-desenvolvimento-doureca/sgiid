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
@Table(name = "GER_SECCAO_UTZ")
public class GER_SECCAO_UTZ {

	private Integer ID;
	private Integer ID_SECCAO;
	private Integer ID_UTZ;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "ID_SECCAO")
	public Integer getID_SECCAO() {
		return ID_SECCAO;
	}

	@Column(name = "ID_UTZ")
	public Integer getID_UTZ() {
		return ID_UTZ;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_SECCAO(Integer iD_SECCAO) {
		ID_SECCAO = iD_SECCAO;
	}

	public void setID_UTZ(Integer iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}
}
