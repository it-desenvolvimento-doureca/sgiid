package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_DIC_AMBITOS")
public class PA_DIC_AMBITOS {
	public Integer ID_AMBITO;
	public String DESCRICAO;
	public Timestamp DATA_CRIA;
	public Integer UTZ_CRIA;
	public Timestamp DATA_MODIF;
	public Integer UTZ_MODIF;
	public Boolean EDITAVEL;

	@Id
	@Column(name = "ID_AMBITO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_AMBITO() {
		return ID_AMBITO;
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

	public void setID_AMBITO(Integer iD_AMBITO) {
		ID_AMBITO = iD_AMBITO;
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

	@Column(name = "EDITAVEL")
	public Boolean getEDITAVEL() {
		return EDITAVEL;
	}

	public void setEDITAVEL(Boolean eDITAVEL) {
		EDITAVEL = eDITAVEL;
	}

}
