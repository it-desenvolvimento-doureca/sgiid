package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AT_OCORRENCIAS")
public class AT_OCORRENCIAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OCORRENCIA")
	private Integer ID_OCORRENCIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "NOME_PESSOA")
	private String NOME_PESSOA;
	@Column(name = "NUMERO_PESSOA")
	private String NUMERO_PESSOA;
	@Column(name = "IDADE_PESSOA")
	private Integer IDADE_PESSOA;
	@Column(name = "NACIONALIDADE")
	private String NACIONALIDADE;
	@Column(name = "FUNCAO_PESSOA")
	private String FUNCAO_PESSOA;
	@Column(name = "DEPARTAMENTO")
	private String DEPARTAMENTO;
	@Column(name = "TIPO_RELATORIO")
	private String TIPO_RELATORIO;
	@Column(name = "COM_BAIXA")
	private Boolean COM_BAIXA;
	@Column(name = "TIPO_ACIDENTE")
	private String TIPO_ACIDENTE;
	@Column(name = "NOTIFICOU_SEGURADORA")
	private Boolean NOTIFICOU_SEGURADORA;
	@Column(name = "COMPANHIA")
	private String COMPANHIA;
	@Column(name = "N_APOLICE")
	private String N_APOLICE;
	@Column(name = "DATA_ACIDENTE")
	private Date DATA_ACIDENTE;
	@Column(name = "HORA_ACIDENTE")
	private Time HORA_ACIDENTE;
	@Column(name = "LOCAL_ACIDENTE")
	private String LOCAL_ACIDENTE;
	@Column(name = "NUMERO_PESSOAS_ENVOLVIDAS")
	private Integer NUMERO_PESSOAS_ENVOLVIDAS;
	@Column(name = "NUMERO_VITIMAS")
	private Integer NUMERO_VITIMAS;
	@Column(name = "DESCRICAO_ACIDENTE")
	private String DESCRICAO_ACIDENTE;
	@Column(name = "GRAU_LESAO")
	private String GRAU_LESAO;
	@Column(name = "GEROU_IT")
	private Boolean GEROU_IT;
	@Column(name = "GEROU_IP")
	private Boolean GEROU_IP;
	@Column(name = "DANOS_MATERIAS")
	private String DANOS_MATERIAS;
	@Column(name = "TRABALHADORES_SIMILARES")
	private Boolean TRABALHADORES_SIMILARES;
	@Column(name = "PA_CABECA")
	private Boolean PA_CABECA;
	@Column(name = "PA_OLHOS")
	private Boolean PA_OLHOS;
	@Column(name = "PA_PESCOCO")
	private Boolean PA_PESCOCO;
	@Column(name = "PA_COSTAS")
	private Boolean PA_COSTAS;
	@Column(name = "PA_TORAX")
	private Boolean PA_TORAX;
	@Column(name = "PA_ABDOMEN")
	private Boolean PA_ABDOMEN;
	@Column(name = "PA_OMBRO")
	private Boolean PA_OMBRO;
	@Column(name = "PA_ANTEBRACO")
	private Boolean PA_ANTEBRACO;
	@Column(name = "PA_PE")
	private Boolean PA_PE;
	@Column(name = "PA_DEDOS_PE")
	private Boolean PA_DEDOS_PE;
	@Column(name = "PA_LOCALIZACOES_MULTIPLAS")
	private Boolean PA_LOCALIZACOES_MULTIPLAS;
	@Column(name = "PA_ANCA")
	private Boolean PA_ANCA;
	@Column(name = "PA_JOELHO")
	private Boolean PA_JOELHO;
	@Column(name = "PA_MAO")
	private Boolean PA_MAO;
	@Column(name = "PA_OUTRO")
	private Boolean PA_OUTRO;
	@Column(name = "PA_OUTRO_TEXTO")
	private String PA_OUTRO_TEXTO;
	@Column(name = "RECOLHA_EVIDENCIAS")
	private String RECOLHA_EVIDENCIAS;
	@Column(name = "TESTEMUNHAS")
	private Boolean TESTEMUNHAS;
	@Column(name = "NUMERO_TESTEMUNHAS")
	private Integer NUMERO_TESTEMUNHAS;
	@Column(name = "MEDIDAS_FORMACAO")
	private String MEDIDAS_FORMACAO;
	@Column(name = "MEDIDAS_ORGANIZACAO")
	private String MEDIDAS_ORGANIZACAO;
	@Column(name = "MEDIDAS_PROTECAO_COL")
	private String MEDIDAS_PROTECAO_COL;
	@Column(name = "MEDIDAS_PROTECAO_IND")
	private String MEDIDAS_PROTECAO_IND;
	@Column(name = "MEDIDAS_OUTRAS")
	private String MEDIDAS_OUTRAS;
	@Column(name = "DIAGRAMA_TOP_ESQ")
	private String DIAGRAMA_TOP_ESQ;
	@Column(name = "DIAGRAMA_TOP_DIR")
	private String DIAGRAMA_TOP_DIR;
	@Column(name = "DIAGRAMA_DIR")
	private String DIAGRAMA_DIR;
	@Column(name = "DIAGRAMA_BT_ESQ")
	private String DIAGRAMA_BT_ESQ;
	@Column(name = "DIAGRAMA_BT_DIR")
	private String DIAGRAMA_BT_DIR;
	@Column(name = "DESCRICAO_CAUSAS")
	private String DESCRICAO_CAUSAS;
	@Column(name = "ANALISE_EFICACIA")
	private String ANALISE_EFICACIA;
	@Column(name = "EFICAZ")
	private Boolean EFICAZ;
	@Column(name = "DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@Column(name = "UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "INATIVO")
	private Boolean INATIVO;
	@Column(name = "VINCULO")
	private Boolean VINCULO;
	@Column(name = "DIAS_PERDIDOS")
	private Integer DIAS_PERDIDOS;
	@Column(name = "CL_CORTE")
	private Boolean CL_CORTE;
	@Column(name = "CL_FERIDA")
	private Boolean CL_FERIDA;
	@Column(name = "CL_CONTUSAO")
	private Boolean CL_CONTUSAO;
	@Column(name = "CL_FRATURA")
	private Boolean CL_FRATURA;
	@Column(name = "CL_HEMATOMA")
	private Boolean CL_HEMATOMA;
	@Column(name = "CL_LESAO_MUSCULO_ESQUELETICA")
	private Boolean CL_LESAO_MUSCULO_ESQUELETICA;
	@Column(name = "CL_INTOXICACAO_ENVENENAMENTO")
	private Boolean CL_INTOXICACAO_ENVENENAMENTO;
	@Column(name = "CL_ENTORSE_LUXACAO")
	private Boolean CL_ENTORSE_LUXACAO;
	@Column(name = "CL_LESAO_OCULAR")
	private Boolean CL_LESAO_OCULAR;
	@Column(name = "CL_ESMAGAMENTO")
	private Boolean CL_ESMAGAMENTO;
	@Column(name = "CL_PERFURACAO")
	private Boolean CL_PERFURACAO;
	@Column(name = "CL_ENTATALAMENTO")
	private Boolean CL_ENTATALAMENTO;
	@Column(name = "CL_AMPUTACAO")
	private Boolean CL_AMPUTACAO;
	@Column(name = "CL_QUEIMADURA")
	private Boolean CL_QUEIMADURA;
	@Column(name = "CL_OUTRO")
	private Boolean CL_OUTRO;
	@Column(name = "CL_OUTRO_TEXTO")
	private String CL_OUTRO_TEXTO;
	/*@Column(name = "NUMERADOR_OCORRENCIAS")
	private String NUMERADOR_OCORRENCIAS;*/
	
	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
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

	public String getNOME_PESSOA() {
		return NOME_PESSOA;
	}

	public String getNUMERO_PESSOA() {
		return NUMERO_PESSOA;
	}

	public Integer getIDADE_PESSOA() {
		return IDADE_PESSOA;
	}

	public String getNACIONALIDADE() {
		return NACIONALIDADE;
	}

	public String getFUNCAO_PESSOA() {
		return FUNCAO_PESSOA;
	}

	public String getDEPARTAMENTO() {
		return DEPARTAMENTO;
	}

	public String getTIPO_RELATORIO() {
		return TIPO_RELATORIO;
	}

	public Boolean getCOM_BAIXA() {
		return COM_BAIXA;
	}

	public String getTIPO_ACIDENTE() {
		return TIPO_ACIDENTE;
	}

	public Boolean getNOTIFICOU_SEGURADORA() {
		return NOTIFICOU_SEGURADORA;
	}

	public String getCOMPANHIA() {
		return COMPANHIA;
	}

	public String getN_APOLICE() {
		return N_APOLICE;
	}

	public Date getDATA_ACIDENTE() {
		return DATA_ACIDENTE;
	}

	public Time getHORA_ACIDENTE() {
		return HORA_ACIDENTE;
	}

	public String getLOCAL_ACIDENTE() {
		return LOCAL_ACIDENTE;
	}

	public Integer getNUMERO_PESSOAS_ENVOLVIDAS() {
		return NUMERO_PESSOAS_ENVOLVIDAS;
	}

	public Integer getNUMERO_VITIMAS() {
		return NUMERO_VITIMAS;
	}

	public String getDESCRICAO_ACIDENTE() {
		return DESCRICAO_ACIDENTE;
	}

	public String getGRAU_LESAO() {
		return GRAU_LESAO;
	}

	public Boolean getGEROU_IT() {
		return GEROU_IT;
	}

	public Boolean getGEROU_IP() {
		return GEROU_IP;
	}

	public String getDANOS_MATERIAS() {
		return DANOS_MATERIAS;
	}

	public Boolean getTRABALHADORES_SIMILARES() {
		return TRABALHADORES_SIMILARES;
	}

	public Boolean getPA_CABECA() {
		return PA_CABECA;
	}

	public Boolean getPA_OLHOS() {
		return PA_OLHOS;
	}

	public Boolean getPA_PESCOCO() {
		return PA_PESCOCO;
	}

	public Boolean getPA_COSTAS() {
		return PA_COSTAS;
	}

	public Boolean getPA_TORAX() {
		return PA_TORAX;
	}

	public Boolean getPA_ABDOMEN() {
		return PA_ABDOMEN;
	}

	public Boolean getPA_OMBRO() {
		return PA_OMBRO;
	}

	public Boolean getPA_ANTEBRACO() {
		return PA_ANTEBRACO;
	}

	public Boolean getPA_PE() {
		return PA_PE;
	}

	public Boolean getPA_DEDOS_PE() {
		return PA_DEDOS_PE;
	}

	public Boolean getPA_LOCALIZACOES_MULTIPLAS() {
		return PA_LOCALIZACOES_MULTIPLAS;
	}

	public Boolean getPA_ANCA() {
		return PA_ANCA;
	}

	public Boolean getPA_JOELHO() {
		return PA_JOELHO;
	}

	public Boolean getPA_MAO() {
		return PA_MAO;
	}

	public Boolean getPA_OUTRO() {
		return PA_OUTRO;
	}

	public String getPA_OUTRO_TEXTO() {
		return PA_OUTRO_TEXTO;
	}

	public String getRECOLHA_EVIDENCIAS() {
		return RECOLHA_EVIDENCIAS;
	}

	public Boolean getTESTEMUNHAS() {
		return TESTEMUNHAS;
	}

	public Integer getNUMERO_TESTEMUNHAS() {
		return NUMERO_TESTEMUNHAS;
	}

	public String getMEDIDAS_FORMACAO() {
		return MEDIDAS_FORMACAO;
	}

	public String getMEDIDAS_ORGANIZACAO() {
		return MEDIDAS_ORGANIZACAO;
	}

	public String getMEDIDAS_PROTECAO_COL() {
		return MEDIDAS_PROTECAO_COL;
	}

	public String getMEDIDAS_PROTECAO_IND() {
		return MEDIDAS_PROTECAO_IND;
	}

	public String getMEDIDAS_OUTRAS() {
		return MEDIDAS_OUTRAS;
	}

	public String getDIAGRAMA_TOP_ESQ() {
		return DIAGRAMA_TOP_ESQ;
	}

	public String getDIAGRAMA_TOP_DIR() {
		return DIAGRAMA_TOP_DIR;
	}

	public String getDIAGRAMA_DIR() {
		return DIAGRAMA_DIR;
	}

	public String getDIAGRAMA_BT_ESQ() {
		return DIAGRAMA_BT_ESQ;
	}

	public String getDIAGRAMA_BT_DIR() {
		return DIAGRAMA_BT_DIR;
	}

	public String getDESCRICAO_CAUSAS() {
		return DESCRICAO_CAUSAS;
	}

	public String getANALISE_EFICACIA() {
		return ANALISE_EFICACIA;
	}

	public Boolean getEFICAZ() {
		return EFICAZ;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public Boolean getVINCULO() {
		return VINCULO;
	}

	public Integer getDIAS_PERDIDOS() {
		return DIAS_PERDIDOS;
	}

	public Boolean getCL_CORTE() {
		return CL_CORTE;
	}

	public Boolean getCL_FERIDA() {
		return CL_FERIDA;
	}

	public Boolean getCL_CONTUSAO() {
		return CL_CONTUSAO;
	}

	public Boolean getCL_FRATURA() {
		return CL_FRATURA;
	}

	public Boolean getCL_HEMATOMA() {
		return CL_HEMATOMA;
	}

	public Boolean getCL_LESAO_MUSCULO_ESQUELETICA() {
		return CL_LESAO_MUSCULO_ESQUELETICA;
	}

	public Boolean getCL_INTOXICACAO_ENVENENAMENTO() {
		return CL_INTOXICACAO_ENVENENAMENTO;
	}

	public Boolean getCL_ENTORSE_LUXACAO() {
		return CL_ENTORSE_LUXACAO;
	}

	public Boolean getCL_LESAO_OCULAR() {
		return CL_LESAO_OCULAR;
	}

	public Boolean getCL_ESMAGAMENTO() {
		return CL_ESMAGAMENTO;
	}

	public Boolean getCL_PERFURACAO() {
		return CL_PERFURACAO;
	}

	public Boolean getCL_ENTATALAMENTO() {
		return CL_ENTATALAMENTO;
	}

	public Boolean getCL_AMPUTACAO() {
		return CL_AMPUTACAO;
	}

	public Boolean getCL_QUEIMADURA() {
		return CL_QUEIMADURA;
	}

	public Boolean getCL_OUTRO() {
		return CL_OUTRO;
	}

	public String getCL_OUTRO_TEXTO() {
		return CL_OUTRO_TEXTO;
	}

	public void setID_OCORRENCIA(Integer iD_OCORRENCIA) {
		ID_OCORRENCIA = iD_OCORRENCIA;
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

	public void setNOME_PESSOA(String nOME_PESSOA) {
		NOME_PESSOA = nOME_PESSOA;
	}

	public void setNUMERO_PESSOA(String nUMERO_PESSOA) {
		NUMERO_PESSOA = nUMERO_PESSOA;
	}

	public void setIDADE_PESSOA(Integer iDADE_PESSOA) {
		IDADE_PESSOA = iDADE_PESSOA;
	}

	public void setNACIONALIDADE(String nACIONALIDADE) {
		NACIONALIDADE = nACIONALIDADE;
	}

	public void setFUNCAO_PESSOA(String fUNCAO_PESSOA) {
		FUNCAO_PESSOA = fUNCAO_PESSOA;
	}

	public void setDEPARTAMENTO(String dEPARTAMENTO) {
		DEPARTAMENTO = dEPARTAMENTO;
	}

	public void setTIPO_RELATORIO(String tIPO_RELATORIO) {
		TIPO_RELATORIO = tIPO_RELATORIO;
	}

	public void setCOM_BAIXA(Boolean cOM_BAIXA) {
		COM_BAIXA = cOM_BAIXA;
	}

	public void setTIPO_ACIDENTE(String tIPO_ACIDENTE) {
		TIPO_ACIDENTE = tIPO_ACIDENTE;
	}

	public void setNOTIFICOU_SEGURADORA(Boolean nOTIFICOU_SEGURADORA) {
		NOTIFICOU_SEGURADORA = nOTIFICOU_SEGURADORA;
	}

	public void setCOMPANHIA(String cOMPANHIA) {
		COMPANHIA = cOMPANHIA;
	}

	public void setN_APOLICE(String n_APOLICE) {
		N_APOLICE = n_APOLICE;
	}

	public void setDATA_ACIDENTE(Date dATA_ACIDENTE) {
		DATA_ACIDENTE = dATA_ACIDENTE;
	}

	public void setHORA_ACIDENTE(Time hORA_ACIDENTE) {
		HORA_ACIDENTE = hORA_ACIDENTE;
	}

	public void setLOCAL_ACIDENTE(String lOCAL_ACIDENTE) {
		LOCAL_ACIDENTE = lOCAL_ACIDENTE;
	}

	public void setNUMERO_PESSOAS_ENVOLVIDAS(Integer nUMERO_PESSOAS_ENVOLVIDAS) {
		NUMERO_PESSOAS_ENVOLVIDAS = nUMERO_PESSOAS_ENVOLVIDAS;
	}

	public void setNUMERO_VITIMAS(Integer nUMERO_VITIMAS) {
		NUMERO_VITIMAS = nUMERO_VITIMAS;
	}

	public void setDESCRICAO_ACIDENTE(String dESCRICAO_ACIDENTE) {
		DESCRICAO_ACIDENTE = dESCRICAO_ACIDENTE;
	}

	public void setGRAU_LESAO(String gRAU_LESAO) {
		GRAU_LESAO = gRAU_LESAO;
	}

	public void setGEROU_IT(Boolean gEROU_IT) {
		GEROU_IT = gEROU_IT;
	}

	public void setGEROU_IP(Boolean gEROU_IP) {
		GEROU_IP = gEROU_IP;
	}

	public void setDANOS_MATERIAS(String dANOS_MATERIAS) {
		DANOS_MATERIAS = dANOS_MATERIAS;
	}

	public void setTRABALHADORES_SIMILARES(Boolean tRABALHADORES_SIMILARES) {
		TRABALHADORES_SIMILARES = tRABALHADORES_SIMILARES;
	}

	public void setPA_CABECA(Boolean pA_CABECA) {
		PA_CABECA = pA_CABECA;
	}

	public void setPA_OLHOS(Boolean pA_OLHOS) {
		PA_OLHOS = pA_OLHOS;
	}

	public void setPA_PESCOCO(Boolean pA_PESCOCO) {
		PA_PESCOCO = pA_PESCOCO;
	}

	public void setPA_COSTAS(Boolean pA_COSTAS) {
		PA_COSTAS = pA_COSTAS;
	}

	public void setPA_TORAX(Boolean pA_TORAX) {
		PA_TORAX = pA_TORAX;
	}

	public void setPA_ABDOMEN(Boolean pA_ABDOMEN) {
		PA_ABDOMEN = pA_ABDOMEN;
	}

	public void setPA_OMBRO(Boolean pA_OMBRO) {
		PA_OMBRO = pA_OMBRO;
	}

	public void setPA_ANTEBRACO(Boolean pA_ANTEBRACO) {
		PA_ANTEBRACO = pA_ANTEBRACO;
	}

	public void setPA_PE(Boolean pA_PE) {
		PA_PE = pA_PE;
	}

	public void setPA_DEDOS_PE(Boolean pA_DEDOS_PE) {
		PA_DEDOS_PE = pA_DEDOS_PE;
	}

	public void setPA_LOCALIZACOES_MULTIPLAS(Boolean pA_LOCALIZACOES_MULTIPLAS) {
		PA_LOCALIZACOES_MULTIPLAS = pA_LOCALIZACOES_MULTIPLAS;
	}

	public void setPA_ANCA(Boolean pA_ANCA) {
		PA_ANCA = pA_ANCA;
	}

	public void setPA_JOELHO(Boolean pA_JOELHO) {
		PA_JOELHO = pA_JOELHO;
	}

	public void setPA_MAO(Boolean pA_MAO) {
		PA_MAO = pA_MAO;
	}

	public void setPA_OUTRO(Boolean pA_OUTRO) {
		PA_OUTRO = pA_OUTRO;
	}

	public void setPA_OUTRO_TEXTO(String pA_OUTRO_TEXTO) {
		PA_OUTRO_TEXTO = pA_OUTRO_TEXTO;
	}

	public void setRECOLHA_EVIDENCIAS(String rECOLHA_EVIDENCIAS) {
		RECOLHA_EVIDENCIAS = rECOLHA_EVIDENCIAS;
	}

	public void setTESTEMUNHAS(Boolean tESTEMUNHAS) {
		TESTEMUNHAS = tESTEMUNHAS;
	}

	public void setNUMERO_TESTEMUNHAS(Integer nUMERO_TESTEMUNHAS) {
		NUMERO_TESTEMUNHAS = nUMERO_TESTEMUNHAS;
	}

	public void setMEDIDAS_FORMACAO(String mEDIDAS_FORMACAO) {
		MEDIDAS_FORMACAO = mEDIDAS_FORMACAO;
	}

	public void setMEDIDAS_ORGANIZACAO(String mEDIDAS_ORGANIZACAO) {
		MEDIDAS_ORGANIZACAO = mEDIDAS_ORGANIZACAO;
	}

	public void setMEDIDAS_PROTECAO_COL(String mEDIDAS_PROTECAO_COL) {
		MEDIDAS_PROTECAO_COL = mEDIDAS_PROTECAO_COL;
	}

	public void setMEDIDAS_PROTECAO_IND(String mEDIDAS_PROTECAO_IND) {
		MEDIDAS_PROTECAO_IND = mEDIDAS_PROTECAO_IND;
	}

	public void setMEDIDAS_OUTRAS(String mEDIDAS_OUTRAS) {
		MEDIDAS_OUTRAS = mEDIDAS_OUTRAS;
	}

	public void setDIAGRAMA_TOP_ESQ(String dIAGRAMA_TOP_ESQ) {
		DIAGRAMA_TOP_ESQ = dIAGRAMA_TOP_ESQ;
	}

	public void setDIAGRAMA_TOP_DIR(String dIAGRAMA_TOP_DIR) {
		DIAGRAMA_TOP_DIR = dIAGRAMA_TOP_DIR;
	}

	public void setDIAGRAMA_DIR(String dIAGRAMA_DIR) {
		DIAGRAMA_DIR = dIAGRAMA_DIR;
	}

	public void setDIAGRAMA_BT_ESQ(String dIAGRAMA_BT_ESQ) {
		DIAGRAMA_BT_ESQ = dIAGRAMA_BT_ESQ;
	}

	public void setDIAGRAMA_BT_DIR(String dIAGRAMA_BT_DIR) {
		DIAGRAMA_BT_DIR = dIAGRAMA_BT_DIR;
	}

	public void setDESCRICAO_CAUSAS(String dESCRICAO_CAUSAS) {
		DESCRICAO_CAUSAS = dESCRICAO_CAUSAS;
	}

	public void setANALISE_EFICACIA(String aNALISE_EFICACIA) {
		ANALISE_EFICACIA = aNALISE_EFICACIA;
	}

	public void setEFICAZ(Boolean eFICAZ) {
		EFICAZ = eFICAZ;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setVINCULO(Boolean vINCULO) {
		VINCULO = vINCULO;
	}

	public void setDIAS_PERDIDOS(Integer dIAS_PERDIDOS) {
		DIAS_PERDIDOS = dIAS_PERDIDOS;
	}

	public void setCL_CORTE(Boolean cL_CORTE) {
		CL_CORTE = cL_CORTE;
	}

	public void setCL_FERIDA(Boolean cL_FERIDA) {
		CL_FERIDA = cL_FERIDA;
	}

	public void setCL_CONTUSAO(Boolean cL_CONTUSAO) {
		CL_CONTUSAO = cL_CONTUSAO;
	}

	public void setCL_FRATURA(Boolean cL_FRATURA) {
		CL_FRATURA = cL_FRATURA;
	}

	public void setCL_HEMATOMA(Boolean cL_HEMATOMA) {
		CL_HEMATOMA = cL_HEMATOMA;
	}

	public void setCL_LESAO_MUSCULO_ESQUELETICA(Boolean cL_LESAO_MUSCULO_ESQUELETICA) {
		CL_LESAO_MUSCULO_ESQUELETICA = cL_LESAO_MUSCULO_ESQUELETICA;
	}

	public void setCL_INTOXICACAO_ENVENENAMENTO(Boolean cL_INTOXICACAO_ENVENENAMENTO) {
		CL_INTOXICACAO_ENVENENAMENTO = cL_INTOXICACAO_ENVENENAMENTO;
	}

	public void setCL_ENTORSE_LUXACAO(Boolean cL_ENTORSE_LUXACAO) {
		CL_ENTORSE_LUXACAO = cL_ENTORSE_LUXACAO;
	}

	public void setCL_LESAO_OCULAR(Boolean cL_LESAO_OCULAR) {
		CL_LESAO_OCULAR = cL_LESAO_OCULAR;
	}

	public void setCL_ESMAGAMENTO(Boolean cL_ESMAGAMENTO) {
		CL_ESMAGAMENTO = cL_ESMAGAMENTO;
	}

	public void setCL_PERFURACAO(Boolean cL_PERFURACAO) {
		CL_PERFURACAO = cL_PERFURACAO;
	}

	public void setCL_ENTATALAMENTO(Boolean cL_ENTATALAMENTO) {
		CL_ENTATALAMENTO = cL_ENTATALAMENTO;
	}

	public void setCL_AMPUTACAO(Boolean cL_AMPUTACAO) {
		CL_AMPUTACAO = cL_AMPUTACAO;
	}

	public void setCL_QUEIMADURA(Boolean cL_QUEIMADURA) {
		CL_QUEIMADURA = cL_QUEIMADURA;
	}

	public void setCL_OUTRO(Boolean cL_OUTRO) {
		CL_OUTRO = cL_OUTRO;
	}

	public void setCL_OUTRO_TEXTO(String cL_OUTRO_TEXTO) {
		CL_OUTRO_TEXTO = cL_OUTRO_TEXTO;
	}

	/*public String getNUMERADOR_OCORRENCIAS() {
		return NUMERADOR_OCORRENCIAS;
	}

	public void setNUMERADOR_OCORRENCIAS(String nUMERADOR_OCORRENCIAS) {
		NUMERADOR_OCORRENCIAS = nUMERADOR_OCORRENCIAS;
	}*/

}
