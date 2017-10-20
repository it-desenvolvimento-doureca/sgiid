package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_TIPO_OPERACAO")
public class AB_DIC_TIPO_OPERACAO {

	private Integer ID_TIPO_OPERACAO;
	private String NOME_TIPO_OPERACAO;
	private Boolean ID195;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;

	@Id
	@Column(name = "ID_TIPO_OPERACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TIPO_OPERACAO() {
		return ID_TIPO_OPERACAO;
	}

	@Column(name = "NOME_TIPO_OPERACAO")
	public String getNOME_TIPO_OPERACAO() {
		return NOME_TIPO_OPERACAO;
	}

	@Column(name = "ID195")
	public Boolean getID195() {
		return ID195;
	}

	public void setID_TIPO_OPERACAO(Integer iD_TIPO_OPERACAO) {
		ID_TIPO_OPERACAO = iD_TIPO_OPERACAO;
	}

	public void setNOME_TIPO_OPERACAO(String nOME_TIPO_OPERACAO) {
		NOME_TIPO_OPERACAO = nOME_TIPO_OPERACAO;
	}

	public void setID195(Boolean iD195) {
		ID195 = iD195;
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

}
