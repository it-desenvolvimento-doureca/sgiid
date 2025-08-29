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
@Table(name = "MAN_MOV_MANUTENCAO_PLANOS")
public class MAN_MOV_MANUTENCAO_PLANOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty("ID_ACAO")
	private Integer ID_ACAO;
	@JsonProperty("PERIOCIDADE")
	private Integer PERIOCIDADE;
	@JsonProperty("ID_EQUIPA")
	private Integer ID_EQUIPA;
	@JsonProperty("DATA_ULTIMA_REALIZADA")
	private Timestamp DATA_ULTIMA_REALIZADA;
	@JsonProperty("DATA_PROXIMA_REALIZADA")
	private Timestamp DATA_PROXIMA_REALIZADA;
	@JsonProperty("TIPO_FIM")
	private Integer TIPO_FIM;
	@JsonProperty("OCORRENCIAS")
	private Integer OCORRENCIAS;
	@JsonProperty("TOTAL_OCORRENCIAS")
	private Integer TOTAL_OCORRENCIAS;
	@JsonProperty("DATA_FINAL")
	private Date DATA_FINAL;
	@JsonProperty("TIPO_REPETICAO")
	private Integer TIPO_REPETICAO;
	@JsonProperty("REPETIR")
	private Integer REPETIR;
	@JsonProperty("DIAS_SEMANA")
	private String DIAS_SEMANA;
	@JsonProperty("DATA_INICIO")
	private Date DATA_INICIO;
	@JsonProperty("TIPO_RESPONSAVEL")
	private String TIPO_RESPONSAVEL;
	@JsonProperty("UTILIZADOR")
	private Integer UTILIZADOR;
	@JsonProperty("TEMPO_ESTIMADO")
	private Time TEMPO_ESTIMADO;
	@JsonProperty("ANEXO")
	private String ANEXO;
	@JsonProperty("TIPO_MANUTENCAO")
	private String TIPO_MANUTENCAO;

	public Integer getID() {
		return ID;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public Integer getID_ACAO() {
		return ID_ACAO;
	}

	public Integer getPERIOCIDADE() {
		return PERIOCIDADE;
	}

	public Integer getID_EQUIPA() {
		return ID_EQUIPA;
	}

	public Timestamp getDATA_ULTIMA_REALIZADA() {
		return DATA_ULTIMA_REALIZADA;
	}

	public Timestamp getDATA_PROXIMA_REALIZADA() {
		return DATA_PROXIMA_REALIZADA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setID_ACAO(Integer iD_ACAO) {
		ID_ACAO = iD_ACAO;
	}

	public void setPERIOCIDADE(Integer pERIOCIDADE) {
		PERIOCIDADE = pERIOCIDADE;
	}

	public void setID_EQUIPA(Integer iD_EQUIPA) {
		ID_EQUIPA = iD_EQUIPA;
	}

	public void setDATA_ULTIMA_REALIZADA(Timestamp dATA_ULTIMA_REALIZADA) {
		DATA_ULTIMA_REALIZADA = dATA_ULTIMA_REALIZADA;
	}

	public void setDATA_PROXIMA_REALIZADA(Timestamp dATA_PROXIMA_REALIZADA) {
		DATA_PROXIMA_REALIZADA = dATA_PROXIMA_REALIZADA;
	}

	public Integer getTIPO_FIM() {
		return TIPO_FIM;
	}

	public Integer getOCORRENCIAS() {
		return OCORRENCIAS;
	}

	public Integer getTOTAL_OCORRENCIAS() {
		return TOTAL_OCORRENCIAS;
	}

	public Date getDATA_FINAL() {
		return DATA_FINAL;
	}

	public Integer getTIPO_REPETICAO() {
		return TIPO_REPETICAO;
	}

	public Integer getREPETIR() {
		return REPETIR;
	}

	public String getDIAS_SEMANA() {
		return DIAS_SEMANA;
	}

	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public void setTIPO_FIM(Integer tIPO_FIM) {
		TIPO_FIM = tIPO_FIM;
	}

	public void setOCORRENCIAS(Integer oCORRENCIAS) {
		OCORRENCIAS = oCORRENCIAS;
	}

	public void setTOTAL_OCORRENCIAS(Integer tOTAL_OCORRENCIAS) {
		TOTAL_OCORRENCIAS = tOTAL_OCORRENCIAS;
	}

	public void setDATA_FINAL(Date dATA_FINAL) {
		DATA_FINAL = dATA_FINAL;
	}

	public void setTIPO_REPETICAO(Integer tIPO_REPETICAO) {
		TIPO_REPETICAO = tIPO_REPETICAO;
	}

	public void setREPETIR(Integer rEPETIR) {
		REPETIR = rEPETIR;
	}

	public void setDIAS_SEMANA(String dIAS_SEMANA) {
		DIAS_SEMANA = dIAS_SEMANA;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public String getTIPO_RESPONSAVEL() {
		return TIPO_RESPONSAVEL;
	}

	public Integer getUTILIZADOR() {
		return UTILIZADOR;
	}

	public void setTIPO_RESPONSAVEL(String tIPO_RESPONSAVEL) {
		TIPO_RESPONSAVEL = tIPO_RESPONSAVEL;
	}

	public void setUTILIZADOR(Integer uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public Time getTEMPO_ESTIMADO() {
		return TEMPO_ESTIMADO;
	}

	public void setTEMPO_ESTIMADO(Time tEMPO_ESTIMADO) {
		TEMPO_ESTIMADO = tEMPO_ESTIMADO;
	}

	public String getANEXO() {
		return ANEXO;
	}

	public void setANEXO(String aNEXO) {
		ANEXO = aNEXO;
	}

	public String getTIPO_MANUTENCAO() {
		return TIPO_MANUTENCAO;
	}

	public void setTIPO_MANUTENCAO(String tIPO_MANUTENCAO) {
		TIPO_MANUTENCAO = tIPO_MANUTENCAO;
	}

}
