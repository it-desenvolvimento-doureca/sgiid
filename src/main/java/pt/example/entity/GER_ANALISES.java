package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_ANALISES")
public class GER_ANALISES {

	private Integer ID;
	private Integer ID_PAI;
	private String LINK;
	private String DESCRICAO;
	private Boolean ATIVO;
	private Integer MODULO;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "LINK")
	public String getLINK() {
		return LINK;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "ATIVO")
	public Boolean getATIVO() {
		return ATIVO;
	}

	@Column(name = "ID_PAI")
	public Integer getID_PAI() {
		return ID_PAI;
	}

	@Column(name = "MODULO")
	public Integer getMODULO() {
		return MODULO;
	}

	public void setMODULO(Integer mODULO) {
		MODULO = mODULO;
	}

	public void setID_PAI(Integer iD_PAI) {
		ID_PAI = iD_PAI;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setLINK(String lINK) {
		LINK = lINK;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

}
