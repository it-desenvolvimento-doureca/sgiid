package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GT_MOV_TAREFAS")
public class GT_MOV_TAREFAS {

	private Integer ID_TAREFA;
	private Integer ID_MODULO;
	private Integer ID_CAMPO;
	private String ESTADO;
	private Timestamp DATA_INICIO;
	private Timestamp DATA_FIM;
	private Timestamp DATA_CONCLUSAO;
	private Integer UTZ_CONCLUSAO;
	private String OBSERVACOES;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Integer UTZ_ID;
	private String UTZ_TIPO;
	private Integer ID_ACCAO;
	private Integer UTZ_ENCAMINHADO;
	private String SUB_MODULO;
	private Timestamp DATA_ENCAMINHADO;
	private Integer UTZ_ENCAMINHOU;
	private Integer PRIORIDADE;
	private Float PERCENTAGEM_CONCLUSAO;
	private String DESCRICAO;
	private Float TEMPO_GASTO;
	private Timestamp DATA_REJEITA;
	private Integer UTZ_REJEITA;
	private String MOTIVO_REJEICAO;

	private Timestamp DATA_VALIDA;
	private Integer UTZ_VALIDA;
	private Timestamp DATA_CONTROLA;
	private Integer UTZ_CONTROLA;
	private String JUSTIFICACAO_ALTERACAO_ESTADO;
	
	@Id
	@Column(name = "ID_TAREFA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TAREFA() {
		return ID_TAREFA;
	}

	@Column(name = "ID_MODULO")
	public Integer getID_MODULO() {
		return ID_MODULO;
	}

	@Column(name = "ID_CAMPO")
	public Integer getID_CAMPO() {
		return ID_CAMPO;
	}

	@Column(name = "ESTADO")
	public String getESTADO() {
		return ESTADO;
	}

	@Column(name = "DATA_INICIO")
	public Timestamp getDATA_INICIO() {
		return DATA_INICIO;
	}

	@Column(name = "DATA_FIM")
	public Timestamp getDATA_FIM() {
		return DATA_FIM;
	}

	@Column(name = "DATA_CONCLUSAO")
	public Timestamp getDATA_CONCLUSAO() {
		return DATA_CONCLUSAO;
	}

	@Column(name = "UTZ_CONCLUSAO")
	public Integer getUTZ_CONCLUSAO() {
		return UTZ_CONCLUSAO;
	}

