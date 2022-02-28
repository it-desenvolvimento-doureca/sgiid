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
@Table(name = "MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA")
public class MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty("ID_DEPARTAMENTO")
	private String ID_DEPARTAMENTO;
	@JsonProperty("NIVEL")
	private String NIVEL;

	public Integer getID() {
		return ID;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public String getID_DEPARTAMENTO() {
		return ID_DEPARTAMENTO;
	}

	public String getNIVEL() {
		return NIVEL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setID_DEPARTAMENTO(String iD_DEPARTAMENTO) {
		ID_DEPARTAMENTO = iD_DEPARTAMENTO;
	}

	public void setNIVEL(String nIVEL) {
		NIVEL = nIVEL;
	}

}
