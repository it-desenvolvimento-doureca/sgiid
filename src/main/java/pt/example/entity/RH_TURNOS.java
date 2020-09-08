package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_TURNOS")
public class RH_TURNOS {

	private Integer COD_TURNO;
	private String DES_TURNO;
	private Date DATA_INICIO;
	private Time HORA_INICIO;
	private Date DATA_FIM;
	private Time HORA_FIM;
	private Boolean ESTADO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Boolean TURNO_CONTINUO;

	@Id
	@Column(name = "COD_TURNO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCOD_TURNO() {
		return COD_TURNO;
	}

	@Column(name = "DES_TURNO")
	public String getDES_TURNO() {
		return DES_TURNO;
	}

	@Column(name = "DATA_INICIO")
	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	@Column(name = "HORA_INICIO")
	public Time getHORA_INICIO() {
		return HORA_INICIO;
	}

	@Column(name = "DATA_FIM")
	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	@Column(name = "HORA_FIM")
	public Time getHORA_FIM() {
		return HORA_FIM;
	}

	@Column(name = "ESTADO")
	public Boolean getESTADO() {
		return ESTADO;
	}

	public void setCOD_TURNO(Integer cOD_TURNO) {
		COD_TURNO = cOD_TURNO;
	}

	public void setDES_TURNO(String dES_TURNO) {
		DES_TURNO = dES_TURNO;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setHORA_INICIO(Time hORA_INICIO) {
		HORA_INICIO = hORA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setHORA_FIM(Time hORA_FIM) {
		HORA_FIM = hORA_FIM;
	}

	public void setESTADO(Boolean eSTADO) {
		ESTADO = eSTADO;
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

	@Column(name = "TURNO_CONTINUO")
	public Boolean getTURNO_CONTINUO() {
		return TURNO_CONTINUO;
	}

	public void setTURNO_CONTINUO(Boolean tURNO_CONTINUO) {
		TURNO_CONTINUO = tURNO_CONTINUO;
	}

}