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
@Table(name = "QUA_DERROGACOES")
public class QUA_DERROGACOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DERROGACAO")
	private Integer ID_DERROGACAO;
	@Column(name = "DATA_INICIO")
	private Timestamp DATA_INICIO;
	@Column(name = "DATA_FIM")
	private Timestamp DATA_FIM;
	@Column(name = "INTERNA_EXTERNA")
	private String INTERNA_EXTERNA;
	@Column(name = "EMISSOR")
	private Integer EMISSOR;
	@Column(name = "UNIDADE")
	private Integer UNIDADE;
	@Column(name = "SETOR")
	private Integer SETOR;
	@Column(name = "ID_CLIENTE")
	private Integer ID_CLIENTE;
	@Column(name = "NOME_CLIENTE")
	private String NOME_CLIENTE;
	@Column(name = "MORADA_CLIENTE")
	private String MORADA_CLIENTE;
	@Column(name = "ETSNUM")
	private String ETSNUM;
	@Column(name = "REFERENCIA")
	private String REFERENCIA;
	@Column(name = "DESIGNACAO_REF")
	private String DESIGNACAO_REF;
	@Column(name = "FAMILIA_REF")
	private String FAMILIA_REF;
	@Column(name = "QTD")
	private Integer QTD;
	@Column(name = "MOTIVO")
	private String MOTIVO;
	@Column(name = "CAUSA")
	private String CAUSA;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "UTZ_FECHO")
	private Integer UTZ_FECHO;
	@Column(name = "DATA_FECHO")
	private Timestamp DATA_FECHO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@Column(name = "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@Column(name = "DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@Column(name = "UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@Column(name = "PLANO_ACOES")
	private Boolean PLANO_ACOES;

	public Integer getID_DERROGACAO() {
		return ID_DERROGACAO;
	}

	public Timestamp getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Timestamp getDATA_FIM() {
		return DATA_FIM;
	}

	public String getINTERNA_EXTERNA() {
		return INTERNA_EXTERNA;
	}

	public Integer getEMISSOR() {
		return EMISSOR;
	}

	public Integer getUNIDADE() {
		return UNIDADE;
	}

	public Integer getSETOR() {
		return SETOR;
	}

	public Integer getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public String getNOME_CLIENTE() {
		return NOME_CLIENTE;
	}

	public String getMORADA_CLIENTE() {
		return MORADA_CLIENTE;
	}

	public String getETSNUM() {
		return ETSNUM;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESIGNACAO_REF() {
		return DESIGNACAO_REF;
	}

	public String getFAMILIA_REF() {
		return FAMILIA_REF;
	}

	public Integer getQTD() {
		return QTD;
	}

	public String getMOTIVO() {
		return MOTIVO;
	}

	public String getCAUSA() {
		return CAUSA;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Integer getUTZ_FECHO() {
		return UTZ_FECHO;
	}

	public Timestamp getDATA_FECHO() {
		return DATA_FECHO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setID_DERROGACAO(Integer iD_DERROGACAO) {
		ID_DERROGACAO = iD_DERROGACAO;
	}

	public void setDATA_INICIO(Timestamp dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Timestamp dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setINTERNA_EXTERNA(String iNTERNA_EXTERNA) {
		INTERNA_EXTERNA = iNTERNA_EXTERNA;
	}

	public void setEMISSOR(Integer eMISSOR) {
		EMISSOR = eMISSOR;
	}

	public void setUNIDADE(Integer uNIDADE) {
		UNIDADE = uNIDADE;
	}

	public void setSETOR(Integer sETOR) {
		SETOR = sETOR;
	}

	public void setID_CLIENTE(Integer iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public void setNOME_CLIENTE(String nOME_CLIENTE) {
		NOME_CLIENTE = nOME_CLIENTE;
	}

	public void setMORADA_CLIENTE(String mORADA_CLIENTE) {
		MORADA_CLIENTE = mORADA_CLIENTE;
	}

	public void setETSNUM(String eTSNUM) {
		ETSNUM = eTSNUM;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESIGNACAO_REF(String dESIGNACAO_REF) {
		DESIGNACAO_REF = dESIGNACAO_REF;
	}

	public void setFAMILIA_REF(String fAMILIA_REF) {
		FAMILIA_REF = fAMILIA_REF;
	}

	public void setQTD(Integer qTD) {
		QTD = qTD;
	}

	public void setMOTIVO(String mOTIVO) {
		MOTIVO = mOTIVO;
	}

	public void setCAUSA(String cAUSA) {
		CAUSA = cAUSA;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setUTZ_FECHO(Integer uTZ_FECHO) {
		UTZ_FECHO = uTZ_FECHO;
	}

	public void setDATA_FECHO(Timestamp dATA_FECHO) {
		DATA_FECHO = dATA_FECHO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public Boolean getPLANO_ACOES() {
		return PLANO_ACOES;
	}

	public void setPLANO_ACOES(Boolean pLANO_ACOES) {
		PLANO_ACOES = pLANO_ACOES;
	}

}
