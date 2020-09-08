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
@Table(name = "RH_FUNCIONARIOS")
public class RH_FUNCIONARIOS {

	private Integer COD_FUNCIONARIO;
	private String NOME;
	private String EMPRESA;
	private Timestamp DATA_ADMISSAO;
	private Timestamp DATA_DEMISSAO;
	private Boolean ATIVO;
	private String LOCAL;
	private String RESPONSAVEL;
	private Integer COD_SECTOR;
	private Integer NUM_CACIFO;
	private Integer ESTADO;
	private Date DATA_PREV_RET;
	private Date DATA_INICIO;
	private Date DATA_FIM;
	private Integer COD_FUNC_ORIGEM;

	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Boolean REGISTA_PONTO;

	@Id
	@Column(name = "COD_FUNCIONARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCOD_FUNCIONARIO() {
		return COD_FUNCIONARIO;
	}

	@Column(name = "NOME")
	public String getNOME() {
		return NOME;
	}

	@Column(name = "EMPRESA")
	public String getEMPRESA() {
		return EMPRESA;
	}

	@Column(name = "DATA_ADMISSAO")
	public Timestamp getDATA_ADMISSAO() {
		return DATA_ADMISSAO;
	}

	@Column(name = "DATA_DEMISSAO")
	public Timestamp getDATA_DEMISSAO() {
		return DATA_DEMISSAO;
	}

	@Column(name = "ATIVO")
	public Boolean getATIVO() {
		return ATIVO;
	}

	@Column(name = "LOCAL")
	public String getLOCAL() {
		return LOCAL;
	}

	@Column(name = "RESPONSAVEL")
	public String getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	@Column(name = "COD_SECTOR")
	public Integer getCOD_SECTOR() {
		return COD_SECTOR;
	}

	@Column(name = "NUM_CACIFO")
	public Integer getNUM_CACIFO() {
		return NUM_CACIFO;
	}

	@Column(name = "ESTADO")
	public Integer getESTADO() {
		return ESTADO;
	}

	@Column(name = "DATA_PREV_RET")
	public Date getDATA_PREV_RET() {
		return DATA_PREV_RET;
	}

	@Column(name = "DATA_INICIO")
	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	@Column(name = "DATA_FIM")
	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	public void setCOD_FUNCIONARIO(Integer cOD_FUNCIONARIO) {
		COD_FUNCIONARIO = cOD_FUNCIONARIO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setEMPRESA(String eMPRESA) {
		EMPRESA = eMPRESA;
	}

	public void setDATA_ADMISSAO(Timestamp dATA_ADMISSAO) {
		DATA_ADMISSAO = dATA_ADMISSAO;
	}

	public void setDATA_DEMISSAO(Timestamp dATA_DEMISSAO) {
		DATA_DEMISSAO = dATA_DEMISSAO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public void setLOCAL(String lOCAL) {
		LOCAL = lOCAL;
	}

	public void setRESPONSAVEL(String rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}

	public void setCOD_SECTOR(Integer cOD_SECTOR) {
		COD_SECTOR = cOD_SECTOR;
	}

	public void setNUM_CACIFO(Integer nUM_CACIFO) {
		NUM_CACIFO = nUM_CACIFO;
	}

	public void setESTADO(Integer eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_PREV_RET(Date dATA_PREV_RET) {
		DATA_PREV_RET = dATA_PREV_RET;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	@Column(name = "COD_FUNC_ORIGEM")
	public Integer getCOD_FUNC_ORIGEM() {
		return COD_FUNC_ORIGEM;
	}

	public void setCOD_FUNC_ORIGEM(Integer cOD_FUNC_ORIGEM) {
		COD_FUNC_ORIGEM = cOD_FUNC_ORIGEM;
	}

	@Column(name = "REGISTA_PONTO")
	public Boolean getREGISTA_PONTO() {
		return REGISTA_PONTO;
	}

	public void setREGISTA_PONTO(Boolean rEGISTA_PONTO) {
		REGISTA_PONTO = rEGISTA_PONTO;
	}

}