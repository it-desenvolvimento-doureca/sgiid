package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_DIC_NIVEL_IMPACTO_INCIDENTE")
public class MAN_DIC_NIVEL_IMPACTO_INCIDENTE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("NIVEL")
	private Integer NIVEL;   // 1=Alto, 2=Médio, 3=Baixo

	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	@JsonProperty("DESC_CONDICAO")
	private String DESC_CONDICAO;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }

	public Integer getNIVEL() { return NIVEL; }
	public void setNIVEL(Integer v) { NIVEL = v; }

	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }

	public String getDESC_CONDICAO() { return DESC_CONDICAO; }
	public void setDESC_CONDICAO(String v) { DESC_CONDICAO = v; }

	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}
