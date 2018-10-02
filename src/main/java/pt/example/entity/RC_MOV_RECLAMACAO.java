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
@Table(name = "RC_MOV_RECLAMACAO")
public class RC_MOV_RECLAMACAO {
	private Integer ID_RECLAMACAO;
	private String TITULO;
	private String NUMERO_RECLAMACAO_CLIENTE;
	private Timestamp DATA_RECLAMACAO;
	private Integer REJEICAO;
	private Integer ID_TIPO_RECLAMACAO;
	private Integer UTZ_RESPONSAVEL;
	private Integer GRAU_IMPORTANCIA;
	private String RESPOSTA_INICIAL;
	private Integer ID_CLIENTE;
	private String NOME_CLIENTE;
	private String MORADA_CLIENTE;
	private String ETSNUM;
	private String CONTATO_CLIENTE;
	private String EMAIL_CLIENTE;
	private String TELEFONE_CLIENTE;
	private String REFERENCIA;
	private String DESIGNACAO_REF;
	private String FAMILIA_REF;
	private String LOTE;
	private Integer TIPO_DEFEITO;
	private Boolean RECLAMACAO_REVISTA;
	private Integer QTD_ENVIADA;
	private Integer QTD_RECUSADA;
	private Boolean DEVOLUCAO;
	private String OBSERVACOES_RECLAMACAO;
	private Boolean STEP1CONCLUIDO;
	private Boolean STEP2CONCLUIDO;
	private Boolean STEP3CONCLUIDO;
	private Boolean STEP4CONCLUIDO;
	private Boolean STEP5CONCLUIDO;
	private Boolean STEP6CONCLUIDO;
	private Boolean STEP7CONCLUIDO;
	private Boolean STEP8CONCLUIDO;
	private String DESCRICAO_PROBLEMA;
	private Boolean PROBLEMA_REPETIDO;
	private Integer NUMERO_RECLAMACAO_REPETIDA;
	private Boolean RECLAMACAO_REPETIDA_ACEITE;
	private Boolean ACCOES_EVITAR;
	private String OBSERVACOES_ACCOES_EVITAR;
	private String CAUSAS_PROBLEMA;
	private Timestamp DATA_PREVISTA_REPOSTA4;
	private Integer DIAS_RESPOSTA4;
	private Timestamp DATA_REAL_RESPOSTA4;
	private Integer DIAS_ATRASO4;
	private String RESPONSABILIDADE_ATRASO4;
	private String RESPONSABILIDADE_ATRASO4_DESCRICAO;
	private Boolean REF_IGUAIS;
	private Boolean SEGUIMENTO_FORNECEDORES;
	private Boolean AMDEC;
	private Boolean PLANO_VIGILANCIA;;
	private Boolean FORMACAO_OPERARIO;
	private Boolean PLANO_MANUTENCAO;
	private Boolean ACCOES_NECESSARIAS;
	private String ACCOES_NECESSARIAS_TEXTO;
	private Boolean RECLAMACAO_ENCERRADA;
	private Timestamp DATA_FECHO;
	private Timestamp DATA_PREVISTA_REPOSTA6;
	private Integer DIAS_RESPOSTA6;
	private Timestamp DATA_REAL_RESPOSTA6;
	private Integer DIAS_ATRASO6;
	private String RESPONSABILIDADE_ATRASO6;
	private String RESPONSABILIDADE_ATRASO6_DESCRICAO;
	private String OBSERVACOES_RESULTADOS;
	private String TIPO_CAMPO_LOTE;
	private Float CUSTOS_EXTERNA;
	private Float CUSTOS_INTERNA;
	private Float CUSTOS_DEVOLUCAO;
	private Float CUSTOS_OUTROS;
	private Float CUSTOS_TOTAL;
	private Float CUSTOS_INTERNA_QTD_CLASSIF;
	private Float CUSTOS_INTERNA_QTD_REJEITADA;
	private Float CUSTOS_REJEICAO_INTERNA;
	private Float CUSTOS_EXTERNA_QTD_CLASSIF;
	private Float CUSTOS_EXTERNA_QTD_REJEITADA;
	private Float CUSTOS_REJEICAO_EXTERNA;
	private String TIPO_RECLAMACAO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	
	private Integer STEP1CONCLUIDO_UTZ;
	private Timestamp STEP1CONCLUIDO_DATA;
	private Integer STEP2CONCLUIDO_UTZ;
	private Timestamp STEP2CONCLUIDO_DATA;
	private Integer STEP3CONCLUIDO_UTZ;
	private Timestamp STEP3CONCLUIDO_DATA;
	private Integer STEP4CONCLUIDO_UTZ;
	private Timestamp STEP4CONCLUIDO_DATA;
	private Integer STEP5CONCLUIDO_UTZ;
	private Timestamp STEP5CONCLUIDO_DATA;
	private Integer STEP6CONCLUIDO_UTZ;
	private Timestamp STEP6CONCLUIDO_DATA;
	private Integer STEP7CONCLUIDO_UTZ;
	private Timestamp STEP7CONCLUIDO_DATA;
	private Integer STEP8CONCLUIDO_UTZ;
	private Timestamp STEP8CONCLUIDO_DATA;

