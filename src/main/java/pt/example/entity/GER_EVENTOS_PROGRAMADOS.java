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
@Table(name = "GER_EVENTOS_PROGRAMADOS")
public class GER_EVENTOS_PROGRAMADOS {

	private Integer ID;
	private Integer MODULO;
	private String PAGINA;
	private String MOMENTO;
	private Boolean ESTADO;
	private Date DATA_INICIAL;
	private String HORA;
	private Integer TIPO_FIM;
	private Integer OCORRENCIAS;
	private Integer TOTAL_OCORRENCIAS;
	private Date DATA_FINAL;
	private Integer TIPO_REPETICAO;
	private Integer REPETIR;
	private String DIAS_SEMANA;
	private Boolean ENVIA_EMAIL;
	private String EMAIL_PARA;
	private String EMAIL_ASSUNTO;
	private String EMAIL_MENSAGEM;
	private Boolean CRIAR_FICHEIRO;
	private String NOME_RELATORIO;
	private String QUERY;
	private String PASTA_DESTINO;
	private Integer UTZ_MODIFICACAO;
	private Integer UTZ_CRIA;
	private Timestamp DATA_CRIA;
	private Timestamp DATA_MODIFICACAO;
	private Date DATA_ULT_OCORRENCIA;
	private Date DATA_PROX_OCORRENCIA;
	private String OBSERVACOES;
	private Boolean ANEXA_FICHEIROS;
	private Integer UTZ_APAGA;
	private Timestamp DATA_APAGA;
	private Boolean INATIVO;
	private String UTILIZADOR;
	private String SENHA;
	private String DOMINIO;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "MODULO")
	public Integer getMODULO() {
		return MODULO;
	}

	@Column(name = "PAGINA")
	public String getPAGINA() {
		return PAGINA;
	}

	@Column(name = "MOMENTO")
	public String getMOMENTO() {
		return MOMENTO;
	}

	@Column(name = "ESTADO")
	public Boolean getESTADO() {
		return ESTADO;
	}

	@Column(name = "DATA_INICIAL")
	public Date getDATA_INICIAL() {
		return DATA_INICIAL;
	}

	@Column(name = "HORA")
	public String getHORA() {
		return HORA;
	}

	@Column(name = "TIPO_FIM")
	public Integer getTIPO_FIM() {
		return TIPO_FIM;
	}

	@Column(name = "OCORRENCIAS")
	public Integer getOCORRENCIAS() {
		return OCORRENCIAS;
	}

	@Column(name = "TOTAL_OCORRENCIAS")
	public Integer getTOTAL_OCORRENCIAS() {
		return TOTAL_OCORRENCIAS;
	}

	@Column(name = "DATA_FINAL")
	public Date getDATA_FINAL() {
		return DATA_FINAL;
	}

	@Column(name = "TIPO_REPETICAO")
	public Integer getTIPO_REPETICAO() {
		return TIPO_REPETICAO;
	}

	@Column(name = "REPETIR")
	public Integer getREPETIR() {
		return REPETIR;
	}

	@Column(name = "DIAS_SEMANA")
	public String getDIAS_SEMANA() {
		return DIAS_SEMANA;
	}

	@Column(name = "ENVIA_EMAIL")
	public Boolean getENVIA_EMAIL() {
		return ENVIA_EMAIL;
	}

	@Column(name = "EMAIL_PARA")
	public String getEMAIL_PARA() {
		return EMAIL_PARA;
	}

	@Column(name = "EMAIL_ASSUNTO")
	public String getEMAIL_ASSUNTO() {
		return EMAIL_ASSUNTO;
	}

	@Column(name = "EMAIL_MENSAGEM")
	public String getEMAIL_MENSAGEM() {
		return EMAIL_MENSAGEM;
	}

	@Column(name = "CRIAR_FICHEIRO")
	public Boolean getCRIAR_FICHEIRO() {
		return CRIAR_FICHEIRO;
	}

	@Column(name = "NOME_RELATORIO")
	public String getNOME_RELATORIO() {
		return NOME_RELATORIO;
	}

	@Column(name = "QUERY")
	public String getQUERY() {
		return QUERY;
	}

	@Column(name = "PASTA_DESTINO")
	public String getPASTA_DESTINO() {
		return PASTA_DESTINO;
	}

	@Column(name = "UTZ_MODIFICACAO")
	public Integer getUTZ_MODIFICACAO() {
		return UTZ_MODIFICACAO;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "DATA_MODIFICACAO")
	public Timestamp getDATA_MODIFICACAO() {
		return DATA_MODIFICACAO;
	}

	@Column(name = "DATA_ULT_OCORRENCIA")
	public Date getDATA_ULT_OCORRENCIA() {
		return DATA_ULT_OCORRENCIA;
	}

	@Column(name = "DATA_PROX_OCORRENCIA")
	public Date getDATA_PROX_OCORRENCIA() {
		return DATA_PROX_OCORRENCIA;
	}

	@Column(name = "UTZ_APAGA")
	public Integer getUTZ_APAGA() {
		return UTZ_APAGA;
	}

	@Column(name = "DATA_APAGA")
	public Timestamp getDATA_APAGA() {
		return DATA_APAGA;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setUTZ_APAGA(Integer uTZ_APAGA) {
		UTZ_APAGA = uTZ_APAGA;
	}

	public void setDATA_APAGA(Timestamp dATA_APAGA) {
		DATA_APAGA = dATA_APAGA;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setMODULO(Integer mODULO) {
		MODULO = mODULO;
	}

	public void setPAGINA(String pAGINA) {
		PAGINA = pAGINA;
	}

	public void setMOMENTO(String mOMENTO) {
		MOMENTO = mOMENTO;
	}

	public void setESTADO(Boolean eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_INICIAL(Date dATA_INICIAL) {
		DATA_INICIAL = dATA_INICIAL;
	}

	public void setHORA(String hORA) {
		HORA = hORA;
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

	public void setENVIA_EMAIL(Boolean eNVIA_EMAIL) {
		ENVIA_EMAIL = eNVIA_EMAIL;
	}

	public void setEMAIL_PARA(String eMAIL_PARA) {
		EMAIL_PARA = eMAIL_PARA;
	}

	public void setEMAIL_ASSUNTO(String eMAIL_ASSUNTO) {
		EMAIL_ASSUNTO = eMAIL_ASSUNTO;
	}

	public void setEMAIL_MENSAGEM(String eMAIL_MENSAGEM) {
		EMAIL_MENSAGEM = eMAIL_MENSAGEM;
	}

	public void setCRIAR_FICHEIRO(Boolean cRIAR_FICHEIRO) {
		CRIAR_FICHEIRO = cRIAR_FICHEIRO;
	}

	public void setNOME_RELATORIO(String nOME_RELATORIO) {
		NOME_RELATORIO = nOME_RELATORIO;
	}

	public void setQUERY(String qUERY) {
		QUERY = qUERY;
	}

	public void setPASTA_DESTINO(String pASTA_DESTINO) {
		PASTA_DESTINO = pASTA_DESTINO;
	}

	public void setUTZ_MODIFICACAO(Integer uTZ_MODIFICACAO) {
		UTZ_MODIFICACAO = uTZ_MODIFICACAO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIFICACAO(Timestamp dATA_MODIFICACAO) {
		DATA_MODIFICACAO = dATA_MODIFICACAO;
	}

	public void setDATA_ULT_OCORRENCIA(Date dATA_ULT_OCORRENCIA) {
		DATA_ULT_OCORRENCIA = dATA_ULT_OCORRENCIA;
	}

	public void setDATA_PROX_OCORRENCIA(Date dATA_PROX_OCORRENCIA) {
		DATA_PROX_OCORRENCIA = dATA_PROX_OCORRENCIA;
	}

	@Column(name = "OBSERVACOES")
	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	@Column(name = "ANEXA_FICHEIROS")
	public Boolean getANEXA_FICHEIROS() {
		return ANEXA_FICHEIROS;
	}

	public void setANEXA_FICHEIROS(Boolean aNEXA_FICHEIROS) {
		ANEXA_FICHEIROS = aNEXA_FICHEIROS;
	}

	@Column(name = "UTILIZADOR")
	public String getUTILIZADOR() {
		return UTILIZADOR;
	}

	@Column(name = "SENHA")
	public String getSENHA() {
		return SENHA;
	}

	@Column(name = "DOMINIO")
	public String getDOMINIO() {
		return DOMINIO;
	}

	public void setUTILIZADOR(String uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setSENHA(String sENHA) {
		SENHA = sENHA;
	}

	public void setDOMINIO(String dOMINIO) {
		DOMINIO = dOMINIO;
	}

}
