package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PA_MOV_LINHA_HISTORICO")
public class PA_MOV_LINHA_HISTORICO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("ID_PLANO_CAB")
	private Integer ID_PLANO_CAB;

	@JsonProperty("ID_PLANO_LINHA")
	private Integer ID_PLANO_LINHA;

	@JsonProperty("TIPO_ALTERACAO")
	private String TIPO_ALTERACAO;

	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	@JsonProperty("VALOR_ANTERIOR")
	private String VALOR_ANTERIOR;

	@JsonProperty("VALOR_NOVO")
	private String VALOR_NOVO;

	@JsonProperty("ESTADO_PLANO_MOMENTO")
	private String ESTADO_PLANO_MOMENTO;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	public Integer getID() { return ID; }
	public void setID(Integer iD) { ID = iD; }

	public Integer getID_PLANO_CAB() { return ID_PLANO_CAB; }
	public void setID_PLANO_CAB(Integer v) { ID_PLANO_CAB = v; }

	public Integer getID_PLANO_LINHA() { return ID_PLANO_LINHA; }
	public void setID_PLANO_LINHA(Integer v) { ID_PLANO_LINHA = v; }

	public String getTIPO_ALTERACAO() { return TIPO_ALTERACAO; }
	public void setTIPO_ALTERACAO(String v) { TIPO_ALTERACAO = v; }

	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }

	public String getVALOR_ANTERIOR() { return VALOR_ANTERIOR; }
	public void setVALOR_ANTERIOR(String v) { VALOR_ANTERIOR = v; }

	public String getVALOR_NOVO() { return VALOR_NOVO; }
	public void setVALOR_NOVO(String v) { VALOR_NOVO = v; }

	public String getESTADO_PLANO_MOMENTO() { return ESTADO_PLANO_MOMENTO; }
	public void setESTADO_PLANO_MOMENTO(String v) { ESTADO_PLANO_MOMENTO = v; }

	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }

	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
}
