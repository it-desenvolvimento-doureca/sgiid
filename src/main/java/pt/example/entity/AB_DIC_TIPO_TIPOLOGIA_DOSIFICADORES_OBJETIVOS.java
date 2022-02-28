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
@Table(name = "AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS")
public class AB_DIC_TIPO_TIPOLOGIA_DOSIFICADORES_OBJETIVOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_TIPO_TIPOLOGIA_DOSIFICADORES")
	private Integer ID_TIPO_TIPOLOGIA_DOSIFICADORES;
	@JsonProperty("VALOR_MINIMO")
	private Integer VALOR_MINIMO;
	@JsonProperty("VALOR_MAXIMO")
	private Integer VALOR_MAXIMO;
	@JsonProperty("COR")
	private String COR;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	public Integer getID() {
		return ID;
	}

	public Integer getID_TIPO_TIPOLOGIA_DOSIFICADORES() {
		return ID_TIPO_TIPOLOGIA_DOSIFICADORES;
	}

	public Integer getVALOR_MINIMO() {
		return VALOR_MINIMO;
	}

	public Integer getVALOR_MAXIMO() {
		return VALOR_MAXIMO;
	}

	public String getCOR() {
		return COR;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
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

	public void setID_TIPO_TIPOLOGIA_DOSIFICADORES(Integer iD_TIPO_TIPOLOGIA_DOSIFICADORES) {
		ID_TIPO_TIPOLOGIA_DOSIFICADORES = iD_TIPO_TIPOLOGIA_DOSIFICADORES;
	}

	public void setVALOR_MINIMO(Integer vALOR_MINIMO) {
		VALOR_MINIMO = vALOR_MINIMO;
	}

	public void setVALOR_MAXIMO(Integer vALOR_MAXIMO) {
		VALOR_MAXIMO = vALOR_MAXIMO;
	}

	public void setCOR(String cOR) {
		COR = cOR;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

}
