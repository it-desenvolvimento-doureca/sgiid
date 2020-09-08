package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIN_DOC_ACORDO")
public class FIN_DOC_ACORDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_CLIENTE")
	private String ID_CLIENTE;
	@Column(name = "DOCUMENTO")
	private String DOCUMENTO;

	public Integer getID() {
		return ID;
	}

	public String getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public String getDOCUMENTO() {
		return DOCUMENTO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_CLIENTE(String iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public void setDOCUMENTO(String dOCUMENTO) {
		DOCUMENTO = dOCUMENTO;
	}

}
