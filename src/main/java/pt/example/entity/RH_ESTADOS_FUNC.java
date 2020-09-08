package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_ESTADOS_FUNC")
public class RH_ESTADOS_FUNC {

	private Integer COD_ESTADO;
	private String DESIGNACAO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Boolean FALTA_LONGA_DURACAO;
	
	@Id
	@Column(name = "COD_ESTADO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCOD_ESTADO() {
		return COD_ESTADO;
	}

	@Column(name = "DESIGNACAO")
	public String getDESIGNACAO() {
		return DESIGNACAO;
	}

	public void setCOD_ESTADO(Integer cOD_ESTADO) {
		COD_ESTADO = cOD_ESTADO;
	}

	public void setDESIGNACAO(String dESIGNACAO) {
		DESIGNACAO = dESIGNACAO;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	@Column(name = "FALTA_LONGA_DURACAO")
	public Boolean getFALTA_LONGA_DURACAO() {
		return FALTA_LONGA_DURACAO;
	}

	public void setFALTA_LONGA_DURACAO(Boolean fALTA_LONGA_DURACAO) {
		FALTA_LONGA_DURACAO = fALTA_LONGA_DURACAO;
	}

}