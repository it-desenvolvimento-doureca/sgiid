package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_SECTORES")
public class RH_SECTORES {

	private Integer COD_SECTOR;
	private String DES_SECTOR;
	private Integer COD_TURNO;
	private String LOCAL;
	private Integer CHEFE1;
	private Integer CHEFE2;
	private Date DATA_INICIO;
	private Date DATA_FIM;
	private Boolean ESTADO;
	private Float RACIO_MIN;
	private Float RACIO_MAX;

	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;

	@Id
	@Column(name = "COD_SECTOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCOD_SECTOR() {
		return COD_SECTOR;
	}

	@Column(name = "DES_SECTOR")
	public String getDES_SECTOR() {
		return DES_SECTOR;
	}

	@Column(name = "COD_TURNO")
	public Integer getCOD_TURNO() {
		return COD_TURNO;
	}

	@Column(name = "LOCAL")
	public String getLOCAL() {
		return LOCAL;
	}

	@Column(name = "CHEFE1")
	public Integer getCHEFE1() {
		return CHEFE1;
	}

	@Column(name = "CHEFE2")
	public Integer getCHEFE2() {
		return CHEFE2;
	}

	@Column(name = "DATA_INICIO")
	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	@Column(name = "DATA_FIM")
	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	@Column(name = "ESTADO")
	public Boolean getESTADO() {
		return ESTADO;
	}

	@Column(name = "RACIO_MIN")
	public Float getRACIO_MIN() {
		return RACIO_MIN;
	}

	@Column(name = "RACIO_MAX")
	public Float getRACIO_MAX() {
		return RACIO_MAX;
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

	public void setCOD_SECTOR(Integer cOD_SECTOR) {
		COD_SECTOR = cOD_SECTOR;
	}

	public void setDES_SECTOR(String dES_SECTOR) {
		DES_SECTOR = dES_SECTOR;
	}

	public void setCOD_TURNO(Integer cOD_TURNO) {
		COD_TURNO = cOD_TURNO;
	}

	public void setLOCAL(String lOCAL) {
		LOCAL = lOCAL;
	}

	public void setCHEFE1(Integer cHEFE1) {
		CHEFE1 = cHEFE1;
	}

	public void setCHEFE2(Integer cHEFE2) {
		CHEFE2 = cHEFE2;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setESTADO(Boolean eSTADO) {
		ESTADO = eSTADO;
	}

	public void setRACIO_MIN(Float rACIO_MIN) {
		RACIO_MIN = rACIO_MIN;
	}

	public void setRACIO_MAX(Float rACIO_MAX) {
		RACIO_MAX = rACIO_MAX;
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