package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LG_DIC_OBJETIVOS")
public class LG_DIC_OBJETIVOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "OBJETIVO_FORNECEDORES")
	private Float OBJETIVO_FORNECEDORES;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "OBJETIVO_CLIENTES")
	private Float OBJETIVO_CLIENTES;

	public Integer getID() {
		return ID;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Float getOBJETIVO_FORNECEDORES() {
		return OBJETIVO_FORNECEDORES;
	}

	public Float getOBJETIVO_CLIENTES() {
		return OBJETIVO_CLIENTES;
	}

	public void setOBJETIVO_FORNECEDORES(Float oBJETIVO_FORNECEDORES) {
		OBJETIVO_FORNECEDORES = oBJETIVO_FORNECEDORES;
	}

	public void setOBJETIVO_CLIENTES(Float oBJETIVO_CLIENTES) {
		OBJETIVO_CLIENTES = oBJETIVO_CLIENTES;
	}

}
