package pt.example.entity;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS")
public class MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;
	@JsonProperty("ID_EQUIPA")
	private Integer ID_EQUIPA;

	public Integer getID() {
		return ID;
	}

	public Integer getID_UTILIZADOR() {
		return ID_UTILIZADOR;
	}

	public Integer getID_EQUIPA() {
		return ID_EQUIPA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_UTILIZADOR(Integer iD_UTILIZADOR) {
		ID_UTILIZADOR = iD_UTILIZADOR;
	}

	public void setID_EQUIPA(Integer iD_EQUIPA) {
		ID_EQUIPA = iD_EQUIPA;
	}

}
