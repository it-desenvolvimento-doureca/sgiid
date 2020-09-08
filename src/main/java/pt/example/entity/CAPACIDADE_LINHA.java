package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAPACIDADE_LINHA")
public class CAPACIDADE_LINHA {
	public Integer ID_CAPACIDADE;
	public Date DATA;
	public Integer LINHA;
	public Float VALOR;
	public Timestamp DATA_CRIA;
	public Integer UTZ_CRIA;
	public Timestamp DATA_MODIF;
	public Integer UTZ_MODIF;

	@Id
	@Column(name = "ID_CAPACIDADE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_CAPACIDADE() {
		return ID_CAPACIDADE;
	}

	@Column(name = "DATA")
	public Date getDATA() {
		return DATA;
	}

	@Column(name = "LINHA")
	public Integer getLINHA() {
		return LINHA;
	}

	@Column(name = "VALOR")
	public Float getVALOR() {
		return VALOR;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_MODIF")
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	@Column(name = "UTZ_MODIF")
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID_CAPACIDADE(Integer iD_CAPACIDADE) {
		ID_CAPACIDADE = iD_CAPACIDADE;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

	public void setVALOR(Float vALOR) {
		VALOR = vALOR;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

}
