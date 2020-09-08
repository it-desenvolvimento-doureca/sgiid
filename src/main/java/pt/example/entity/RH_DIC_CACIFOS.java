package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_DIC_CACIFOS")
public class RH_DIC_CACIFOS {

	public Integer ID;
	public String CODIGO;
	public String TIPO_UTILIZADOR;
	public Integer LOCAL;
	public Integer UTILIZADOR;
	public Integer UTZ_CRIA;
	public Timestamp DATA_CRIA;
	public Integer UTZ_MODIF;
	public Timestamp DATA_MODIF;
	public Integer UTZ_APAGA;
	public Timestamp DATA_APAGA;
	public Boolean INATIVO;
	public Integer TIPO_CACIFO;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Integer getID() {
		return ID;
	}

	@Column(name = "CODIGO")
	public String getCODIGO() {
		return CODIGO;
	}

	@Column(name = "TIPO_UTILIZADOR")
	public String getTIPO_UTILIZADOR() {
		return TIPO_UTILIZADOR;
	}

	@Column(name = "LOCAL")
	public Integer getLOCAL() {
		return LOCAL;
	}

	@Column(name = "UTILIZADOR")
	public Integer getUTILIZADOR() {
		return UTILIZADOR;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_MODIF")
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	@Column(name = "DATA_MODIF")
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	@Column(name = "UTZ_APAGA")
	public Integer getUTZ_APAGA() {
		return UTZ_APAGA;
	}

	@Column(name = "DATA_APAGA")
	public Timestamp getDATA_APAGA() {
		return DATA_APAGA;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}

	public void setTIPO_UTILIZADOR(String tIPO_UTILIZADOR) {
		TIPO_UTILIZADOR = tIPO_UTILIZADOR;
	}

	public void setLOCAL(Integer lOCAL) {
		LOCAL = lOCAL;
	}

	public void setUTILIZADOR(Integer uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_APAGA(Integer uTZ_APAGA) {
		UTZ_APAGA = uTZ_APAGA;
	}

	public void setDATA_APAGA(Timestamp dATA_APAGA) {
		DATA_APAGA = dATA_APAGA;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	@Column(name = "TIPO_CACIFO")
	public Integer getTIPO_CACIFO() {
		return TIPO_CACIFO;
	}

	public void setTIPO_CACIFO(Integer tIPO_CACIFO) {
		TIPO_CACIFO = tIPO_CACIFO;
	}

}