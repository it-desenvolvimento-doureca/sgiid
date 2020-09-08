package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_DIC_TIPOLOGIA_ENSAIO")
public class PR_DIC_TIPOLOGIA_ENSAIO {
	public Integer ID_TIPOLOGIA_ENSAIO;
	public String DESCRICAO;
	public Timestamp DATA_CRIA;
	public Integer UTZ_CRIA;
	public Timestamp DATA_MODIF;
	public Integer UTZ_MODIF;

	@Id
	@Column(name = "ID_TIPOLOGIA_ENSAIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TIPOLOGIA_ENSAIO() {
		return ID_TIPOLOGIA_ENSAIO;
	}

	
	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
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

	public void setID_TIPOLOGIA_ENSAIO(Integer iD_TIPOLOGIA_ENSAIO) {
		ID_TIPOLOGIA_ENSAIO = iD_TIPOLOGIA_ENSAIO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
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