	@Column(name = "OBSERVACOES")
	public String getOBSERVACOES() {
		return OBSERVACOES;
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

	@Column(name = "UTZ_ID")
	public Integer getUTZ_ID() {
		return UTZ_ID;
	}

	@Column(name = "UTZ_TIPO")
	public String getUTZ_TIPO() {
		return UTZ_TIPO;
	}

	@Column(name = "ID_ACCAO")
	public Integer getID_ACCAO() {
		return ID_ACCAO;
	}

	@Column(name = "UTZ_ENCAMINHADO")
	public Integer getUTZ_ENCAMINHADO() {
		return UTZ_ENCAMINHADO;
	}

	@Column(name = "SUB_MODULO")
	public String getSUB_MODULO() {
		return SUB_MODULO;
	}

	@Column(name = "DATA_ENCAMINHADO")
	public Timestamp getDATA_ENCAMINHADO() {
		return DATA_ENCAMINHADO;
	}

	@Column(name = "UTZ_ENCAMINHOU")
	public Integer getUTZ_ENCAMINHOU() {
		return UTZ_ENCAMINHOU;
	}

	@Column(name = "PRIORIDADE")
	public Integer getPRIORIDADE() {
		return PRIORIDADE;
	}

	@Column(name = "PERCENTAGEM_CONCLUSAO")
	public Float getPERCENTAGEM_CONCLUSAO() {
		return PERCENTAGEM_CONCLUSAO;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "TEMPO_GASTO")
	public Float getTEMPO_GASTO() {
		return TEMPO_GASTO;
	}

	@Column(name = "DATA_REJEITA")
	public Timestamp getDATA_REJEITA() {
		return DATA_REJEITA;
	}

	@Column(name = "UTZ_REJEITA")
	public Integer getUTZ_REJEITA() {
		return UTZ_REJEITA;
	}

	@Column(name = "MOTIVO_REJEICAO")
	public String getMOTIVO_REJEICAO() {
		return MOTIVO_REJEICAO;
	}

	public void setMOTIVO_REJEICAO(String mOTIVO_REJEICAO) {
		MOTIVO_REJEICAO = mOTIVO_REJEICAO;
	}

	public void setDATA_REJEITA(Timestamp dATA_REJEITA) {
		DATA_REJEITA = dATA_REJEITA;
	}

	public void setUTZ_REJEITA(Integer uTZ_REJEITA) {
		UTZ_REJEITA = uTZ_REJEITA;
	}

	public void setPERCENTAGEM_CONCLUSAO(Float pERCENTAGEM_CONCLUSAO) {
		PERCENTAGEM_CONCLUSAO = pERCENTAGEM_CONCLUSAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setTEMPO_GASTO(Float tEMPO_GASTO) {
		TEMPO_GASTO = tEMPO_GASTO;
	}

	public void setDATA_ENCAMINHADO(Timestamp dATA_ENCAMINHADO) {
		DATA_ENCAMINHADO = dATA_ENCAMINHADO;
	}

	public void setUTZ_ENCAMINHOU(Integer uTZ_ENCAMINHOU) {
		UTZ_ENCAMINHOU = uTZ_ENCAMINHOU;
	}

	public void setPRIORIDADE(Integer pRIORIDADE) {
		PRIORIDADE = pRIORIDADE;
	}

	public void setUTZ_ENCAMINHADO(Integer uTZ_ENCAMINHADO) {
		UTZ_ENCAMINHADO = uTZ_ENCAMINHADO;
	}

	public void setSUB_MODULO(String sUB_MODULO) {
		SUB_MODULO = sUB_MODULO;
	}

	public void setID_ACCAO(Integer iD_ACCAO) {
		ID_ACCAO = iD_ACCAO;
	}

	public void setUTZ_ID(Integer uTZ_ID) {
		UTZ_ID = uTZ_ID;
	}

	public void setUTZ_TIPO(String uTZ_TIPO) {
		UTZ_TIPO = uTZ_TIPO;
	}

	public void setID_TAREFA(Integer iD_TAREFA) {
		ID_TAREFA = iD_TAREFA;
	}

	public void setID_MODULO(Integer iD_MODULO) {
		ID_MODULO = iD_MODULO;
	}

	public void setID_CAMPO(Integer iD_CAMPO) {
		ID_CAMPO = iD_CAMPO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_INICIO(Timestamp dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Timestamp dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setDATA_CONCLUSAO(Timestamp dATA_CONCLUSAO) {
		DATA_CONCLUSAO = dATA_CONCLUSAO;
	}

	public void setUTZ_CONCLUSAO(Integer uTZ_CONCLUSAO) {
		UTZ_CONCLUSAO = uTZ_CONCLUSAO;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
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

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	@Column(name = "DATA_VALIDA")
	public Timestamp getDATA_VALIDA() {
		return DATA_VALIDA;
	}

	@Column(name = "UTZ_VALIDA")
	public Integer getUTZ_VALIDA() {
		return UTZ_VALIDA;
	}

	@Column(name = "DATA_CONTROLA")
	public Timestamp getDATA_CONTROLA() {
		return DATA_CONTROLA;
	}

	@Column(name = "UTZ_CONTROLA")
	public Integer getUTZ_CONTROLA() {
		return UTZ_CONTROLA;
	}

	public void setDATA_VALIDA(Timestamp dATA_VALIDA) {
		DATA_VALIDA = dATA_VALIDA;
	}

	public void setUTZ_VALIDA(Integer uTZ_VALIDA) {
		UTZ_VALIDA = uTZ_VALIDA;
	}

	public void setDATA_CONTROLA(Timestamp dATA_CONTROLA) {
		DATA_CONTROLA = dATA_CONTROLA;
	}

	public void setUTZ_CONTROLA(Integer uTZ_CONTROLA) {
		UTZ_CONTROLA = uTZ_CONTROLA;
	}

	public String getJUSTIFICACAO_ALTERACAO_ESTADO() {
		return JUSTIFICACAO_ALTERACAO_ESTADO;
	}

	public void setJUSTIFICACAO_ALTERACAO_ESTADO(String jUSTIFICACAO_ALTERACAO_ESTADO) {
		JUSTIFICACAO_ALTERACAO_ESTADO = jUSTIFICACAO_ALTERACAO_ESTADO;
	}

}
