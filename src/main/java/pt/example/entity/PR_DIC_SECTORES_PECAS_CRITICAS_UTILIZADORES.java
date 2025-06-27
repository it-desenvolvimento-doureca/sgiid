package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES")
public class PR_DIC_SECTORES_PECAS_CRITICAS_UTILIZADORES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;

	@JsonProperty("ID_SECTORES_PECAS_CRITICAS")
	private Integer ID_SECTORES_PECAS_CRITICAS;

	public Integer getID() {
		return ID;
	}

	public Integer getID_UTILIZADOR() {
		return ID_UTILIZADOR;
	}

	public Integer getID_SECTORES_PECAS_CRITICAS() {
		return ID_SECTORES_PECAS_CRITICAS;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_UTILIZADOR(Integer iD_UTILIZADOR) {
		ID_UTILIZADOR = iD_UTILIZADOR;
	}

	public void setID_SECTORES_PECAS_CRITICAS(Integer iD_SECTORES_PECAS_CRITICAS) {
		ID_SECTORES_PECAS_CRITICAS = iD_SECTORES_PECAS_CRITICAS;
	}

}
