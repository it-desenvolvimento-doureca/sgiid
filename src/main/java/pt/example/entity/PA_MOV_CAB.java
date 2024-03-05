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
@Table(name = "PA_MOV_CAB")
public class PA_MOV_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLANO_CAB")
	private Integer ID_PLANO_CAB;
	@Column(name = "ID_LINHA")
	private Integer ID_LINHA;
	@Column(name = "DEPARTAMENTO_ORIGEM")
	private Integer DEPARTAMENTO_ORIGEM;
	@Column(name = "REFERENCIA")
	private String REFERENCIA;
	@Column(name = "DESIGN_REFERENCIA")
	private String DESIGN_REFERENCIA;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "ESTADO")
	private String ESTADO;
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
	
	@Column(name = "DATA_OBJETIVO")
	private Date DATA_OBJETIVO;
	@Column(name = "DATA_ORIGEM")
	private Date DATA_ORIGEM;
	@Column(name = "AMBITO")
	private Integer AMBITO;
	@Column(name = "ORIGEM")
	private String ORIGEM;
	@Column(name = "UNIDADE")
	private Integer UNIDADE;
	
	@Column(name = "OBJETIVO")
	private String OBJETIVO;
	@Column(name = "ID_PLANO_ESTRATEGICO")
	private Integer ID_PLANO_ESTRATEGICO;

	public Integer getID_PLANO_CAB() {
		return ID_PLANO_CAB;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESIGN_REFERENCIA() {
		return DESIGN_REFERENCIA;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getESTADO() {
		return ESTADO;
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

	public void setID_PLANO_CAB(Integer iD_PLANO_CAB) {
		ID_PLANO_CAB = iD_PLANO_CAB;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESIGN_REFERENCIA(String dESIGN_REFERENCIA) {
		DESIGN_REFERENCIA = dESIGN_REFERENCIA;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
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

	public Integer getDEPARTAMENTO_ORIGEM() {
		return DEPARTAMENTO_ORIGEM;
	}

	public void setDEPARTAMENTO_ORIGEM(Integer dEPARTAMENTO_ORIGEM) {
		DEPARTAMENTO_ORIGEM = dEPARTAMENTO_ORIGEM;
	}

	public Date getDATA_OBJETIVO() {
		return DATA_OBJETIVO;
	}

	public Integer getAMBITO() {
		return AMBITO;
	}

	public String getORIGEM() {
		return ORIGEM;
	}

	public void setDATA_OBJETIVO(Date dATA_OBJETIVO) {
		DATA_OBJETIVO = dATA_OBJETIVO;
	}

	public void setAMBITO(Integer aMBITO) {
		AMBITO = aMBITO;
	}

	public void setORIGEM(String oRIGEM) {
		ORIGEM = oRIGEM;
	}

	public Integer getUNIDADE() {
		return UNIDADE;
	}

	public void setUNIDADE(Integer uNIDADE) {
		UNIDADE = uNIDADE;
	}

	public String getOBJETIVO() {
		return OBJETIVO;
	}

	public Integer getID_PLANO_ESTRATEGICO() {
		return ID_PLANO_ESTRATEGICO;
	}

	public void setOBJETIVO(String oBJETIVO) {
		OBJETIVO = oBJETIVO;
	}

	public void setID_PLANO_ESTRATEGICO(Integer iD_PLANO_ESTRATEGICO) {
		ID_PLANO_ESTRATEGICO = iD_PLANO_ESTRATEGICO;
	}

	public Date getDATA_ORIGEM() {
		return DATA_ORIGEM;
	}

	public void setDATA_ORIGEM(Date dATA_ORIGEM) {
		DATA_ORIGEM = dATA_ORIGEM;
	}

}
