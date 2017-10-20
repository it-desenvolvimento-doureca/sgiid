package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_ZONA")
public class AB_DIC_ZONA {

	private Integer ID_ZONA;
	private String NOME_ZONA;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;

	@Id
	@Column(name = "ID_ZONA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_ZONA() {
		return ID_ZONA;
	}

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

	@Column(name = "NOME_ZONA")
	public String getNOME_ZONA() {
		return NOME_ZONA;
	}

	public void setID_ZONA(Integer iD_ZONA) {
		ID_ZONA = iD_ZONA;
	}

	public void setNOME_ZONA(String nOME_ZONA) {
		NOME_ZONA = nOME_ZONA;
	}

}
