package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_UNIDADE_MEDIDA")
public class AB_DIC_UNIDADE_MEDIDA {

	private Integer ID_MEDIDA;
	private String MEDIDA;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;

	@Column(name = "DATA_ANULACAO")
	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	@Column(name = "UTZ_ANULACAO")
	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}
	
	@Id
	@Column(name = "ID_MEDIDA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_MEDIDA() {
		return ID_MEDIDA;
	}

	public void setID_MEDIDA(Integer iD_MEDIDA) {
		ID_MEDIDA = iD_MEDIDA;
	}

	@Column(name = "MEDIDA")
	public String getMEDIDA() {
		return MEDIDA;
	}

	public void setMEDIDA(String mEDIDA) {
		MEDIDA = mEDIDA;
	}
	
	
}
