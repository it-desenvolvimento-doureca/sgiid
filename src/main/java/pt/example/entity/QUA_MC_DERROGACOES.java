package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_MC_DERROGACOES")
public class QUA_MC_DERROGACOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_DERROGACAO")
	private Integer ID_DERROGACAO;
	@JsonProperty("DATA") private Date DATA;
	@JsonProperty("N_DERROGACAO") private String N_DERROGACAO;
	@JsonProperty("INTERNA_EXTERNA") private String INTERNA_EXTERNA;
	@JsonProperty("EMISSOR") private String EMISSOR;
	@JsonProperty("DEPARTAMENTO") private String DEPARTAMENTO;
	@JsonProperty("REFERENCIA") private String REFERENCIA;
	@JsonProperty("DESIGNACAO") private String DESIGNACAO;
	@JsonProperty("PROJETO_VEICULO") private String PROJETO_VEICULO;
	@JsonProperty("LOTE_NUM_OF") private String LOTE_NUM_OF;
	@JsonProperty("CONSTATACAO") private String CONSTATACAO;
	@JsonProperty("MOTIVO_DERROGACAO") private String MOTIVO_DERROGACAO;
	@JsonProperty("IMPACTO_DESCRICAO") private String IMPACTO_DESCRICAO;
	@JsonProperty("SEGURANCA") private Boolean SEGURANCA;
	@JsonProperty("REGULAMENTACAO") private Boolean REGULAMENTACAO;
	@JsonProperty("IMPACTO_CLIENTE") private Boolean IMPACTO_CLIENTE;
	@JsonProperty("ACOES_CONTENCAO") private String ACOES_CONTENCAO;
	@JsonProperty("CLIENTE") private String CLIENTE;
	@JsonProperty("QTD_PECAS_IMPACTADAS") private Integer QTD_PECAS_IMPACTADAS;
	@JsonProperty("REPERAGEM") private String REPERAGEM;
	@JsonProperty("NOMINAL") private String NOMINAL;
	@JsonProperty("PRIMEIRO_NUM_BL") private String PRIMEIRO_NUM_BL;
	@JsonProperty("DECISAO_INTERNA") private String DECISAO_INTERNA;
	@JsonProperty("DECISAO_INTERNA_RESULTADO") private String DECISAO_INTERNA_RESULTADO;
	@JsonProperty("DECISAO_CLIENTE") private String DECISAO_CLIENTE;
	@JsonProperty("DATA_INICIO") private Date DATA_INICIO;
	@JsonProperty("DATA_FIM") private Date DATA_FIM;
	@JsonProperty("ESTADO") private String ESTADO;
	@JsonProperty("UTZ_CRIA") private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA") private Date DATA_CRIA;
	@JsonProperty("UTZ_MODIF") private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF") private Date DATA_MODIF;
	@JsonProperty("UTZ_ANULA") private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA") private Date DATA_ANULA;
	@JsonProperty("ATIVO") private Boolean ATIVO;

	public Integer getID_DERROGACAO() { return ID_DERROGACAO; }
	public void setID_DERROGACAO(Integer v) { ID_DERROGACAO = v; }
	public Date getDATA() { return DATA; } public void setDATA(Date v) { DATA = v; }
	public String getN_DERROGACAO() { return N_DERROGACAO; } public void setN_DERROGACAO(String v) { N_DERROGACAO = v; }
	public String getINTERNA_EXTERNA() { return INTERNA_EXTERNA; } public void setINTERNA_EXTERNA(String v) { INTERNA_EXTERNA = v; }
	public String getEMISSOR() { return EMISSOR; } public void setEMISSOR(String v) { EMISSOR = v; }
	public String getDEPARTAMENTO() { return DEPARTAMENTO; } public void setDEPARTAMENTO(String v) { DEPARTAMENTO = v; }
	public String getREFERENCIA() { return REFERENCIA; } public void setREFERENCIA(String v) { REFERENCIA = v; }
	public String getDESIGNACAO() { return DESIGNACAO; } public void setDESIGNACAO(String v) { DESIGNACAO = v; }
	public String getPROJETO_VEICULO() { return PROJETO_VEICULO; } public void setPROJETO_VEICULO(String v) { PROJETO_VEICULO = v; }
	public String getLOTE_NUM_OF() { return LOTE_NUM_OF; } public void setLOTE_NUM_OF(String v) { LOTE_NUM_OF = v; }
	public String getCONSTATACAO() { return CONSTATACAO; } public void setCONSTATACAO(String v) { CONSTATACAO = v; }
	public String getMOTIVO_DERROGACAO() { return MOTIVO_DERROGACAO; } public void setMOTIVO_DERROGACAO(String v) { MOTIVO_DERROGACAO = v; }
	public String getIMPACTO_DESCRICAO() { return IMPACTO_DESCRICAO; } public void setIMPACTO_DESCRICAO(String v) { IMPACTO_DESCRICAO = v; }
	public Boolean getSEGURANCA() { return SEGURANCA; } public void setSEGURANCA(Boolean v) { SEGURANCA = v; }
	public Boolean getREGULAMENTACAO() { return REGULAMENTACAO; } public void setREGULAMENTACAO(Boolean v) { REGULAMENTACAO = v; }
	public Boolean getIMPACTO_CLIENTE() { return IMPACTO_CLIENTE; } public void setIMPACTO_CLIENTE(Boolean v) { IMPACTO_CLIENTE = v; }
	public String getACOES_CONTENCAO() { return ACOES_CONTENCAO; } public void setACOES_CONTENCAO(String v) { ACOES_CONTENCAO = v; }
	public String getCLIENTE() { return CLIENTE; } public void setCLIENTE(String v) { CLIENTE = v; }
	public Integer getQTD_PECAS_IMPACTADAS() { return QTD_PECAS_IMPACTADAS; } public void setQTD_PECAS_IMPACTADAS(Integer v) { QTD_PECAS_IMPACTADAS = v; }
	public String getREPERAGEM() { return REPERAGEM; } public void setREPERAGEM(String v) { REPERAGEM = v; }
	public String getNOMINAL() { return NOMINAL; } public void setNOMINAL(String v) { NOMINAL = v; }
	public String getPRIMEIRO_NUM_BL() { return PRIMEIRO_NUM_BL; } public void setPRIMEIRO_NUM_BL(String v) { PRIMEIRO_NUM_BL = v; }
	public String getDECISAO_INTERNA() { return DECISAO_INTERNA; } public void setDECISAO_INTERNA(String v) { DECISAO_INTERNA = v; }
	public String getDECISAO_INTERNA_RESULTADO() { return DECISAO_INTERNA_RESULTADO; } public void setDECISAO_INTERNA_RESULTADO(String v) { DECISAO_INTERNA_RESULTADO = v; }
	public String getDECISAO_CLIENTE() { return DECISAO_CLIENTE; } public void setDECISAO_CLIENTE(String v) { DECISAO_CLIENTE = v; }
	public Date getDATA_INICIO() { return DATA_INICIO; } public void setDATA_INICIO(Date v) { DATA_INICIO = v; }
	public Date getDATA_FIM() { return DATA_FIM; } public void setDATA_FIM(Date v) { DATA_FIM = v; }
	public String getESTADO() { return ESTADO; } public void setESTADO(String v) { ESTADO = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; } public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Date getDATA_CRIA() { return DATA_CRIA; } public void setDATA_CRIA(Date v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; } public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Date getDATA_MODIF() { return DATA_MODIF; } public void setDATA_MODIF(Date v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; } public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Date getDATA_ANULA() { return DATA_ANULA; } public void setDATA_ANULA(Date v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; } public void setATIVO(Boolean v) { ATIVO = v; }
}
