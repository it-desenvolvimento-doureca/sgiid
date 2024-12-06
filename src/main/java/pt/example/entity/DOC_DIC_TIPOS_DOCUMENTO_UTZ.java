package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "DOC_DIC_TIPOS_DOCUMENTO_UTZ")
public class DOC_DIC_TIPOS_DOCUMENTO_UTZ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_TIPO_DOCUMENTO")
	private Integer ID_TIPO_DOCUMENTO;
	@JsonProperty("ID_UTZ")
	private Integer ID_UTZ;

	public Integer getID() {
		return ID;
	}

	public Integer getID_TIPO_DOCUMENTO() {
		return ID_TIPO_DOCUMENTO;
	}

	public Integer getID_UTZ() {
		return ID_UTZ;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_TIPO_DOCUMENTO(Integer iD_TIPO_DOCUMENTO) {
		ID_TIPO_DOCUMENTO = iD_TIPO_DOCUMENTO;
	}

	public void setID_UTZ(Integer iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}

}
