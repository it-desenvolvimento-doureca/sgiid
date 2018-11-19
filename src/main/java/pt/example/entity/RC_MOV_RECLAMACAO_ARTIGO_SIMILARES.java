package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RC_MOV_RECLAMACAO_ARTIGO_SIMILARES")
public class RC_MOV_RECLAMACAO_ARTIGO_SIMILARES {
	private Integer ID;
	private String CODREF;
	private String DESIGNACAO;
	private String OFNUM;
	private Integer ID_RECLAMACAO;
	private Integer QUANTIDADE;
	private String ONDE;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "CODREF")
	public String getCODREF() {
		return CODREF;
	}

	@Column(name = "DESIGNACAO")
	public String getDESIGNACAO() {
		return DESIGNACAO;
	}

	@Column(name = "OFNUM")
	public String getOFNUM() {
		return OFNUM;
	}

	@Column(name = "ID_RECLAMACAO")
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	@Column(name = "QUANTIDADE")
	public Integer getQUANTIDADE() {
		return QUANTIDADE;
	}

	@Column(name = "ONDE")
	public String getONDE() {
		return ONDE;
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

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCODREF(String cODREF) {
		CODREF = cODREF;
	}

	public void setDESIGNACAO(String dESIGNACAO) {
		DESIGNACAO = dESIGNACAO;
	}

	public void setOFNUM(String oFNUM) {
		OFNUM = oFNUM;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setQUANTIDADE(Integer qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public void setONDE(String oNDE) {
		ONDE = oNDE;
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

}
