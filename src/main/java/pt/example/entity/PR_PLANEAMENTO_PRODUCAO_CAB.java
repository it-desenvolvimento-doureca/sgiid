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
@Table(name = "PR_PLANEAMENTO_PRODUCAO_CAB")
public class PR_PLANEAMENTO_PRODUCAO_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLANEAMENTO_PRODUCAO_CAB")
	private Integer ID_PLANEAMENTO_PRODUCAO_CAB;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "DATA_MRP")
	private Date DATA_MRP;
	@Column(name = "N_MRP")
	private String N_MRP;
	@Column(name = "USER_MRP")
	private String USER_MRP;
	@Column(name = "HORA_MRP")
	private Time HORA_MRP;
	@Column(name = "ID_LINHA")
	private Integer ID_LINHA;
	@Column(name = "ID_SEMANAS_ANALISE")
	private Integer ID_SEMANAS_ANALISE;
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
	@Column(name = "SEMANAS")
	private String SEMANAS;
	@Column(name = "NUMERO_SEMANAS")
	private Integer NUMERO_SEMANAS;

	public Integer getID_PLANEAMENTO_PRODUCAO_CAB() {
		return ID_PLANEAMENTO_PRODUCAO_CAB;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Date getDATA_MRP() {
		return DATA_MRP;
	}

	public String getN_MRP() {
		return N_MRP;
	}

	public String getUSER_MRP() {
		return USER_MRP;
	}

	public Time getHORA_MRP() {
		return HORA_MRP;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public Integer getID_SEMANAS_ANALISE() {
		return ID_SEMANAS_ANALISE;
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

	public void setID_PLANEAMENTO_PRODUCAO_CAB(Integer iD_PLANEAMENTO_PRODUCAO_CAB) {
		ID_PLANEAMENTO_PRODUCAO_CAB = iD_PLANEAMENTO_PRODUCAO_CAB;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_MRP(Date dATA_MRP) {
		DATA_MRP = dATA_MRP;
	}

	public void setN_MRP(String n_MRP) {
		N_MRP = n_MRP;
	}

	public void setUSER_MRP(String uSER_MRP) {
		USER_MRP = uSER_MRP;
	}

	public void setHORA_MRP(Time hORA_MRP) {
		HORA_MRP = hORA_MRP;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setID_SEMANAS_ANALISE(Integer iD_SEMANAS_ANALISE) {
		ID_SEMANAS_ANALISE = iD_SEMANAS_ANALISE;
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

	public String getSEMANAS() {
		return SEMANAS;
	}

	public Integer getNUMERO_SEMANAS() {
		return NUMERO_SEMANAS;
	}

	public void setSEMANAS(String sEMANAS) {
		SEMANAS = sEMANAS;
	}

	public void setNUMERO_SEMANAS(Integer nUMERO_SEMANAS) {
		NUMERO_SEMANAS = nUMERO_SEMANAS;
	}

}