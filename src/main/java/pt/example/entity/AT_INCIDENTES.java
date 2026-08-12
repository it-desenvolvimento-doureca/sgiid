package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Relatorio de Investigacao de Incidentes
 * Industriais/Tecnologicos (formulario ID288.03).
 * As causas ficam em AT_INCIDENTES_CAUSAS e reutilizam o dicionario
 * AT_DIC_CAUSAS_ACIDENTE (Diagrama de Ishikawa).
 */
@Entity
@Table(name = "AT_INCIDENTES")
public class AT_INCIDENTES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INCIDENTE")
	private Integer ID_INCIDENTE;
	@Column(name = "NUMERO")
	private Integer NUMERO;
	@Column(name = "ANO")
	private Integer ANO;
	@Column(name = "LOCAL_ZONA")
	private String LOCAL_ZONA;
	@Column(name = "DATA_HORA_INICIO")
	private Timestamp DATA_HORA_INICIO;
	@Column(name = "DATA_HORA_FIM")
	private Timestamp DATA_HORA_FIM;
	@Column(name = "TP_DERRAME")
	private Boolean TP_DERRAME;
	@Column(name = "TP_INCENDIO")
	private Boolean TP_INCENDIO;
	@Column(name = "TP_EXPLOSAO")
	private Boolean TP_EXPLOSAO;
	@Column(name = "TP_FUGA")
	private Boolean TP_FUGA;
	@Column(name = "TP_OUTRO")
	private Boolean TP_OUTRO;
	@Column(name = "TP_OUTRO_TEXTO")
	private String TP_OUTRO_TEXTO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "SUBSTANCIAS_PERIGOSAS")
	private Boolean SUBSTANCIAS_PERIGOSAS;
	@Column(name = "SUBSTANCIAS_QUAIS")
	private String SUBSTANCIAS_QUAIS;
	@Column(name = "QUANTIDADE_ESTIMADA")
	private String QUANTIDADE_ESTIMADA;
	@Column(name = "DESCRICAO_CAUSAS")
	private String DESCRICAO_CAUSAS;
	@Column(name = "TRABALHOS_REPARACAO")
	private Boolean TRABALHOS_REPARACAO;
	@Column(name = "TRABALHOS_QUAIS")
	private String TRABALHOS_QUAIS;
	@Column(name = "MEIO_EXTINTORES")
	private Boolean MEIO_EXTINTORES;
	@Column(name = "MEIO_MANTAS")
	private Boolean MEIO_MANTAS;
	@Column(name = "MEIO_CARRETEIS")
	private Boolean MEIO_CARRETEIS;
	@Column(name = "MEIO_HIDRANTES")
	private Boolean MEIO_HIDRANTES;
	@Column(name = "MEIO_OUTRO")
	private Boolean MEIO_OUTRO;
	@Column(name = "MEIO_OUTRO_TEXTO")
	private String MEIO_OUTRO_TEXTO;
	@Column(name = "PROVIDENCIAS")
	private Boolean PROVIDENCIAS;
	@Column(name = "PROVIDENCIAS_QUAIS")
	private String PROVIDENCIAS_QUAIS;
	@Column(name = "CONSEQUENCIAS")
	private String CONSEQUENCIAS;
	@Column(name = "FERIDOS")
	private Boolean FERIDOS;
	@Column(name = "ID_OCORRENCIA")
	private Integer ID_OCORRENCIA;
	@Column(name = "TEMPO_DETECAO")
	private String TEMPO_DETECAO;
	@Column(name = "TEMPO_1A_INTERVENCAO")
	private String TEMPO_1A_INTERVENCAO;
	@Column(name = "TEMPO_CHEGADA_MEIOS")
	private String TEMPO_CHEGADA_MEIOS;
	@Column(name = "LICOES_APRENDIDAS")
	private String LICOES_APRENDIDAS;
	@Column(name = "REQUER_ATUALIZACAO_DOC")
	private Boolean REQUER_ATUALIZACAO_DOC;
	@Column(name = "DOCUMENTOS_ATUALIZAR")
	private String DOCUMENTOS_ATUALIZAR;
	@Column(name = "ACOES_CORRETIVAS")
	private String ACOES_CORRETIVAS;
	@Column(name = "MEDIDAS_PREVENTIVAS")
	private String MEDIDAS_PREVENTIVAS;
	@Column(name = "EQUIPA_INVESTIGACAO")
	private String EQUIPA_INVESTIGACAO;
	@Column(name = "DATA_RELATORIO")
	private Date DATA_RELATORIO;
	@Column(name = "DIFUSAO_RELATORIO")
	private String DIFUSAO_RELATORIO;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@Column(name = "DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@Column(name = "INATIVO")
	private Boolean INATIVO;

	public Integer getID_INCIDENTE() {
		return ID_INCIDENTE;
	}

	public void setID_INCIDENTE(Integer iD_INCIDENTE) {
		ID_INCIDENTE = iD_INCIDENTE;
	}

	public Integer getNUMERO() {
		return NUMERO;
	}

	public void setNUMERO(Integer nUMERO) {
		NUMERO = nUMERO;
	}

	public Integer getANO() {
		return ANO;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public String getLOCAL_ZONA() {
		return LOCAL_ZONA;
	}

	public void setLOCAL_ZONA(String lOCAL_ZONA) {
		LOCAL_ZONA = lOCAL_ZONA;
	}

	public Timestamp getDATA_HORA_INICIO() {
		return DATA_HORA_INICIO;
	}

	public void setDATA_HORA_INICIO(Timestamp dATA_HORA_INICIO) {
		DATA_HORA_INICIO = dATA_HORA_INICIO;
	}

	public Timestamp getDATA_HORA_FIM() {
		return DATA_HORA_FIM;
	}

	public void setDATA_HORA_FIM(Timestamp dATA_HORA_FIM) {
		DATA_HORA_FIM = dATA_HORA_FIM;
	}

	public Boolean getTP_DERRAME() {
		return TP_DERRAME;
	}

	public void setTP_DERRAME(Boolean tP_DERRAME) {
		TP_DERRAME = tP_DERRAME;
	}

	public Boolean getTP_INCENDIO() {
		return TP_INCENDIO;
	}

	public void setTP_INCENDIO(Boolean tP_INCENDIO) {
		TP_INCENDIO = tP_INCENDIO;
	}

	public Boolean getTP_EXPLOSAO() {
		return TP_EXPLOSAO;
	}

	public void setTP_EXPLOSAO(Boolean tP_EXPLOSAO) {
		TP_EXPLOSAO = tP_EXPLOSAO;
	}

	public Boolean getTP_FUGA() {
		return TP_FUGA;
	}

	public void setTP_FUGA(Boolean tP_FUGA) {
		TP_FUGA = tP_FUGA;
	}

	public Boolean getTP_OUTRO() {
		return TP_OUTRO;
	}

	public void setTP_OUTRO(Boolean tP_OUTRO) {
		TP_OUTRO = tP_OUTRO;
	}

	public String getTP_OUTRO_TEXTO() {
		return TP_OUTRO_TEXTO;
	}

	public void setTP_OUTRO_TEXTO(String tP_OUTRO_TEXTO) {
		TP_OUTRO_TEXTO = tP_OUTRO_TEXTO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public Boolean getSUBSTANCIAS_PERIGOSAS() {
		return SUBSTANCIAS_PERIGOSAS;
	}

	public void setSUBSTANCIAS_PERIGOSAS(Boolean sUBSTANCIAS_PERIGOSAS) {
		SUBSTANCIAS_PERIGOSAS = sUBSTANCIAS_PERIGOSAS;
	}

	public String getSUBSTANCIAS_QUAIS() {
		return SUBSTANCIAS_QUAIS;
	}

	public void setSUBSTANCIAS_QUAIS(String sUBSTANCIAS_QUAIS) {
		SUBSTANCIAS_QUAIS = sUBSTANCIAS_QUAIS;
	}

	public String getQUANTIDADE_ESTIMADA() {
		return QUANTIDADE_ESTIMADA;
	}

	public void setQUANTIDADE_ESTIMADA(String qUANTIDADE_ESTIMADA) {
		QUANTIDADE_ESTIMADA = qUANTIDADE_ESTIMADA;
	}

	public String getDESCRICAO_CAUSAS() {
		return DESCRICAO_CAUSAS;
	}

	public void setDESCRICAO_CAUSAS(String dESCRICAO_CAUSAS) {
		DESCRICAO_CAUSAS = dESCRICAO_CAUSAS;
	}

	public Boolean getTRABALHOS_REPARACAO() {
		return TRABALHOS_REPARACAO;
	}

	public void setTRABALHOS_REPARACAO(Boolean tRABALHOS_REPARACAO) {
		TRABALHOS_REPARACAO = tRABALHOS_REPARACAO;
	}

	public String getTRABALHOS_QUAIS() {
		return TRABALHOS_QUAIS;
	}

	public void setTRABALHOS_QUAIS(String tRABALHOS_QUAIS) {
		TRABALHOS_QUAIS = tRABALHOS_QUAIS;
	}

	public Boolean getMEIO_EXTINTORES() {
		return MEIO_EXTINTORES;
	}

	public void setMEIO_EXTINTORES(Boolean mEIO_EXTINTORES) {
		MEIO_EXTINTORES = mEIO_EXTINTORES;
	}

	public Boolean getMEIO_MANTAS() {
		return MEIO_MANTAS;
	}

	public void setMEIO_MANTAS(Boolean mEIO_MANTAS) {
		MEIO_MANTAS = mEIO_MANTAS;
	}

	public Boolean getMEIO_CARRETEIS() {
		return MEIO_CARRETEIS;
	}

	public void setMEIO_CARRETEIS(Boolean mEIO_CARRETEIS) {
		MEIO_CARRETEIS = mEIO_CARRETEIS;
	}

	public Boolean getMEIO_HIDRANTES() {
		return MEIO_HIDRANTES;
	}

	public void setMEIO_HIDRANTES(Boolean mEIO_HIDRANTES) {
		MEIO_HIDRANTES = mEIO_HIDRANTES;
	}

	public Boolean getMEIO_OUTRO() {
		return MEIO_OUTRO;
	}

	public void setMEIO_OUTRO(Boolean mEIO_OUTRO) {
		MEIO_OUTRO = mEIO_OUTRO;
	}

	public String getMEIO_OUTRO_TEXTO() {
		return MEIO_OUTRO_TEXTO;
	}

	public void setMEIO_OUTRO_TEXTO(String mEIO_OUTRO_TEXTO) {
		MEIO_OUTRO_TEXTO = mEIO_OUTRO_TEXTO;
	}

	public Boolean getPROVIDENCIAS() {
		return PROVIDENCIAS;
	}

	public void setPROVIDENCIAS(Boolean pROVIDENCIAS) {
		PROVIDENCIAS = pROVIDENCIAS;
	}

	public String getPROVIDENCIAS_QUAIS() {
		return PROVIDENCIAS_QUAIS;
	}

	public void setPROVIDENCIAS_QUAIS(String pROVIDENCIAS_QUAIS) {
		PROVIDENCIAS_QUAIS = pROVIDENCIAS_QUAIS;
	}

	public String getCONSEQUENCIAS() {
		return CONSEQUENCIAS;
	}

	public void setCONSEQUENCIAS(String cONSEQUENCIAS) {
		CONSEQUENCIAS = cONSEQUENCIAS;
	}

	public Boolean getFERIDOS() {
		return FERIDOS;
	}

	public void setFERIDOS(Boolean fERIDOS) {
		FERIDOS = fERIDOS;
	}

	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}

	public void setID_OCORRENCIA(Integer iD_OCORRENCIA) {
		ID_OCORRENCIA = iD_OCORRENCIA;
	}

	public String getTEMPO_DETECAO() {
		return TEMPO_DETECAO;
	}

	public void setTEMPO_DETECAO(String tEMPO_DETECAO) {
		TEMPO_DETECAO = tEMPO_DETECAO;
	}

	public String getTEMPO_1A_INTERVENCAO() {
		return TEMPO_1A_INTERVENCAO;
	}

	public void setTEMPO_1A_INTERVENCAO(String tEMPO_1A_INTERVENCAO) {
		TEMPO_1A_INTERVENCAO = tEMPO_1A_INTERVENCAO;
	}

	public String getTEMPO_CHEGADA_MEIOS() {
		return TEMPO_CHEGADA_MEIOS;
	}

	public void setTEMPO_CHEGADA_MEIOS(String tEMPO_CHEGADA_MEIOS) {
		TEMPO_CHEGADA_MEIOS = tEMPO_CHEGADA_MEIOS;
	}

	public String getLICOES_APRENDIDAS() {
		return LICOES_APRENDIDAS;
	}

	public void setLICOES_APRENDIDAS(String lICOES_APRENDIDAS) {
		LICOES_APRENDIDAS = lICOES_APRENDIDAS;
	}

	public Boolean getREQUER_ATUALIZACAO_DOC() {
		return REQUER_ATUALIZACAO_DOC;
	}

	public void setREQUER_ATUALIZACAO_DOC(Boolean rEQUER_ATUALIZACAO_DOC) {
		REQUER_ATUALIZACAO_DOC = rEQUER_ATUALIZACAO_DOC;
	}

	public String getDOCUMENTOS_ATUALIZAR() {
		return DOCUMENTOS_ATUALIZAR;
	}

	public void setDOCUMENTOS_ATUALIZAR(String dOCUMENTOS_ATUALIZAR) {
		DOCUMENTOS_ATUALIZAR = dOCUMENTOS_ATUALIZAR;
	}

	public String getACOES_CORRETIVAS() {
		return ACOES_CORRETIVAS;
	}

	public void setACOES_CORRETIVAS(String aCOES_CORRETIVAS) {
		ACOES_CORRETIVAS = aCOES_CORRETIVAS;
	}

	public String getMEDIDAS_PREVENTIVAS() {
		return MEDIDAS_PREVENTIVAS;
	}

	public void setMEDIDAS_PREVENTIVAS(String mEDIDAS_PREVENTIVAS) {
		MEDIDAS_PREVENTIVAS = mEDIDAS_PREVENTIVAS;
	}

	public String getEQUIPA_INVESTIGACAO() {
		return EQUIPA_INVESTIGACAO;
	}

	public void setEQUIPA_INVESTIGACAO(String eQUIPA_INVESTIGACAO) {
		EQUIPA_INVESTIGACAO = eQUIPA_INVESTIGACAO;
	}

	public Date getDATA_RELATORIO() {
		return DATA_RELATORIO;
	}

	public void setDATA_RELATORIO(Date dATA_RELATORIO) {
		DATA_RELATORIO = dATA_RELATORIO;
	}

	public String getDIFUSAO_RELATORIO() {
		return DIFUSAO_RELATORIO;
	}

	public void setDIFUSAO_RELATORIO(String dIFUSAO_RELATORIO) {
		DIFUSAO_RELATORIO = dIFUSAO_RELATORIO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

}
