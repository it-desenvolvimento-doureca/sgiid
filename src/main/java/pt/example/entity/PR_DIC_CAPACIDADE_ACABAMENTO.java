package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_DIC_CAPACIDADE_ACABAMENTO")
public class PR_DIC_CAPACIDADE_ACABAMENTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAPACIDADE_ACABAMENTO")
    private Integer ID_CAPACIDADE_ACABAMENTO;
	@Column(name = "TIPO_ACABAMENTO")
	private String TIPO_ACABAMENTO;
	@Column(name = "ID_LINHA")
	private Integer ID_LINHA;
	@Column(name = "N_BARRAS_DIA")
	private Integer N_BARRAS_DIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_ANULA")
	private Timestamp DATA_ANULA;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;
	@Column(name = "ATIVO")
	private Boolean ATIVO;

	public Integer getID_CAPACIDADE_ACABAMENTO() {
		return ID_CAPACIDADE_ACABAMENTO;
	}

	public String getTIPO_ACABAMENTO() {
		return TIPO_ACABAMENTO;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public Integer getN_BARRAS_DIA() {
		return N_BARRAS_DIA;
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

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID_CAPACIDADE_ACABAMENTO(Integer iD_CAPACIDADE_ACABAMENTO) {
		ID_CAPACIDADE_ACABAMENTO = iD_CAPACIDADE_ACABAMENTO;
	}

	public void setTIPO_ACABAMENTO(String tIPO_ACABAMENTO) {
		TIPO_ACABAMENTO = tIPO_ACABAMENTO;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setN_BARRAS_DIA(Integer n_BARRAS_DIA) {
		N_BARRAS_DIA = n_BARRAS_DIA;
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

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}
}