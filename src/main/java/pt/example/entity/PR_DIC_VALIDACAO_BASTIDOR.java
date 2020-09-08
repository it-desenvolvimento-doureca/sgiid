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
@Table(name = "PR_DIC_VALIDACAO_BASTIDOR")
public class PR_DIC_VALIDACAO_BASTIDOR {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VALIDACAO_BASTIDOR")
	private Integer ID_VALIDACAO_BASTIDOR;
	@Column(name = "SIMBOLO")
	private String SIMBOLO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "COR")
	private String COR;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "ATIVO")
	private Boolean ATIVO;
	@Column(name = "DATA_ANULA")
	private Timestamp DATA_ANULA;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;

	public Integer getID_VALIDACAO_BASTIDOR() {
		return ID_VALIDACAO_BASTIDOR;
	}

	public String getSIMBOLO() {
		return SIMBOLO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getCOR() {
		return COR;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public void setID_VALIDACAO_BASTIDOR(Integer iD_VALIDACAO_BASTIDOR) {
		ID_VALIDACAO_BASTIDOR = iD_VALIDACAO_BASTIDOR;
	}

	public void setSIMBOLO(String sIMBOLO) {
		SIMBOLO = sIMBOLO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setCOR(String cOR) {
		COR = cOR;
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

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

}
