package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_MOV_MANUTENCAO_CAB")
public class AB_MOV_MANUTENCAO_CAB {

	private Integer ID_MANUTENCAO_CAB;
	private Integer ID_MANUTENCAO;
	private Integer ID_BANHO;
	private Integer ID_TINA;
	private Integer ID_ANALISE;
	private Integer ID_TIPO_OPERACAO;
	private Integer ID_TIPO_ADICAO;
	private Date DATA_PREVISTA;
	private String HORA_PREVISTA;
	private Date DATA_EXECUCAO;
	private String HORA_EXECUCAO;
	private String OBS_EXECUCAO;
	private String OBS_PLANEAMENTO;
	private String OBS_PREPARACAO;
	private Date DATA_PREPARACAO;
	private String HORA_PREPARACAO;
	private Integer UTZ_PREPARACAO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Integer UTZ_EXECUCAO;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Timestamp DATA_ULT_IMPRES;
	private Integer UTZ_ULT_IMPRES;
	private Boolean IMPRESSO;
	private String DOSEADOR;

	@Column(name = "DATA_ULT_IMPRES")
	public Timestamp getDATA_ULT_IMPRES() {
		return DATA_ULT_IMPRES;
	}

	@Column(name = "UTZ_ULT_IMPRES")
	public Integer getUTZ_ULT_IMPRES() {
		return UTZ_ULT_IMPRES;
	}

	@Column(name = "IMPRESSO")
	public Boolean getIMPRESSO() {
		return IMPRESSO;
	}

	public void setDATA_ULT_IMPRES(Timestamp dATA_ULT_IMPRES) {
		DATA_ULT_IMPRES = dATA_ULT_IMPRES;
	}

	public void setUTZ_ULT_IMPRES(Integer uTZ_ULT_IMPRES) {
		UTZ_ULT_IMPRES = uTZ_ULT_IMPRES;
	}

	public void setIMPRESSO(Boolean iMPRESSO) {
		IMPRESSO = iMPRESSO;
	}

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
	@Column(name = "ID_MANUTENCAO_CAB")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	@Column(name = "ID_BANHO")
	public Integer getID_BANHO() {
		return ID_BANHO;
	}

	@Column(name = "ID_ANALISE")
	public Integer getID_ANALISE() {
		return ID_ANALISE;
	}

	@Column(name = "HORA_EXECUCAO")
	public String getHORA_EXECUCAO() {
		return HORA_EXECUCAO;
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

	@Column(name = "ID_MANUTENCAO")
	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	@Column(name = "ID_TINA")
	public Integer getID_TINA() {
		return ID_TINA;
	}

	@Column(name = "ID_TIPO_OPERACAO")
	public Integer getID_TIPO_OPERACAO() {
		return ID_TIPO_OPERACAO;
	}

	@Column(name = "ID_TIPO_ADICAO")
	public Integer getID_TIPO_ADICAO() {
		return ID_TIPO_ADICAO;
	}

	@Column(name = "DATA_PREVISTA")
	public Date getDATA_PREVISTA() {
		return DATA_PREVISTA;
	}

	@Column(name = "HORA_PREVISTA")
	public String getHORA_PREVISTA() {
		return HORA_PREVISTA;
	}

	@Column(name = "DATA_EXECUCAO")
	public Date getDATA_EXECUCAO() {
		return DATA_EXECUCAO;
	}

	@Column(name = "OBS_EXECUCAO")
	public String getOBS_EXECUCAO() {
		return OBS_EXECUCAO;
	}

	@Column(name = "UTZ_EXECUCAO")
	public Integer getUTZ_EXECUCAO() {
		return UTZ_EXECUCAO;
	}

	@Column(name = "OBS_PLANEAMENTO")
	public String getOBS_PLANEAMENTO() {
		return OBS_PLANEAMENTO;
	}

	@Column(name = "OBS_PREPARACAO")
	public String getOBS_PREPARACAO() {
		return OBS_PREPARACAO;
	}

	@Column(name = "DATA_PREPARACAO")
	public Date getDATA_PREPARACAO() {
		return DATA_PREPARACAO;
	}

	@Column(name = "HORA_PREPARACAO")
	public String getHORA_PREPARACAO() {
		return HORA_PREPARACAO;
	}

	@Column(name = "UTZ_PREPARACAO")
	public Integer getUTZ_PREPARACAO() {
		return UTZ_PREPARACAO;
	}

	public void setOBS_PREPARACAO(String oBS_PREPARACAO) {
		OBS_PREPARACAO = oBS_PREPARACAO;
	}

	public void setDATA_PREPARACAO(Date dATA_PREPARACAO) {
		DATA_PREPARACAO = dATA_PREPARACAO;
	}

	public void setHORA_PREPARACAO(String hORA_PREPARACAO) {
		HORA_PREPARACAO = hORA_PREPARACAO;
	}

	public void setUTZ_PREPARACAO(Integer uTZ_PREPARACAO) {
		UTZ_PREPARACAO = uTZ_PREPARACAO;
	}

	public void setOBS_PLANEAMENTO(String oBS_PLANEAMENTO) {
		OBS_PLANEAMENTO = oBS_PLANEAMENTO;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setID_TINA(Integer iD_TINA) {
		ID_TINA = iD_TINA;
	}

	public void setID_TIPO_OPERACAO(Integer iD_TIPO_OPERACAO) {
		ID_TIPO_OPERACAO = iD_TIPO_OPERACAO;
	}

	public void setID_TIPO_ADICAO(Integer iD_TIPO_ADICAO) {
		ID_TIPO_ADICAO = iD_TIPO_ADICAO;
	}

	public void setDATA_PREVISTA(Date dATA_PREVISTA) {
		DATA_PREVISTA = dATA_PREVISTA;
	}

	public void setHORA_PREVISTA(String hORA_PREVISTA) {
		HORA_PREVISTA = hORA_PREVISTA;
	}

	public void setDATA_EXECUCAO(Date dATA_EXECUCAO) {
		DATA_EXECUCAO = dATA_EXECUCAO;
	}

	public void setOBS_EXECUCAO(String oBS_EXECUCAO) {
		OBS_EXECUCAO = oBS_EXECUCAO;
	}

	public void setUTZ_EXECUCAO(Integer uTZ_EXECUCAO) {
		UTZ_EXECUCAO = uTZ_EXECUCAO;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setID_BANHO(Integer iD_BANHO) {
		ID_BANHO = iD_BANHO;
	}

	public void setID_ANALISE(Integer iD_ANALISE) {
		ID_ANALISE = iD_ANALISE;
	}

	public void setHORA_EXECUCAO(String hORA_EXECUCAO) {
		HORA_EXECUCAO = hORA_EXECUCAO;
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

	@Column(name = "DOSEADOR")
	public String getDOSEADOR() {
		return DOSEADOR;
	}

	public void setDOSEADOR(String dOSEADOR) {
		DOSEADOR = dOSEADOR;
	}

}
