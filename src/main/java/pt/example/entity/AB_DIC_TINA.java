package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_TINA")
public class AB_DIC_TINA {

	private Integer ID_TINA;
	private Integer ID_LINHA;
	private Integer ID_ZONA;
	private String COD_TINA;
	private Integer CAPACIDADE;
	private String OBS;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
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
	@Column(name = "ID_TINA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TINA() {
		return ID_TINA;
	}

	@Column(name = "ID_LINHA")
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	@Column(name = "COD_TINA")
	public String getCOD_TINA() {
		return COD_TINA;
	}

	@Column(name = "CAPACIDADE")
	public Integer getCAPACIDADE() {
		return CAPACIDADE;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
	}

	@Column(name = "ID_ZONA")
	public Integer getID_ZONA() {
		return ID_ZONA;
	}

	public void setID_ZONA(Integer iD_ZONA) {
		ID_ZONA = iD_ZONA;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public void setID_TINA(Integer iD_TINA) {
		ID_TINA = iD_TINA;
	}

	public void setCOD_TINA(String cOD_TINA) {
		COD_TINA = cOD_TINA;
	}

	public void setCAPACIDADE(Integer cAPACIDADE) {
		CAPACIDADE = cAPACIDADE;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

}
