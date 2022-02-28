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
@Table(name = "REU_REUNIOES_PARTICIPANTES")
public class REU_REUNIOES_PARTICIPANTES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_REUNIAO")
	private Integer ID_REUNIAO;
	@Column(name = "ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;
	@Column(name = "EMAIL")
	private String EMAIL;
	@Column(name = "PRESENTE")
	private Boolean PRESENTE;

	public Integer getID() {
		return ID;
	}

	public Integer getID_REUNIAO() {
		return ID_REUNIAO;
	}

	public Integer getID_UTILIZADOR() {
		return ID_UTILIZADOR;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_REUNIAO(Integer iD_REUNIAO) {
		ID_REUNIAO = iD_REUNIAO;
	}

	public void setID_UTILIZADOR(Integer iD_UTILIZADOR) {
		ID_UTILIZADOR = iD_UTILIZADOR;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Boolean getPRESENTE() {
		return PRESENTE;
	}

	public void setPRESENTE(Boolean pRESENTE) {
		PRESENTE = pRESENTE;
	}

}
