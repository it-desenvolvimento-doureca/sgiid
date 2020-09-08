package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANEAMENTO_CAB")
public class PLANEAMENTO_CAB {
	public Integer ID_PLANEAMENTO;
	public Timestamp DATA_CRIA;
	public Integer UTZ_CRIA;
	public Timestamp DATA_MODIF;
	public Integer UTZ_MODIF;
	public String ESTADO;
	public Boolean INATIVO;
	public Timestamp DATA_ANULACAO;
	public Integer UTZ_ANULACAO;
	public Integer ANO;
	public Integer SEMANA;
	public Integer LINHA;

	@Id
	@Column(name = "ID_PLANEAMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_PLANEAMENTO() {
		return ID_PLANEAMENTO;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_MODIF")
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	@Column(name = "UTZ_MODIF")
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	@Column(name = "ESTADO")
	public String getESTADO() {
		return ESTADO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	@Column(name = "DATA_ANULACAO")
	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	@Column(name = "UTZ_ANULACAO")
	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	@Column(name = "ANO")
	public Integer getANO() {
		return ANO;
	}

	@Column(name = "SEMANA")
	public Integer getSEMANA() {
		return SEMANA;
	}

	@Column(name = "LINHA")
	public Integer getLINHA() {
		return LINHA;
	}

	public void setID_PLANEAMENTO(Integer iD_PLANEAMENTO) {
		ID_PLANEAMENTO = iD_PLANEAMENTO;
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

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setSEMANA(Integer sEMANA) {
		SEMANA = sEMANA;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

}
