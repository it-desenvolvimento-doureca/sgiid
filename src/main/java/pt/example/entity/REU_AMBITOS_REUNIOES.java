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

@Entity
@Table(name = "REU_AMBITOS_REUNIOES")
public class REU_AMBITOS_REUNIOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AMBITO")
	private Integer ID_AMBITO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "DATA_ULTIMA_REALIZADA")
	private Timestamp DATA_ULTIMA_REALIZADA;
	@Column(name = "DATA_PROXIMA_REALIZADA")
	private Timestamp DATA_PROXIMA_REALIZADA;
	@Column(name = "PRAZO_DISP_ATA")
	private Integer PRAZO_DISP_ATA;
	@Column(name = "DATA_INICIO")
	private Date DATA_INICIO;
	@Column(name = "HORA_INICIO")
	private String HORA_INICIO;
	@Column(name = "TEMPO_ESTIMADO")
	private String TEMPO_ESTIMADO;
	@Column(name = "MEIO_REALIZACAO")
	private String MEIO_REALIZACAO;
	@Column(name = "LINK_REUNIAO")
	private String LINK_REUNIAO;
	@Column(name = "TIPO_FIM")
	private Integer TIPO_FIM;
	@Column(name = "OCORRENCIAS")
	private Integer OCORRENCIAS;
	@Column(name = "TOTAL_OCORRENCIAS")
	private Integer TOTAL_OCORRENCIAS;
	@Column(name = "DATA_FINAL")
	private Date DATA_FINAL;
	@Column(name = "TIPO_REPETICAO")
	private Integer TIPO_REPETICAO;
	@Column(name = "REPETIR")
	private Integer REPETIR;
	@Column(name = "DIAS_SEMANA")
	private String DIAS_SEMANA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "DATA_ANULA")
	private Timestamp DATA_ANULA;
	@Column(name = "INATIVO")
	private Boolean INATIVO;

	public Integer getID_AMBITO() {
		return ID_AMBITO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Timestamp getDATA_ULTIMA_REALIZADA() {
		return DATA_ULTIMA_REALIZADA;
	}

	public Timestamp getDATA_PROXIMA_REALIZADA() {
		return DATA_PROXIMA_REALIZADA;
	}

	public Integer getPRAZO_DISP_ATA() {
		return PRAZO_DISP_ATA;
	}

	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public String getHORA_INICIO() {
		return HORA_INICIO;
	}

	public String getTEMPO_ESTIMADO() {
		return TEMPO_ESTIMADO;
	}

	public String getMEIO_REALIZACAO() {
		return MEIO_REALIZACAO;
	}

	public String getLINK_REUNIAO() {
		return LINK_REUNIAO;
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

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setID_AMBITO(Integer iD_AMBITO) {
		ID_AMBITO = iD_AMBITO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDATA_ULTIMA_REALIZADA(Timestamp dATA_ULTIMA_REALIZADA) {
		DATA_ULTIMA_REALIZADA = dATA_ULTIMA_REALIZADA;
	}

	public void setDATA_PROXIMA_REALIZADA(Timestamp dATA_PROXIMA_REALIZADA) {
		DATA_PROXIMA_REALIZADA = dATA_PROXIMA_REALIZADA;
	}

	public void setPRAZO_DISP_ATA(Integer pRAZO_DISP_ATA) {
		PRAZO_DISP_ATA = pRAZO_DISP_ATA;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setHORA_INICIO(String hORA_INICIO) {
		HORA_INICIO = hORA_INICIO;
	}

	public void setTEMPO_ESTIMADO(String tEMPO_ESTIMADO) {
		TEMPO_ESTIMADO = tEMPO_ESTIMADO;
	}

	public void setMEIO_REALIZACAO(String mEIO_REALIZACAO) {
		MEIO_REALIZACAO = mEIO_REALIZACAO;
	}

	public void setLINK_REUNIAO(String lINK_REUNIAO) {
		LINK_REUNIAO = lINK_REUNIAO;
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

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

}
