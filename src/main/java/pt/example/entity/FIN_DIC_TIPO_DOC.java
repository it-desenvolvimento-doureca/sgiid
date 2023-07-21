package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIN_DIC_TIPO_DOC")
public class FIN_DIC_TIPO_DOC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "TIPO_DOCUMENTO")
	private String TIPO_DOCUMENTO;
	@Column(name = "NOME_DOCUMENTO")
	private String NOME_DOCUMENTO;
	@Column(name = "TIPO_FICHEIRO_1")
	private String TIPO_FICHEIRO_1;
	@Column(name = "TIPO_FICHEIRO_2")
	private String TIPO_FICHEIRO_2;

	public Integer getID() {
		return ID;
	}

	public String getTIPO_DOCUMENTO() {
		return TIPO_DOCUMENTO;
	}

	public String getNOME_DOCUMENTO() {
		return NOME_DOCUMENTO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setTIPO_DOCUMENTO(String tIPO_DOCUMENTO) {
		TIPO_DOCUMENTO = tIPO_DOCUMENTO;
	}

	public void setNOME_DOCUMENTO(String nOME_DOCUMENTO) {
		NOME_DOCUMENTO = nOME_DOCUMENTO;
	}

	public String getTIPO_FICHEIRO_1() {
		return TIPO_FICHEIRO_1;
	}

	public void setTIPO_FICHEIRO_1(String tIPO_FICHEIRO_1) {
		TIPO_FICHEIRO_1 = tIPO_FICHEIRO_1;
	}

	public String getTIPO_FICHEIRO_2() {
		return TIPO_FICHEIRO_2;
	}

	public void setTIPO_FICHEIRO_2(String tIPO_FICHEIRO_2) {
		TIPO_FICHEIRO_2 = tIPO_FICHEIRO_2;
	}

}
