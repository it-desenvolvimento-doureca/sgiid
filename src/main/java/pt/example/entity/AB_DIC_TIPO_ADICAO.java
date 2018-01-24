package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_TIPO_ADICAO")
public class AB_DIC_TIPO_ADICAO {

	private Integer ID_TIPO_ADICAO;
	private String NOME_TIPO_ADICAO;
	private String ID_TIPO_OPERACAO;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private String CLASSIF;

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
	@Column(name = "ID_TIPO_ADICAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TIPO_ADICAO() {
		return ID_TIPO_ADICAO;
	}

	@Column(name = "NOME_TIPO_ADICAO")
	public String getNOME_TIPO_ADICAO() {
		return NOME_TIPO_ADICAO;
	}

	@Column(name = "ID_TIPO_OPERACAO")
	public String getID_TIPO_OPERACAO() {
		return ID_TIPO_OPERACAO;
	}

	public void setID_TIPO_OPERACAO(String iD_TIPO_OPERACAO) {
		ID_TIPO_OPERACAO = iD_TIPO_OPERACAO;
	}

	public void setID_TIPO_ADICAO(Integer iD_TIPO_ADICAO) {
		ID_TIPO_ADICAO = iD_TIPO_ADICAO;
	}

	public void setNOME_TIPO_ADICAO(String nOME_TIPO_ADICAO) {
		NOME_TIPO_ADICAO = nOME_TIPO_ADICAO;
	}

	@Column(name = "CLASSIF")
	public String getCLASSIF() {
		return CLASSIF;
	}

	public void setCLASSIF(String cLASSIF) {
		CLASSIF = cLASSIF;
	}

}
