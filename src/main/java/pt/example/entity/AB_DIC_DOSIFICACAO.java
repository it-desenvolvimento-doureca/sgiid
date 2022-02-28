package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "AB_DIC_DOSIFICACAO")
public class AB_DIC_DOSIFICACAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("INTERVALO_AMPERES")
	private Integer INTERVALO_AMPERES;
	@JsonProperty("HORA_MANUTENCAO_ARRANQUE")
	private Time HORA_MANUTENCAO_ARRANQUE;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	public Integer getID() {
		return ID;
	}

	public Integer getINTERVALO_AMPERES() {
		return INTERVALO_AMPERES;
	}

	public Time getHORA_MANUTENCAO_ARRANQUE() {
		return HORA_MANUTENCAO_ARRANQUE;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setINTERVALO_AMPERES(Integer iNTERVALO_AMPERES) {
		INTERVALO_AMPERES = iNTERVALO_AMPERES;
	}

	public void setHORA_MANUTENCAO_ARRANQUE(Time hORA_MANUTENCAO_ARRANQUE) {
		HORA_MANUTENCAO_ARRANQUE = hORA_MANUTENCAO_ARRANQUE;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

}
