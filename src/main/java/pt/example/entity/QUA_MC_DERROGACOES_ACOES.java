package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_MC_DERROGACOES_ACOES")
public class QUA_MC_DERROGACOES_ACOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID") private Integer ID;
	@JsonProperty("ID_DERROGACAO") private Integer ID_DERROGACAO;
	@JsonProperty("NUM") private Integer NUM;
	@JsonProperty("ACAO") private String ACAO;
	@JsonProperty("RESPONSAVEL") private String RESPONSAVEL;
	@JsonProperty("DATA_P") private Date DATA_P;
	@JsonProperty("DATA_D") private Date DATA_D;
	@JsonProperty("DATA_C") private Date DATA_C;
	@JsonProperty("DATA_A") private Date DATA_A;
	@JsonProperty("ESTADO") private String ESTADO;
	@JsonProperty("ID_TAREFA") private Integer ID_TAREFA;
	@JsonProperty("UTZ_CRIA") private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA") private Date DATA_CRIA;
	@JsonProperty("UTZ_MODIF") private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF") private Date DATA_MODIF;
	@JsonProperty("UTZ_ANULA") private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA") private Date DATA_ANULA;
	@JsonProperty("ATIVO") private Boolean ATIVO;

	public Integer getID() { return ID; } public void setID(Integer v) { ID = v; }
	public Integer getID_DERROGACAO() { return ID_DERROGACAO; } public void setID_DERROGACAO(Integer v) { ID_DERROGACAO = v; }
	public Integer getNUM() { return NUM; } public void setNUM(Integer v) { NUM = v; }
	public String getACAO() { return ACAO; } public void setACAO(String v) { ACAO = v; }
	public String getRESPONSAVEL() { return RESPONSAVEL; } public void setRESPONSAVEL(String v) { RESPONSAVEL = v; }
	public Date getDATA_P() { return DATA_P; } public void setDATA_P(Date v) { DATA_P = v; }
	public Date getDATA_D() { return DATA_D; } public void setDATA_D(Date v) { DATA_D = v; }
	public Date getDATA_C() { return DATA_C; } public void setDATA_C(Date v) { DATA_C = v; }
	public Date getDATA_A() { return DATA_A; } public void setDATA_A(Date v) { DATA_A = v; }
	public String getESTADO() { return ESTADO; } public void setESTADO(String v) { ESTADO = v; }
	public Integer getID_TAREFA() { return ID_TAREFA; } public void setID_TAREFA(Integer v) { ID_TAREFA = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; } public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Date getDATA_CRIA() { return DATA_CRIA; } public void setDATA_CRIA(Date v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; } public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Date getDATA_MODIF() { return DATA_MODIF; } public void setDATA_MODIF(Date v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; } public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Date getDATA_ANULA() { return DATA_ANULA; } public void setDATA_ANULA(Date v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; } public void setATIVO(Boolean v) { ATIVO = v; }
}
