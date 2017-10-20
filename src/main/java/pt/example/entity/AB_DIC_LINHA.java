package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_LINHA")
public class AB_DIC_LINHA {

	private Integer ID_LINHA;
	private String NOME_LINHA;
	private String COR;
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
	@Column(name = "ID_LINHA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	@Column(name = "NOME_LINHA")
	public String getNOME_LINHA() {
		return NOME_LINHA;
	}

	@Column(name = "COR")
	public String getCOR() {
		return COR;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setNOME_LINHA(String nOME_LINHA) {
		NOME_LINHA = nOME_LINHA;
	}

	public void setCOR(String cOR) {
		COR = cOR;
	}

}
