package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_DIC_PRIORIDADE_INCIDENTE")
public class MAN_DIC_PRIORIDADE_INCIDENTE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("PRIORIDADE")
	private Integer PRIORIDADE;   // 1 a 5

	@JsonProperty("COR")
	private String COR;   // 'vermelho', 'laranja', 'amarelo', 'verde-claro', 'verde'

	@JsonProperty("DESC_PRIORIDADE")
	private String DESC_PRIORIDADE;

	@JsonProperty("TEMPO_RESPOSTA_HORAS")
	private Integer TEMPO_RESPOSTA_HORAS;   // 0 = ação imediata

	@JsonProperty("TEMPO_RESOLUCAO_HORAS")
	private Integer TEMPO_RESOLUCAO_HORAS;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }

	public Integer getPRIORIDADE() { return PRIORIDADE; }
	public void setPRIORIDADE(Integer v) { PRIORIDADE = v; }

	public String getCOR() { return COR; }
	public void setCOR(String v) { COR = v; }

	public String getDESC_PRIORIDADE() { return DESC_PRIORIDADE; }
	public void setDESC_PRIORIDADE(String v) { DESC_PRIORIDADE = v; }

	public Integer getTEMPO_RESPOSTA_HORAS() { return TEMPO_RESPOSTA_HORAS; }
	public void setTEMPO_RESPOSTA_HORAS(Integer v) { TEMPO_RESPOSTA_HORAS = v; }

	public Integer getTEMPO_RESOLUCAO_HORAS() { return TEMPO_RESOLUCAO_HORAS; }
	public void setTEMPO_RESOLUCAO_HORAS(Integer v) { TEMPO_RESOLUCAO_HORAS = v; }

	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}
