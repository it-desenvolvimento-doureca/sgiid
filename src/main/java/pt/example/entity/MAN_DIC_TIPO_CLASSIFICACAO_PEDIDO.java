package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO")
public class MAN_DIC_TIPO_CLASSIFICACAO_PEDIDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("CODIGO")
	private String CODIGO;   // 'IN', 'PI', 'PM', 'EV'

	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;

	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }

	public String getCODIGO() { return CODIGO; }
	public void setCODIGO(String v) { CODIGO = v; }

	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }

	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }

	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }

	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }

	public Integer getUTZ_ULT_MODIF() { return UTZ_ULT_MODIF; }
	public void setUTZ_ULT_MODIF(Integer v) { UTZ_ULT_MODIF = v; }

	public Timestamp getDATA_ULT_MODIF() { return DATA_ULT_MODIF; }
	public void setDATA_ULT_MODIF(Timestamp v) { DATA_ULT_MODIF = v; }
}
