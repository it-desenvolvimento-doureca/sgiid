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
@Table(name = "PR_AMOSTRAS_CAB")
public class PR_AMOSTRAS_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AMOSTRA")
	private Integer ID_AMOSTRA;
	@Column(name = "ID_LINHA")
	private Integer ID_LINHA;
	@Column(name = "REFERENCIA")
	private String REFERENCIA;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "QUANT_BARRAS")
	private Float QUANT_BARRAS;
	@Column(name = "ID_TIPOLOGIA_ENSAIO")
	private Integer ID_TIPOLOGIA_ENSAIO;
	@Column(name = "INDICE")
	private String INDICE;
	@Column(name = "DATA_LANCAMENTO")
	private Date DATA_LANCAMENTO;
	@Column(name = "DATA_FIM")
	private Date DATA_FIM;
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
	@Column(name = "UNIDADE")
	private Integer UNIDADE;
	@Column(name = "OFNUM")
	private Long OFNUM;

	public Integer getID_AMOSTRA() {
		return ID_AMOSTRA;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Float getQUANT_BARRAS() {
		return QUANT_BARRAS;
	}

	public Integer getID_TIPOLOGIA_ENSAIO() {
		return ID_TIPOLOGIA_ENSAIO;
	}

	public String getINDICE() {
		return INDICE;
	}

	public Date getDATA_LANCAMENTO() {
		return DATA_LANCAMENTO;
	}

	public Date getDATA_FIM() {
		return DATA_FIM;
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

	public void setID_AMOSTRA(Integer iD_AMOSTRA) {
		ID_AMOSTRA = iD_AMOSTRA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setQUANT_BARRAS(Float qUANT_BARRAS) {
		QUANT_BARRAS = qUANT_BARRAS;
	}

	public void setID_TIPOLOGIA_ENSAIO(Integer iD_TIPOLOGIA_ENSAIO) {
		ID_TIPOLOGIA_ENSAIO = iD_TIPOLOGIA_ENSAIO;
	}

	public void setINDICE(String iNDICE) {
		INDICE = iNDICE;
	}

	public void setDATA_LANCAMENTO(Date dATA_LANCAMENTO) {
		DATA_LANCAMENTO = dATA_LANCAMENTO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
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

	public Integer getUNIDADE() {
		return UNIDADE;
	}

	public Long getOFNUM() {
		return OFNUM;
	}

	public void setUNIDADE(Integer uNIDADE) {
		UNIDADE = uNIDADE;
	}

	public void setOFNUM(Long oFNUM) {
		OFNUM = oFNUM;
	}

}