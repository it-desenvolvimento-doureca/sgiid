package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tipos de paragem que o tablet de injecao mostra.
 *
 * LISTA ESCRITA A MAO, no SGIID. O Silver tem um dicionario de paragens
 * (SPAARR), mas a injecao nao usa os 56 que la estao — ha limpezas de posto,
 * mudas de tinta e formacoes que sao de outras seccoes, e uma grelha de 56
 * botoes num tablet de chao de fabrica nao se usa.
 *
 * O CODIGO e o que vai no ficheiro para o Silver: quem o escreve e responsavel
 * por ele bater certo com o de la.
 *
 * APAGAR UM TIPO E SEGURO: a paragem registada nao aponta para aqui, guarda o
 * codigo e a descricao copiados no momento (INJ_PARAGEM.TIPO_PARAGEM e
 * DES_PARAGEM). Uma paragem antiga continua a ler-se como o operario a viu.
 */
@Entity
@Table(name = "INJ_DIC_TIPO_PARAGEM")
public class INJ_DIC_TIPO_PARAGEM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	/** E o que vai no ficheiro para o Silver. */
	@JsonProperty("CODIGO")
	private String CODIGO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("ORDEM")
	private Integer ORDEM;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }
	public String getCODIGO() { return CODIGO; }
	public void setCODIGO(String v) { CODIGO = v; }
	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
	public Integer getORDEM() { return ORDEM; }
	public void setORDEM(Integer v) { ORDEM = v; }
	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Timestamp getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Timestamp v) { DATA_MODIF = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
}
