package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PA_MOV_CAB_HISTORICO")
public class PA_MOV_CAB_HISTORICO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("ID_PLANO_CAB")
	private Integer ID_PLANO_CAB;

	@JsonProperty("TIPO_ALTERACAO")
	private String TIPO_ALTERACAO;

	@JsonProperty("VALOR_ANTERIOR")
	private String VALOR_ANTERIOR;

	@JsonProperty("VALOR_NOVO")
	private String VALOR_NOVO;

	@JsonProperty("JUSTIFICACAO")
	private String JUSTIFICACAO;

	@JsonProperty("ESTADO_PE")
	private String ESTADO_PE;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	public Integer getID() { return ID; }
	public void setID(Integer iD) { ID = iD; }

	public Integer getID_PLANO_CAB() { return ID_PLANO_CAB; }
	public void setID_PLANO_CAB(Integer v) { ID_PLANO_CAB = v; }

	public String getTIPO_ALTERACAO() { return TIPO_ALTERACAO; }
	public void setTIPO_ALTERACAO(String v) { TIPO_ALTERACAO = v; }

	public String getVALOR_ANTERIOR() { return VALOR_ANTERIOR; }
	public void setVALOR_ANTERIOR(String v) { VALOR_ANTERIOR = v; }

	public String getVALOR_NOVO() { return VALOR_NOVO; }
	public void setVALOR_NOVO(String v) { VALOR_NOVO = v; }

	public String getJUSTIFICACAO() { return JUSTIFICACAO; }
	public void setJUSTIFICACAO(String v) { JUSTIFICACAO = v; }

	public String getESTADO_PE() { return ESTADO_PE; }
	public void setESTADO_PE(String v) { ESTADO_PE = v; }

	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }

	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
}
