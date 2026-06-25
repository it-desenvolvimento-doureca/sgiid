package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_MC_DECLARACOES_NC")
public class QUA_MC_DECLARACOES_NC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_DECLARACAO") private Integer ID_DECLARACAO;
	@JsonProperty("EMITIDA_POR") private String EMITIDA_POR;
	@JsonProperty("DATA_EMISSAO") private Date DATA_EMISSAO;
	@JsonProperty("ID_EQUIPAMENTO") private Integer ID_EQUIPAMENTO;
	@JsonProperty("COD_EQUIPAMENTO") private String COD_EQUIPAMENTO;
	@JsonProperty("DEFEITO_CONSTATADO") private String DEFEITO_CONSTATADO;
	@JsonProperty("DESCRICAO_DEFEITO") private String DESCRICAO_DEFEITO;
	@JsonProperty("PODE_TRABALHAR_DERROGACAO") private Boolean PODE_TRABALHAR_DERROGACAO;
	@JsonProperty("DATA_LIMITE_VALIDADE") private Date DATA_LIMITE_VALIDADE;
	@JsonProperty("CONDICOES_DERROGACAO") private String CONDICOES_DERROGACAO;
	@JsonProperty("ACOES_A_TOMAR") private String ACOES_A_TOMAR;
	@JsonProperty("DEFEITO_A_CONSERTAR") private String DEFEITO_A_CONSERTAR;
	@JsonProperty("ESTADO") private String ESTADO;
	@JsonProperty("UTZ_CRIA") private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA") private Date DATA_CRIA;
	@JsonProperty("UTZ_MODIF") private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF") private Date DATA_MODIF;
	@JsonProperty("UTZ_ANULA") private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA") private Date DATA_ANULA;
	@JsonProperty("ATIVO") private Boolean ATIVO;

	public Integer getID_DECLARACAO() { return ID_DECLARACAO; } public void setID_DECLARACAO(Integer v) { ID_DECLARACAO = v; }
	public String getEMITIDA_POR() { return EMITIDA_POR; } public void setEMITIDA_POR(String v) { EMITIDA_POR = v; }
	public Date getDATA_EMISSAO() { return DATA_EMISSAO; } public void setDATA_EMISSAO(Date v) { DATA_EMISSAO = v; }
	public Integer getID_EQUIPAMENTO() { return ID_EQUIPAMENTO; } public void setID_EQUIPAMENTO(Integer v) { ID_EQUIPAMENTO = v; }
	public String getCOD_EQUIPAMENTO() { return COD_EQUIPAMENTO; } public void setCOD_EQUIPAMENTO(String v) { COD_EQUIPAMENTO = v; }
	public String getDEFEITO_CONSTATADO() { return DEFEITO_CONSTATADO; } public void setDEFEITO_CONSTATADO(String v) { DEFEITO_CONSTATADO = v; }
	public String getDESCRICAO_DEFEITO() { return DESCRICAO_DEFEITO; } public void setDESCRICAO_DEFEITO(String v) { DESCRICAO_DEFEITO = v; }
	public Boolean getPODE_TRABALHAR_DERROGACAO() { return PODE_TRABALHAR_DERROGACAO; } public void setPODE_TRABALHAR_DERROGACAO(Boolean v) { PODE_TRABALHAR_DERROGACAO = v; }
	public Date getDATA_LIMITE_VALIDADE() { return DATA_LIMITE_VALIDADE; } public void setDATA_LIMITE_VALIDADE(Date v) { DATA_LIMITE_VALIDADE = v; }
	public String getCONDICOES_DERROGACAO() { return CONDICOES_DERROGACAO; } public void setCONDICOES_DERROGACAO(String v) { CONDICOES_DERROGACAO = v; }
	public String getACOES_A_TOMAR() { return ACOES_A_TOMAR; } public void setACOES_A_TOMAR(String v) { ACOES_A_TOMAR = v; }
	public String getDEFEITO_A_CONSERTAR() { return DEFEITO_A_CONSERTAR; } public void setDEFEITO_A_CONSERTAR(String v) { DEFEITO_A_CONSERTAR = v; }
	public String getESTADO() { return ESTADO; } public void setESTADO(String v) { ESTADO = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; } public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Date getDATA_CRIA() { return DATA_CRIA; } public void setDATA_CRIA(Date v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; } public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Date getDATA_MODIF() { return DATA_MODIF; } public void setDATA_MODIF(Date v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; } public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Date getDATA_ANULA() { return DATA_ANULA; } public void setDATA_ANULA(Date v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; } public void setATIVO(Boolean v) { ATIVO = v; }
}
