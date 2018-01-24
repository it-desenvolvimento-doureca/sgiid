package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_BANHO")
public class AB_DIC_BANHO {

	private Integer ID_BANHO;
	private Integer ID_LINHA;
	private Integer ID_ZONA;
	private String NOME_BANHO;
	private Boolean ESTADO;
	private Integer ID_TINA;
	private String OBS;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Boolean CELULAHULL;
	private String EMAIL_PARA;

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
	@Column(name = "ID_BANHO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_BANHO() {
		return ID_BANHO;
	}

	@Column(name = "ID_LINHA")
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	@Column(name = "NOME_BANHO")
	public String getNOME_BANHO() {
		return NOME_BANHO;
	}

	@Column(name = "ESTADO")
	public Boolean getESTADO() {
		return ESTADO;
	}

	@Column(name = "ID_TINA")
	public Integer getID_TINA() {
		return ID_TINA;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
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

	@Column(name = "ID_ZONA")
	public Integer getID_ZONA() {
		return ID_ZONA;
	}

	@Column(name = "CELULAHULL")
	public Boolean getCELULAHULL() {
		return CELULAHULL;
	}

	public void setCELULAHULL(Boolean cELULAHULL) {
		CELULAHULL = cELULAHULL;
	}

	public void setID_ZONA(Integer iD_ZONA) {
		ID_ZONA = iD_ZONA;
	}

	public void setID_BANHO(Integer iD_BANHO) {
		ID_BANHO = iD_BANHO;
	}

	public void setNOME_BANHO(String nOME_BANHO) {
		NOME_BANHO = nOME_BANHO;
	}

	public void setESTADO(Boolean eSTADO) {
		ESTADO = eSTADO;
	}

	public void setID_TINA(Integer iD_TINA) {
		ID_TINA = iD_TINA;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
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

	@Column(name = "EMAIL_PARA")
	public String getEMAIL_PARA() {
		return EMAIL_PARA;
	}

	public void setEMAIL_PARA(String eMAIL_PARA) {
		EMAIL_PARA = eMAIL_PARA;
	}

}
