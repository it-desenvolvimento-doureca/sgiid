package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_TURNO")
public class AB_DIC_TURNO {

	private Integer ID_TURNO;
	private String NOME_TURNO;
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
	@Column(name = "ID_TURNO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TURNO() {
		return ID_TURNO;
	}

	@Column(name = "NOME_TURNO")
	public String getNOME_TURNO() {
		return NOME_TURNO;
	}

	public void setID_TURNO(Integer iD_TURNO) {
		ID_TURNO = iD_TURNO;
	}

	public void setNOME_TURNO(String nOME_TURNO) {
		NOME_TURNO = nOME_TURNO;
	}

}