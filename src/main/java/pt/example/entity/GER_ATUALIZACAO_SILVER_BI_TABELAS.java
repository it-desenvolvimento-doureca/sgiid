package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_ATUALIZACAO_SILVER_BI_TABELAS")
public class GER_ATUALIZACAO_SILVER_BI_TABELAS {
	private Integer ID;

	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private String TABELA;
	private String DIAS;
	private Boolean TOTAL;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "TABELA")
	public String getTABELA() {
		return TABELA;
	}

	@Column(name = "DIAS")
	public String getDIAS() {
		return DIAS;
	}

	@Column(name = "TOTAL")
	public Boolean getTOTAL() {
		return TOTAL;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setTABELA(String tABELA) {
		TABELA = tABELA;
	}

	public void setDIAS(String dIAS) {
		DIAS = dIAS;
	}

	public void setTOTAL(Boolean tOTAL) {
		TOTAL = tOTAL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

}
