package pt.example.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_PREPARACAO_CAB")
public class PIN_MOV_PREPARACAO_CAB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PREPARACAO_CAB")
	private Integer ID_PREPARACAO_CAB;
	@JsonProperty("ID_PREPARACAO")
	private Integer ID_PREPARACAO;

	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("OBS_PLANEAMENTO")
	private String OBS_PLANEAMENTO;
	@JsonProperty("OBS_PREPARACAO")
	private String OBS_PREPARACAO;
	@JsonProperty("OBS_EXECUCAO")
	private String OBS_EXECUCAO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_PREVISTA")
	private Date DATA_PREVISTA;
	@JsonProperty("HORA_PREVISTA")
	private String HORA_PREVISTA;
	@JsonProperty("DATA_PREPARACAO")
	private Date DATA_PREPARACAO;
	@JsonProperty("HORA_PREPARACAO")
	private String HORA_PREPARACAO;
	@JsonProperty("UTZ_PREPARACAO")
	private Integer UTZ_PREPARACAO;
	@JsonProperty("DATA_EXECUCAO")
	private Date DATA_EXECUCAO;
	@JsonProperty("HORA_EXECUCAO")
	private String HORA_EXECUCAO;
	@JsonProperty("UTZ_EXECUCAO")
	private Integer UTZ_EXECUCAO;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@JsonProperty("UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@JsonProperty("IMPRESSO")
	private Boolean IMPRESSO;
	@JsonProperty("DATA_ULT_IMPRES")
	private Timestamp DATA_ULT_IMPRES;
	@JsonProperty("UTZ_ULT_IMPRES")
	private Integer UTZ_ULT_IMPRES;
	@JsonProperty("VERSAO")
	private Integer VERSAO;

	public Integer getID_PREPARACAO_CAB() {
		return ID_PREPARACAO_CAB;
	}

	public void setID_PREPARACAO_CAB(Integer iD_PREPARACAO_CAB) {
		ID_PREPARACAO_CAB = iD_PREPARACAO_CAB;
	}

	public Integer getID_PREPARACAO() {
		return ID_PREPARACAO;
	}

	public void setID_PREPARACAO(Integer iD_PREPARACAO) {
		ID_PREPARACAO = iD_PREPARACAO;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
	}

	public String getOBS_PLANEAMENTO() {
		return OBS_PLANEAMENTO;
	}

	public void setOBS_PLANEAMENTO(String oBS_PLANEAMENTO) {
		OBS_PLANEAMENTO = oBS_PLANEAMENTO;
	}

	public String getOBS_PREPARACAO() {
		return OBS_PREPARACAO;
	}

	public void setOBS_PREPARACAO(String oBS_PREPARACAO) {
		OBS_PREPARACAO = oBS_PREPARACAO;
	}

	public String getOBS_EXECUCAO() {
		return OBS_EXECUCAO;
	}

	public void setOBS_EXECUCAO(String oBS_EXECUCAO) {
		OBS_EXECUCAO = oBS_EXECUCAO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Date getDATA_PREVISTA() {
		return DATA_PREVISTA;
	}

	public void setDATA_PREVISTA(Date dATA_PREVISTA) {
		DATA_PREVISTA = dATA_PREVISTA;
	}

	public String getHORA_PREVISTA() {
		return HORA_PREVISTA;
	}

	public void setHORA_PREVISTA(String hORA_PREVISTA) {
		HORA_PREVISTA = hORA_PREVISTA;
	}

	public Date getDATA_PREPARACAO() {
		return DATA_PREPARACAO;
	}

	public void setDATA_PREPARACAO(Date dATA_PREPARACAO) {
		DATA_PREPARACAO = dATA_PREPARACAO;
	}

	public String getHORA_PREPARACAO() {
		return HORA_PREPARACAO;
	}

	public void setHORA_PREPARACAO(String hORA_PREPARACAO) {
		HORA_PREPARACAO = hORA_PREPARACAO;
	}

	public Integer getUTZ_PREPARACAO() {
		return UTZ_PREPARACAO;
	}

	public void setUTZ_PREPARACAO(Integer uTZ_PREPARACAO) {
		UTZ_PREPARACAO = uTZ_PREPARACAO;
	}

	public Date getDATA_EXECUCAO() {
		return DATA_EXECUCAO;
	}

	public void setDATA_EXECUCAO(Date dATA_EXECUCAO) {
		DATA_EXECUCAO = dATA_EXECUCAO;
	}

	public String getHORA_EXECUCAO() {
		return HORA_EXECUCAO;
	}

	public void setHORA_EXECUCAO(String hORA_EXECUCAO) {
		HORA_EXECUCAO = hORA_EXECUCAO;
	}

	public Integer getUTZ_EXECUCAO() {
		return UTZ_EXECUCAO;
	}

	public void setUTZ_EXECUCAO(Integer uTZ_EXECUCAO) {
		UTZ_EXECUCAO = uTZ_EXECUCAO;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public Boolean getIMPRESSO() {
		return IMPRESSO;
	}

	public void setIMPRESSO(Boolean iMPRESSO) {
		IMPRESSO = iMPRESSO;
	}

	public Timestamp getDATA_ULT_IMPRES() {
		return DATA_ULT_IMPRES;
	}

	public void setDATA_ULT_IMPRES(Timestamp dATA_ULT_IMPRES) {
		DATA_ULT_IMPRES = dATA_ULT_IMPRES;
	}

	public Integer getUTZ_ULT_IMPRES() {
		return UTZ_ULT_IMPRES;
	}

	public void setUTZ_ULT_IMPRES(Integer uTZ_ULT_IMPRES) {
		UTZ_ULT_IMPRES = uTZ_ULT_IMPRES;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

}
