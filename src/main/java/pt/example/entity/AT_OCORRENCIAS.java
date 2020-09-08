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
	public Integer ID_OCORRENCIA;
	public Timestamp DATA_CRIA;
	public Integer UTZ_CRIA;
	public Timestamp DATA_MODIF;
	public Integer UTZ_MODIF;
	public String NOME_PESSOA;
	public String NUMERO_PESSOA;
	public Integer IDADE_PESSOA;
	public String NACIONALIDADE;
	public String FUNCAO_PESSOA;
	public String DEPARTAMENTO;
	public String TIPO_RELATORIO;
	public Boolean COM_BAIXA;
	public String TIPO_ACIDENTE;
	public Boolean NOTIFICOU_SEGURADORA;
	public String COMPANHIA;
	public String N_APOLICE;
	public Date DATA_ACIDENTE;
	public Time HORA_ACIDENTE;
	public String LOCAL_ACIDENTE;
	public Integer NUMERO_PESSOAS_ENVOLVIDAS;
	public Integer NUMERO_VITIMAS;
	public String DESCRICAO_ACIDENTE;
	public String GRAU_LESAO;
	public Boolean GEROU_IT;
	public Boolean GEROU_IP;
	public String DANOS_MATERIAS;
	public Boolean TRABALHADORES_SIMILARES;
	public Boolean PA_CABECA;
	public Boolean PA_OLHOS;
	public Boolean PA_PESCOCO;
	public Boolean PA_COSTAS;
	public Boolean PA_TORAX;
	public Boolean PA_ABDOMEN;
	public Boolean PA_OMBRO;
	public Boolean PA_ANTEBRACO;
	public Boolean PA_PE;
	public Boolean PA_DEDOS_PE;
	public Boolean PA_LOCALIZACOES_MULTIPLAS;
	public Boolean PA_ANCA;
	public Boolean PA_JOELHO;
	public Boolean PA_MAO;
	public Boolean PA_OUTRO;
	public String PA_OUTRO_TEXTO;
	public String RECOLHA_EVIDENCIAS;
	public Boolean TESTEMUNHAS;
	public Integer NUMERO_TESTEMUNHAS;
	public String MEDIDAS_FORMACAO;
	public String MEDIDAS_ORGANIZACAO;
	public String MEDIDAS_PROTECAO_COL;
	public String MEDIDAS_PROTECAO_IND;
	public String MEDIDAS_OUTRAS;
	public String DIAGRAMA_TOP_ESQ;
	public String DIAGRAMA_TOP_DIR;
	public String DIAGRAMA_DIR;
	public String DIAGRAMA_BT_ESQ;
	public String DIAGRAMA_BT_DIR;
	public String DESCRICAO_CAUSAS;
	public String ANALISE_EFICACIA;
	public Boolean EFICAZ;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	public String ESTADO;

	@Id
	@Column(name = "ID_OCORRENCIA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_MODIF")
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	@Column(name = "UTZ_MODIF")
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	@Column(name = "NOME_PESSOA")
	public String getNOME_PESSOA() {
		return NOME_PESSOA;
	}

	@Column(name = "NUMERO_PESSOA")
	public String getNUMERO_PESSOA() {
		return NUMERO_PESSOA;
	}

	@Column(name = "IDADE_PESSOA")
	public Integer getIDADE_PESSOA() {
		return IDADE_PESSOA;
	}

	@Column(name = "NACIONALIDADE")
	public String getNACIONALIDADE() {
		return NACIONALIDADE;
	}

	@Column(name = "FUNCAO_PESSOA")
	public String getFUNCAO_PESSOA() {
		return FUNCAO_PESSOA;
	}

	@Column(name = "DEPARTAMENTO")

	public String getDEPARTAMENTO() {
		return DEPARTAMENTO;
	}

	@Column(name = "TIPO_RELATORIO")
	public String getTIPO_RELATORIO() {
		return TIPO_RELATORIO;
	}

	@Column(name = "COM_BAIXA")
	public Boolean getCOM_BAIXA() {
		return COM_BAIXA;
	}

	@Column(name = "TIPO_ACIDENTE")
	public String getTIPO_ACIDENTE() {
		return TIPO_ACIDENTE;
	}

	@Column(name = "NOTIFICOU_SEGURADORA")
	public Boolean getNOTIFICOU_SEGURADORA() {
		return NOTIFICOU_SEGURADORA;
	}

	@Column(name = "COMPANHIA")
	public String getCOMPANHIA() {
		return COMPANHIA;
	}

	@Column(name = "N_APOLICE")
	public String getN_APOLICE() {
		return N_APOLICE;
	}

	@Column(name = "DATA_ACIDENTE")
	public Date getDATA_ACIDENTE() {
		return DATA_ACIDENTE;
	}

	@Column(name = "HORA_ACIDENTE")
	public Time getHORA_ACIDENTE() {
		return HORA_ACIDENTE;
	}

	@Column(name = "LOCAL_ACIDENTE")
	public String getLOCAL_ACIDENTE() {
		return LOCAL_ACIDENTE;
	}

	@Column(name = "NUMERO_PESSOAS_ENVOLVIDAS")
	public Integer getNUMERO_PESSOAS_ENVOLVIDAS() {
		return NUMERO_PESSOAS_ENVOLVIDAS;
	}

	@Column(name = "NUMERO_VITIMAS")
	public Integer getNUMERO_VITIMAS() {
		return NUMERO_VITIMAS;
	}

	@Column(name = "DESCRICAO_ACIDENTE")
	public String getDESCRICAO_ACIDENTE() {
		return DESCRICAO_ACIDENTE;
	}

	@Column(name = "GRAU_LESAO")
	public String getGRAU_LESAO() {
		return GRAU_LESAO;
	}

	@Column(name = "GEROU_IT")
	public Boolean getGEROU_IT() {
		return GEROU_IT;
	}

	@Column(name = "GEROU_IP")
	public Boolean getGEROU_IP() {
		return GEROU_IP;
	}

	@Column(name = "DANOS_MATERIAS")
	public String getDANOS_MATERIAS() {
		return DANOS_MATERIAS;
	}

	@Column(name = "TRABALHADORES_SIMILARES")
	public Boolean getTRABALHADORES_SIMILARES() {
		return TRABALHADORES_SIMILARES;
	}

	@Column(name = "PA_CABECA")
	public Boolean getPA_CABECA() {
		return PA_CABECA;
	}

	@Column(name = "PA_OLHOS")
	public Boolean getPA_OLHOS() {
		return PA_OLHOS;
	}

	@Column(name = "PA_PESCOCO")
	public Boolean getPA_PESCOCO() {
		return PA_PESCOCO;
	}

	@Column(name = "PA_COSTAS")
	public Boolean getPA_COSTAS() {
		return PA_COSTAS;
	}

	@Column(name = "PA_TORAX")
	public Boolean getPA_TORAX() {
		return PA_TORAX;
	}

	@Column(name = "PA_ABDOMEN")
	public Boolean getPA_ABDOMEN() {
		return PA_ABDOMEN;
	}

	@Column(name = "PA_OMBRO")
	public Boolean getPA_OMBRO() {
		return PA_OMBRO;
	}

	@Column(name = "PA_ANTEBRACO")
	public Boolean getPA_ANTEBRACO() {
		return PA_ANTEBRACO;
	}

	@Column(name = "PA_PE")
	public Boolean getPA_PE() {
		return PA_PE;
	}

	@Column(name = "PA_DEDOS_PE")
	public Boolean getPA_DEDOS_PE() {
		return PA_DEDOS_PE;
	}

	@Column(name = "PA_LOCALIZACOES_MULTIPLAS")
	public Boolean getPA_LOCALIZACOES_MULTIPLAS() {
		return PA_LOCALIZACOES_MULTIPLAS;
	}

	@Column(name = "PA_ANCA")
	public Boolean getPA_ANCA() {
		return PA_ANCA;
	}

	@Column(name = "PA_JOELHO")
	public Boolean getPA_JOELHO() {
		return PA_JOELHO;
	}

	@Column(name = "PA_MAO")
	public Boolean getPA_MAO() {
		return PA_MAO;
	}

	@Column(name = "PA_OUTRO")
	public Boolean getPA_OUTRO() {
		return PA_OUTRO;
	}

	@Column(name = "PA_OUTRO_TEXTO")
	public String getPA_OUTRO_TEXTO() {
		return PA_OUTRO_TEXTO;
	}

	@Column(name = "RECOLHA_EVIDENCIAS")
	public String getRECOLHA_EVIDENCIAS() {
		return RECOLHA_EVIDENCIAS;
	}

	@Column(name = "TESTEMUNHAS")
	public Boolean getTESTEMUNHAS() {
		return TESTEMUNHAS;
	}

	@Column(name = "NUMERO_TESTEMUNHAS")
	public Integer getNUMERO_TESTEMUNHAS() {
		return NUMERO_TESTEMUNHAS;
	}

	@Column(name = "MEDIDAS_FORMACAO")
	public String getMEDIDAS_FORMACAO() {
		return MEDIDAS_FORMACAO;
	}

	@Column(name = "MEDIDAS_ORGANIZACAO")
	public String getMEDIDAS_ORGANIZACAO() {
		return MEDIDAS_ORGANIZACAO;
	}

	@Column(name = "MEDIDAS_PROTECAO_COL")
	public String getMEDIDAS_PROTECAO_COL() {
		return MEDIDAS_PROTECAO_COL;
	}

	@Column(name = "MEDIDAS_PROTECAO_IND")
	public String getMEDIDAS_PROTECAO_IND() {
		return MEDIDAS_PROTECAO_IND;
	}

	@Column(name = "MEDIDAS_OUTRAS")
	public String getMEDIDAS_OUTRAS() {
		return MEDIDAS_OUTRAS;
	}

	@Column(name = "DIAGRAMA_TOP_ESQ")
	public String getDIAGRAMA_TOP_ESQ() {
		return DIAGRAMA_TOP_ESQ;
	}

	@Column(name = "DIAGRAMA_TOP_DIR")
	public String getDIAGRAMA_TOP_DIR() {
		return DIAGRAMA_TOP_DIR;
	}

	@Column(name = "DIAGRAMA_DIR")
	public String getDIAGRAMA_DIR() {
		return DIAGRAMA_DIR;
	}

	@Column(name = "DIAGRAMA_BT_ESQ")
	public String getDIAGRAMA_BT_ESQ() {
		return DIAGRAMA_BT_ESQ;
	}

	@Column(name = "DIAGRAMA_BT_DIR")
	public String getDIAGRAMA_BT_DIR() {
		return DIAGRAMA_BT_DIR;
	}

	@Column(name = "DESCRICAO_CAUSAS")
	public String getDESCRICAO_CAUSAS() {
		return DESCRICAO_CAUSAS;
	}

	@Column(name = "ANALISE_EFICACIA")
	public String getANALISE_EFICACIA() {
		return ANALISE_EFICACIA;
	}

	@Column(name = "EFICAZ")
	public Boolean getEFICAZ() {
		return EFICAZ;
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

	@Column(name = "ESTADO")
	public String getESTADO() {
		return ESTADO;
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

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

}