	private Timestamp STEP1CONCLUIDO_DATA_MOD;
	private Timestamp STEP2CONCLUIDO_DATA_MOD;
	private Timestamp STEP3CONCLUIDO_DATA_MOD;
	private Timestamp STEP4CONCLUIDO_DATA_MOD;
	private Timestamp STEP5CONCLUIDO_DATA_MOD;
	private Timestamp STEP6CONCLUIDO_DATA_MOD;
	private Timestamp STEP7CONCLUIDO_DATA_MOD;
	private Timestamp STEP8CONCLUIDO_DATA_MOD;

	@Id
	@Column(name = "ID_RECLAMACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	@Column(name = "TITULO")
	public String getTITULO() {
		return TITULO;
	}

	@Column(name = "NUMERO_RECLAMACAO_CLIENTE")
	public String getNUMERO_RECLAMACAO_CLIENTE() {
		return NUMERO_RECLAMACAO_CLIENTE;
	}

	@Column(name = "DATA_RECLAMACAO")
	public Timestamp getDATA_RECLAMACAO() {
		return DATA_RECLAMACAO;
	}

	@Column(name = "REJEICAO")
	public Integer getREJEICAO() {
		return REJEICAO;
	}

	@Column(name = "ID_TIPO_RECLAMACAO")
	public Integer getID_TIPO_RECLAMACAO() {
		return ID_TIPO_RECLAMACAO;
	}

	@Column(name = "UTZ_RESPONSAVEL")
	public Integer getUTZ_RESPONSAVEL() {
		return UTZ_RESPONSAVEL;
	}

	@Column(name = "GRAU_IMPORTANCIA")
	public Integer getGRAU_IMPORTANCIA() {
		return GRAU_IMPORTANCIA;
	}

	@Column(name = "RESPOSTA_INICIAL")
	public String getRESPOSTA_INICIAL() {
		return RESPOSTA_INICIAL;
	}

	@Column(name = "ID_CLIENTE")
	public Integer getID_CLIENTE() {
		return ID_CLIENTE;
	}

	@Column(name = "NOME_CLIENTE")
	public String getNOME_CLIENTE() {
		return NOME_CLIENTE;
	}

	@Column(name = "MORADA_CLIENTE")
	public String getMORADA_CLIENTE() {
		return MORADA_CLIENTE;
	}

	@Column(name = "CONTATO_CLIENTE")
	public String getCONTATO_CLIENTE() {
		return CONTATO_CLIENTE;
	}

	@Column(name = "EMAIL_CLIENTE")
	public String getEMAIL_CLIENTE() {
		return EMAIL_CLIENTE;
	}

	@Column(name = "TELEFONE_CLIENTE")
	public String getTELEFONE_CLIENTE() {
		return TELEFONE_CLIENTE;
	}

	@Column(name = "REFERENCIA")
	public String getREFERENCIA() {
		return REFERENCIA;
	}

	@Column(name = "DESIGNACAO_REF")
	public String getDESIGNACAO_REF() {
		return DESIGNACAO_REF;
	}

	@Column(name = "FAMILIA_REF")
	public String getFAMILIA_REF() {
		return FAMILIA_REF;
	}

	@Column(name = "LOTE")
	public String getLOTE() {
		return LOTE;
	}

	@Column(name = "TIPO_DEFEITO")
	public Integer getTIPO_DEFEITO() {
		return TIPO_DEFEITO;
	}

	@Column(name = "RECLAMACAO_REVISTA")
	public Boolean getRECLAMACAO_REVISTA() {
		return RECLAMACAO_REVISTA;
	}

	@Column(name = "QTD_ENVIADA")
	public Integer getQTD_ENVIADA() {
		return QTD_ENVIADA;
	}

	@Column(name = "QTD_RECUSADA")
	public Integer getQTD_RECUSADA() {
		return QTD_RECUSADA;
	}

	@Column(name = "DEVOLUCAO")
	public Boolean getDEVOLUCAO() {
		return DEVOLUCAO;
	}

	@Column(name = "OBSERVACOES_RECLAMACAO")
	public String getOBSERVACOES_RECLAMACAO() {
		return OBSERVACOES_RECLAMACAO;
	}

	@Column(name = "STEP1CONCLUIDO")
	public Boolean getSTEP1CONCLUIDO() {
		return STEP1CONCLUIDO;
	}

	@Column(name = "STEP2CONCLUIDO")
	public Boolean getSTEP2CONCLUIDO() {
		return STEP2CONCLUIDO;
	}

	@Column(name = "STEP3CONCLUIDO")
	public Boolean getSTEP3CONCLUIDO() {
		return STEP3CONCLUIDO;
	}

	@Column(name = "STEP4CONCLUIDO")
	public Boolean getSTEP4CONCLUIDO() {
		return STEP4CONCLUIDO;
	}

	@Column(name = "STEP5CONCLUIDO")
	public Boolean getSTEP5CONCLUIDO() {
		return STEP5CONCLUIDO;
	}

	@Column(name = "STEP6CONCLUIDO")
	public Boolean getSTEP6CONCLUIDO() {
		return STEP6CONCLUIDO;
	}

	@Column(name = "STEP7CONCLUIDO")
	public Boolean getSTEP7CONCLUIDO() {
		return STEP7CONCLUIDO;
	}

	@Column(name = "STEP8CONCLUIDO")
	public Boolean getSTEP8CONCLUIDO() {
		return STEP8CONCLUIDO;
	}

	@Column(name = "DESCRICAO_PROBLEMA")
	public String getDESCRICAO_PROBLEMA() {
		return DESCRICAO_PROBLEMA;
	}

	@Column(name = "PROBLEMA_REPETIDO")
	public Boolean getPROBLEMA_REPETIDO() {
		return PROBLEMA_REPETIDO;
	}

	@Column(name = "NUMERO_RECLAMACAO_REPETIDA")
	public Integer getNUMERO_RECLAMACAO_REPETIDA() {
		return NUMERO_RECLAMACAO_REPETIDA;
	}

	@Column(name = "RECLAMACAO_REPETIDA_ACEITE")
	public Boolean getRECLAMACAO_REPETIDA_ACEITE() {
		return RECLAMACAO_REPETIDA_ACEITE;
	}

	@Column(name = "ACCOES_EVITAR")
	public Boolean getACCOES_EVITAR() {
		return ACCOES_EVITAR;
	}

	@Column(name = "OBSERVACOES_ACCOES_EVITAR")
	public String getOBSERVACOES_ACCOES_EVITAR() {
		return OBSERVACOES_ACCOES_EVITAR;
	}

	@Column(name = "CAUSAS_PROBLEMA")
	public String getCAUSAS_PROBLEMA() {
		return CAUSAS_PROBLEMA;
	}

	@Column(name = "DATA_PREVISTA_REPOSTA4")
	public Timestamp getDATA_PREVISTA_REPOSTA4() {
		return DATA_PREVISTA_REPOSTA4;
	}

	@Column(name = "DIAS_RESPOSTA4")
	public Integer getDIAS_RESPOSTA4() {
		return DIAS_RESPOSTA4;
	}

	@Column(name = "DATA_REAL_RESPOSTA4")
	public Timestamp getDATA_REAL_RESPOSTA4() {
		return DATA_REAL_RESPOSTA4;
	}

	@Column(name = "DIAS_ATRASO4")
	public Integer getDIAS_ATRASO4() {
		return DIAS_ATRASO4;
	}

	@Column(name = "RESPONSABILIDADE_ATRASO4")
	public String getRESPONSABILIDADE_ATRASO4() {
		return RESPONSABILIDADE_ATRASO4;
	}

	@Column(name = "RESPONSABILIDADE_ATRASO4_DESCRICAO")
	public String getRESPONSABILIDADE_ATRASO4_DESCRICAO() {
		return RESPONSABILIDADE_ATRASO4_DESCRICAO;
	}

	@Column(name = "ACCOES_NECESSARIAS")
	public Boolean getACCOES_NECESSARIAS() {
		return ACCOES_NECESSARIAS;
	}

	@Column(name = "ACCOES_NECESSARIAS_TEXTO")
	public String getACCOES_NECESSARIAS_TEXTO() {
		return ACCOES_NECESSARIAS_TEXTO;
	}

	@Column(name = "RECLAMACAO_ENCERRADA")
	public Boolean getRECLAMACAO_ENCERRADA() {
		return RECLAMACAO_ENCERRADA;
	}

	@Column(name = "DATA_FECHO")
	public Timestamp getDATA_FECHO() {
		return DATA_FECHO;
	}

	@Column(name = "DATA_PREVISTA_REPOSTA6")
	public Timestamp getDATA_PREVISTA_REPOSTA6() {
		return DATA_PREVISTA_REPOSTA6;
	}

	@Column(name = "DIAS_RESPOSTA6")
	public Integer getDIAS_RESPOSTA6() {
		return DIAS_RESPOSTA6;
	}

	@Column(name = "DATA_REAL_RESPOSTA6")
	public Timestamp getDATA_REAL_RESPOSTA6() {
		return DATA_REAL_RESPOSTA6;
	}

	@Column(name = "DIAS_ATRASO6")
	public Integer getDIAS_ATRASO6() {
		return DIAS_ATRASO6;
	}

	@Column(name = "RESPONSABILIDADE_ATRASO6")
	public String getRESPONSABILIDADE_ATRASO6() {
		return RESPONSABILIDADE_ATRASO6;
	}

	@Column(name = "RESPONSABILIDADE_ATRASO6_DESCRICAO")
	public String getRESPONSABILIDADE_ATRASO6_DESCRICAO() {
		return RESPONSABILIDADE_ATRASO6_DESCRICAO;
	}

	@Column(name = "OBSERVACOES_RESULTADOS")
	public String getOBSERVACOES_RESULTADOS() {
		return OBSERVACOES_RESULTADOS;
	}

	@Column(name = "CUSTOS_EXTERNA")
	public Float getCUSTOS_EXTERNA() {
		return CUSTOS_EXTERNA;
	}

	@Column(name = "CUSTOS_INTERNA")
	public Float getCUSTOS_INTERNA() {
		return CUSTOS_INTERNA;
	}

	@Column(name = "CUSTOS_DEVOLUCAO")
	public Float getCUSTOS_DEVOLUCAO() {
		return CUSTOS_DEVOLUCAO;
	}

	@Column(name = "CUSTOS_OUTROS")
	public Float getCUSTOS_OUTROS() {
		return CUSTOS_OUTROS;
	}

	@Column(name = "CUSTOS_TOTAL")
	public Float getCUSTOS_TOTAL() {
		return CUSTOS_TOTAL;
	}

	@Column(name = "CUSTOS_INTERNA_QTD_CLASSIF")
	public Float getCUSTOS_INTERNA_QTD_CLASSIF() {
		return CUSTOS_INTERNA_QTD_CLASSIF;
	}

	@Column(name = "CUSTOS_INTERNA_QTD_REJEITADA")
	public Float getCUSTOS_INTERNA_QTD_REJEITADA() {
		return CUSTOS_INTERNA_QTD_REJEITADA;
	}

	@Column(name = "CUSTOS_REJEICAO_INTERNA")
	public Float getCUSTOS_REJEICAO_INTERNA() {
		return CUSTOS_REJEICAO_INTERNA;
	}

	@Column(name = "CUSTOS_EXTERNA_QTD_CLASSIF")
	public Float getCUSTOS_EXTERNA_QTD_CLASSIF() {
		return CUSTOS_EXTERNA_QTD_CLASSIF;
	}

	@Column(name = "CUSTOS_EXTERNA_QTD_REJEITADA")
	public Float getCUSTOS_EXTERNA_QTD_REJEITADA() {
		return CUSTOS_EXTERNA_QTD_REJEITADA;
	}

	@Column(name = "CUSTOS_REJEICAO_EXTERNA")
	public Float getCUSTOS_REJEICAO_EXTERNA() {
		return CUSTOS_REJEICAO_EXTERNA;
	}

	@Column(name = "TIPO_RECLAMACAO")
	public String getTIPO_RECLAMACAO() {
		return TIPO_RECLAMACAO;
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

	@Column(name = "REF_IGUAIS")
	public Boolean getREF_IGUAIS() {
		return REF_IGUAIS;
	}

	@Column(name = "SEGUIMENTO_FORNECEDORES")
	public Boolean getSEGUIMENTO_FORNECEDORES() {
		return SEGUIMENTO_FORNECEDORES;
	}

	@Column(name = "AMDEC")
	public Boolean getAMDEC() {
		return AMDEC;
	}

	@Column(name = "PLANO_VIGILANCIA")
	public Boolean getPLANO_VIGILANCIA() {
		return PLANO_VIGILANCIA;
	}

	@Column(name = "FORMACAO_OPERARIO")
	public Boolean getFORMACAO_OPERARIO() {
		return FORMACAO_OPERARIO;
	}

	@Column(name = "PLANO_MANUTENCAO")
	public Boolean getPLANO_MANUTENCAO() {
		return PLANO_MANUTENCAO;
	}

	@Column(name = "STEP1CONCLUIDO_UTZ")
	public Integer getSTEP1CONCLUIDO_UTZ() {
		return STEP1CONCLUIDO_UTZ;
	}

	@Column(name = "STEP1CONCLUIDO_DATA")
	public Timestamp getSTEP1CONCLUIDO_DATA() {
		return STEP1CONCLUIDO_DATA;
	}

	@Column(name = "STEP2CONCLUIDO_UTZ")
	public Integer getSTEP2CONCLUIDO_UTZ() {
		return STEP2CONCLUIDO_UTZ;
	}

	@Column(name = "STEP2CONCLUIDO_DATA")
	public Timestamp getSTEP2CONCLUIDO_DATA() {
		return STEP2CONCLUIDO_DATA;
	}

	@Column(name = "STEP3CONCLUIDO_UTZ")
	public Integer getSTEP3CONCLUIDO_UTZ() {
		return STEP3CONCLUIDO_UTZ;
	}

	@Column(name = "STEP3CONCLUIDO_DATA")
	public Timestamp getSTEP3CONCLUIDO_DATA() {
		return STEP3CONCLUIDO_DATA;
	}

	@Column(name = "STEP4CONCLUIDO_UTZ")
	public Integer getSTEP4CONCLUIDO_UTZ() {
		return STEP4CONCLUIDO_UTZ;
	}

	@Column(name = "STEP4CONCLUIDO_DATA")
	public Timestamp getSTEP4CONCLUIDO_DATA() {
		return STEP4CONCLUIDO_DATA;
	}

	@Column(name = "STEP5CONCLUIDO_UTZ")
	public Integer getSTEP5CONCLUIDO_UTZ() {
		return STEP5CONCLUIDO_UTZ;
	}

	@Column(name = "STEP5CONCLUIDO_DATA")
	public Timestamp getSTEP5CONCLUIDO_DATA() {
		return STEP5CONCLUIDO_DATA;
	}

	@Column(name = "STEP6CONCLUIDO_UTZ")
	public Integer getSTEP6CONCLUIDO_UTZ() {
		return STEP6CONCLUIDO_UTZ;
	}

	@Column(name = "STEP6CONCLUIDO_DATA")
	public Timestamp getSTEP6CONCLUIDO_DATA() {
		return STEP6CONCLUIDO_DATA;
	}

	@Column(name = "STEP7CONCLUIDO_UTZ")
	public Integer getSTEP7CONCLUIDO_UTZ() {
		return STEP7CONCLUIDO_UTZ;
	}

	@Column(name = "STEP7CONCLUIDO_DATA")
	public Timestamp getSTEP7CONCLUIDO_DATA() {
		return STEP7CONCLUIDO_DATA;
	}

	@Column(name = "STEP8CONCLUIDO_UTZ")
	public Integer getSTEP8CONCLUIDO_UTZ() {
		return STEP8CONCLUIDO_UTZ;
	}

	@Column(name = "STEP8CONCLUIDO_DATA")
	public Timestamp getSTEP8CONCLUIDO_DATA() {
		return STEP8CONCLUIDO_DATA;
	}

	@Column(name = "ETSNUM")
	public String getETSNUM() {
		return ETSNUM;
	}

	@Column(name = "STEP1CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP1CONCLUIDO_DATA_MOD() {
		return STEP1CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP2CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP2CONCLUIDO_DATA_MOD() {
		return STEP2CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP3CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP3CONCLUIDO_DATA_MOD() {
		return STEP3CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP4CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP4CONCLUIDO_DATA_MOD() {
		return STEP4CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP5CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP5CONCLUIDO_DATA_MOD() {
		return STEP5CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP6CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP6CONCLUIDO_DATA_MOD() {
		return STEP6CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP7CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP7CONCLUIDO_DATA_MOD() {
		return STEP7CONCLUIDO_DATA_MOD;
	}

	@Column(name = "STEP8CONCLUIDO_DATA_MOD")
	public Timestamp getSTEP8CONCLUIDO_DATA_MOD() {
		return STEP8CONCLUIDO_DATA_MOD;
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

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setSTEP1CONCLUIDO_DATA_MOD(Timestamp sTEP1CONCLUIDO_DATA_MOD) {
		STEP1CONCLUIDO_DATA_MOD = sTEP1CONCLUIDO_DATA_MOD;
	}

	public void setSTEP2CONCLUIDO_DATA_MOD(Timestamp sTEP2CONCLUIDO_DATA_MOD) {
		STEP2CONCLUIDO_DATA_MOD = sTEP2CONCLUIDO_DATA_MOD;
	}

	public void setSTEP3CONCLUIDO_DATA_MOD(Timestamp sTEP3CONCLUIDO_DATA_MOD) {
		STEP3CONCLUIDO_DATA_MOD = sTEP3CONCLUIDO_DATA_MOD;
	}

	public void setSTEP4CONCLUIDO_DATA_MOD(Timestamp sTEP4CONCLUIDO_DATA_MOD) {
		STEP4CONCLUIDO_DATA_MOD = sTEP4CONCLUIDO_DATA_MOD;
	}

	public void setSTEP5CONCLUIDO_DATA_MOD(Timestamp sTEP5CONCLUIDO_DATA_MOD) {
		STEP5CONCLUIDO_DATA_MOD = sTEP5CONCLUIDO_DATA_MOD;
	}

	public void setSTEP6CONCLUIDO_DATA_MOD(Timestamp sTEP6CONCLUIDO_DATA_MOD) {
		STEP6CONCLUIDO_DATA_MOD = sTEP6CONCLUIDO_DATA_MOD;
	}

	public void setSTEP7CONCLUIDO_DATA_MOD(Timestamp sTEP7CONCLUIDO_DATA_MOD) {
		STEP7CONCLUIDO_DATA_MOD = sTEP7CONCLUIDO_DATA_MOD;
	}

	public void setSTEP8CONCLUIDO_DATA_MOD(Timestamp sTEP8CONCLUIDO_DATA_MOD) {
		STEP8CONCLUIDO_DATA_MOD = sTEP8CONCLUIDO_DATA_MOD;
	}

	@Column(name = "TIPO_CAMPO_LOTE")
	public String getTIPO_CAMPO_LOTE() {
		return TIPO_CAMPO_LOTE;
	}

	public void setTIPO_CAMPO_LOTE(String tIPO_CAMPO_LOTE) {
		TIPO_CAMPO_LOTE = tIPO_CAMPO_LOTE;
	}

	public void setETSNUM(String eTSNUM) {
		ETSNUM = eTSNUM;
	}

	public void setSTEP1CONCLUIDO_UTZ(Integer sTEP1CONCLUIDO_UTZ) {
		STEP1CONCLUIDO_UTZ = sTEP1CONCLUIDO_UTZ;
	}

	public void setSTEP1CONCLUIDO_DATA(Timestamp sTEP1CONCLUIDO_DATA) {
		STEP1CONCLUIDO_DATA = sTEP1CONCLUIDO_DATA;
	}

	public void setSTEP2CONCLUIDO_UTZ(Integer sTEP2CONCLUIDO_UTZ) {
		STEP2CONCLUIDO_UTZ = sTEP2CONCLUIDO_UTZ;
	}

	public void setSTEP2CONCLUIDO_DATA(Timestamp sTEP2CONCLUIDO_DATA) {
		STEP2CONCLUIDO_DATA = sTEP2CONCLUIDO_DATA;
	}

	public void setSTEP3CONCLUIDO_UTZ(Integer sTEP3CONCLUIDO_UTZ) {
		STEP3CONCLUIDO_UTZ = sTEP3CONCLUIDO_UTZ;
	}

	public void setSTEP3CONCLUIDO_DATA(Timestamp sTEP3CONCLUIDO_DATA) {
		STEP3CONCLUIDO_DATA = sTEP3CONCLUIDO_DATA;
	}

	public void setSTEP4CONCLUIDO_UTZ(Integer sTEP4CONCLUIDO_UTZ) {
		STEP4CONCLUIDO_UTZ = sTEP4CONCLUIDO_UTZ;
	}

	public void setSTEP4CONCLUIDO_DATA(Timestamp sTEP4CONCLUIDO_DATA) {
		STEP4CONCLUIDO_DATA = sTEP4CONCLUIDO_DATA;
	}

	public void setSTEP5CONCLUIDO_UTZ(Integer sTEP5CONCLUIDO_UTZ) {
		STEP5CONCLUIDO_UTZ = sTEP5CONCLUIDO_UTZ;
	}

	public void setSTEP5CONCLUIDO_DATA(Timestamp sTEP5CONCLUIDO_DATA) {
		STEP5CONCLUIDO_DATA = sTEP5CONCLUIDO_DATA;
	}

	public void setSTEP6CONCLUIDO_UTZ(Integer sTEP6CONCLUIDO_UTZ) {
		STEP6CONCLUIDO_UTZ = sTEP6CONCLUIDO_UTZ;
	}

	public void setSTEP6CONCLUIDO_DATA(Timestamp sTEP6CONCLUIDO_DATA) {
		STEP6CONCLUIDO_DATA = sTEP6CONCLUIDO_DATA;
	}

	public void setSTEP7CONCLUIDO_UTZ(Integer sTEP7CONCLUIDO_UTZ) {
		STEP7CONCLUIDO_UTZ = sTEP7CONCLUIDO_UTZ;
	}

	public void setSTEP7CONCLUIDO_DATA(Timestamp sTEP7CONCLUIDO_DATA) {
		STEP7CONCLUIDO_DATA = sTEP7CONCLUIDO_DATA;
	}

	public void setSTEP8CONCLUIDO_UTZ(Integer sTEP8CONCLUIDO_UTZ) {
		STEP8CONCLUIDO_UTZ = sTEP8CONCLUIDO_UTZ;
	}

	public void setSTEP8CONCLUIDO_DATA(Timestamp sTEP8CONCLUIDO_DATA) {
		STEP8CONCLUIDO_DATA = sTEP8CONCLUIDO_DATA;
	}

	public void setREF_IGUAIS(Boolean rEF_IGUAIS) {
		this.REF_IGUAIS = rEF_IGUAIS;
	}

	public void setSEGUIMENTO_FORNECEDORES(Boolean sEGUIMENTO_FORNECEDORES) {
		this.SEGUIMENTO_FORNECEDORES = sEGUIMENTO_FORNECEDORES;
	}

	public void setAMDEC(Boolean aMDEC) {
		this.AMDEC = aMDEC;
	}

	public void setPLANO_VIGILANCIA(Boolean PLANO_VIGILANCIA) {
		this.PLANO_VIGILANCIA = PLANO_VIGILANCIA;
	}

	public void setFORMACAO_OPERARIO(Boolean fORMACAO_OPERARIO) {
		this.FORMACAO_OPERARIO = fORMACAO_OPERARIO;
	}

	public void setPLANO_MANUTENCAO(Boolean pLANO_MANUTENCAO) {
		this.PLANO_MANUTENCAO = pLANO_MANUTENCAO;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setTITULO(String tITULO) {
		TITULO = tITULO;
	}

	public void setNUMERO_RECLAMACAO_CLIENTE(String nUMERO_RECLAMACAO_CLIENTE) {
		NUMERO_RECLAMACAO_CLIENTE = nUMERO_RECLAMACAO_CLIENTE;
	}

	public void setDATA_RECLAMACAO(Timestamp dATA_RECLAMACAO) {
		DATA_RECLAMACAO = dATA_RECLAMACAO;
	}

	public void setREJEICAO(Integer rEJEICAO) {
		REJEICAO = rEJEICAO;
	}

	public void setID_TIPO_RECLAMACAO(Integer iD_TIPO_RECLAMACAO) {
		ID_TIPO_RECLAMACAO = iD_TIPO_RECLAMACAO;
	}

	public void setUTZ_RESPONSAVEL(Integer uTZ_RESPONSAVEL) {
		UTZ_RESPONSAVEL = uTZ_RESPONSAVEL;
	}

	public void setGRAU_IMPORTANCIA(Integer gRAU_IMPORTANCIA) {
		GRAU_IMPORTANCIA = gRAU_IMPORTANCIA;
	}

	public void setRESPOSTA_INICIAL(String rESPOSTA_INICIAL) {
		RESPOSTA_INICIAL = rESPOSTA_INICIAL;
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

	public void setCONTATO_CLIENTE(String cONTATO_CLIENTE) {
		CONTATO_CLIENTE = cONTATO_CLIENTE;
	}

	public void setEMAIL_CLIENTE(String eMAIL_CLIENTE) {
		EMAIL_CLIENTE = eMAIL_CLIENTE;
	}

	public void setTELEFONE_CLIENTE(String tELEFONE_CLIENTE) {
		TELEFONE_CLIENTE = tELEFONE_CLIENTE;
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

	public void setLOTE(String lOTE) {
		LOTE = lOTE;
	}

	public void setTIPO_DEFEITO(Integer tIPO_DEFEITO) {
		TIPO_DEFEITO = tIPO_DEFEITO;
	}

	public void setRECLAMACAO_REVISTA(Boolean rECLAMACAO_REVISTA) {
		RECLAMACAO_REVISTA = rECLAMACAO_REVISTA;
	}

	public void setQTD_ENVIADA(Integer qTD_ENVIADA) {
		QTD_ENVIADA = qTD_ENVIADA;
	}

	public void setQTD_RECUSADA(Integer qTD_RECUSADA) {
		QTD_RECUSADA = qTD_RECUSADA;
	}

	public void setDEVOLUCAO(Boolean dEVOLUCAO) {
		DEVOLUCAO = dEVOLUCAO;
	}

	public void setOBSERVACOES_RECLAMACAO(String oBSERVACOES_RECLAMACAO) {
		OBSERVACOES_RECLAMACAO = oBSERVACOES_RECLAMACAO;
	}

	public void setSTEP1CONCLUIDO(Boolean sTEP1CONCLUIDO) {
		STEP1CONCLUIDO = sTEP1CONCLUIDO;
	}

	public void setSTEP2CONCLUIDO(Boolean sTEP2CONCLUIDO) {
		STEP2CONCLUIDO = sTEP2CONCLUIDO;
	}

	public void setSTEP3CONCLUIDO(Boolean sTEP3CONCLUIDO) {
		STEP3CONCLUIDO = sTEP3CONCLUIDO;
	}

	public void setSTEP4CONCLUIDO(Boolean sTEP4CONCLUIDO) {
		STEP4CONCLUIDO = sTEP4CONCLUIDO;
	}

	public void setSTEP5CONCLUIDO(Boolean sTEP5CONCLUIDO) {
		STEP5CONCLUIDO = sTEP5CONCLUIDO;
	}

	public void setSTEP6CONCLUIDO(Boolean sTEP6CONCLUIDO) {
		STEP6CONCLUIDO = sTEP6CONCLUIDO;
	}

	public void setSTEP7CONCLUIDO(Boolean sTEP7CONCLUIDO) {
		STEP7CONCLUIDO = sTEP7CONCLUIDO;
	}

	public void setSTEP8CONCLUIDO(Boolean sTEP8CONCLUIDO) {
		STEP8CONCLUIDO = sTEP8CONCLUIDO;
	}

	public void setDESCRICAO_PROBLEMA(String dESCRICAO_PROBLEMA) {
		DESCRICAO_PROBLEMA = dESCRICAO_PROBLEMA;
	}

	public void setPROBLEMA_REPETIDO(Boolean pROBLEMA_REPETIDO) {
		PROBLEMA_REPETIDO = pROBLEMA_REPETIDO;
	}

	public void setNUMERO_RECLAMACAO_REPETIDA(Integer nUMERO_RECLAMACAO_REPETIDA) {
		NUMERO_RECLAMACAO_REPETIDA = nUMERO_RECLAMACAO_REPETIDA;
	}

	public void setRECLAMACAO_REPETIDA_ACEITE(Boolean rECLAMACAO_REPETIDA_ACEITE) {
		RECLAMACAO_REPETIDA_ACEITE = rECLAMACAO_REPETIDA_ACEITE;
	}

	public void setACCOES_EVITAR(Boolean aCCOES_EVITAR) {
		ACCOES_EVITAR = aCCOES_EVITAR;
	}

	public void setOBSERVACOES_ACCOES_EVITAR(String oBSERVACOES_ACCOES_EVITAR) {
		OBSERVACOES_ACCOES_EVITAR = oBSERVACOES_ACCOES_EVITAR;
	}

	public void setCAUSAS_PROBLEMA(String cAUSAS_PROBLEMA) {
		CAUSAS_PROBLEMA = cAUSAS_PROBLEMA;
	}

	public void setDATA_PREVISTA_REPOSTA4(Timestamp dATA_PREVISTA_REPOSTA4) {
		DATA_PREVISTA_REPOSTA4 = dATA_PREVISTA_REPOSTA4;
	}

	public void setDIAS_RESPOSTA4(Integer dIAS_RESPOSTA4) {
		DIAS_RESPOSTA4 = dIAS_RESPOSTA4;
	}

	public void setDATA_REAL_RESPOSTA4(Timestamp dATA_REAL_RESPOSTA4) {
		DATA_REAL_RESPOSTA4 = dATA_REAL_RESPOSTA4;
	}

	public void setDIAS_ATRASO4(Integer dIAS_ATRASO4) {
		DIAS_ATRASO4 = dIAS_ATRASO4;
	}

	public void setRESPONSABILIDADE_ATRASO4(String rESPONSABILIDADE_ATRASO4) {
		RESPONSABILIDADE_ATRASO4 = rESPONSABILIDADE_ATRASO4;
	}

	public void setRESPONSABILIDADE_ATRASO4_DESCRICAO(String rESPONSABILIDADE_ATRASO4_DESCRICAO) {
		RESPONSABILIDADE_ATRASO4_DESCRICAO = rESPONSABILIDADE_ATRASO4_DESCRICAO;
	}

	public void setACCOES_NECESSARIAS(Boolean aCCOES_NECESSARIAS) {
		ACCOES_NECESSARIAS = aCCOES_NECESSARIAS;
	}

	public void setACCOES_NECESSARIAS_TEXTO(String aCCOES_NECESSARIAS_TEXTO) {
		ACCOES_NECESSARIAS_TEXTO = aCCOES_NECESSARIAS_TEXTO;
	}

	public void setRECLAMACAO_ENCERRADA(Boolean rECLAMACAO_ENCERRADA) {
		RECLAMACAO_ENCERRADA = rECLAMACAO_ENCERRADA;
	}

	public void setDATA_FECHO(Timestamp dATA_FECHO) {
		DATA_FECHO = dATA_FECHO;
	}

	public void setDATA_PREVISTA_REPOSTA6(Timestamp dATA_PREVISTA_REPOSTA6) {
		DATA_PREVISTA_REPOSTA6 = dATA_PREVISTA_REPOSTA6;
	}

	public void setDIAS_RESPOSTA6(Integer dIAS_RESPOSTA6) {
		DIAS_RESPOSTA6 = dIAS_RESPOSTA6;
	}

	public void setDATA_REAL_RESPOSTA6(Timestamp dATA_REAL_RESPOSTA6) {
		DATA_REAL_RESPOSTA6 = dATA_REAL_RESPOSTA6;
	}

	public void setDIAS_ATRASO6(Integer dIAS_ATRASO6) {
		DIAS_ATRASO6 = dIAS_ATRASO6;
	}

	public void setRESPONSABILIDADE_ATRASO6(String rESPONSABILIDADE_ATRASO6) {
		RESPONSABILIDADE_ATRASO6 = rESPONSABILIDADE_ATRASO6;
	}

	public void setRESPONSABILIDADE_ATRASO6_DESCRICAO(String rESPONSABILIDADE_ATRASO6_DESCRICAO) {
		RESPONSABILIDADE_ATRASO6_DESCRICAO = rESPONSABILIDADE_ATRASO6_DESCRICAO;
	}

	public void setOBSERVACOES_RESULTADOS(String oBSERVACOES_RESULTADOS) {
		OBSERVACOES_RESULTADOS = oBSERVACOES_RESULTADOS;
	}

	public void setCUSTOS_EXTERNA(Float cUSTOS_EXTERNA) {
		CUSTOS_EXTERNA = cUSTOS_EXTERNA;
	}

	public void setCUSTOS_INTERNA(Float cUSTOS_INTERNA) {
		CUSTOS_INTERNA = cUSTOS_INTERNA;
	}

	public void setCUSTOS_DEVOLUCAO(Float cUSTOS_DEVOLUCAO) {
		CUSTOS_DEVOLUCAO = cUSTOS_DEVOLUCAO;
	}

	public void setCUSTOS_OUTROS(Float cUSTOS_OUTROS) {
		CUSTOS_OUTROS = cUSTOS_OUTROS;
	}

	public void setCUSTOS_TOTAL(Float cUSTOS_TOTAL) {
		CUSTOS_TOTAL = cUSTOS_TOTAL;
	}

	public void setCUSTOS_INTERNA_QTD_CLASSIF(Float cUSTOS_INTERNA_QTD_CLASSIF) {
		CUSTOS_INTERNA_QTD_CLASSIF = cUSTOS_INTERNA_QTD_CLASSIF;
	}

	public void setCUSTOS_INTERNA_QTD_REJEITADA(Float cUSTOS_INTERNA_QTD_REJEITADA) {
		CUSTOS_INTERNA_QTD_REJEITADA = cUSTOS_INTERNA_QTD_REJEITADA;
	}

	public void setCUSTOS_REJEICAO_INTERNA(Float cUSTOS_REJEICAO_INTERNA) {
		CUSTOS_REJEICAO_INTERNA = cUSTOS_REJEICAO_INTERNA;
	}

	public void setCUSTOS_EXTERNA_QTD_CLASSIF(Float cUSTOS_EXTERNA_QTD_CLASSIF) {
		CUSTOS_EXTERNA_QTD_CLASSIF = cUSTOS_EXTERNA_QTD_CLASSIF;
	}

	public void setCUSTOS_EXTERNA_QTD_REJEITADA(Float cUSTOS_EXTERNA_QTD_REJEITADA) {
		CUSTOS_EXTERNA_QTD_REJEITADA = cUSTOS_EXTERNA_QTD_REJEITADA;
	}

	public void setCUSTOS_REJEICAO_EXTERNA(Float cUSTOS_REJEICAO_EXTERNA) {
		CUSTOS_REJEICAO_EXTERNA = cUSTOS_REJEICAO_EXTERNA;
	}

	public void setTIPO_RECLAMACAO(String tIPO_RECLAMACAO) {
		TIPO_RECLAMACAO = tIPO_RECLAMACAO;
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

}
