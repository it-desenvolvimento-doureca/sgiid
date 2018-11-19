package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RC_MOV_RECLAMACAO_STOCK")
public class RC_MOV_RECLAMACAO_STOCK {
	private Integer ID;
	private String LIECOD;
	private Float STOQTE;
	private Integer ID_RECLAMACAO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Integer ID_LINHA_ARTIGO_SIMILAR;

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

	@Column(name = "LIECOD")
	public String getLIECOD() {
		return LIECOD;
	}

	@Column(name = "STOQTE")
	public Float getSTOQTE() {
		return STOQTE;
	}

	@Column(name = "ID_RECLAMACAO")
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	@Column(name = "ID_LINHA_ARTIGO_SIMILAR")
	public Integer getID_LINHA_ARTIGO_SIMILAR() {
		return ID_LINHA_ARTIGO_SIMILAR;
	}

	public void setID_LINHA_ARTIGO_SIMILAR(Integer iD_LINHA_ARTIGO_SIMILAR) {
		ID_LINHA_ARTIGO_SIMILAR = iD_LINHA_ARTIGO_SIMILAR;
	}

	public void setLIECOD(String lIECOD) {
		LIECOD = lIECOD;
	}

	public void setSTOQTE(Float sTOQTE) {
		STOQTE = sTOQTE;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

}
